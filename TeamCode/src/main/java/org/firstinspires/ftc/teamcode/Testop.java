package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.bot.Bot;
import org.firstinspires.ftc.teamcode.bot.HardwareBot;
import org.firstinspires.ftc.teamcode.input.ControllerMapping;
import org.firstinspires.ftc.teamcode.input.Intent;

@TeleOp(name = "Test Teleop", group = "Teleop")
public class Testop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HardwareBot hardwareBot = new HardwareBot();
        hardwareBot.initReal(hardwareMap, telemetry);
        waitForStart();
        Servo claw = hardwareMap.get(Servo.class, "clawServo");
        while(opModeIsActive()){
            if(gamepad2.dpad_up){
                claw.setPosition(claw.getPosition()+0.0001);
            }else if(gamepad2.dpad_down){
                claw.setPosition(claw.getPosition()-0.0001);
            }
            telemetry.addData("claw pos: ",claw.getPosition());
            telemetry.update();
        }
    }
}
