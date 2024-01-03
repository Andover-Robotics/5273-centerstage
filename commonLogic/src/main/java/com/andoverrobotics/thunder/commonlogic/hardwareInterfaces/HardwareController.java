package com.andoverrobotics.thunder.commonlogic.hardwareInterfaces;

public interface HardwareController {
    double getLeftStickX();

    double getLeftStickY();

    double getRightStickX();

    double getRightStickY();

    double getLeftTrigger();

    double getRightTrigger();

    boolean getA();

    boolean getB();

    boolean getX();

    boolean getY();

    boolean getDpadUp();

    boolean getDpadDown();

    boolean getDpadLeft();

    boolean getDpadRight();

    boolean getLeftBumper();

    boolean getRightBumper();

    boolean getLeftStickButton();

    boolean getRightStickButton();
}
