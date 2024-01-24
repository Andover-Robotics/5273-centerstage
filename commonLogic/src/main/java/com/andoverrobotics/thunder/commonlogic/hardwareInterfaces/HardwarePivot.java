package com.andoverrobotics.thunder.commonlogic.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.util.Direction;

public interface HardwarePivot {
    int getPosition();
    void setDirection(Direction direction);
    void setPower(double power);
    void resetEncoders();
}
