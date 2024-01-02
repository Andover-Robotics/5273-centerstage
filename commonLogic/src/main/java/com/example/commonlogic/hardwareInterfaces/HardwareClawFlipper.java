package com.example.commonlogic.hardwareInterfaces;

public interface HardwareClawFlipper{
    enum Direction {
        FORWARD,
        REVERSE
    }
    void setDirection(Direction direction);
    void setPosition(double pos);
    double getPosition();
}
