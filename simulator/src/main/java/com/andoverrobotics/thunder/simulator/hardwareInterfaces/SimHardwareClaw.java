package com.andoverrobotics.thunder.simulator.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareClaw;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.util.DSConfig;
import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.andoverrobotics.thunder.simulator.SimHardwareMap;
import com.andoverrobotics.thunder.simulator.simComponents.SimServo;

public class SimHardwareClaw implements HardwareClaw {
    private final Logger logger;
    private final SimServo servo;

    public SimHardwareClaw(SimHardwareMap simulation, Logger logger) {
        this.logger = logger;
        this.servo = simulation.getServo(DSConfig.CLAW_SERVO);
    }

    @Override
    public double getPosition() {
        return servo.getPosition();
    }

    @Override
    public void scaleRange(double min, double max) {
        servo.scaleRange(min, max);
    }

    @Override
    public Direction getDirection() {
        return servo.getDirection();
    }

    @Override
    public void setDirection(Direction direction) {
        servo.setDirection(direction);
    }

    @Override
    public void setPosition(double position) {
        servo.setPosition(position);
    }
}
