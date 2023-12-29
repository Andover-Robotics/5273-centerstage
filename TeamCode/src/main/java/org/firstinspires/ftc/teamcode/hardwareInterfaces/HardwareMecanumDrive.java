package org.firstinspires.ftc.teamcode.hardwareInterfaces;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public interface HardwareMecanumDrive {
    void setPower(double leftFrontPower, double rightFrontPower, double leftBackPower, double rightBackPower);
    double[] getPower();
    int[] getCurrentPosition();
    int[] getTargetPosition();
    void setTargetPosition(int leftFrontPos, int rightFrontPos, int leftBackPos, int rightBackPos);
    boolean[] isBusy();
    DcMotorSimple.Direction[] getDirection();
    void setDirection(DcMotorSimple.Direction leftFrontDir, DcMotorSimple.Direction rightFrontDir, DcMotorSimple.Direction leftBackDir, DcMotorSimple.Direction rightBackDir);
    void resetEncoders();
    void resetEncoders(boolean leftFront, boolean rightFront, boolean leftBack, boolean rightBack);
}