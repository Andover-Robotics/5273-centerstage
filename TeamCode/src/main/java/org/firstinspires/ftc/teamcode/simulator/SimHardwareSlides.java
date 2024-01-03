package org.firstinspires.ftc.teamcode.simulator;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareSlides;

public class SimHardwareSlides implements HardwareSlides {
    final Simulation sim;
    public SimHardwareSlides(Simulation sim) {
        this.sim = sim;
    }
    @Override
    public int[] getSlidesPositions() {
        return new int[0];
    }

    @Override
    public void setDirections(Direction directionLeft, Direction directionRight) {

    }

    @Override
    public void setPowers(double powerLeft, double powerRight) {

    }

    @Override
    public double[] getPowers() {
        return new double[0];
    }

    @Override
    public void resetEncoders() {

    }
}
