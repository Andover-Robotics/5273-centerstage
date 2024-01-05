package com.andoverrobotics.thunder.simulator.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareHanger;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.simulator.Simulation;
import com.andoverrobotics.thunder.simulator.simComponents.SimMotor;

public class SimHardwareHanger implements HardwareHanger {
    private final Simulation simulation;
    private final Logger logger;
    private final SimMotor hangerMotor;

    public SimHardwareHanger(Simulation simulation, Logger logger) {
        this.simulation = simulation;
        this.logger = logger;
        this.hangerMotor = simulation.getMotor("hangerMotor");
    }

    @Override
    public void setDirection(Direction direction) {
        // Implement the logic to set the direction of the hanger motor
        hangerMotor.setDirection(direction);
    }

    @Override
    public void setPower(double power) {
        // Implement the logic to set the power of the hanger motor
        hangerMotor.setPower(power);
    }

    @Override
    public int getCurrentPosition() {
        // Implement the logic to get the current position of the hanger motor
        return hangerMotor.getCurrentPosition();
    }
}

