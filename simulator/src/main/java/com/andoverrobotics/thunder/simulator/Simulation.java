package com.andoverrobotics.thunder.simulator;

import com.andoverrobotics.thunder.simulator.simComponents.SimCRServo;
import com.andoverrobotics.thunder.simulator.simComponents.SimMotor;
import com.andoverrobotics.thunder.simulator.simComponents.SimServo;

public class Simulation {
    private SimServo clawPincher;
    private SimServo clawFlipper;
    private SimCRServo flyWheelLeft;
    private SimCRServo flyWheelRight;
    private SimServo launcher;
    private SimMotor hanger;
    private SimMotor slidesLeft;
    private SimMotor slidesRight;
    private SimMotor driveFrontLeft;
    private SimMotor driveFrontRight;
    private SimMotor driveBackLeft;
    private SimMotor driveBackRight;

    private static RobotConfiguration robotConfiguration;

    public SimServo getServo(String servoName) {
        switch (servoName) {
            case "clawPincher":
                return clawPincher;
            case "clawFlipper":
                return clawFlipper;
            case "launcher":
                return launcher;
            default:
                throw new IllegalArgumentException("Unknown servo name: " + servoName);
        }
    }

    public SimMotor getMotor(String motorName) {
        switch (motorName) {
            case "hanger":
                return hanger;
            case "slidesLeft":
                return slidesLeft;
            case "slidesRight":
                return slidesRight;
            case "driveFrontLeft":
                return driveFrontLeft;
            case "driveFrontRight":
                return driveFrontRight;
            case "driveBackLeft":
                return driveBackLeft;
            case "driveBackRight":
                return driveBackRight;
            default:
                throw new IllegalArgumentException("Unknown motor name: " + motorName);
        }
    }

    public SimCRServo getCRServo(String crServoName) {
        switch (crServoName) {
            case "flyWheelLeft":
                return flyWheelLeft;
            case "flyWheelRight":
                return flyWheelRight;
            default:
                throw new IllegalArgumentException("Unknown CR servo name: " + crServoName);
        }
    }
}