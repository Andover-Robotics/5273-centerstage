package com.andoverrobotics.thunder.simulator.simComponents;

import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.andoverrobotics.thunder.simulator.Simulation;

// https://www.gobilda.com/2000-series-dual-mode-servo-25-2-torque/
// control hub/driver station sends 6v to servos, using the no load speed
public class SimCRServo {
    private static final double MAX_SPEED = 50; // RPM
    private static final double MAX_TORQUE = 300 * Simulation.OUNCE_INCH_TO_NEWTON_METER; // N*m
    public double realPosition = 0.5;
    private Direction direction = Direction.FORWARD;
    private double momentOfInertia; // kg*m^2
    private double rotationalVelocity = 0; // RPM
    private double power = 0;

    public static double lbmm2tokgm2(double lbmm2) {
        return lbmm2 * 4.5359237 * Math.pow(10, -7);
    }

    // construct with the moment of inertia of what is attached to the servo
    // moment of inertia is given in kg*m^2
    public SimCRServo(double momentOfInertia) {
        this.momentOfInertia = momentOfInertia;
    }

    private double getServoTorque() {
        // MAX_TORQUE * power is the motor torque
        // back EMF torque is -MAX_TORQUE * (rotationalVelocity / MAX_SPEED)
        // the net torque is the sum of the two
        double motorTorque = MAX_TORQUE * power;
        double backEMFTorque = -MAX_TORQUE * (rotationalVelocity / MAX_SPEED);
        return motorTorque + backEMFTorque;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setPower(double position) {
        assert position >= 0 && position <= 1;
    }

    public void update(double dt) {
        double torque = getServoTorque();
        double angularAcceleration = torque / momentOfInertia;
        rotationalVelocity += angularAcceleration * dt;
        realPosition += rotationalVelocity * dt;
    }
}
