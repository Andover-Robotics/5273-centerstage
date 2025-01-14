package com.andoverrobotics.thunder.simulator.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareClawFlipper;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.util.DSConfig;
import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.andoverrobotics.thunder.simulator.SimHardwareMap;
import com.andoverrobotics.thunder.simulator.simComponents.SimServo;

public class SimHardwareClawFlipper implements HardwareClawFlipper {
    private final Logger logger;
    private final SimServo clawFlipperServo;

    public SimHardwareClawFlipper(SimHardwareMap simHardwareMap, Logger logger) {
        this.logger = logger;
        this.clawFlipperServo = simHardwareMap.getServo(DSConfig.FLIPPER_SERVO);
    }

    @Override
    public void setDirection(Direction direction) {
        // Implement the logic to set the direction of the claw flipper servo
        // (Assuming that SimServo class has a setDirection method)
        clawFlipperServo.setDirection(direction);
    }

    @Override
    public void setPosition(double pos) {
        // Implement the logic to set the position of the claw flipper servo
        clawFlipperServo.setPosition(pos);
    }

    @Override
    public double getPosition() {
        // Implement the logic to get the position of the claw flipper servo
        return clawFlipperServo.getPosition();
    }
}
