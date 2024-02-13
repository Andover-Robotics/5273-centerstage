package com.andoverrobotics.thunder.simulator;

import com.andoverrobotics.thunder.simulator.simComponents.SimCRServo;
import com.andoverrobotics.thunder.simulator.simComponents.SimMotor;
import com.andoverrobotics.thunder.simulator.simComponents.SimServo;

public class SimHardwareMap {
    private Simulation sim;

    public SimHardwareMap(Simulation sim) {
        this.sim = sim;
    }

    public SimCRServo getCRServo(String id) {
        return sim.getCRServo(id);
    }

    public SimMotor getMotor(String id) {
        return sim.getMotor(id);
    }

    public SimServo getServo(String id) {
        return sim.getServo(id);
    }
}
