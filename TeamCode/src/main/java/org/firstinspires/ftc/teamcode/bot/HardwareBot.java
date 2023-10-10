package org.firstinspires.ftc.teamcode.bot;

import org.firstinspires.ftc.teamcode.hardware.*;
import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class HardwareBot {
    public HardwareClaw claw;
    public HardwareFlyWheel flyWheel;
    public HardwareHanger hanger;
    public HardwareLaunch launch;
    public HardwareMechanumDrive mechanumDrive;
    public HardwareSlides slides;

    public HardwareBot() {
        claw = null;
        flyWheel = null;
        hanger = null;
        launch = null;
        mechanumDrive = null;
        slides = null;
    }

    public void initReal() {
        claw = new RealHardwareClaw();
        flyWheel = new RealHardwareFlyWheel();
        hanger = new RealHardwareHanger();
        launch = new RealHardwareLaunch();
        mechanumDrive = new RealHardwareMechanumDrive();
        slides = new RealHardwareSlides();
    }

    public void initSim(Simulation sim) {
        claw = new SimHardwareClaw(sim);
        flyWheel = new SimHardwareFlyWheel(sim);
        hanger = new SimHardwareHanger(sim);
        launch = new SimHardwareLaunch(sim);
        mechanumDrive = new SimHardwareMechanumDrive(sim);
        slides = new SimHardwareSlides(sim);
    }
}
