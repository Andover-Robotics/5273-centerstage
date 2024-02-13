package com.andoverrobotics.thunder.simulator.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareMecanumDrive;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.util.DSConfig;
import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.andoverrobotics.thunder.simulator.SimHardwareMap;
import com.andoverrobotics.thunder.simulator.simComponents.SimMotor;

public class SimHardwareMecanumDrive implements HardwareMecanumDrive {
    private final Logger logger;
    private final SimMotor leftFrontMotor;
    private final SimMotor rightFrontMotor;
    private final SimMotor leftBackMotor;
    private final SimMotor rightBackMotor;

    public SimHardwareMecanumDrive(SimHardwareMap simHardwareMap, Logger logger) {
        this.logger = logger;
        this.leftFrontMotor = simHardwareMap.getMotor(DSConfig.DRIVETRAIN_FRONT_LEFT_MOTOR);
        this.rightFrontMotor = simHardwareMap.getMotor(DSConfig.DRIVETRAIN_FRONT_RIGHT_MOTOR);
        this.leftBackMotor = simHardwareMap.getMotor(DSConfig.DRIVETRAIN_BACK_LEFT_MOTOR);
        this.rightBackMotor = simHardwareMap.getMotor(DSConfig.DRIVETRAIN_BACK_RIGHT_MOTOR);
    }

    @Override
    public void setPower(
            double leftFrontPower,
            double rightFrontPower,
            double leftBackPower,
            double rightBackPower) {
        // Implement the logic to set power for each motor
        leftFrontMotor.setPower(leftFrontPower);
        rightFrontMotor.setPower(rightFrontPower);
        leftBackMotor.setPower(leftBackPower);
        rightBackMotor.setPower(rightBackPower);
    }

    @Override
    public double[] getPower() {
        // Implement the logic to get power of each motor
        return new double[] {
            leftFrontMotor.getPower(),
            rightFrontMotor.getPower(),
            leftBackMotor.getPower(),
            rightBackMotor.getPower()
        };
    }

    @Override
    public int[] getCurrentPosition() {
        // Implement the logic to get current position of each motor
        return new int[] {
            leftFrontMotor.getCurrentPosition(),
            rightFrontMotor.getCurrentPosition(),
            leftBackMotor.getCurrentPosition(),
            rightBackMotor.getCurrentPosition()
        };
    }

    @Override
    public Direction[] getDirection() {
        // Implement the logic to get direction of each motor
        return new Direction[] {
            leftFrontMotor.getDirection(),
            rightFrontMotor.getDirection(),
            leftBackMotor.getDirection(),
            rightBackMotor.getDirection()
        };
    }

    @Override
    public void setDirection(
            Direction leftFrontDir,
            Direction rightFrontDir,
            Direction leftBackDir,
            Direction rightBackDir) {
        // Implement the logic to set direction for each motor
        leftFrontMotor.setDirection(leftFrontDir);
        rightFrontMotor.setDirection(rightFrontDir);
        leftBackMotor.setDirection(leftBackDir);
        rightBackMotor.setDirection(rightBackDir);
    }

    @Override
    public void resetEncoders() {
        // Implement the logic to reset encoders for all motors
        leftFrontMotor.resetEncoder();
        rightFrontMotor.resetEncoder();
        leftBackMotor.resetEncoder();
        rightBackMotor.resetEncoder();
    }

    @Override
    public void resetEncoders(
            boolean leftFront, boolean rightFront, boolean leftBack, boolean rightBack) {
        // Implement the logic to reset encoders for selected motors
        if (leftFront) leftFrontMotor.resetEncoder();
        if (rightFront) rightFrontMotor.resetEncoder();
        if (leftBack) leftBackMotor.resetEncoder();
        if (rightBack) rightBackMotor.resetEncoder();
    }
}
