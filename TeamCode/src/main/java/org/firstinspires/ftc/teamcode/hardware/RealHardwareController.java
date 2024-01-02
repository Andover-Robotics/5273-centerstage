package org.firstinspires.ftc.teamcode.hardware;

import com.example.commonlogic.hardwareInterfaces.HardwareController;
import com.qualcomm.robotcore.hardware.Gamepad;

public class RealHardwareController implements HardwareController {
    public final double DEADZONE = 0.05;

    final Gamepad gamepad;
    public RealHardwareController(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    @Override
    public double getLeftStickX() {
        float x = gamepad.left_stick_x;
        if (Math.abs(x) < DEADZONE) {
            return 0;
        }
        return x;
    }

    @Override
    public double getLeftStickY() {
        float y = gamepad.left_stick_y;
        if (Math.abs(y) < DEADZONE) {
            return 0;
        }
        return y;
    }

    @Override
    public double getRightStickX() {
        float x = gamepad.right_stick_x;
        if (Math.abs(x) < DEADZONE) {
            return 0;
        }
        return x;
    }

    @Override
    public double getRightStickY() {
        float y = gamepad.right_stick_y;
        if (Math.abs(y) < DEADZONE) {
            return 0;
        }
        return y;
    }

    @Override
    public double getLeftTrigger() {
        return gamepad.left_trigger;
    }

    @Override
    public double getRightTrigger() {
        return gamepad.right_trigger;
    }

    @Override
    public boolean getA() {
        return gamepad.a;
    }

    @Override
    public boolean getB() {
        return gamepad.b;
    }

    @Override
    public boolean getX() {
        return gamepad.x;
    }

    @Override
    public boolean getY() {
        return gamepad.y;
    }

    @Override
    public boolean getDpadUp() {
        return gamepad.dpad_up;
    }

    @Override
    public boolean getDpadDown() {
        return gamepad.dpad_down;
    }

    @Override
    public boolean getDpadLeft() {
        return gamepad.dpad_left;
    }

    @Override
    public boolean getDpadRight() {
        return gamepad.dpad_right;
    }

    @Override
    public boolean getLeftBumper() {
        return gamepad.left_bumper;
    }

    @Override
    public boolean getRightBumper() {
        return gamepad.right_bumper;
    }

    @Override
    public boolean getLeftStickButton() {
        return gamepad.left_stick_button;
    }

    @Override
    public boolean getRightStickButton() {
        return gamepad.right_stick_button;
    }
}
