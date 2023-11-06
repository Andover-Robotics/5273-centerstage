package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.*;
import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class HardwareBot {
    public HardwareClaw claw;
    public HardwareFlyWheel flyWheel;
    public HardwareHanger hanger;
    public HardwareLaunch launch;
    public HardwareMecanumDrive mecanumDrive;
    public HardwareSlides slides;

    public void initReal(HardwareMap hardwareMap, Telemetry telemetry) {
        claw = new RealHardwareClaw(hardwareMap, telemetry);
//        flyWheel = new RealHardwareFlyWheel(hardwareMap);
//        hanger = new RealHardwareHanger(hardwareMap);
//        launch = new RealHardwareLaunch(hardwareMap);
        mecanumDrive = new RealHardwareMecanumDrive(hardwareMap, telemetry);
//        slides = new RealHardwareSlides(hardwareMap);
    }

    public void initSim(Simulation sim) {
        claw = new SimHardwareClaw(sim);
        flyWheel = new SimHardwareFlyWheel(sim);
        hanger = new SimHardwareHanger(sim);
        launch = new SimHardwareLaunch(sim);
        mecanumDrive = new SimHardwareMecanumDrive(sim);
        slides = new SimHardwareSlides(sim);
    }
}
