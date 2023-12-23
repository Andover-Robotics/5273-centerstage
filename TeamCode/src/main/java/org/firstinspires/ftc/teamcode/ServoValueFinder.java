package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Servo Value Finder", group = "Teleop")
public class LauncherValueFinder extends LinearOpMode {
    private static final String[] SERVO_LIST = {
            "clawServo",
            "flipperServo",
            "launchServo"
    };
    private static int num = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        while(!gamepad2.a){
            if(gamepad2.dpad_right){
                num = (num + 1) % SERVO_LIST.length;
                while(gamepad2.dpad_right){}
            }else if(gamepad2.dpad_left){
                num = (num + SERVO_LIST.length - 1) % SERVO_LSIT.length;
                while(gamepad2.dpad_left){}
            }
            telemetry.addData("selected servo: ", SERVO_LIST[num]);
            telemetry.update();
        }
        Servo servo = hardwareMap.get(Servo.class, SERVO_LIST[num]);
        while(opModeIsActive()){
            if(gamepad2.dpad_up){
                servo.setPosition(claw.getPosition()+0.0001);
            }else if(gamepad2.dpad_down){
                servo.setPosition(claw.getPosition()-0.0001);
            }
            telemetry.addData("servo pos: ",claw.getPosition());
            telemetry.update();
        }
    }
}
