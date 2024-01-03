package org.firstinspires.ftc.teamcode.hardware;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareHanger;

public class RealHardwareHanger implements HardwareHanger {
    private final DcMotor motor;
    private final Logger logger;
    public RealHardwareHanger(HardwareMap hardwareMap, Logger logger){
        this.logger = logger;
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
    public Direction getDirection(){
        return motor.getDirection() == DcMotorSimple.Direction.FORWARD ? HardwareHanger.Direction.FORWARD : Direction.BACKWARD;
    }
    @Override
    public double getPower(){
        return motor.getPower();
    }
    @Override
    public void setDirection(Direction direction){
        motor.setDirection(direction == HardwareHanger.Direction.FORWARD ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
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