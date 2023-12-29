package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Launcher Value Finder", group = "Teleop")
public class LauncherValueFinder extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        Servo launcher = hardwareMap.get(Servo.class, "launchServo");
        while(opModeIsActive()){
            if(gamepad2.dpad_up){
                launcher.setPosition(claw.getPosition()+0.0001);
            }else if(gamepad2.dpad_down){
                launcher.setPosition(claw.getPosition()-0.0001);
            }
            telemetry.addData("launcher pos: ",claw.getPosition());
            telemetry.update();
        }
    }
}