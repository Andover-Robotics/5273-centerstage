package com.andoverrobotics.thunder.commonlogic.subsystems;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareCamera;
import com.andoverrobotics.thunder.commonlogic.util.MarkerPos;

public class Camera {
    private final HardwareCamera camera;
    public Camera(HardwareCamera camera){
        this.camera = camera;
    }
    public MarkerPos getMarkerPos(){
        return camera.getMarkerPos();
    }
}
