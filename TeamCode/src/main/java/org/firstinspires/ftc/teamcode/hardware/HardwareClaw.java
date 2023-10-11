package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.Servo;

public interface HardwareClaw {
    public double getPosition();
    public void scaleRange(double min, double max);
    public Servo.Direction getDirection();
    public void setDirection(Servo.Direction direction);
    public void setPosition(double position);

}