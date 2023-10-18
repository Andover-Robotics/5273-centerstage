package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public interface HardwareMechanumDrive {
    void setPower(double leftFrontPower, double rightFrontPower, double leftBackPower, double rightBackPower);
    double[] getPower();
    int[] getCurrentPosition();
    int[] getTargetPosition();
    void setTargetPosition(int leftFrontPos, int rightFrontPos, int leftBackPos, int rightBackPos);
    boolean[] isBusy();
    DcMotorSimple.Direction[] getDirection();
    void setDirection(DcMotorSimple.Direction leftFrontDir, DcMotorSimple.Direction rightFrontDir, DcMotorSimple.Direction leftBackDir, DcMotorSimple.Direction rightBackDir);
}