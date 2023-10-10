package org.firstinspires.ftc.teamcode.hardware;

import org.firstinspires.ftc.teamcode.hardware.HardwareMechanumDrive;
import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class SimHardwareMechanumDrive implements HardwareMechanumDrive {
    Simulation sim;
    public SimHardwareMechanumDrive(Simulation sim) {
        this.sim = sim;
    }

    @Override
    public void setDrivePower(double leftFront, double rightFront, double leftBack, double rightBack) {

    }
}
