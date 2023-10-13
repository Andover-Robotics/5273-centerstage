package org.firstinspires.ftc.teamcode.subsystems;

public class Camera {
    public enum MarkPosition {
        LEFT,
        CENTER,
        RIGHT
    }

    public enum PixelColor {
        PURPLE,
        YELLOW,
        WHITE
    }
    //TODO: create a hardware wrapper for the camera and pass it in here
    public Camera(){

    }

    public MarkPosition findMark(){
        return null;
    }

    //NOTE: size will always be 7x11
    public MarkPosition[][] scanBoard(){
        return null;
    }

    //TODO: figure out how the results will be returned, positions will be relative to different things
    public void getApriltagPosition(){

    }
}
