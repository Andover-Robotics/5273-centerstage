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

    public DcMotorSimple.Direction getDirection(){
        return null;
    }
    public int getCurrentPosition(){
        return 0; //TODO: Implement
    }
    public int getTargetPosition(){
        return 0; //TODO: Implement
    }
    public void setTargetPosition(int position){
        //TODO: Implement
    }
    public boolean isBusy(){
        return false; //TODO: Implement
    }
    public double getPower(){
        return 0.0; //TODO: Implement
    }
    public void setPower(double power){
        //TODO: Implement
    }
}
