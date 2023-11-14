package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RealHardwareClawFlipper implements HardwareClawFlipper{
    private final Servo servo;
    public RealHardwareClawFlipper(HardwareMap hardwareMap){
        servo = hardwareMap.get(Servo.class, "flipperServo");
    }
    @Override
    public void setDirection(Servo.Direction direction) {
        servo.setDirection(direction);
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