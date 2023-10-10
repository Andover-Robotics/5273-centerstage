package org.firstinspires.ftc.teamcode.hardware;

import org.firstinspires.ftc.teamcode.hardware.HardwareClaw;
import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class SimHardwareClaw implements HardwareClaw {
    private Simulation sim;

    public SimHardwareClaw(Simulation sim) {
        this.sim = sim;
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public HardwareClaw getState() {
        return null;
    }
}
