package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.hardware.HardwareSlides;
import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class SimHardwareSlides implements HardwareSlides {
    Simulation sim;
    public SimHardwareSlides(Simulation sim) {
        this.sim = sim;
    }

    @Override
    public void setSlidesPosition(int power) {

    }

    @Override
    public double getSlidesPosition() {
        return 0;
    }

    @Override
    public DcMotorSimple.Direction getDirection() {
        return null;
    }

    @Override
    public void setPower(double power) {

    }

    @Override
    public double getPower() {
        return 0;
    }

    @Override
    public void setMode(DcMotor.RunMode runMode) {

    }
}
