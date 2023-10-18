package org.firstinspires.ftc.teamcode.hardware;

import org.firstinspires.ftc.teamcode.hardware.HardwareFlyWheel;
import org.firstinspires.ftc.teamcode.simulator.Simulation;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class SimHardwareFlyWheel implements HardwareFlyWheel {
    Simulation sim;
    public SimHardwareFlyWheel(Simulation sim) {
        this.sim = sim;
    }
    public void setDirection(DcMotorSimple.Direction direction){
        //TODO: Implement
    }
    @Override

    public DcMotorSimple.Direction getDirection(){
        return null;
    }
    @Override
    public int getCurrentPosition(){
        return 0; //TODO: Implement
    }
    @Override
    public int getTargetPosition(){
        return 0; //TODO: Implement
    }
    @Override
    public void setTargetPosition(int position){
        //TODO: Implement
    }
    @Override
    public boolean isBusy(){
        return false; //TODO: Implement
    }
    @Override
    public double getPower(){
        return 0.0; //TODO: Implement
    }
    @Override
    public void setPower(double power){
        //TODO: Implement
    }
}
