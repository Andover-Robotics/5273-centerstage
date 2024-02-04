package com.andoverrobotics.thunder.simulator.simComponents;

import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.andoverrobotics.thunder.simulator.Simulation;

//https://www.gobilda.com/2000-series-dual-mode-servo-25-2-torque/
//control hub/driver station sends 6v to servos, using the no load speed
public class SimServo {
    private static final double MAX_SPEED = 50; //RPM
    private static final double MAX_TORQUE = 300 * Simulation.INCH_OUNCE_TO_NEWTON_METER; //N*m
    public double realPosition = 0.5;
    private double softwarePosition = 0;
    private double min = 0;
    private double max = 1;
    private Direction direction = Direction.FORWARD;
    private double momentOfInertia; //kg*m^2
    private double rotationalVelocity = 0; //RPM
    //construct with the moment of inertia of what is attached to the servo
    public SimServo(double momentOfInertia) {
        this.momentOfInertia = momentOfInertia;
    }
    public double getServoTorque(){
        //max torque at stall
        //0 torque at max speed
        //linear in between
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
        softwarePosition = (direction == Direction.FORWARD ? position : 1 - position) * (max - min) + min;
    }

    public void update(double dt){
        double diff = realPosition - softwarePosition;
        double change = dt * MAX_SPEED / 60;
        //if it is close enough, just set it to the real position
        if (Math.abs(diff) < change){
            realPosition = softwarePosition;
        } else {
            realPosition -= change * Math.signum(diff);
        }

    }
}
