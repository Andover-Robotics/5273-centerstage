package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.Bot;
import org.firstinspires.ftc.teamcode.bot.HardwareBot;

@Autonomous(name="moveToOdoTest", group="Auto")
public class moveToOdoTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBot hardwareBot = new HardwareBot();
        hardwareBot.initReal(hardwareMap, telemetry);
        Bot bot = new Bot(hardwareBot, telemetry);
        waitForStart();
        bot.movement.resetEncoders();
        bot.slides.resetEncoders();
        bot.movement.moveTo(20, 0);

    }
}
