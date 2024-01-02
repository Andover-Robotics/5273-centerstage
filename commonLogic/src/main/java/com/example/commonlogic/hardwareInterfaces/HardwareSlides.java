package com.example.commonlogic.hardwareInterfaces;

public interface HardwareSlides {
    int[] getSlidesPositions();
    enum Direction {
        FORWARD,
        BACKWARD
    }
    void setDirections(Direction directionLeft, Direction directionRight);

    void setPowers(double powerLeft, double powerRight);
    double[] getPowers();
    void resetEncoders();
}
