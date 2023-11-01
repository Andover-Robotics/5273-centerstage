package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.hardware.HardwareMecanumDrive;

public class Movement {
    public float x; // x position
    public float y; // y position
    public float heading; // the direction that the bot is facing in radians
    private final HardwareMecanumDrive drive;
    public Movement(float x, float y, float heading, HardwareMecanumDrive hardwareMecanumDrive) {
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.drive = hardwareMecanumDrive;
        //TODO: verify that the right side is the side that should be reversed
        hardwareMecanumDrive.setDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE);
    }
    public void moveTo(float x, float y) { // no rotation
        while (Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2)) > 0.1){
            double theta = Math.atan2(y - this.y, x - this.x) - heading;
            double motorPow1 = Math.sin(theta + Math.PI / 4);
            double motorPow2 = Math.sin(theta - Math.PI / 4);
            double scale = 1 / Math.max(Math.abs(motorPow1), Math.abs(motorPow2));
            motorPow1 *= scale;
            motorPow2 *= scale;
            drive.setPower(motorPow1, motorPow2, motorPow2, motorPow1); // vroom vroom
            // TODO: update x and y
        }
    }
    public void moveTo(float x, float y, float heading){
        while (Math.abs(this.heading - heading) > 0.1 && Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2)) > 0.1){
            double theta = Math.atan2(y - this.y, x - this.x) - this.heading;
            int turn = (int)(Math.abs(heading - this.heading) / (heading - this.heading)); // probably will not divide by zero
            double motorPow1 = Math.sin(theta + Math.PI / 4); // left front and right back
            double motorPow2 = Math.sin(theta - Math.PI / 4); // right front and left back
            double scale = 1 / Math.max(Math.abs(motorPow1), Math.abs(motorPow2)); // scale up
            double moveSpeed = 1; // TODO
            motorPow1 *= scale * moveSpeed;
            motorPow2 *= scale * moveSpeed;
            double turnSpeed = 1; // TODO
            double motorPow3 = -turn * turnSpeed; // left side
            double motorPow4 = turn * turnSpeed; // right side
            double leftFront = motorPow1 + motorPow3;
            double rightFront = motorPow2 + motorPow4;
            double leftBack = motorPow2 + motorPow3;
            double rightBack = motorPow1 + motorPow4;
            scale=Math.max(Math.max(Math.max(Math.abs(leftFront), Math.abs(rightFront)),Math.abs(leftBack)),Math.abs(rightBack));
            if(scale>1){ // scale down if needed
                scale=1/scale;
                leftFront*=scale;
                rightFront*=scale;
                leftBack*=scale;
                rightBack*=scale;
            }
            drive.setPower(leftFront, rightFront, leftBack, rightBack); // vroom vroom
            // TODO: update x and y
        }
    }
}