package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bot.Bot;
import org.firstinspires.ftc.teamcode.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.input.ControllerMapping;
import org.firstinspires.ftc.teamcode.input.Intent;

@TeleOp(name = "Main Teleop", group = "Teleop")
public class Teleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBot hardwareBot = new HardwareBot();
        hardwareBot.initReal(hardwareMap, telemetry);
        Bot bot = new Bot(hardwareBot, telemetry);
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

            telemetry.addData("x", bot.movement.x);
            telemetry.addData("y", bot.movement.y);
            telemetry.addData("heading", bot.movement.heading);

            telemetry.update();
        }
    }
}
