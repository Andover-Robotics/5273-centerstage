package com.example.commonlogic.hardwareInterfaces;

public interface HardwareClaw {
    double getPosition();
    void scaleRange(double min, double max);
    enum Direction {
        FORWARD,
        BACKWARD
    }
    Direction getDirection();
    void setDirection(Direction direction);
    void setPosition(double position);
}