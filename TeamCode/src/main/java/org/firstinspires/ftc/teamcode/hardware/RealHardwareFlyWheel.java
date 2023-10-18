package org.firstinspires.ftc.teamcode.hardware;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class RealHardwareFlyWheel implements HardwareFlyWheel {
    private final DcMotor flyWheelMotor;
    public RealHardwareFlyWheel(){
        flyWheelMotor=hardwareMap.get(DcMotor.class, "flyWheelMotor");
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
