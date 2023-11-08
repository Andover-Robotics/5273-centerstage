package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RealHardwareClawFlipper implements HardwareClawFlipper{
    private final Servo servo;
    public RealHardwareClawFlipper(HardwareMap hardwareMap){
        servo = hardwareMap.get(Servo.class, "clawFlipper");
    }
    public void setDirection(Servo.Direction direction) {
        servo.setDirection(direction);
    }
    public void setPosition(double pos){
        servo.setPosition(pos);
    }
    public double getPosition(){
        return servo.getPosition();
    }
}
