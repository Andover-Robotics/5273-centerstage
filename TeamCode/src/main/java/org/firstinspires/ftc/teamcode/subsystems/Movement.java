package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.HardwareMecanumDrive;

public class Movement {
    public double x; // x position
    public double y; // y position
    public double heading; // the direction that the bot is facing in radians
    private final HardwareMecanumDrive drive;
    private static final double ENCODER_RES = 384.5; // ticks per revolution
    private static final double WHEEL_RAD = 1.9; // wheel radius (inches)
    private static final double LY = 7.5625; // half distance between front and back wheels (inches)
    private static final double LX = 6.625; // half distance between front wheels (inches)
    private final Telemetry telemetry;
    public Movement(double x, double y, double heading, HardwareMecanumDrive hardwareMecanumDrive, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.drive = hardwareMecanumDrive;
        hardwareMecanumDrive.setDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE);
    }
    public void moveTo(double x, double y) { // no rotation
        drive.resetEncoders();
        while (Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2)) > 0.1){
            moveTick(heading - Math.atan2(y - this.y, x - this.x),1,0);
        }
    }
    public void moveTo(double x, double y, double heading){
        drive.resetEncoders();
        while (Math.abs(this.heading - heading) > 0.1 && Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2)) > 0.1){
            moveTick(this.heading - Math.atan2(y - this.y, x - this.x),1,(int)(Math.abs(heading - this.heading) / (heading - this.heading))); // probably will not divide by zero
        }
    }
    public void moveTick2(double direction, double power, double turnPower){ // direction is field centric, power [0,1], turnPower is [-1,1]
        moveTick(heading-direction,power,turnPower);
    }
    private void moveTick(double theta, double power, double turnPower){ // power is [0,1], turnPower is [-1,1]
        double motorPow1 = Math.sin(theta + Math.PI / 4); // left front and right back
        double motorPow2 = Math.sin(theta - Math.PI / 4); // right front and left back
        double scale = 1 / Math.max(Math.abs(motorPow1), Math.abs(motorPow2)); // scale up
        motorPow1 *= scale * power;
        motorPow2 *= scale * power;
        double motorPow3 = -turnPower; // left side
        double motorPow4 = turnPower; // right side
        double leftFront = motorPow1 + motorPow3;
        double rightFront = motorPow2 + motorPow4;
        double leftBack = motorPow2 + motorPow3;
        double rightBack = motorPow1 + motorPow4;
        scale = Math.max(Math.max(Math.max(Math.abs(leftFront), Math.abs(rightFront)),Math.abs(leftBack)),Math.abs(rightBack));
        if(scale > 1){ // scale down if needed
            scale = 1 / scale;
            leftFront *= scale;
            rightFront *= scale;
            leftBack *= scale;
            rightBack *= scale;
        }
        drive.setPower(leftFront, rightFront, leftBack, rightBack); // vroom vroom
        updateXYH();
    }
    private void updateXYH(){ // updates x, y, and heading and also resets encoders
        int[] encoders = drive.getCurrentPosition();
        drive.resetEncoders();
        double wheel1AVel = encoders[0] / ENCODER_RES * 2 * Math.PI;
        double wheel2AVel = encoders[1] / ENCODER_RES * 2 * Math.PI;
        double wheel3AVel = encoders[2] / ENCODER_RES * 2 * Math.PI;
        double wheel4AVel = encoders[3] / ENCODER_RES * 2 * Math.PI;
        double xVel = (wheel1AVel + wheel2AVel + wheel3AVel + wheel4AVel) * WHEEL_RAD / 4;
        double yVel = (-wheel1AVel + wheel2AVel + wheel3AVel - wheel4AVel) * WHEEL_RAD / 4;
        double aVel = (-wheel1AVel + wheel2AVel - wheel3AVel + wheel4AVel) * WHEEL_RAD / (4 * (LX + LY));
        heading += aVel;
        x += Math.cos(heading) * yVel + Math.sin(heading) * xVel;
        y += Math.sin(heading) * yVel - Math.cos(heading) * xVel;
    }
    public void resetEncoders(){ // bruh
        drive.resetEncoders();
    }
}