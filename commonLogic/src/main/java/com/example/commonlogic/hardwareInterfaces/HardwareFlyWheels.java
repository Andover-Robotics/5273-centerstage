package com.example.commonlogic.hardwareInterfaces;

public interface HardwareFlyWheels {
    enum Direction {
        FORWARD,
        REVERSE
    }
    void setDirection(Direction direction);
    void setPower(double power);
}

