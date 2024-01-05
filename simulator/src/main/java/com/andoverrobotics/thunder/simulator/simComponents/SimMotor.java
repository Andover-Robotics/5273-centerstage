package com.andoverrobotics.thunder.simulator.simComponents;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareMecanumDrive;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareSlides;

public class SimMotor {
    public void setDirection(HardwareSlides.Direction directionLeft) {
    }

    public void setPower(double powerRight) {
    }

    public void resetEncoder() {
    }

    public int getCurrentPosition() {
        return 0;
    }

    public double getPower() {
        return 0;
    }

    public HardwareMecanumDrive.Direction getDirection() {
        return null;
    }
}
