package com.andoverrobotics.thunder.simulator.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareFlyWheels;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.util.DSConfig;
import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.andoverrobotics.thunder.simulator.SimHardwareMap;
import com.andoverrobotics.thunder.simulator.simComponents.SimCRServo;

public class SimHardwareFlyWheels implements HardwareFlyWheels {
    private final Logger logger;
    private final SimCRServo leftFlyWheel;
    private final SimCRServo rightFlyWheel;

    public SimHardwareFlyWheels(SimHardwareMap simHardwareMap, Logger logger) {
        this.logger = logger;
        this.leftFlyWheel = simHardwareMap.getCRServo(DSConfig.FLYWHEEL_LEFT_CR_SERVO);
        this.rightFlyWheel = simHardwareMap.getCRServo(DSConfig.FLYWHEEL_RIGHT_CR_SERVO);
    }

    @Override
    public void setDirection(Direction direction) {
        // Implement the logic to set the direction of the flywheels
        // (Assuming that SimCRServo class has a setDirection method)
        if (direction == Direction.FORWARD) {
            leftFlyWheel.setDirection(Direction.FORWARD);
            rightFlyWheel.setDirection(Direction.REVERSE);
        } else {
            leftFlyWheel.setDirection(Direction.REVERSE);
            rightFlyWheel.setDirection(Direction.FORWARD);
        }
    }

    @Override
    public void setPower(double power) {
        // Implement the logic to set the power of the flywheels
        leftFlyWheel.setPower(power);
        rightFlyWheel.setPower(power);
    }
}
