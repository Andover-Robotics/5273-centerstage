package com.andoverrobotics.thunder.commonlogic.subsystems;
import com.andoverrobotics.thunder.commonlogic.util.Direction;



import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareSlides;


public class Slides {
    private final HardwareSlides hardwareSlides;
    private static final double ENCODER_RES = 384.5;
    private static final int AUTO_RES = 20;
    private static final int OFFSET = 3100; // max height when minHeight = 0
    private int minHeight=0; // can be overridden
    private static final double SPEED_LIMIT_FACTOR = 0.2;
    private static final double DECEL_FACTOR = 0.5;
    private final Logger logger;
    private int lastPos;

    public Slides(HardwareSlides hardwareSlides, Logger logger){
        this.logger = logger;
        this.hardwareSlides = hardwareSlides;
        hardwareSlides.resetEncoders();
        hardwareSlides.setDirections(Direction.REVERSE, Direction.FORWARD);
        int[] positions = hardwareSlides.getSlidesPositions();
        lastPos = 0;
    }

    private boolean between(int value, int limit1, int limit2){
        if(limit1 > limit2){
            return value >= limit2 && value <= limit1;
        }else{
            return value >= limit1 && value <= limit2;
        }
    }

    private int clamp(int value, int limit1, int limit2){
        if(limit1 > limit2){
            if(value < limit2){
                return limit2;
            }else if(value > limit1){
                return limit1;
            }else{
                return value;
            }
        }else{

            if(value < limit1){
                return limit1;
            }else if(value > limit2){
                return limit2;
            }else{
                return value;
            }
        }
    }

    public void executeIntent(double power, boolean override){
        int pos = getPos();
        lastPos = pos;

        logger.setProp("real power", power);

        //prevent pos from going above 0
        if(pos < minHeight && power < 0){
            if(override){
                minHeight = pos;
            }else {
                logger.setProp("limiting", "lower bound");
                power = 0;
            }
        }
        //prevent pos from going below MAX_SLIDES_POSITION
        if((pos > minHeight + OFFSET) && (power > 0)){
            if(override){
                minHeight = pos - OFFSET;
            }else {
                logger.setProp("limiting", "upper bound");
                power = 0;
            }
        }

//        power *= 0.5;
        logger.setProp("power", power);
        logger.setProp("pos", pos);

        hardwareSlides.setPowers(power, power);


    }
    public void moveTo(int targ){
        int dist;
        while(Math.abs(dist = targ - getPos()) > AUTO_RES){
            double power = (dist < 0 ? -1 : 1) * (-Math.pow(2, -DECEL_FACTOR * Math.abs(dist)) + 1);
            hardwareSlides.setPowers(power, power);
        }
        hardwareSlides.setPowers(0, 0);
    }
    private int getPos(){
        int[] positions = hardwareSlides.getSlidesPositions();
        return Math.min(positions[0], positions[1]);
    }

    public void resetEncoders() {
        hardwareSlides.resetEncoders();
    }
}
