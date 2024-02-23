package com.andoverrobotics.thunder.simulator.simComponents;

import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.andoverrobotics.thunder.simulator.Simulation;

// https://www.gobilda.com/2000-series-dual-mode-servo-25-2-torque/
// control hub/driver station sends 6v to servos, using the no load speed
public class SimServo {
    private static final double MAX_SPEED = 50; // RPM
    private static final double MAX_TORQUE = 300 * Simulation.OUNCE_INCH_TO_NEWTON_METER; // N*m
    public double realPosition = 0.5;
    private double softwarePosition = 0;
    private double min = 0;
    private double max = 1;
    private Direction direction = Direction.FORWARD;
    private double momentOfInertia; // kg*m^2
    private double rotationalVelocity = 0; // RPM

    public static double lbmm2tokgm2(double lbmm2) {
        return lbmm2 * 4.5359237 * Math.pow(10, -7);
    }

    // construct with the moment of inertia of what is attached to the servo
    // moment of inertia is given in kg*m^2
    public SimServo(double momentOfInertia) {
        this.momentOfInertia = momentOfInertia;
    }

    private double getServoTorque() {
        // max torque at stall
        // 0 torque at max speed
        // linear in between
        return MAX_TORQUE * (1 - rotationalVelocity / MAX_SPEED);
    }

    public double getPosition() {
        double unfliped = (realPosition - min) / (max - min);
        return direction == Direction.FORWARD ? unfliped : 1 - unfliped;
    }

    public void scaleRange(double min, double max) {
        assert min < max;
        assert min >= 0 && max <= 1;
        this.min = min;
        this.max = max;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setPosition(double position) {
        assert position >= 0 && position <= 1;
        softwarePosition =
                (direction == Direction.FORWARD ? position : 1 - position) * (max - min) + min;
    }

    public void update(double dt) {
        double diff = realPosition - softwarePosition;
        // only use getServoTorque if the servo is accelerating in the same direction as the torque
        double torque = diff * rotationalVelocity > 0 ? getServoTorque() : MAX_TORQUE;
        // set the correct sign for the torque
        torque *= diff > 0 ? 1 : -1;
        double angularAcceleration = torque / momentOfInertia;
        double angularVelocity = rotationalVelocity + angularAcceleration * dt;
        realPosition += angularVelocity * dt;
        double newDiff = realPosition - softwarePosition;
        // if the position is close enough to the software position and the servo speed is low
        // enough, set the position to the software position and the speed to 0
        if (Math.abs(newDiff) < 0.001 && Math.abs(angularVelocity) < 0.001) {
            realPosition = softwarePosition;
            rotationalVelocity = 0;
        }
    }
}
