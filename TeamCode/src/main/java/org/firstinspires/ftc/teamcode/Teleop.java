package org.firstinspires.ftc.teamcode;

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
        hardwareBot.initReal(hardwareMap);
        Bot bot = new Bot(hardwareBot);
        waitForStart();
        bot.movement.resetEncoders();
        ControllerMapping controllerMapping = new ControllerMapping(gamepad1, gamepad2);
        while(opModeIsActive()){
            Intent intent = controllerMapping.get_intent();
            bot.movement.moveTick2(intent.move_dir, intent.move_speed, intent.turn_speed);
        }
    }
}
