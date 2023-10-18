package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class RealHardwareHanger implements HardwareHanger {
    private final DcMotor motor;
    public RealHardwareHanger(){
        motor = hardwareMap.get(DcMotor.class, "hangerMotor");
    }
    @Override
    public int getTargetPosition(){
        return motor.getTargetPosition();
    }
    @Override
    public boolean isBusy(){
        return motor.isBusy();
    }
    @Override
    public void setTargetPosition(int position){
        motor.setTargetPosition(position);
    }
    @Override
    public DcMotorSimple.Direction getDirection(){
        return motor.getDirection();
    }
    @Override
    public double getPower(){
        return motor.getPower();
    }
    @Override
    public void setDirection(DcMotorSimple.Direction direction){
        motor.setDirection(direction);
    }
    @Override
    public void setPower(double power){
        motor.setPower(power);
    }
    @Override
    public int getCurrentPosition(){
        return motor.getCurrentPosition();
    }
}