package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.hardware.HardwareSlides;
import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class SimHardwareSlides implements HardwareSlides {
    Simulation sim;
    public SimHardwareSlides(Simulation sim) {
        this.sim = sim;
    }
    @Override
    public int[] getSlidesPositions() {
        return new int[0];
    }

    @Override
    public void setDirections(DcMotorSimple.Direction directionLeft, DcMotorSimple.Direction directionRight) {

    }

    @Override
    public void setPowers(double powerLeft, double powerRight) {

    }

    @Override
    public double[] getPowers() {
        return new double[0];
    }

    @Override
    public void setModes(DcMotor.RunMode runModeLeft, DcMotor.RunMode runModeRight) {

    }
}
