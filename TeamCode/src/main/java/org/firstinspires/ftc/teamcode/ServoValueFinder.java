package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Servo Value Finder", group = "Teleop")
public class ServoValueFinder extends LinearOpMode {
    private static final String[] SERVO_LIST = {
            "clawServo",
            "flipperServo",
            "launchServo"
    };
    private static int num = 0;
    private boolean dpad_right_prev = false;
    private boolean dpad_left_prev = false;
    @Override
    public void runOpMode() throws InterruptedException {
        while(!gamepad2.a && !isStopRequested()){
            if(gamepad2.dpad_right && !dpad_right_prev){
                num = (num + 1) % SERVO_LIST.length;
                dpad_right_prev = true;
            }else if(gamepad2.dpad_left && !dpad_left_prev){
                num = (num + SERVO_LIST.length - 1) % SERVO_LIST.length;
                dpad_left_prev = true;
            }
            if(!gamepad2.dpad_right){
                dpad_right_prev = false;
            }
            if(!gamepad2.dpad_left){
                dpad_left_prev = false;
            }
            telemetry.addData("selected servo: ", SERVO_LIST[num]);
            telemetry.update();
        }
        Servo servo = hardwareMap.get(Servo.class, SERVO_LIST[num]);
        waitForStart();
        while(opModeIsActive()){
            if(gamepad2.dpad_up){
                servo.setPosition(servo.getPosition()+0.0001);
            }else if(gamepad2.dpad_down){
                servo.setPosition(servo.getPosition()-0.0001);
            }
            telemetry.addData("servo pos: ",servo.getPosition());
            telemetry.update();
        }
    }
}
