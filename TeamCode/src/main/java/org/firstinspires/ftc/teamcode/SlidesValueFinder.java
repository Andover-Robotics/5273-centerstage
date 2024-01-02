package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.bot.Bot;
import org.firstinspires.ftc.teamcode.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.hardware.FtcDashboardLogger;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.FileLogger;
import org.firstinspires.ftc.teamcode.hardware.FtcTelemetryLogger;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.CombinedLogger;
import org.firstinspires.ftc.teamcode.input.ControllerMapping;
import org.firstinspires.ftc.teamcode.input.Intent;

@TeleOp(name = "sides value finder", group = "Teleop")
public class SlidesValueFinder extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        CombinedLogger logger = new CombinedLogger(
                new FtcTelemetryLogger(telemetry),
                new FtcDashboardLogger()
        );
        logger.setProp("opmode", "Main Teleop");

        HardwareBot hardwareBot = new HardwareBot();
        hardwareBot.initReal(hardwareMap, logger);
        Bot bot = new Bot(hardwareBot, logger);
        DcMotor slidesMotorLeft=hardwareMap.get(DcMotor.class, "slidesWheelMotorLeft");
        DcMotor slidesMotorRight=hardwareMap.get(DcMotor.class, "slidesWheelMotorRight");
        slidesMotorLeft.setDirection(DcMotor.Direction.FORWARD);
        slidesMotorRight.setDirection(DcMotor.Direction.REVERSE);
        bot.movement.resetEncoders();
        bot.slides.resetEncoders();
        bot.claw.executeIntent(Intent.ClawPincherIntent.CLOSE_FULL, Intent.ClawFlipIntent.IN);
        waitForStart();
        while(opModeIsActive()){
            if(gamepad2.dpad_up){
                slidesMotorLeft.setPower(0.3);
                slidesMotorRight.setPower(0.3);
            } else if (gamepad2.dpad_down) {
                slidesMotorLeft.setPower(-0.3);
                slidesMotorRight.setPower(-0.3);
            } else {
                slidesMotorLeft.setPower(0);
                slidesMotorRight.setPower(0);
            }
            logger.setProp("slides pos", (slidesMotorLeft.getCurrentPosition() + slidesMotorRight.getCurrentPosition()) / 2.0);
            logger.push();
        }
        logger.close();
    }
}

