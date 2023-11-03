package org.firstinspires.ftc.teamcode.hardware;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public interface HardwareSlides {
    void setSlidesPosition(int power);
    double getSlidesPosition();
    DcMotorSimple.Direction getDirection();
    void setPower(double power);
    double getPower();
    void setMode(DcMotor.RunMode runMode);
}
