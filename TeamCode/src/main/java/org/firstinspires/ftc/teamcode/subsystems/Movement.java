package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.hardware.HardwareMecanumDrive;

public class Movement {
    public float x; // x position
    public float y; // y position
    public float heading; // the direction that the bot is facing in degrees: [0,360)
    private final HardwareMecanumDrive hardwareMecanumDrive;
    public Movement(float x, float y, float heading, HardwareMecanumDrive hardwareMecanumDrive) {
        this.x = x;
        this.y = y;
        this.heading = heading;
        this.hardwareMecanumDrive = hardwareMecanumDrive;
        //TODO: verify that the right side is the side that should be reversed
        hardwareMecanumDrive.setDirection(DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE);
    }
    public void moveTo(float x, float y, float heading) {
        //TODO
    }
    public void moveForward(int dist){
        hardwareMecanumDrive.resetEncoders();
        hardwareMecanumDrive.setPower(1,1,1,1);
        hardwareMecanumDrive.setTargetPosition(dist,dist,dist,dist);
        // TODO: test the math of all position updating according to heading
        x+=dist*Math.cos(Math.toRadians(heading));
        y+=dist*Math.sin(Math.toRadians(heading));
    }
    public void moveBackward(int dist){
        hardwareMecanumDrive.resetEncoders();
        hardwareMecanumDrive.setPower(-1,-1,-1,-1);
        hardwareMecanumDrive.setTargetPosition(dist,dist,dist,dist);
        //TODO: update position according to heading
        x+=dist*Math.cos(Math.toRadians(heading+180));
        y+=dist*Math.sin(Math.toRadians(heading+180));
    }
    public void moveRight(int dist){
        hardwareMecanumDrive.resetEncoders();
        hardwareMecanumDrive.setPower(1,-1,-1,1);
        hardwareMecanumDrive.setTargetPosition(dist,dist,dist,dist);
        x+=dist*Math.cos(Math.toRadians(heading-90));
        y+=dist*Math.sin(Math.toRadians(heading-90));
    }
    public void moveLeft(int dist){
        hardwareMecanumDrive.resetEncoders();
        hardwareMecanumDrive.setPower(-1,1,1,-1);
        hardwareMecanumDrive.setTargetPosition(dist,dist,dist,dist);
        x+=dist*Math.cos(Math.toRadians(heading+90));
        y+=dist*Math.sin(Math.toRadians(heading+90));
    }
    public void stationaryRotateClockwise(int dist){
        hardwareMecanumDrive.resetEncoders();
        hardwareMecanumDrive.setPower(1,-1,1,-1);
        hardwareMecanumDrive.setTargetPosition(dist,dist,dist,dist);
        //TODO: update heading
    }
    public void stationaryRotateCounterclockwise(int dist){
        hardwareMecanumDrive.resetEncoders();
        hardwareMecanumDrive.setPower(-1,1,-1,1);
        hardwareMecanumDrive.setTargetPosition(dist,dist,dist,dist);
        //TODO: update heading
    }
    public void moveForwardRight(int dist){
        hardwareMecanumDrive.resetEncoders(true,false,false,true);
        hardwareMecanumDrive.setPower(1,0,0,1);
        hardwareMecanumDrive.setTargetPosition(dist,0,0,dist);
        x+=dist*Math.cos(Math.toRadians(heading+45));
        y+=dist*Math.sin(Math.toRadians(heading+45));
    }
    public void moveForwardLeft(int dist){
        hardwareMecanumDrive.resetEncoders(false,true,false,true);
        hardwareMecanumDrive.setPower(0,1,1,0);
        hardwareMecanumDrive.setTargetPosition(0,dist,dist,0);
        x+=dist*Math.cos(Math.toRadians(heading+135));
        y+=dist*Math.sin(Math.toRadians(heading+135));
    }
    public void moveBackRight(int dist){
        hardwareMecanumDrive.resetEncoders(false,true,false,true);
        hardwareMecanumDrive.setPower(0,-1,-1,0);
        hardwareMecanumDrive.setTargetPosition(0,dist,dist,0);
        x+=dist*Math.cos(Math.toRadians(heading-45));
        y+=dist*Math.sin(Math.toRadians(heading-45));
    }
    public void moveBackLeft(int dist){
        hardwareMecanumDrive.resetEncoders(true,false,false,true);
        hardwareMecanumDrive.setPower(-1,0,0,-1);
        hardwareMecanumDrive.setTargetPosition(dist,0,0,dist);
        x+=dist*Math.cos(Math.toRadians(heading-135));
        y+=dist*Math.sin(Math.toRadians(heading-135));
    }
}