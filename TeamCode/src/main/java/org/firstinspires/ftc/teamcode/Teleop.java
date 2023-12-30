package org.firstinspires.ftc.teamcode;

import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bot.Bot;
import org.firstinspires.ftc.teamcode.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.hardware.FtcDashboardLogger;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.FileLogger;
import org.firstinspires.ftc.teamcode.hardware.FtcTelemetryLogger;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.CombinedLogger;
import org.firstinspires.ftc.teamcode.input.ControllerMapping;
import org.firstinspires.ftc.teamcode.input.Intent;

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
        hardwareBot.initReal(hardwareMap, logger);
        Bot bot = new Bot(hardwareBot, logger);
        waitForStart();
        bot.movement.resetEncoders();
        bot.slides.resetEncoders();
        ControllerMapping controllerMapping = new ControllerMapping(gamepad1, gamepad2);
        while(opModeIsActive()){
            Intent intent = controllerMapping.get_intent();
            telemetry.addData("intent", intent.toString());
            bot.movement.executeIntent(intent.movement);
            bot.intake.executeIntent(intent.intake);
            bot.slides.executeIntent(intent.slides, intent.slidesOverride);
            bot.claw.executeIntent(intent.clawPincher, intent.clawFlip);
            bot.launch.executeIntent(intent.launch);

            logger.setProp("x", bot.movement.x);
            logger.setProp("y", bot.movement.y);
            logger.setProp("heading", bot.movement.heading);

            logger.push();
        }
        logger.close();
    }
}
