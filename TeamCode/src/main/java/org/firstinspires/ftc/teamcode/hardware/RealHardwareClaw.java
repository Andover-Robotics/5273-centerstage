package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.Servo;

public class RealHardwareClaw implements HardwareClaw{
    private final Servo clawServo;
    public RealHardwareClaw() {
        this.clawServo = hardwareMap.get(Servo.class, "clawServo");
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
    public Servo.Direction getDirection() {
        return clawServo.getDirection();
    }

    @Override
    public void setDirection(Servo.Direction direction) {
        clawServo.setDirection(direction);
    }

    @Override
    public void setPosition(double position) {
        clawServo.setPosition(position);
    }
}
