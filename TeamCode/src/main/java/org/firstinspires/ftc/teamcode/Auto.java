package org.firstinspires.ftc.teamcode;

import com.andoverrobotics.thunder.commonlogic.util.Alliance;
import com.andoverrobotics.thunder.commonlogic.util.StartPos;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.andoverrobotics.thunder.commonlogic.bot.Bot;
import com.andoverrobotics.thunder.commonlogic.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.hardware.FtcDashboardLogger;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.FileLogger;
import org.firstinspires.ftc.teamcode.hardware.FtcTelemetryLogger;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareCamera;
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

@TeleOp(name = "Main Auto", group = "Auto")
public class Auto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        CombinedLogger logger = new CombinedLogger(
                new FtcTelemetryLogger(telemetry),
                new FtcDashboardLogger(),
                new FileLogger("/sdcard/FIRST/")
        );
        logger.setProp("opmode", "Main Auto");

        HardwareBot hardwareBot = new HardwareBot();
        hardwareBot.claw = new RealHardwareClaw(hardwareMap, logger);
        hardwareBot.clawFlipper = new RealHardwareClawFlipper(hardwareMap, logger);
        hardwareBot.flyWheel = new RealHardwareFlyWheels(hardwareMap, logger);
        hardwareBot.mecanumDrive = new RealHardwareMecanumDrive(hardwareMap, logger);
        hardwareBot.launch = new RealHardwareLaunch(hardwareMap, logger);
        hardwareBot.hanger = new RealHardwareHanger(hardwareMap, logger);
        hardwareBot.slides = new RealHardwareSlides(hardwareMap, logger);
        boolean right_prev = false;
        boolean left_prev = false;
        boolean b_prev = false;
        boolean x_prev = false;
        Alliance alliance = null;
        StartPos startPos = null;
        while(!gamepad2.a && !isStopRequested()){
            if(gamepad2.b && !b_prev) {
                alliance = Alliance.RED;
                b_prev = true;
            }else if(gamepad2.x && !x_prev){
                alliance = Alliance.BLUE;
                x_prev = true;
            }
            if(!gamepad2.b){
                b_prev = false;
            }
            if(!gamepad2.x){
                x_prev = false;
            }
            if(gamepad2.dpad_right && !right_prev) {
                startPos = StartPos.RIGHT;
                right_prev = true;
            }else if(gamepad2.dpad_left && !left_prev){
                startPos = StartPos.LEFT;
                left_prev = true;
            }
            if(!gamepad2.dpad_right){
                right_prev = false;
            }
            if(!gamepad2.dpad_left){
                left_prev = false;
            }
            logger.setProp("Alliance:", alliance);
            logger.setProp("Pos:",startPos);
            logger.push();
        }
        hardwareBot.camera = new RealHardwareCamera(hardwareMap, alliance, logger);
        Bot bot = new Bot(hardwareBot, logger);
        waitForStart();
        bot.movement.resetEncoders();
        bot.slides.resetEncoders();
        // auto code
        logger.close();
    }
}
