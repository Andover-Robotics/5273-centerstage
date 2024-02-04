package com.andoverrobotics.thunder.commonlogic.subsystems;

import com.andoverrobotics.thunder.commonlogic.input.Intent;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareMecanumDrive;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.util.Direction;


public class Movement {
    public double x; // x position
    public double y; // y position
    public double heading; // the direction that the bot is facing in radians
    private final HardwareMecanumDrive drive;
    private static final double ENCODER_RES = 384.5; // ticks per revolution
    private static final double WHEEL_RAD = 1.9; // wheel radius (inches)

    private static final double LY = 7.5625; // half distance between front and back wheels (inches)
    private static final double LX = 6.625; // half distance between front wheels (inches)
    private static final double MAX_ACCEL = 3.0; // max acceleration (inches per second squared)
    private static final double DECEL_FACTOR = 0.5;
    private static final double TARGET_RES = 1; // target resolution (inches)
    private final Logger logger;

    private static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
    public Movement(double x, double y, double heading, HardwareMecanumDrive hardwareMecanumDrive, Logger logger) {
        this.logger = logger;
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.drive = hardwareMecanumDrive;
        hardwareMecanumDrive.setDirection(Direction.REVERSE, Direction.FORWARD, Direction.REVERSE, Direction.FORWARD);
    }
    public void moveTo(double x, double y) { // no rotation
        moveTo(x, y, heading);
    }
    public void moveTo(double targetXDiff, double targetYDiff, final double targetH){
        final double targetX = x + targetXDiff;
        final double targetY = y + targetYDiff;
        drive.resetEncoders();
        double dist;
        while ((dist=Math.sqrt(Math.pow(getX() - targetX, 2) + Math.pow(getY() - targetY, 2))) > TARGET_RES || Math.abs(getH() - targetH) > 0.1){
            logger.setProp("distance error", dist);
            logger.setProp("heading error", getH() - targetH);
            double power = -Math.pow(2,-DECEL_FACTOR*dist)+1;
            logger.setProp("power", power);

            double turnPower = clamp(getH() - targetH, -1, 1);
            logger.setProp("turn power", turnPower);

            moveTick(Math.atan2(targetY - getY(), targetX - getX()) - targetH, power, turnPower);
        }
        drive.setPower(0,0,0,0);
    }

    public void turnTo(final double targetHeading){
        while(Math.abs(getH() - targetHeading) > 0.1){
            double turnPower = clamp(getH() - targetHeading, -1, 1);
            moveTick(0, 0, turnPower);
        }

    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getH(){
        return heading;
    }

//    public void motionProfileTo(double x, double y, double heading)

    public void executeIntent(Intent.MovementIntent intent) {
        if (intent == null) {
            return;
        }
        if(intent.resetHeading == Intent.MovementIntent.HeadingReset.UP){
            heading = 0;
        }else if(intent.resetHeading == Intent.MovementIntent.HeadingReset.DOWN){
            heading = Math.PI;
        }else if(intent.resetHeading == Intent.MovementIntent.HeadingReset.LEFT) {
            heading = Math.PI / 2;
        }else if(intent.resetHeading == Intent.MovementIntent.HeadingReset.RIGHT){
            heading = -Math.PI / 2;
        }

        if(intent.centric == Intent.Centric.ROBOT) {
            moveTick(intent.moveDirection, intent.moveSpeed, intent.turnSpeed);
        } else if(intent.centric == Intent.Centric.FIELD){
            moveTick(intent.moveDirection - heading, intent.moveSpeed, intent.turnSpeed);
        }
    }
    private void moveTick(double theta, double power, double turnPower){ // power is [0,1], turnPower is [-1,1]
        // pi/2 rad is forwards
        double motorPow1 = Math.sin(theta + Math.PI / 4); // left front and right back
        double motorPow2 = Math.sin(theta - Math.PI / 4); // right front and left back
        double scale = 1 / Math.max(Math.abs(motorPow1), Math.abs(motorPow2)); // scale up
        motorPow1 *= scale * power;
        motorPow2 *= scale * power;
        double motorPow3 = turnPower; // left side
        double motorPow4 = -turnPower; // right side
        double leftFront = motorPow1 + motorPow3;
        double rightFront = motorPow2 + motorPow4;
        double leftBack = motorPow2 + motorPow3;
        double rightBack = motorPow1 + motorPow4;
        logger.setProp("FR power", rightFront);
        logger.setProp("FL power", leftFront);
        logger.setProp("BR power", rightBack);
        logger.setProp("BL power", leftBack);

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

    private static class RobotVals {
        public final double x;
        public final double y;
        public final double heading;
        public RobotVals(double x, double y, double heading) {
            this.x = x;
            this.y = y;
            this.heading = heading;
        }
    }

    public void updateXYH(){ // updates x, y, and heading and also resets encoders
        int[] encoders = drive.getCurrentPosition();
        drive.resetEncoders();
        double wheel1AVel = encoders[0] / ENCODER_RES * 2 * Math.PI;
        double wheel2AVel = encoders[1] / ENCODER_RES * 2 * Math.PI;
        double wheel3AVel = encoders[2] / ENCODER_RES * 2 * Math.PI;
        double wheel4AVel = encoders[3] / ENCODER_RES * 2 * Math.PI;
        double xVel_local = (wheel1AVel + wheel2AVel + wheel3AVel + wheel4AVel) * WHEEL_RAD / 4;
        double yVel_local = (-wheel1AVel + wheel2AVel + wheel3AVel - wheel4AVel) * WHEEL_RAD / 4;
        double aVel = -(-wheel1AVel + wheel2AVel - wheel3AVel + wheel4AVel) * WHEEL_RAD / (4 * (LX + LY));

        double xVel_global = xVel_local * Math.cos(-heading) + yVel_local * Math.sin(-heading);
        double yVel_global = -xVel_local * Math.sin(-heading) + yVel_local * Math.cos(-heading);

        x += xVel_global;
        y += yVel_global;
        heading -= aVel;//this needs to be like this, otherwise its reversed

        heading = (((heading % (Math.PI*2)) + (Math.PI*2)) % (Math.PI*2));
    }
    public void resetEncoders(){ // bruh
        drive.resetEncoders();
    }
}