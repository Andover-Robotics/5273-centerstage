package com.andoverrobotics.thunder.commonlogic.hardwareInterfaces;


public interface HardwareMecanumDrive {
    void setPower(double leftFrontPower, double rightFrontPower, double leftBackPower, double rightBackPower);
    double[] getPower();
    int[] getCurrentPosition();
    enum Direction {
        FORWARD,
        REVERSE
    }
    Direction[] getDirection();
    void setDirection(Direction leftFrontDir, Direction rightFrontDir, Direction leftBackDir, Direction rightBackDir);
    void resetEncoders();
    void resetEncoders(boolean leftFront, boolean rightFront, boolean leftBack, boolean rightBack);
}