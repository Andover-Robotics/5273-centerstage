package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.andoverrobotics.thunder.commonlogic.bot.Bot;
import com.andoverrobotics.thunder.commonlogic.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.hardware.FtcDashboardLogger;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.FileLogger;
import org.firstinspires.ftc.teamcode.hardware.FtcTelemetryLogger;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareClaw;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareClawFlipper;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareController;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareFlyWheels;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareHanger;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareLaunch;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareSlides;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.CombinedLogger;
import com.andoverrobotics.thunder.commonlogic.input.ControllerMapping;
import com.andoverrobotics.thunder.commonlogic.input.Intent;

@TeleOp(name = "Main Teleop", group = "Teleop")
public class Teleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        CombinedLogger logger = new CombinedLogger(
            new FtcTelemetryLogger(telemetry),
            new FtcDashboardLogger(),
            new FileLogger("/sdcard/FIRST/")
        );
        logger.setProp("opmode", "Main Teleop");

        HardwareBot hardwareBot = new HardwareBot();
        hardwareBot.claw = new RealHardwareClaw(hardwareMap, logger);
        hardwareBot.clawFlipper = new RealHardwareClawFlipper(hardwareMap, logger);
        hardwareBot.flyWheel = new RealHardwareFlyWheels(hardwareMap, logger);
        hardwareBot.mecanumDrive = new RealHardwareMecanumDrive(hardwareMap, logger);
        hardwareBot.launch = new RealHardwareLaunch(hardwareMap, logger);
        hardwareBot.hanger = new RealHardwareHanger(hardwareMap, logger);
        hardwareBot.slides = new RealHardwareSlides(hardwareMap, logger);
        Bot bot = new Bot(hardwareBot, logger);
        waitForStart();
        bot.movement.resetEncoders();
        bot.slides.resetEncoders();
        ControllerMapping controllerMapping = new ControllerMapping(new RealHardwareController(gamepad1), new RealHardwareController(gamepad2));
        while(opModeIsActive()){
            Intent intent = controllerMapping.get_intent();
            telemetry.addData("intent", intent.toString());
            bot.movement.executeIntent(intent.movement);
            bot.intake.executeIntent(intent.intake);
            bot.slides.executeIntent(intent.slides, intent.slidesOverride);
            bot.claw.executeIntent(intent.clawPincher, intent.clawFlip);
            bot.launch.executeIntent(intent.launch);
            bot.pivot.executeIntent(intent.pivot);

            logger.setProp("x", bot.movement.x);
            logger.setProp("y", bot.movement.y);
            logger.setProp("heading", bot.movement.heading);

            logger.push();
        }
        logger.close();
    }
}
