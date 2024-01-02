package org.firstinspires.ftc.teamcode.simulator;

import com.qualcomm.robotcore.hardware.Servo;

import com.example.commonlogic.hardwareInterfaces.HardwareClawFlipper;

public class SimHardwareClawFlipper implements HardwareClawFlipper {
    public SimHardwareClawFlipper(Simulation sim){}

    @Override
    public void setDirection(Direction direction) {

    }

    @Override
    public void setPosition(double pos) {

    }

    @Override
    public double getPosition() {
        return 0;
    }
}
