package org.firstinspires.ftc.teamcode.simulator;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareClaw;
import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class SimHardwareClaw implements HardwareClaw {
    private final Simulation sim;

    public SimHardwareClaw(Simulation sim) {
        this.sim = sim;
    }


    @Override
    public double getPosition() {
        return 0; //TODO: Implement
    }

    @Override
    public void scaleRange(double min, double max) {
        //TODO: Implement
    }

    @Override
    public Servo.Direction getDirection() {
        return null;//TODO: Implement
    }

    @Override
    public void setDirection(Servo.Direction direction) {
        //TODO: Implement
    }

    @Override
    public void setPosition(double position) {
        //TODO: Implement
    }
}
