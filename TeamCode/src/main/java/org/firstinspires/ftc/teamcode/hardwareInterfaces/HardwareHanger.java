package org.firstinspires.ftc.teamcode.hardwareInterfaces;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public interface HardwareHanger {
    int getTargetPosition();
    boolean isBusy();
    void setTargetPosition(int position);
    DcMotorSimple.Direction getDirection();
    double getPower();
    void setDirection(DcMotorSimple.Direction direction);
    void setPower(double power);
    int getCurrentPosition();
}