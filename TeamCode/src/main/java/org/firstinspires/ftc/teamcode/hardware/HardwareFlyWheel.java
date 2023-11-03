package org.firstinspires.ftc.teamcode.hardware;
import com.qualcomm.robotcore.hardware.Servo;

public interface HardwareFlyWheel {
    double getPosition();
    void scaleRange(double min, double max);
    Servo.Direction getDirection();
    void setDirection(Servo.Direction direction);
    void setPosition(double position);
}

