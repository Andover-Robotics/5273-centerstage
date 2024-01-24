package com.andoverrobotics.thunder.simulator.hardwareInterfaces;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareController;

public class simHardwareGamepad implements HardwareController {
    public float leftStickY;
    public float leftStickX;
    public float rightStickY;
    public float rightStickX;
    public float leftTrigger;
    public float rightTrigger;
    public boolean a;
    public boolean b;
    public boolean x;
    public boolean y;
    public boolean dpadUp;
    public boolean dpadDown;
    public boolean dpadLeft;
    public boolean dpadRight;
    public boolean leftBumper;
    public boolean rightBumper;
    public boolean leftStickButton;
    public boolean rightStickButton;
    @Override
    public double getLeftStickX() {
        return leftStickX;
    }

    @Override
    public double getLeftStickY() {
        return leftStickY;
    }

    @Override
    public double getRightStickX() {
        return rightStickX;
    }

    @Override
    public double getRightStickY() {
        return rightStickY;
    }

    @Override
    public double getLeftTrigger() {
        return leftTrigger;
    }

    @Override
    public double getRightTrigger() {
        return rightTrigger;
    }

    @Override
    public boolean getA() {
        return a;
    }

    @Override
    public boolean getB() {
        return b;
    }

    @Override
    public boolean getX() {
        return x;
    }

    @Override
    public boolean getY() {
        return y;
    }

    @Override
    public boolean getDpadUp() {
        return dpadUp;
    }

    @Override
    public boolean getDpadDown() {
        return dpadDown;
    }

    @Override
    public boolean getDpadLeft() {
        return dpadLeft;
    }

    @Override
    public boolean getDpadRight() {
        return dpadRight;
    }

    @Override
    public boolean getLeftBumper() {
        return leftBumper;
    }

    @Override
    public boolean getRightBumper() {
        return rightBumper;
    }

    @Override
    public boolean getLeftStickButton() {
        return leftStickButton;
    }

    @Override
    public boolean getRightStickButton() {
        return rightStickButton;
    }

    @Override
    public String toString() {
        return "simHardwareGamepad{" +
                "leftStickY=" + leftStickY +
                ", leftStickX=" + leftStickX +
                ", rightStickY=" + rightStickY +
                ", rightStickX=" + rightStickX +
                ", leftTrigger=" + leftTrigger +
                ", rightTrigger=" + rightTrigger +
                ", a=" + a +
                ", b=" + b +
                ", x=" + x +
                ", y=" + y +
                ", dpadUp=" + dpadUp +
                ", dpadDown=" + dpadDown +
                ", dpadLeft=" + dpadLeft +
                ", dpadRight=" + dpadRight +
                ", leftBumper=" + leftBumper +
                ", rightBumper=" + rightBumper +
                ", leftStickButton=" + leftStickButton +
                ", rightStickButton=" + rightStickButton +
                '}';
    }
}
