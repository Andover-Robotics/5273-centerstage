package com.andoverrobotics.thunder.commonlogic.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.util.MarkerPos;

public interface HardwareCamera {
    MarkerPos getMarkerPos();
    boolean isReady();
}
