package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bot.Bot;
import org.firstinspires.ftc.teamcode.bot.HardwareBot;

@Autonomous(name="moveToOdoTest", group="Auto")
public class moveToOdoTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBot hardwareBot = new HardwareBot();
        hardwareBot.initReal(hardwareMap, telemetry);
        Bot bot = new Bot(hardwareBot, telemetry);
        TelemetryPacket packet = new TelemetryPacket();
        packet.put("distance error", 0);
        packet.put("heading error", 0);
        packet.put("power", 0);
        packet.put("turn power", 0);
        FtcDashboard.getInstance().sendTelemetryPacket(packet);
        waitForStart();
        bot.movement.resetEncoders();
        bot.slides.resetEncoders();
        bot.movement.moveTo(0, 0, Math.PI);
//        while(true){
//            bot.movement.moveTo(30, 0, 0);
//            bot.movement.moveTo(0, 0, 0);
//        }


    }
}
