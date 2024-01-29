package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.andoverrobotics.thunder.commonlogic.bot.Bot;
import com.andoverrobotics.thunder.commonlogic.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.hardware.FtcDashboardLogger;
import org.firstinspires.ftc.teamcode.hardware.FtcTelemetryLogger;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareClaw;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareClawFlipper;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareFlyWheels;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareLaunch;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareMecanumDrive;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareSlides;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.CombinedLogger;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.FileLogger;
import com.andoverrobotics.thunder.commonlogic.input.Intent;

@Autonomous(name = "find movement acceleration", group = "Auto")
public class AccelerationMeasurement extends LinearOpMode {

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
        hardwareBot.slides = new RealHardwareSlides(hardwareMap, logger);
        Bot bot = new Bot(hardwareBot, logger);
        waitForStart();
        bot.movement.resetEncoders();
        bot.slides.resetEncoders();

        double lastTime = System.currentTimeMillis();
        double lastX = bot.movement.x;
        double lastY = bot.movement.y;
        double lastHeading = bot.movement.heading;

        Intent.MovementIntent intent = new Intent.MovementIntent();
        intent.centric = Intent.Centric.ROBOT;
        intent.moveDirection = 0;
        intent.moveSpeed = 1;

        bot.movement.executeIntent(intent);

        while (opModeIsActive()) {
            bot.movement.updateXYH();
            double currentTime = System.currentTimeMillis();
            double currentX = bot.movement.x;
            double currentY = bot.movement.y;
            double currentHeading = bot.movement.heading;
            double deltaTime = currentTime - lastTime;
            double deltaX = currentX - lastX;
            double deltaY = currentY - lastY;
            double deltaHeading = currentHeading - lastHeading;
            double accelerationX = deltaX / deltaTime;
            double accelerationY = deltaY / deltaTime;
            double accelerationHeading = deltaHeading / deltaTime;
            telemetry.addData("accelerationX", accelerationX);
            telemetry.addData("accelerationY", accelerationY);
            telemetry.addData("accelerationHeading", accelerationHeading);
            telemetry.update();
            lastTime = currentTime;
            lastX = currentX;
            lastY = currentY;
            lastHeading = currentHeading;
        }

    }
}
