package org.firstinspires.ftc.teamcode.simulator;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareClaw;

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
    public Direction getDirection() {
        return null;//TODO: Implement
    }

    @Override
    public void setDirection(Direction direction) {
        //TODO: Implement
    }

    @Override
    public void setPosition(double position) {
        //TODO: Implement
    }
}
