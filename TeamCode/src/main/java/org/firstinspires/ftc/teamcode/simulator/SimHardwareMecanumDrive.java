package org.firstinspires.ftc.teamcode.simulator;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareMecanumDrive;

public class SimHardwareMecanumDrive implements HardwareMecanumDrive {
    final Simulation sim;
    public SimHardwareMecanumDrive(Simulation sim) {
        this.sim = sim;
    }
    @Override
    public void setPower(double leftFrontPower, double rightFrontPower, double leftBackPower, double rightBackPower){

    }
    @Override
    public double[] getPower(){
        return null;
    }
    @Override
    public int[] getCurrentPosition(){
        return null;
    }
    @Override
    public int[] getTargetPosition(){
        return null;
    }
    @Override
    public void setTargetPosition(int leftFrontPos, int rightFrontPos, int leftBackPos, int rightBackPos){

    }
    @Override
    public boolean[] isBusy(){
        return null;
    }
    @Override
    public Direction[] getDirection(){
        return null;
    }
    @Override
    public void setDirection(Direction leftFrontDir, Direction rightFrontDir, Direction leftBackDir, Direction rightBackDir){

    }
    public void resetEncoders(){

    }
    public void resetEncoders(boolean leftFront, boolean rightFront, boolean leftBack, boolean rightBack){

    }
}
