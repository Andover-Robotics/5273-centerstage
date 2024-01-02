package com.example.commonlogic.subsystems;

import com.example.commonlogic.hardwareInterfaces.HardwareFlyWheels;
import com.example.commonlogic.input.Intent;

public class Intake {
    private final HardwareFlyWheels flyWheels;
    public Intake(HardwareFlyWheels flyWheels) {
        this.flyWheels = flyWheels;
        flyWheels.setDirection(HardwareFlyWheels.Direction.REVERSE);
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