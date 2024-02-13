package com.andoverrobotics.thunder.commonlogic.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.util.Direction;

public interface HardwareClawFlipper {
    void setDirection(Direction direction);

    void setPosition(double pos);

    double getPosition();
}
