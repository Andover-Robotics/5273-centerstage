package org.firstinspires.ftc.teamcode.hardware;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

public interface HardwareSlides {
    int[] getSlidesPositions();
    void setDirections(DcMotorSimple.Direction directionLeft, DcMotorSimple.Direction directionRight);

    void setPowers(double powerLeft, double powerRight);
    double[] getPowers();
    void setModes(DcMotor.RunMode runModeLeft, DcMotor.RunMode runModeRight);
}
