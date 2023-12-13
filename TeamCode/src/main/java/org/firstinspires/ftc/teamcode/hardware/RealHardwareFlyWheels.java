package org.firstinspires.ftc.teamcode.hardware;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RealHardwareFlyWheels implements HardwareFlyWheels {
    private final CRServo leftFlyWheel;
    private final CRServo rightFlyWheel;
    public RealHardwareFlyWheels(HardwareMap hardwareMap) {
        leftFlyWheel = hardwareMap.get(CRServo.class, "flywheelLeft");
        rightFlyWheel = hardwareMap.get(CRServo.class, "flywheelRight");
        leftFlyWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFlyWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void setDirection(DcMotorSimple.Direction direction) {

    }

    @Override
    public void setPower(double power) {
        leftFlyWheel.setPower(power);
        rightFlyWheel.setPower(power);
    }
}
