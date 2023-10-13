package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public interface HardwareFlyWheel {
    public enum onOffState {
        ON,
        OFF
    }
    public void on();
    public void off();
    public onOffState getState();
    public void setDirection(DcMotorSimple.Direction direction);
    public DcMotorSimple.Direction getDirection();
    public int getCurrentPosition();
    public DcMotor.RunMode getMode();
    public void setMode(DcMotor.RunMode mode);
    public int getTargetPosition();
    public void setTargetPosition(int position);
    public boolean isBusy();
    public double getPower();
    public void setPower(double power);
}

