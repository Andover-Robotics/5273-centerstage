package org.firstinspires.ftc.teamcode.hardware;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareFlyWheels;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.Logger;

public class RealHardwareFlyWheels implements HardwareFlyWheels {
    private final CRServo leftFlyWheel;
    private final CRServo rightFlyWheel;
    private final Logger logger;
    public RealHardwareFlyWheels(HardwareMap hardwareMap, Logger logger) {
        this.logger = logger;
        leftFlyWheel = hardwareMap.get(CRServo.class, "flywheelLeft");
        rightFlyWheel = hardwareMap.get(CRServo.class, "flywheelRight");
        leftFlyWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFlyWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void setDirection(DcMotorSimple.Direction direction) {
        leftFlyWheel.setDirection(direction);
        rightFlyWheel.setDirection(direction == DcMotorSimple.Direction.FORWARD ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void setPower(double power) {
        leftFlyWheel.setPower(power);
        rightFlyWheel.setPower(power);
    }
}
