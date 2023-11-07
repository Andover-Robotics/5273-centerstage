package org.firstinspires.ftc.teamcode.hardware;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class SimHardwareFlyWheels implements HardwareFlyWheels {

    public SimHardwareFlyWheels(Simulation sim) {

    }

    @Override
    public void setDirections(DcMotorSimple.Direction directionLeft, DcMotorSimple.Direction directionRight) {

    }

    @Override
    public void setPowers(double powerLeft, double powerRight) {

    }
}