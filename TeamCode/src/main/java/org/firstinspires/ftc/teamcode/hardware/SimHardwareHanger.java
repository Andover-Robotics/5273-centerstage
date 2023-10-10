package org.firstinspires.ftc.teamcode.hardware;

import org.firstinspires.ftc.teamcode.hardware.HardwareHanger;
import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class SimHardwareHanger implements HardwareHanger {
    Simulation sim;
    public SimHardwareHanger(Simulation sim) {
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
