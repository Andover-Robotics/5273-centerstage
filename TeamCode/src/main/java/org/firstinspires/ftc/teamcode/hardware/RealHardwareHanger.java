package org.firstinspires.ftc.teamcode.hardware;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.util.Direction;
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
    public void setDirection(Direction direction){
        motor.setDirection(direction == Direction.FORWARD ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
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