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
        Intent intent=new Intent();
        intent.movement.centric=Intent.Centric.FIELD;
        //intent.movement.moveDirection=Math.PI/2;
        intent.movement.moveSpeed=0.6;
        intent.movement.turnSpeed=0;
//        while(opModeIsActive()&&bot.movement.y<0.25){
//            bot.movement.executeIntent(intent.movement);
//        }
        intent.movement.moveDirection=Math.PI;
        while(opModeIsActive()&&bot.movement.y<45){
            bot.movement.executeIntent(intent.movement);
        }
    }
}
