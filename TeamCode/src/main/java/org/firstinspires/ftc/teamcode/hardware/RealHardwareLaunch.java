package org.firstinspires.ftc.teamcode.hardware;
import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareLaunch;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;

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
    public Direction getDirection() {
        return launchServo.getDirection() == Servo.Direction.FORWARD ? Direction.FORWARD : Direction.REVERSE;
    }

    @Override
    public void setDirection(Direction direction) {
        launchServo.setDirection(direction == Direction.FORWARD ? Servo.Direction.FORWARD : Servo.Direction.REVERSE);
    }

    @Override
    public void setPosition(double position) {
        launchServo.setPosition(position);
    }
}
