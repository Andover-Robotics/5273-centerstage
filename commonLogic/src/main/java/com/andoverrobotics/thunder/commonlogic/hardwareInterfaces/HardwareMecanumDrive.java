package com.andoverrobotics.thunder.commonlogic.hardwareInterfaces;
import com.andoverrobotics.thunder.commonlogic.util.Direction;


public interface HardwareMecanumDrive {
    void setPower(double leftFrontPower, double rightFrontPower, double leftBackPower, double rightBackPower);
    double[] getPower();
    int[] getCurrentPosition();
    Direction[] getDirection();
    void setDirection(Direction leftFrontDir, Direction rightFrontDir, Direction leftBackDir, Direction rightBackDir);
    void resetEncoders();
    void resetEncoders(boolean leftFront, boolean rightFront, boolean leftBack, boolean rightBack);
}