package org.firstinspires.ftc.teamcode.hardware;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class RealHardwareFlyWheel implements HardwareFlyWheel {
    private final DcMotor flyWheelMotor;
    public RealHardwareFlyWheel(){
        flyWheelMotor=hardwareMap.get(DcMotor.class, "flyWheelMotor");
    }
    @Override
    public void setDirection(DcMotorSimple.Direction direction){
        flyWheelMotor.setDirection(direction);
    }
    @Override
    public DcMotorSimple.Direction getDirection(){
        return flyWheelMotor.getDirection();
    }
    @Override
    public int getCurrentPosition(){
        return flyWheelMotor.getCurrentPosition();
    }
    @Override
    public int getTargetPosition(){
        return flyWheelMotor.getTargetPosition();
    }
    @Override
    public void setTargetPosition(int position){
        flyWheelMotor.setTargetPosition(position);
    }
    @Override
    public boolean isBusy(){
        return flyWheelMotor.isBusy();
    }
    @Override
    public double getPower(){
        return flyWheelMotor.getPower();
    }
    @Override
    public void setPower(double power){
        flyWheelMotor.setPower(power);
    }
}
