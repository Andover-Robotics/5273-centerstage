package com.andoverrobotics.thunder.commonlogic.hardwareInterfaces;

public interface HardwareHanger {
    enum Direction {
        FORWARD,
        BACKWARD
    }
    void setDirection(Direction direction);
    void setPower(double power);
    int getCurrentPosition();
}