package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareFlyWheels;
import org.firstinspires.ftc.teamcode.input.Intent;

public class Intake {
    private final HardwareFlyWheels flyWheels;
    public Intake(HardwareFlyWheels flyWheels) {
        this.flyWheels = flyWheels;
        flyWheels.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void forward(){
        flyWheels.setPower(1);
    }
    public void off(){
        flyWheels.setPower(0);
    }
    public void reverse(){
        flyWheels.setPower(-1);
    }

    public void executeIntent(Intent.IntakeIntent intake) {
        switch (intake) {
            case FORWARD:
                forward();
                break;
            case STOP:
                off();
                break;
            case BACKWARD:
                reverse();
                break;
        }
    }
}