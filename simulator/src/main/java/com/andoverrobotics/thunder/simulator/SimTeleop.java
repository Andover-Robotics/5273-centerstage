package com.andoverrobotics.thunder.simulator;

import com.andoverrobotics.thunder.commonlogic.bot.HardwareBot;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.CombinedLogger;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.FileLogger;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareClaw;
import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareClawFlipper;
import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareFlyWheels;
import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareHanger;
import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareLaunch;
import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareMecanumDrive;
import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareSlides;

public class SimTeleop {
    public void runOpmode() {
        Logger logger = new CombinedLogger(
                new FileLogger("./logs/"),
                new ConsoleLogger()
        );
        logger.log("starting simulation...");
        Simulation simulation = new Simulation();
        HardwareBot hardwareBot = new HardwareBot();
        hardwareBot.claw = new SimHardwareClaw(simulation, logger);
        hardwareBot.clawFlipper = new SimHardwareClawFlipper(simulation, logger);
        hardwareBot.flyWheel = new SimHardwareFlyWheels(simulation, logger);
        hardwareBot.mecanumDrive = new SimHardwareMecanumDrive(simulation, logger);
        hardwareBot.launch = new SimHardwareLaunch(simulation, logger);
        hardwareBot.hanger = new SimHardwareHanger(simulation, logger);
        hardwareBot.slides = new SimHardwareSlides(simulation, logger);
    }
}
