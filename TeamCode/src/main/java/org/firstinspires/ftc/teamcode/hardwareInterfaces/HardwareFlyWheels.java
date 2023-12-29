package org.firstinspires.ftc.teamcode.hardwareInterfaces;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public interface HardwareFlyWheels {
    void setDirection(DcMotorSimple.Direction direction);
    void setPower(double power);
}

