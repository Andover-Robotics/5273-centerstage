package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.simulator.Simulation;

public class SimHardwareClawFlipper implements HardwareClawFlipper{
    public SimHardwareClawFlipper(Simulation sim){}
    public void setDirection(Servo.Direction direction){}
    public void setPosition(double pos){}
    public double getPosition(){
        return 0;
    }
}
