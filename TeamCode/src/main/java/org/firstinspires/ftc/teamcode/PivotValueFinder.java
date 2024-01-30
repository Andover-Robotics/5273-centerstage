package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.andoverrobotics.thunder.commonlogic.bot.Bot;
import com.andoverrobotics.thunder.commonlogic.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.hardware.FtcDashboardLogger;
import org.firstinspires.ftc.teamcode.hardware.FtcTelemetryLogger;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareClaw;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareClawFlipper;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareFlyWheels;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareLaunch;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.RealHardwarePivot;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareSlides;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.CombinedLogger;
import com.andoverrobotics.thunder.commonlogic.input.Intent;

@TeleOp(name = "pivot value finder", group = "Teleop")
public class PivotValueFinder extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        CombinedLogger logger = new CombinedLogger(
                new FtcTelemetryLogger(telemetry),
                new FtcDashboardLogger()
        );
        logger.setProp("opmode", "Main Teleop");

        HardwareBot hardwareBot = new HardwareBot();
        RealHardwarePivot pivot = new RealHardwarePivot(hardwareMap, logger);
        pivot.resetEncoders();
        waitForStart();
        while(opModeIsActive()){
            if(gamepad2.dpad_up){
                pivot.setPower(0.3);
            } else if (gamepad2.dpad_down) {
                pivot.setPower(-0.3);
            } else {
                pivot.setPower(0);
            }
            logger.setProp("pivot pos", pivot.getPosition());
            logger.push();
        }
        logger.close();
    }
}

