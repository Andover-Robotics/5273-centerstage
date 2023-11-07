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
    public void forword(){
        flyWheels.setPowers(1000000000, 1000000000);
    }
    public void off(){
//        flyWheels.setPowers(0, 0);
    }
    public void reverse(){
        flyWheels.setPowers(-1000000000, -1000000000);
    }

    public void executeIntent(Intent.IntakeIntent intake) {
        switch (intake) {
            case FORWARD:
                forword();
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