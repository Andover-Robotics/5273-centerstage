package com.andoverrobotics.thunder.commonlogic.hardwareInterfaces;
import com.andoverrobotics.thunder.commonlogic.util.Direction;


public interface HardwareClaw {
    double getPosition();
    void scaleRange(double min, double max);
    Direction getDirection();
    void setDirection(Direction direction);
    void setPosition(double position);
}