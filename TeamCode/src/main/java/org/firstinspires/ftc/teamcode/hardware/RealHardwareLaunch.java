package org.firstinspires.ftc.teamcode.hardware;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class RealHardwareLaunch implements HardwareLaunch {
    private final Servo launchServo;
    public RealHardwareLaunch(HardwareMap hardwareMap) {
        this.launchServo = hardwareMap.get(Servo.class, "launchServo");
    }

    @Override
    public double getPosition() {
        return launchServo.getPosition();
    }

    @Override
    public void scaleRange(double min, double max) {
        launchServo.scaleRange(min, max);
    }

    @Override
    public Servo.Direction getDirection() {
        return launchServo.getDirection();
    }

    @Override
    public void setDirection(Servo.Direction direction) {
        launchServo.setDirection(direction);
    }

    @Override
    public void setPosition(double position) {
        launchServo.setPosition(position);
    }
}
