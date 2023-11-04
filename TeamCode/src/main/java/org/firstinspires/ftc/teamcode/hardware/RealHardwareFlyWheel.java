package org.firstinspires.ftc.teamcode.hardware;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class RealHardwareFlyWheel implements HardwareFlyWheel {
    private final Servo flyWheelServo;
    public RealHardwareFlyWheel(HardwareMap hardwareMap) {
        this.flyWheelServo = hardwareMap.get(Servo.class, "flyWheelServo");
    }

    @Override
    public double getPosition() {
        return flyWheelServo.getPosition();
    }

    @Override
    public void scaleRange(double min, double max) {
        flyWheelServo.scaleRange(min, max);
    }

    @Override
    public Servo.Direction getDirection() {
        return flyWheelServo.getDirection();
    }

    @Override
    public void setDirection(Servo.Direction direction) {
        flyWheelServo.setDirection(direction);
    }

    @Override
    public void setPosition(double position) {
        flyWheelServo.setPosition(position);
    }
}
