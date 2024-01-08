package com.andoverrobotics.thunder.commonlogic.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.util.Direction;

public interface HardwareSlides {
    int[] getSlidesPositions();
    void setDirections(Direction directionLeft, Direction directionRight);

    void setPowers(double powerLeft, double powerRight);
    double[] getPowers();
    void resetEncoders();
}
