package org.firstinspires.ftc.teamcode.hardware;

import org.firstinspires.ftc.teamcode.hardware.HardwareLaunch;
import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class SimHardwareLaunch implements HardwareLaunch {
    Simulation sim;
    public SimHardwareLaunch(Simulation sim) {
        this.sim = sim;
    }

    @Override
    public void open() {

    }

    @Override
    public openCloseState getState() {
        return null;
    }
}