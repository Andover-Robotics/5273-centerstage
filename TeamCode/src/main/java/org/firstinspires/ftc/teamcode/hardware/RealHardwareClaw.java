package org.firstinspires.ftc.teamcode.hardware;

import com.andoverrobotics.thunder.commonlogic.util.DSConfig;
import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareClaw;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;

public class RealHardwareClaw implements HardwareClaw {
    private final Servo clawServo;
    private final Logger logger;
    public RealHardwareClaw(HardwareMap hardwareMap, Logger logger){
        this.clawServo = hardwareMap.get(Servo.class, DSConfig.CLAW_SERVO);
        this.logger = logger;
    }

    @Override
    public double getPosition() {
        return clawServo.getPosition();
    }

    @Override
    public void scaleRange(double min, double max) {
        clawServo.scaleRange(min, max);
    }

    @Override
    public Direction getDirection() {
        return clawServo.getDirection() == Servo.Direction.FORWARD ? Direction.FORWARD : Direction.REVERSE;
    }

    @Override
    public void setDirection(Direction direction) {
        clawServo.setDirection(direction == Direction.FORWARD ? Servo.Direction.FORWARD : Servo.Direction.REVERSE);
    }

    @Override
    public void setPosition(double position) {
        clawServo.setPosition(position);
    }
}
