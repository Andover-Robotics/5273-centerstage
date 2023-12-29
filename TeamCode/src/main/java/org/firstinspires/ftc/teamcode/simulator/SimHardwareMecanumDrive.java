package org.firstinspires.ftc.teamcode.simulator;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareMecanumDrive;
import org.firstinspires.ftc.teamcode.simulator.Simulation;

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
    public DcMotorSimple.Direction[] getDirection(){
        return null;
    }
    @Override
    public void setDirection(DcMotorSimple.Direction leftFrontDir, DcMotorSimple.Direction rightFrontDir, DcMotorSimple.Direction leftBackDir, DcMotorSimple.Direction rightBackDir){

    }
    public void resetEncoders(){

    }
    public void resetEncoders(boolean leftFront, boolean rightFront, boolean leftBack, boolean rightBack){

    }
}
