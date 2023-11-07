package org.firstinspires.ftc.teamcode.hardware;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RealHardwareFlyWheels implements HardwareFlyWheels {
    private final CRServo servoLeft;
    private final CRServo servoRight;
    public RealHardwareFlyWheels(HardwareMap hardwareMap) {
        servoLeft = hardwareMap.get(CRServo.class, "flyWheelLeft");
        servoRight = hardwareMap.get(CRServo.class, "flyWheelRight");
    }

    @Override
    public void setDirections(DcMotorSimple.Direction directionLeft, DcMotorSimple.Direction directionRight) {
        servoLeft.setDirection(directionLeft);
        servoRight.setDirection(directionRight);
    }

    @Override
    public void setPowers(double powerLeft, double powerRight) {
        servoLeft.setPower(powerLeft);
        servoRight.setPower(powerRight);
    }
}
