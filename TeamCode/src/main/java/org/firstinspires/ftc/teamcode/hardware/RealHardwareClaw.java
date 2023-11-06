package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RealHardwareClaw implements HardwareClaw{
    private final Servo clawServo;
    private final Telemetry telemetry;
    public RealHardwareClaw(HardwareMap hardwareMap, Telemetry telemetry){
        this.clawServo = hardwareMap.get(Servo.class, "clawServo");
        this.telemetry = telemetry;
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
