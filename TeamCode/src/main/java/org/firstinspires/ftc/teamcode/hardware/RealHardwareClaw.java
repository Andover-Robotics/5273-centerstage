package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareClaw;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.Logger;

public class RealHardwareClaw implements HardwareClaw {
    private final Servo clawServo;
    private final Logger logger;
    public RealHardwareClaw(HardwareMap hardwareMap, Logger logger){
        this.clawServo = hardwareMap.get(Servo.class, "clawServo");
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
