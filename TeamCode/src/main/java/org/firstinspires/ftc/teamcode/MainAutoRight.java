package org.firstinspires.ftc.teamcode;

import static androidx.core.math.MathUtils.clamp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.Bot;
import org.firstinspires.ftc.teamcode.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.input.Intent;

import java.time.Duration;
import java.util.Timer;

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
        Intent intent=new Intent();
        intent.movement.centric=Intent.Centric.FIELD;
        //intent.movement.moveDirection=Math.PI/2;
        intent.movement.moveSpeed=0.6;
        intent.movement.turnSpeed=0;
//        while(opModeIsActive()&&bot.movement.y<0.25){
//            bot.movement.executeIntent(intent.movement);
//            telemetry.addData("x: ",bot.movement.x);
//            telemetry.addData("y: ",bot.movement.y);
//            telemetry.update();
//        }
        intent.movement.moveDirection=0;

        while(opModeIsActive()&&bot.movement.y>-45){
            bot.movement.executeIntent(intent.movement);
            telemetry.addData("x: ",bot.movement.x);
            telemetry.addData("y: ",bot.movement.y);
            telemetry.update();
        }
    }
}
