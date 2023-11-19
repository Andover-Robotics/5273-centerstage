package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.Bot;
import org.firstinspires.ftc.teamcode.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.input.Intent;

@Autonomous(name="MainAutoLeft", group="Linear Opmode")
public class MainAutoLeft extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBot hardwareBot = new HardwareBot();
        hardwareBot.initReal(hardwareMap, telemetry);
        Bot bot = new Bot(hardwareBot, telemetry);
        waitForStart();
        bot.movement.resetEncoders();
        bot.slides.resetEncoders();
        while(opModeIsActive()){
            Intent intent = new Intent();

            intent.movement.centric = Intent.Centric.ROBOT;
            intent.movement.moveDirection = 0;
            intent.movement.moveSpeed = 0.4;
            intent.movement.turnSpeed = 0;
            bot.movement.executeIntent(intent.movement);
            telemetry.update();
        }
    }
}
