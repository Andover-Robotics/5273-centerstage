package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareClawFlipper;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.Logger;

public class RealHardwareClawFlipper implements HardwareClawFlipper {
    private final Servo servo;
    private final Logger logger;
    public RealHardwareClawFlipper(HardwareMap hardwareMap, Logger logger){
        this.logger = logger;
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