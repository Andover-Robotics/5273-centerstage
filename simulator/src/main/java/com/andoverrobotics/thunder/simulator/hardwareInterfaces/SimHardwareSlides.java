package com.andoverrobotics.thunder.simulator.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareSlides;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.simulator.Simulation;
import com.andoverrobotics.thunder.simulator.simComponents.SimMotor;

public class SimHardwareSlides implements HardwareSlides {
    private final Simulation simulation;
    private final Logger logger;
    private final SimMotor leftMotor;
    private final SimMotor rightMotor;

    public SimHardwareSlides(Simulation simulation, Logger logger) {
        this.simulation = simulation;
        this.logger = logger;
        this.leftMotor = simulation.getMotor("leftSlideMotor");
        this.rightMotor = simulation.getMotor("rightSlideMotor");
    }

    @Override
    public int[] getSlidesPositions() {
        // Implement the logic to get the positions of the slides
        // Example: return new int[]{leftMotor.getCurrentPosition(), rightMotor.getCurrentPosition()};
        return new int[0];
    }

    @Override
    public void setDirections(Direction directionLeft, Direction directionRight) {
        // Implement the logic to set directions for left and right slides
        leftMotor.setDirection(directionLeft);
        rightMotor.setDirection(directionRight);
    }

    @Override
    public void setPowers(double powerLeft, double powerRight) {
        // Implement the logic to set powers for left and right slides
        leftMotor.setPower(powerLeft);
        rightMotor.setPower(powerRight);
    }

    @Override
    public double[] getPowers() {
        // Implement the logic to get the powers of the slides
        // Example: return new double[]{leftMotor.getPower(), rightMotor.getPower()};
        return new double[0];
    }

    @Override
    public void resetEncoders() {
        // Implement the logic to reset encoders for the slides
        leftMotor.resetEncoder();
        rightMotor.resetEncoder();
    }
}
