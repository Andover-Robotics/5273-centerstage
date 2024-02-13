package com.andoverrobotics.thunder.simulator;

import com.andoverrobotics.thunder.commonlogic.bot.Bot;
import com.andoverrobotics.thunder.commonlogic.bot.HardwareBot;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.CombinedLogger;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.FileLogger;
import com.andoverrobotics.thunder.commonlogic.input.ControllerMapping;
import com.andoverrobotics.thunder.commonlogic.input.Intent;
import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareClaw;
import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareClawFlipper;
import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareFlyWheels;
import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareLaunch;
import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareMecanumDrive;
import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareSlides;

public class SimTeleop extends SimLinearOpMode {
    public void runOpmode() {
        CombinedLogger logger =
                new CombinedLogger(new ConsoleLogger(), new FileLogger("./logs/"), telemetry);
        logger.setProp("opmode", "Main Teleop Sim");

        HardwareBot hardwareBot = new HardwareBot();
        hardwareBot.claw = new SimHardwareClaw(hardwareMap, logger);
        hardwareBot.clawFlipper = new SimHardwareClawFlipper(hardwareMap, logger);
        hardwareBot.flyWheel = new SimHardwareFlyWheels(hardwareMap, logger);
        hardwareBot.mecanumDrive = new SimHardwareMecanumDrive(hardwareMap, logger);
        hardwareBot.launch = new SimHardwareLaunch(hardwareMap, logger);
        hardwareBot.slides = new SimHardwareSlides(hardwareMap, logger);
        hardwareBot.pivot = new SimHardwarePivot(hardwareMap, logger);
        Bot bot = new Bot(hardwareBot, logger);
        waitForStart();
        bot.movement.resetEncoders();
        bot.slides.resetEncoders();
        ControllerMapping controllerMapping = new ControllerMapping(gamepad1, gamepad2);
        while (opModeIsActive()) {
            Intent intent = controllerMapping.get_intent();
            logger.setProp("intent", intent.toString());
            bot.movement.executeIntent(intent.movement);
            bot.intake.executeIntent(intent.intake);
            bot.slides.executeIntent(intent.slides, intent.override);
            bot.claw.executeIntent(intent.clawPincher, intent.clawFlip);
            bot.launch.executeIntent(intent.launch);
            bot.pivot.executeIntent(intent);

            logger.setProp("x", bot.movement.x);
            logger.setProp("y", bot.movement.y);
            logger.setProp("heading", bot.movement.heading);

            logger.push();
        }
        logger.close();
    }
}
