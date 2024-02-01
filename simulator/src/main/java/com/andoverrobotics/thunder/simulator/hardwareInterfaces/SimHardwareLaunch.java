package com.andoverrobotics.thunder.simulator.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareLaunch;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.util.DSConfig;
import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.andoverrobotics.thunder.simulator.SimHardwareMap;
import com.andoverrobotics.thunder.simulator.Simulation;
import com.andoverrobotics.thunder.simulator.simComponents.SimServo;

public class SimHardwareLaunch implements HardwareLaunch {
    private final Logger logger;
    private final SimServo launchServo;

    public SimHardwareLaunch(SimHardwareMap simHardwareMap, Logger logger) {
        this.logger = logger;
        this.launchServo = simHardwareMap.getServo(DSConfig.LAUNCH_SERVO);
    }

    @Override
    public double getPosition() {
        // Implement the logic to get the position of the launch servo
        // Example: return launchServo.getPosition();
        return 0.0;
    }

    @Override
    public void scaleRange(double min, double max) {
        // Implement the logic to scale the range of the launch servo
        launchServo.scaleRange(min, max);
    }

    @Override
    public Direction getDirection() {
        // Implement the logic to get the direction of the launch servo
        // (Assuming that SimServo class has a getDirection method)
        return launchServo.getDirection();
    }

    @Override
    public void setDirection(Direction direction) {
        // Implement the logic to set the direction of the launch servo
        // (Assuming that SimServo class has a setDirection method)
        launchServo.setDirection(direction);
    }

    @Override
    public void setPosition(double position) {
        // Implement the logic to set the position of the launch servo
        launchServo.setPosition(position);
    }
}

