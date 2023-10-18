package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.hardware.HardwareHanger;
import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class SimHardwareHanger implements HardwareHanger {
    Simulation sim;
    public SimHardwareHanger(Simulation sim) {
        this.sim = sim;
    }

    public int getTargetPosition(){
        return 0;
    }
    public boolean isBusy(){
        return true;
    }
    public void setTargetPosition(int position){

    }
    public DcMotorSimple.Direction getDirection(){
        return null;
    }
    public double getPower(){
        return 0;
    }
    public void setDirection(DcMotorSimple.Direction direction){

    }
    public void setPower(double power){

    }
    public int getCurrentPosition(){
        return 0;
    }
}
