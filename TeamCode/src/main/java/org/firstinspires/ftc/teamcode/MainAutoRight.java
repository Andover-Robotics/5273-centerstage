package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.example.commonlogic.bot.Bot;
import com.example.commonlogic.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.hardware.FtcDashboardLogger;
import org.firstinspires.ftc.teamcode.hardware.FtcTelemetryLogger;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareClaw;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareClawFlipper;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareFlyWheels;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareHanger;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareLaunch;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareSlides;

import com.example.commonlogic.hardwareInterfaces.CombinedLogger;
import com.example.commonlogic.hardwareInterfaces.FileLogger;
import com.example.commonlogic.input.Intent;

@Autonomous(name="MainAutoRight", group="Linear Opmode")
public class MainAutoRight extends LinearOpMode {
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
