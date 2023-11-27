package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.Bot;
import org.firstinspires.ftc.teamcode.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.input.Intent;

@Autonomous(name="find movement acceleration", group="Auto")
public class AccelerationMeasurement extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBot hardwareBot = new HardwareBot();
        hardwareBot.initReal(hardwareMap, telemetry);
        Bot bot = new Bot(hardwareBot, telemetry);
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

        while(opModeIsActive()){
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
