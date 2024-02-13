package com.andoverrobotics.thunder.simulator.simComponents;

import com.andoverrobotics.thunder.commonlogic.util.Direction;

public class SimMotor {
    public void setDirection(Direction directionLeft) {}

    public void setPower(double powerRight) {}

    public void resetEncoder() {}

    public int getCurrentPosition() {
        return 0;
    }

    public double getPower() {
        return 0;
    }

    public Direction getDirection() {
        return null;
    }
}
