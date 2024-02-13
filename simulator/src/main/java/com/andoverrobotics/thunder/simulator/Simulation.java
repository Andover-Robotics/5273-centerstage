package com.andoverrobotics.thunder.simulator;

import com.andoverrobotics.thunder.commonlogic.util.DSConfig;
import com.andoverrobotics.thunder.simulator.simComponents.SimCRServo;
import com.andoverrobotics.thunder.simulator.simComponents.SimMotor;
import com.andoverrobotics.thunder.simulator.simComponents.SimServo;

public class Simulation {
    public static final double INCH_OUNCE_TO_NEWTON_METER = 0.00706155183333;
    private SimServo clawPincher;
    private SimServo clawFlipper = new SimServo(0.001);
    private SimCRServo flyWheelLeft;
    private SimCRServo flyWheelRight;
    private SimServo launcher;
    private SimMotor slidesLeft;
    private SimMotor slidesRight;
    private SimMotor pivot;
    private SimMotor driveFrontLeft;
    private SimMotor driveFrontRight;
    private SimMotor driveBackLeft;
    private SimMotor driveBackRight;
    private double width;
    private double length;
    private double mass;
    public double x;
    public double y;
    public double heading;
    public double vel_x;
    public double vel_y;
    public double ang_vel;
    private double moment_of_inertia;
    private double force_x;
    private double force_y;
    private double torque;

    public Simulation(double width, double length, double mass) {
        this.width = width;
        this.length = length;
        this.mass = mass;
        this.moment_of_inertia = mass * (Math.pow(width, 2) + Math.pow(length, 2)) / 12;
    }

    public SimServo getServo(String servoName) {
        switch (servoName) {
            case DSConfig.CLAW_SERVO:
                return clawPincher;
            case DSConfig.FLIPPER_SERVO:
                return clawFlipper;
            case DSConfig.LAUNCH_SERVO:
                return launcher;
            default:
                throw new IllegalArgumentException("Unknown servo name: " + servoName);
        }
    }

    public SimMotor getMotor(String motorName) {
        switch (motorName) {
            case DSConfig.SLIDES_LEFT_MOTOR:
                return slidesLeft;
            case DSConfig.SLIDES_RIGHT_MOTOR:
                return slidesRight;
            case DSConfig.DRIVETRAIN_FRONT_LEFT_MOTOR:
                return driveFrontLeft;
            case DSConfig.DRIVETRAIN_FRONT_RIGHT_MOTOR:
                return driveFrontRight;
            case DSConfig.DRIVETRAIN_BACK_LEFT_MOTOR:
                return driveBackLeft;
            case DSConfig.DRIVETRAIN_BACK_RIGHT_MOTOR:
                return driveBackRight;
            case DSConfig.PIVOT_MOTOR:
                return pivot;
            default:
                throw new IllegalArgumentException("Unknown motor name: " + motorName);
        }
    }

    public SimCRServo getCRServo(String crServoName) {
        switch (crServoName) {
            case DSConfig.FLYWHEEL_LEFT_CR_SERVO:
                return flyWheelLeft;
            case DSConfig.FLYWHEEL_RIGHT_CR_SERVO:
                return flyWheelRight;
            default:
                throw new IllegalArgumentException("Unknown CR servo name: " + crServoName);
        }
    }

    public void apply_force(double x, double y, double heading, double magnitude) {
        // the x and y is the offset from the center of mass in the robot's frame of reference
        // the heading is the angle of the force relative to the robot's frame of reference
        // the magnitude is the magnitude of the force

        double torque = x * magnitude * Math.sin(heading) - y * magnitude * Math.cos(heading);

        double force_x = magnitude * Math.cos(heading);
        double force_y = magnitude * Math.sin(heading);

        this.force_x += force_x;
        this.force_y += force_y;
        this.torque += torque;
    }

    public void physics_tick(double dt) {
        double accel_x = force_x / mass;
        double accel_y = force_y / mass;
        double alpha = torque / moment_of_inertia;

        vel_x += accel_x * dt;
        vel_y += accel_y * dt;
        ang_vel += alpha * dt;

        x += vel_x * dt;
        y += vel_y * dt;
        heading += ang_vel * dt;
    }
}
