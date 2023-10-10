package org.firstinspires.ftc.teamcode.hardware;

import org.firstinspires.ftc.teamcode.hardware.HardwareSlides;
import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class SimHardwareSlides implements HardwareSlides {
    Simulation sim;
    public SimHardwareSlides(Simulation sim) {
        this.sim = sim;
    }

    @Override
    public void setSlidesPower(double power) {

    }

    @Override
    public int getSlidesPosition() {
        return 0;
    }
}
