package com.andoverrobotics.thunder.commonlogic.subsystems;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwarePivot;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareSlides;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.input.Intent;
import com.andoverrobotics.thunder.commonlogic.util.Direction;

public class Pivot {
    private final HardwarePivot pivot;
    private static final int OFFSET = 6600;
    private static final int AUTO_RES = 20;
    private static final double DECEL_FACTOR = 0.5;
    private final Logger logger;
    private int minHeight = 0;
    public Pivot(HardwarePivot pivot, Logger logger) {
        this.pivot = pivot;
        this.logger = logger;
        pivot.resetEncoders();
        pivot.setDirection(Direction.REVERSE);
    }
    public void executeIntent(Intent intent){

        //prevent pos from going above 0
        int pos = pivot.getPosition();
        double power = intent.pivotIntent == Intent.PivotIntent.POWER ? intent.pivot : (pos > 150 ? -1 : -0.5);
        if(pos < minHeight && power < 0){
            if(intent.override){
                minHeight = pos;
            }else {
                logger.setProp("limiting pivot", "lower bound");
                power = 0;
            }
        }
        //prevent pos from going below MAX_SLIDES_POSITION
        if((pos > minHeight + OFFSET) && (power > 0)){
            if(intent.override){
                minHeight = pos - OFFSET;
            }else {
                logger.setProp("limiting pivot", "upper bound");
                power = 0;
            }
        }
        pivot.setPower(power);

        double angle = 180 * ((double) (pos - minHeight) / OFFSET);
        intent.clawFlip.referenceAngle = angle;
    }
    public void moveTo(final int targ){
        int dist;
        while(Math.abs(dist = targ - getPos()) > AUTO_RES){
            double power = (dist < 0 ? -1 : 1) * (-Math.pow(2, -DECEL_FACTOR * Math.abs(dist)) + 1);
            pivot.setPower(power);
        }
        pivot.setPower(0);
    }
    public int getPos(){
        return pivot.getPosition();
    }
    public void resetEncoders(){
        pivot.resetEncoders();
    }
}
