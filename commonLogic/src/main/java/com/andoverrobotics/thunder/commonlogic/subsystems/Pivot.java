package com.andoverrobotics.thunder.commonlogic.subsystems;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwarePivot;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareSlides;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.util.Direction;

public class Pivot {
    private final HardwarePivot pivot;
    private final Logger logger;
    public Pivot(HardwarePivot pivot, Logger logger) {
        this.pivot = pivot;
        this.logger = logger;
        pivot.resetEncoders();
        pivot.setDirection(Direction.FORWARD);
    }
    public void executeIntent(double pow){
        pivot.setPower(pow);
    }
}
