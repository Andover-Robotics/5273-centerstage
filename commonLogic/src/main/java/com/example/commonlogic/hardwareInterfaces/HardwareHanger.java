package com.example.commonlogic.hardwareInterfaces;

public interface HardwareHanger {
    int getTargetPosition();
    boolean isBusy();
    void setTargetPosition(int position);
    enum Direction {
        FORWARD,
        BACKWARD
    }
    Direction getDirection();
    double getPower();
    void setDirection(Direction direction);
    void setPower(double power);
    int getCurrentPosition();
}