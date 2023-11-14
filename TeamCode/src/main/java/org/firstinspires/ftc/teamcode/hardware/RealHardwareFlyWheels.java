package org.firstinspires.ftc.teamcode.hardware;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RealHardwareFlyWheels implements HardwareFlyWheels {
    private final DcMotor motor;
    public RealHardwareFlyWheels(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, "flyWheelMotor");
    }

    @Override
    public void setDirection(DcMotorSimple.Direction direction) {
        motor.setDirection(direction);
    }

    @Override
    public void setPower(double power) {
        motor.setPower(power);
    }
}
