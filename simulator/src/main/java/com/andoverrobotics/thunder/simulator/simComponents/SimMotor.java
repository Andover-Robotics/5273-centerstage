package com.andoverrobotics.thunder.simulator.simComponents;

import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.andoverrobotics.thunder.simulator.Simulation;

// https://www.gobilda.com/5202-series-yellow-jacket-planetary-gear-motor-13-7-1-ratio-24mm-length-6mm-d-shaft-435-rpm-36mm-gearbox-3-3-5v-encoder/
public class SimMotor {
    private Direction direction = Direction.FORWARD; // forward is positive is counterclockwise
    private double power = 0;
    public double position = 0; // rotations
    public double encoderPosition = 0; // rotations
    private static final double MAX_SPEED = 435; // RPM
    private static final double STALL_TORQUE = 260 * Simulation.OUNCE_INCH_TO_NEWTON_METER; // N*m
    private static final double TICKS_PER_ROTATION =
            ((((1 + (46.0 / 17))) * (1 + (46.0 / 17))) * 28);

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void resetEncoder() {
        encoderPosition = 0;
    }

    public int getCurrentPosition() {
        return (int) (encoderPosition * TICKS_PER_ROTATION);
    }

    public double getPower() {
        return power;
    }

    public Direction getDirection() {
        return direction;
    }

    public double getMotorTorque() {
        // MAX_TORQUE * power is the motor torque
        // back EMF torque is -MAX_TORQUE * (rotationalVelocity / MAX_SPEED)
        // the net torque is the sum of the two
        double motorTorque = STALL_TORQUE * power;
        double backEMFTorque = -STALL_TORQUE * (position / MAX_SPEED);
        return motorTorque + backEMFTorque;
    }

    public void update(double dt) {
        double torque = getMotorTorque();
        double angularAcceleration = torque / 0.0001; // moment of inertia
        position += angularAcceleration * dt;
        encoderPosition += angularAcceleration * dt;
    }
    // TODO: figure out how to manage the external forces in the update logic in an elegant way
}
