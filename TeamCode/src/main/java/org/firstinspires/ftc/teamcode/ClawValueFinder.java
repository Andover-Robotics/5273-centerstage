package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Claw Value Finder", group = "Teleop")
public class ClawValueFinder extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
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
