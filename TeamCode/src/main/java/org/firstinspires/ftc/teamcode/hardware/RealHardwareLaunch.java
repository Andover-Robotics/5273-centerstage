package org.firstinspires.ftc.teamcode.hardware;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareLaunch;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.Logger;

public class RealHardwareLaunch implements HardwareLaunch {
    private final Servo launchServo;
    private final Logger logger;
    public RealHardwareLaunch(HardwareMap hardwareMap, Logger logger) {
        this.logger = logger;
        this.launchServo = hardwareMap.get(Servo.class, "launchServo");
    }

    @Override
    public double getPosition() {
        return launchServo.getPosition();
    }

    @Override
    public void scaleRange(double min, double max) {
        launchServo.scaleRange(min, max);
    }

    @Override
    public Servo.Direction getDirection() {
        return launchServo.getDirection();
    }

    @Override
    public void setDirection(Servo.Direction direction) {
        launchServo.setDirection(direction);
    }

    @Override
    public void setPosition(double position) {
        launchServo.setPosition(position);
    }
}
