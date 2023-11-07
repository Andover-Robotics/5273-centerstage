package org.firstinspires.ftc.teamcode.hardware;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public interface HardwareFlyWheels {
    void setDirections(DcMotorSimple.Direction directionLeft, DcMotorSimple.Direction directionRight);
    void setPowers(double powerLeft, double powerRight);
}

