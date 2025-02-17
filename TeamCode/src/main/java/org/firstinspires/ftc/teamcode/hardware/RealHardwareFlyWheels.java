package org.firstinspires.ftc.teamcode.hardware;
import com.andoverrobotics.thunder.commonlogic.util.DSConfig;
import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareFlyWheels;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;

public class RealHardwareFlyWheels implements HardwareFlyWheels {
    private final CRServo leftFlyWheel;
    private final CRServo rightFlyWheel;
    private final Logger logger;
    public RealHardwareFlyWheels(HardwareMap hardwareMap, Logger logger) {
        this.logger = logger;
        leftFlyWheel = hardwareMap.get(CRServo.class, DSConfig.FLYWHEEL_LEFT_CR_SERVO);
        rightFlyWheel = hardwareMap.get(CRServo.class, DSConfig.FLYWHEEL_RIGHT_CR_SERVO);
        leftFlyWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFlyWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void setDirection(Direction direction) {
        leftFlyWheel.setDirection(direction == Direction.FORWARD ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
        rightFlyWheel.setDirection(direction == Direction.FORWARD ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void setPower(double power) {
        leftFlyWheel.setPower(power);
        rightFlyWheel.setPower(power);
    }
}
