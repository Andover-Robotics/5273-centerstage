package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.hardware.HardwareMechanumDrive;

public class Movement {
    public float x;
    public float y;
    public float heading;
    private HardwareMechanumDrive hardwareMechanumDrive;
    public Movement(float x, float y, float heading, HardwareMechanumDrive hardwareMechanumDrive) {
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.hardwareMechanumDrive = hardwareMechanumDrive;
    }

    public void pathTo(float x, float y, float heading) {
        //TODO: Implement
    }


}
