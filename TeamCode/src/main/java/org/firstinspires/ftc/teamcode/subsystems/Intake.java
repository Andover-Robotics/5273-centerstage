package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.hardware.HardwareFlyWheels;
import org.firstinspires.ftc.teamcode.input.Intent;

public class Intake {
    private final HardwareFlyWheels flyWheels;
    public Intake(HardwareFlyWheels flyWheels) {
        this.flyWheels = flyWheels;
        flyWheels.setDirections(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE);
    }
    public void forward(){
        flyWheels.setPowers(1, 1);
    }
    public void off(){
        flyWheels.setPowers(0, 0);
    }
    public void reverse(){
        flyWheels.setPowers(-1, -1);
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