package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.Servo;

public interface HardwareClawFlipper{
    void setDirection(Servo.Direction direction);
    void setPosition(double pos);
    double getPosition();
}
