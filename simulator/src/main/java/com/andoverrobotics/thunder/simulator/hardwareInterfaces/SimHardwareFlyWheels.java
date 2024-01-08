package com.andoverrobotics.thunder.simulator.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareFlyWheels;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.andoverrobotics.thunder.simulator.Simulation;
import com.andoverrobotics.thunder.simulator.simComponents.SimCRServo;

public class SimHardwareFlyWheels implements HardwareFlyWheels {
    private final Simulation simulation;
    private final Logger logger;
    private final SimCRServo leftFlyWheel;
    private final SimCRServo rightFlyWheel;

    public SimHardwareFlyWheels(Simulation simulation, Logger logger) {
        this.simulation = simulation;
        this.logger = logger;
        this.leftFlyWheel = simulation.getCRServo("leftFlyWheel");
        this.rightFlyWheel = simulation.getCRServo("rightFlyWheel");
    }

    @Override
    public void setDirection(Direction direction) {
        // Implement the logic to set the direction of the flywheels
        // (Assuming that SimCRServo class has a setDirection method)
        if (direction == Direction.FORWARD) {
            leftFlyWheel.setDirection(Direction.FORWARD);
            rightFlyWheel.setDirection(Direction.REVERSE);
        } else {
            leftFlyWheel.setDirection(Direction.REVERSE);
            rightFlyWheel.setDirection(Direction.FORWARD);
        }
    }

    @Override
    public void setPower(double power) {
        // Implement the logic to set the power of the flywheels
        leftFlyWheel.setPower(power);
        rightFlyWheel.setPower(power);
    }
}
