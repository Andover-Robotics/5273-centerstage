package org.firstinspires.ftc.teamcode.subsystems;

import static androidx.core.math.MathUtils.clamp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareMecanumDrive;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.Logger;
import org.firstinspires.ftc.teamcode.input.Intent;

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
    public Movement(double x, double y, double heading, HardwareMecanumDrive hardwareMecanumDrive, Logger logger) {
        this.logger = logger;
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.drive = hardwareMecanumDrive;
        hardwareMecanumDrive.setDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE);
    }
    public void moveTo(double x, double y) { // no rotation
        moveTo(x, y, heading);
    }
    public void moveTo(double x, double y, double heading){
        drive.resetEncoders();
        double dist;
        while ((dist=Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2))) > TARGET_RES || Math.abs(this.heading - heading) > 0.1){
            TelemetryPacket packet = new TelemetryPacket();
            packet.put("distance error", dist);
            packet.put("heading error", this.heading - heading);
            double power = -Math.pow(2,-DECEL_FACTOR*dist)+1;
            packet.put("power", power);

            double turnPower = clamp(this.heading - heading, -1, 1);
            packet.put("turn power", turnPower);

                double[] xPoints = new double[4];
                double[] yPoints = new double[4];
                double width = 18;
                double length = 18;
                xPoints[0] = width / 2;
                yPoints[0] = length / 2;
                xPoints[1] = -width / 2;
                yPoints[1] = length / 2;
                xPoints[2] = -width / 2;
                yPoints[2] = -length / 2;
                xPoints[3] = width / 2;
                yPoints[3] = -length / 2;
                for (int i = 0; i < 4; i++) {
                    double xTemp = xPoints[i];
                    double yTemp = yPoints[i];
                    xPoints[i] = xTemp * Math.cos(heading) - yTemp * Math.sin(heading) + x;
                    yPoints[i] = xTemp * Math.sin(heading) + yTemp * Math.cos(heading) + y;
                }
                packet.fieldOverlay().fillPolygon(xPoints, yPoints);


            FtcDashboard.getInstance().sendTelemetryPacket(packet);
            moveTick(Math.atan2(y - this.y, x - this.x) - heading, power, turnPower);
        }
        drive.setPower(0,0,0,0);
    }

//    public void motionProfileTo(double x, double y, double heading)

    public void executeIntent(Intent.MovementIntent intent) {
        if (intent == null) {
            return;
        }
        if(intent.resetHeading){
            heading = 0;
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
        double motorPow3 = -turnPower; // left side
        double motorPow4 = turnPower; // right side
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
        heading += aVel;
        heading = (((heading % (Math.PI*2)) + (Math.PI*2)) % (Math.PI*2));
    }
    public void resetEncoders(){ // bruh
        drive.resetEncoders();
    }
}