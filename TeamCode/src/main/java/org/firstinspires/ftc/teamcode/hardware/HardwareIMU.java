package org.firstinspires.ftc.teamcode.hardware;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
public interface HardwareIMU {
    Orientation getAnglularOrientation();
}