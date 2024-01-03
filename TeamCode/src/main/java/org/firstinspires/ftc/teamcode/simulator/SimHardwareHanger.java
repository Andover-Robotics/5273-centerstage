package org.firstinspires.ftc.teamcode.simulator;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareHanger;

public class SimHardwareHanger implements HardwareHanger {
    final Simulation sim;
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
    public Direction getDirection(){
        return null;
    }
    public double getPower(){
        return 0;
    }
    public void setDirection(Direction direction){

    }
    public void setPower(double power){

    }
    public int getCurrentPosition(){
        return 0;
    }
}
