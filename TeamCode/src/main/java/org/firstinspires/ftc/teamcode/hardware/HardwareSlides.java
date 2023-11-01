package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public interface HardwareSlides {
    void setSlidesPosition(int power);
    double getSlidesPosition();
    DcMotorSimple.Direction getDirection();
    void setPower(double power);
    double getPower();

}
