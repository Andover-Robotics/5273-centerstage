package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareClawFlipper;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;

public class RealHardwareClawFlipper implements HardwareClawFlipper {
    private final Servo servo;
    private final Logger logger;
    public RealHardwareClawFlipper(HardwareMap hardwareMap, Logger logger){
        this.logger = logger;
        servo = hardwareMap.get(Servo.class, "flipperServo");
    }
    @Override
    public void setDirection(Direction direction) {
        servo.setDirection(direction == Direction.FORWARD ? Servo.Direction.FORWARD : Servo.Direction.REVERSE);
    }

    @Override
    public void setPosition(double pos) {
        servo.setPosition(pos);
    }

    @Override
    public double getPosition() {
        return servo.getPosition();
    }
}