package com.andoverrobotics.thunder.commonlogic.hardwareInterfaces;

public interface HardwareFlyWheels {
    enum Direction {
        FORWARD,
        REVERSE
    }
    void setDirection(Direction direction);
    void setPower(double power);
}

