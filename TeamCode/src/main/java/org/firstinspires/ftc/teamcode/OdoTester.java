package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.Bot;
import org.firstinspires.ftc.teamcode.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.hardware.FtcDashboardLogger;
import org.firstinspires.ftc.teamcode.hardware.FtcTelemetryLogger;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.CombinedLogger;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.FileLogger;

@Autonomous(name="odo tester", group="Linear Opmode")
public class OdoTester extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        CombinedLogger logger = new CombinedLogger(
                new FtcTelemetryLogger(telemetry),
                new FtcDashboardLogger(),
                new FileLogger("/sdcard/FIRST/")
        );
        logger.setProp("opmode", "Main Teleop");
        HardwareBot hardwareBot = new HardwareBot();
        hardwareBot.initReal(hardwareMap, logger);
        Bot bot = new Bot(hardwareBot, logger);
        waitForStart();
        bot.movement.resetEncoders();
        bot.slides.resetEncoders();


        while(opModeIsActive()){
            TelemetryPacket packet = new TelemetryPacket();
            bot.movement.updateXYH();
            packet.put("x", bot.movement.x);
            packet.put("y", bot.movement.y);
            packet.put("heading", bot.movement.heading);
            //make a list of points for a square of the robot with odo vals
            //use heading for rotation
            double[] xPoints = new double[4];
            double[] yPoints = new double[4];
            double x = bot.movement.x;
            double y = bot.movement.y;
            double heading = bot.movement.heading;
            double width = 18;
            double length = 18;
            xPoints[0] =  width /2;
            yPoints[0] =  length /2;
            xPoints[1] =  - width/2;
            yPoints[1] =  length /2;
            xPoints[2] =  - width/2;
            yPoints[2] =  - length/2;
            xPoints[3] =  width /2;
            yPoints[3] =  - length/2;
            for(int i = 0; i < 4; i++){
                double xTemp = xPoints[i];
                double yTemp = yPoints[i];
                xPoints[i] = xTemp * Math.cos(heading) - yTemp * Math.sin(heading) + x;
                yPoints[i] = xTemp * Math.sin(heading) + yTemp * Math.cos(heading) + y;

            }
            packet.fieldOverlay().fillPolygon(xPoints, yPoints);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }
    }
}
