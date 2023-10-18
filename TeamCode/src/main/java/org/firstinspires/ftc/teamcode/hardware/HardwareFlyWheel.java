package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public interface HardwareFlyWheel {
    void setDirection(DcMotorSimple.Direction direction);
    DcMotorSimple.Direction getDirection();
    int getCurrentPosition();
    int getTargetPosition();
    void setTargetPosition(int position);
    boolean isBusy();
    double getPower();
    void setPower(double power);
}

