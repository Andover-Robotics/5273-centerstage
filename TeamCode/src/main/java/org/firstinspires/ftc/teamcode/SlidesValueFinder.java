package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Slides Value Finder", group = "Teleop")
public class SlidesValueFinder extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        waitForStart();
        DcMotor slidesMotorLeft=hardwareMap.get(DcMotor.class, "slidesWheelMotorLeft");
        DcMotor slidesMotorRight=hardwareMap.get(DcMotor.class, "slidesWheelMotorRight");
        slidesMotorLeft.setDirection(DcMotor.Direction.FORWARD);
        slidesMotorRight.setDirection(DcMotor.Direction.REVERSE);
        slidesMotorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slidesMotorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slidesMotorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slidesMotorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while(opModeIsActive()){
            if(gamepad2.dpad_up){
                slidesMotorLeft.setPower(0.3);
                slidesMotorRight.setPower(0.3);
            }else if(gamepad2.dpad_down){
                slidesMotorLeft.setPower(-0.3);
                slidesMotorRight.setPower(-0.3);
            }else{
                slidesMotorLeft.setPower(0);
                slidesMotorRight.setPower(0);
            }
            telemetry.addData("slides pos: ",(slidesMotorLeft.getCurrentPosition()+slidesMotorRight.getCurrentPosition())/2.0);
            telemetry.update();
        }
    }
}
