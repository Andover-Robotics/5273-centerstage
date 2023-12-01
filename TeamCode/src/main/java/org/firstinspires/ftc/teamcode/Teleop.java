package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bot.Bot;
import org.firstinspires.ftc.teamcode.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.input.ControllerMapping;
import org.firstinspires.ftc.teamcode.input.Intent;

@TeleOp(name = "Main Teleop", group = "Teleop")
public class Teleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBot hardwareBot = new HardwareBot();
        hardwareBot.initReal(hardwareMap, telemetry);
        Bot bot = new Bot(hardwareBot, telemetry);
        waitForStart();
        bot.movement.resetEncoders();
        bot.slides.resetEncoders();
        ControllerMapping controllerMapping = new ControllerMapping(gamepad1, gamepad2);
        while(opModeIsActive()){
            Intent intent = controllerMapping.get_intent();
            telemetry.addData("intent", intent.toString());
            bot.movement.executeIntent(intent.movement);
            bot.intake.executeIntent(intent.intake);
            intent.clawFlip = bot.slides.executeIntent(intent.slides);
            bot.claw.executeIntent(intent.clawPincher, intent.clawFlip);

            TelemetryPacket packet = new TelemetryPacket();
            double[] xPoints = new double[4];
            double[] yPoints = new double[4];
            double x = bot.movement.x;
            double y = bot.movement.y;
            double heading = bot.movement.heading;
            double width = 18;
            double length = 18;
            xPoints[0] =  + width/2;
            yPoints[0] =  + length/2;
            xPoints[1] =  - width/2;
            yPoints[1] =  + length/2;
            xPoints[2] =  - width/2;
            yPoints[2] =  - length/2;
            xPoints[3] =  + width/2;
            yPoints[3] =  - length/2;
            for(int i = 0; i < 4; i++){
                double xTemp = xPoints[i];
                double yTemp = yPoints[i];
                xPoints[i] = xTemp * Math.cos(heading) - yTemp * Math.sin(heading) + x;
                yPoints[i] = xTemp * Math.sin(heading) + yTemp * Math.cos(heading) + y;

            }
            packet.fieldOverlay().fillPolygon(xPoints, yPoints);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);

            telemetry.update();
        }
    }
}
