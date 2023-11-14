package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class SimHardwareClawFlipper implements HardwareClawFlipper{
    public SimHardwareClawFlipper(Simulation sim){}

    @Override
    public void setDirection(Servo.Direction direction) {

    }

    @Override
    public void setPosition(double pos) {

    }

    @Override
    public double getPosition() {
        return 0;
    }
}
