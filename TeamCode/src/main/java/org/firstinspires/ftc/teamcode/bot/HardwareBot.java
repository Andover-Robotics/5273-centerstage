package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.*;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareClaw;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareClawFlipper;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareFlyWheels;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareHanger;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareLaunch;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareMecanumDrive;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareSlides;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.Logger;
import org.firstinspires.ftc.teamcode.simulator.SimHardwareClaw;
import org.firstinspires.ftc.teamcode.simulator.SimHardwareClawFlipper;
import org.firstinspires.ftc.teamcode.simulator.SimHardwareFlyWheels;
import org.firstinspires.ftc.teamcode.simulator.SimHardwareHanger;
import org.firstinspires.ftc.teamcode.simulator.SimHardwareLaunch;
import org.firstinspires.ftc.teamcode.simulator.SimHardwareMecanumDrive;
import org.firstinspires.ftc.teamcode.simulator.SimHardwareSlides;
import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class HardwareBot {
    public HardwareClaw claw;
    public HardwareFlyWheels flyWheel;
    public HardwareHanger hanger;
    public HardwareLaunch launch;
    public HardwareMecanumDrive mecanumDrive;
    public HardwareSlides slides;
    public HardwareClawFlipper clawFlipper;

    public void initReal(HardwareMap hardwareMap, Logger logger) {
        claw = new RealHardwareClaw(hardwareMap, logger);
        clawFlipper = new RealHardwareClawFlipper(hardwareMap, logger);
        flyWheel = new RealHardwareFlyWheels(hardwareMap, logger);
//        hanger = new RealHardwareHanger(hardwareMap, logger);
        launch = new RealHardwareLaunch(hardwareMap, logger);
        mecanumDrive = new RealHardwareMecanumDrive(hardwareMap, logger);
        slides = new RealHardwareSlides(hardwareMap, logger);
    }

    public void initSim(Simulation sim) {
        claw = new SimHardwareClaw(sim);
        clawFlipper = new SimHardwareClawFlipper(sim);
        flyWheel = new SimHardwareFlyWheels(sim);
        hanger = new SimHardwareHanger(sim);
        launch = new SimHardwareLaunch(sim);
        mecanumDrive = new SimHardwareMecanumDrive(sim);
        slides = new SimHardwareSlides(sim);
    }
}
