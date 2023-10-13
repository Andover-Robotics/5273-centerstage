package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class RealHardwareFlyWheel implements HardwareFlyWheel {
    private DcMotor flyWheelMotor;
    public RealHardwareFlyWheel(){
        // to do
    }
    public void on()
    {
        // ToDo: Turn the fly wheel on
    }

    public void off()
    {
        // ToDo: Turn the fly wheel off
    }

    public onOffState getState()
    {
        // ToDo: return whether the fly wheel is on or off
        return null;
    }
    public void setDirection(DcMotorSimple.Direction direction){
        flyWheelMotor.setDirection(direction);
    }
    public DcMotorSimple.Direction getDirection(){
        return flyWheelMotor.getDirection();
    }
    public int getCurrentPosition(){
        return flyWheelMotor.getCurrentPosition();
    }
    public DcMotor.RunMode getMode(){
        return flyWheelMotor.getMode();
    }
    public void setMode(DcMotor.RunMode mode){
        flyWheelMotor.setMode(mode);
    }
    public int getTargetPosition(){
        return flyWheelMotor.getTargetPosition();
    }
    public void setTargetPosition(int position){
        flyWheelMotor.setTargetPosition(position);
    }
    public boolean isBusy(){
        return flyWheelMotor.isBusy();
    }
    public double getPower(){
        return flyWheelMotor.getPower();
    }
    public void setPower(double power){
        flyWheelMotor.setPower(power);
    }
}
