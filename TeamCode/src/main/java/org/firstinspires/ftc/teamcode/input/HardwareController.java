package org.firstinspires.ftc.teamcode.input;

import com.qualcomm.robotcore.hardware.Gamepad;

public class HardwareController {
    public final double DEADZONE = 0.05;

    final Gamepad gamepad;
    public HardwareController(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public double getLeftStickX() {
        float x = gamepad.left_stick_x;
        if (Math.abs(x) < DEADZONE) {
            return 0;
        }
        return x;
    }

    public double getLeftStickY() {
        float y = gamepad.left_stick_y;
        if (Math.abs(y) < DEADZONE) {
            return 0;
        }
        return y;
    }

    public double getRightStickX() {
        float x = gamepad.right_stick_x;
        if (Math.abs(x) < DEADZONE) {
            return 0;
        }
        return x;
    }

    public double getRightStickY() {
        float y = gamepad.right_stick_y;
        if (Math.abs(y) < DEADZONE) {
            return 0;
        }
        return y;
    }

    public double getLeftTrigger() {
        return gamepad.left_trigger;
    }

    public double getRightTrigger() {
        return gamepad.right_trigger;
    }

    public boolean getA() {
        return gamepad.a;
    }

    public boolean getB() {
        return gamepad.b;
    }

    public boolean getX() {
        return gamepad.x;
    }

    public boolean getY() {
        return gamepad.y;
    }

    public boolean getDpadUp() {
        return gamepad.dpad_up;
    }

    public boolean getDpadDown() {
        return gamepad.dpad_down;
    }

    public boolean getDpadLeft() {
        return gamepad.dpad_left;
    }

    public boolean getDpadRight() {
        return gamepad.dpad_right;
    }

    public boolean getLeftBumper() {
        return gamepad.left_bumper;
    }

    public boolean getRightBumper() {
        return gamepad.right_bumper;
    }

    public boolean getLeftStickButton() {
        return gamepad.left_stick_button;
    }

    public boolean getRightStickButton() {
        return gamepad.right_stick_button;
    }
}
