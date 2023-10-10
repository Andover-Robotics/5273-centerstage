package org.firstinspires.ftc.teamcode.hardware;

import org.firstinspires.ftc.teamcode.hardware.HardwareFlyWheel;
import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class SimHardwareFlyWheel implements HardwareFlyWheel {
    Simulation sim;
    public SimHardwareFlyWheel(Simulation sim) {
        this.sim = sim;
    }

    @Override
    public void on() {

    }

    @Override
    public void off() {

    }

    @Override
    public onOffState getState() {
        return null;
    }
}
