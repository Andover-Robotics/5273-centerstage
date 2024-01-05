package com.andoverrobotics.thunder.simulator.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareClawFlipper;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.simulator.Simulation;
import com.andoverrobotics.thunder.simulator.simComponents.SimServo;

public class SimHardwareClawFlipper implements HardwareClawFlipper {
    private final Simulation simulation;
    private final Logger logger;
    private final SimServo clawFlipperServo;

    public SimHardwareClawFlipper(Simulation simulation, Logger logger) {
        this.simulation = simulation;
        this.logger = logger;
        this.clawFlipperServo = simulation.getServo("clawFlipperServo");
    }

    @Override
    public void setDirection(Direction direction) {
        // Implement the logic to set the direction of the claw flipper servo
        // (Assuming that SimServo class has a setDirection method)
        clawFlipperServo.setDirection(direction);
    }

    @Override
    public void setPosition(double pos) {
        // Implement the logic to set the position of the claw flipper servo
        clawFlipperServo.setPosition(pos);
    }

    @Override
    public double getPosition() {
        // Implement the logic to get the position of the claw flipper servo
        return clawFlipperServo.getPosition();
    }
}

