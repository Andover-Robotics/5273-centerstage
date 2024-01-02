package com.example.commonlogic.hardwareInterfaces;

public interface HardwareLaunch {
    double getPosition();
    void scaleRange(double min, double max);
    enum Direction {
        FORWARD,
        REVERSE
    }
    Direction getDirection();
    void setDirection(Direction direction);
    void setPosition(double position);
}
