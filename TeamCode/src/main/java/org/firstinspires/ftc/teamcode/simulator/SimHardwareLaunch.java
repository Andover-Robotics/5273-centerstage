package org.firstinspires.ftc.teamcode.simulator;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareLaunch;

public class SimHardwareLaunch implements HardwareLaunch {
    private final Simulation sim;

    public SimHardwareLaunch(Simulation sim)
    {
        this.sim = sim;
    }

    @Override
    public double getPosition()
    {
        return 0; //TODO: Implement
    }

    @Override
    public void scaleRange(double min, double max)
    {
        //TODO: Implement
    }

    @Override
    public Direction getDirection()
    {
        return null;//TODO: Implement
    }

    @Override
    public void setDirection(Direction direction)
    {
        //TODO: Implement
    }

    @Override
    public void setPosition(double position)
    {
        //TODO: Implement
    }
}
