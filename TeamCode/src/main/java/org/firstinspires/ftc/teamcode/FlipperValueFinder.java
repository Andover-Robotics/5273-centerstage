package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Flipper Value Finder", group = "Teleop")
public class FlipperValueFinder extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        Servo flipperServo = hardwareMap.get(Servo.class, "flipperServo");
        while(opModeIsActive()){
            if(gamepad2.dpad_up){
                flipperServo.setPosition(flipperServo.getPosition()+0.0001);
            }else if(gamepad2.dpad_down){
                flipperServo.setPosition(flipperServo.getPosition()-0.0001);
            }
            telemetry.addData("Flipper Position", flipperServo.getPosition());
            telemetry.update();
        }
    }
}
