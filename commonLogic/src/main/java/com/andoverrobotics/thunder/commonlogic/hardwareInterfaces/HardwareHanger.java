package com.andoverrobotics.thunder.commonlogic.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.util.Direction;

public interface HardwareHanger {
    void setDirection(Direction direction);

    void setPower(double power);

    int getCurrentPosition();
}
