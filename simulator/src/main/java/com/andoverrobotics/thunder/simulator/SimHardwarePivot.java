package com.andoverrobotics.thunder.simulator;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.CombinedLogger;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwarePivot;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.util.DSConfig;
import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.andoverrobotics.thunder.simulator.simComponents.SimMotor;

public class SimHardwarePivot implements HardwarePivot {
    private final Logger logger;
    private final SimMotor pivotMotor;

    public SimHardwarePivot(SimHardwareMap simHardwareMap, Logger logger) {
        this.logger = logger;
        this.pivotMotor = simHardwareMap.getMotor(DSConfig.PIVOT_MOTOR);
    }

    @Override
    public int getPosition() {
        return pivotMotor.getCurrentPosition();
    }

    @Override
    public void setDirection(Direction direction) {
        pivotMotor.setDirection(direction);
    }

    @Override
    public void setPower(double power) {
        pivotMotor.setPower(power);
    }

    @Override
    public void resetEncoders() {
        pivotMotor.resetEncoder();
    }
}
