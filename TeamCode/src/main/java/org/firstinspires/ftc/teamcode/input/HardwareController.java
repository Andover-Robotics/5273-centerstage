package org.firstinspires.ftc.teamcode.input;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

public class HardwareController {
    public static final double DEADZONE = 0.05;

    public static double getLeftStickX1() {
        float x = gamepad1.left_stick_x;
        if (Math.abs(x) < DEADZONE) {
            return 0;
        }
        return x;
    }

    public static double getLeftStickY1() {
        float y = gamepad1.left_stick_y;
        if (Math.abs(y) < DEADZONE) {
            return 0;
        }
        return y;
    }

    public static double getRightStickX1() {
        float x = gamepad1.right_stick_x;
        if (Math.abs(x) < DEADZONE) {
            return 0;
        }
        return x;
    }

    public static double getRightStickY1() {
        float y = gamepad1.right_stick_y;
        if (Math.abs(y) < DEADZONE) {
            return 0;
        }
        return y;
    }

    public static double getLeftTrigger1() {
        return gamepad1.left_trigger;
    }

    public static double getRightTrigger1() {
        return gamepad1.right_trigger;
    }

    public static boolean getA1() {
        return gamepad1.a;
    }

    public static boolean getB1() {
        return gamepad1.b;
    }

    public static boolean getX1() {
        return gamepad1.x;
    }

    public static boolean getY1() {
        return gamepad1.y;
    }

    public static boolean getDpadUp1() {
        return gamepad1.dpad_up;
    }

    public static boolean getDpadDown1() {
        return gamepad1.dpad_down;
    }

    public static boolean getDpadLeft1() {
        return gamepad1.dpad_left;
    }

    public static boolean getDpadRight1() {
        return gamepad1.dpad_right;
    }

    public static boolean getLeftBumper1() {
        return gamepad1.left_bumper;
    }

    public static boolean getRightBumper1() {
        return gamepad1.right_bumper;
    }

    public static boolean getLeftStickButton1() {
        return gamepad1.left_stick_button;
    }

    public static boolean getRightStickButton1() {
        return gamepad1.right_stick_button;
    }

    //////////////////////////////////////////////////////2
    public static double getLeftStickX2() {
        float x = gamepad2.left_stick_x;
        if (Math.abs(x) < DEADZONE) {
            return 0;
        }
        return x;
    }

    public static double getLeftStickY2() {
        float y = gamepad2.left_stick_y;
        if (Math.abs(y) < DEADZONE) {
            return 0;
        }
        return y;
    }

    public static double getRightStickX2() {
        float x = gamepad2.right_stick_x;
        if (Math.abs(x) < DEADZONE) {
            return 0;
        }
        return x;
    }

    public static double getRightStickY2() {
        float y = gamepad2.right_stick_y;
        if (Math.abs(y) < DEADZONE) {
            return 0;
        }
        return y;
    }

    public static double getLeftTrigger2() {
        return gamepad2.left_trigger;
    }

    public static double getRightTrigger2() {
        return gamepad2.right_trigger;
    }

    public static boolean getA2() {
        return gamepad2.a;
    }

    public static boolean getB2() {
        return gamepad2.b;
    }

    public static boolean getX2() {
        return gamepad2.x;
    }

    public static boolean getY2() {
        return gamepad2.y;
    }

    public static boolean getDpadUp2() {
        return gamepad2.dpad_up;
    }

    public static boolean getDpadDown2() {
        return gamepad2.dpad_down;
    }

    public static boolean getDpadLeft2() {
        return gamepad2.dpad_left;
    }

    public static boolean getDpadRight2() {
        return gamepad2.dpad_right;
    }

    public static boolean getLeftBumper2() {
        return gamepad2.left_bumper;
    }

    public static boolean getRightBumper2() {
        return gamepad2.right_bumper;
    }

    public static boolean getLeftStickButton2() {
        return gamepad2.left_stick_button;
    }

    public static boolean getRightStickButton2() {
        return gamepad2.right_stick_button;
    }



}
