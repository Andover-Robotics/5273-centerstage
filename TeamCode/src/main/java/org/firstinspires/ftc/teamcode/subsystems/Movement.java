package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.hardware.HardwareMechanumDrive;

public class Movement {
    public float x;
    public float y;
    public float heading;
    private final HardwareMechanumDrive hardwareMechanumDrive;
    public Movement(float x, float y, float heading, HardwareMechanumDrive hardwareMechanumDrive) {
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.hardwareMechanumDrive = hardwareMechanumDrive;
        //guessed right reverse, may be wrong
        hardwareMechanumDrive.setDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE);
    }

    public void moveTo(float x, float y, float heading) {
        //TODO: Implement
    }
    public void moveForward(int dist){
        hardwareMechanumDrive.resetEncoders();
        hardwareMechanumDrive.setTargetPosition(dist,dist,dist,dist);
        //TODO: update position according to heading
    }
}