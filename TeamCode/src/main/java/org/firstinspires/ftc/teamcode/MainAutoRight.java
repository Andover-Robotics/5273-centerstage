package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.Bot;
import org.firstinspires.ftc.teamcode.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.input.Intent;

@Autonomous(name="MainAutoRight", group="Linear Opmode")
public class MainAutoRight extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBot hardwareBot = new HardwareBot();
        hardwareBot.initReal(hardwareMap, telemetry);
        Bot bot = new Bot(hardwareBot, telemetry);
        waitForStart();
        bot.movement.resetEncoders();
        bot.slides.resetEncoders();
        while(opModeIsActive()){
            bot.movement.moveTo(0, -20);
            telemetry.addData("x", bot.movement.x);
            telemetry.addData("y", bot.movement.y);
            telemetry.addData("theta", bot.movement.heading);
            telemetry.update();
        }
    }
}
