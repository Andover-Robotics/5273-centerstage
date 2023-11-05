package org.firstinspires.ftc.teamcode.input;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ControllerMapping {
    HardwareController controller1;
    HardwareController controller2;
    private static class ControllerState {
        public double left_stick_x;
        public double left_stick_y;
        public double right_stick_x;
        public double right_stick_y;
        public double left_trigger;
        public double right_trigger;
        public boolean a;
        public boolean b;
        public boolean x;
        public boolean y;
        public boolean dpad_up;
        public boolean dpad_down;
        public boolean dpad_left;
        public boolean dpad_right;
        public boolean left_bumper;
        public boolean right_bumper;
        public boolean left_stick_button;
        public boolean right_stick_button;
        public ControllerState(HardwareController gamepad) {
            left_stick_x = gamepad.getLeftStickX();
            left_stick_y = gamepad.getLeftStickY();
            right_stick_x = gamepad.getRightStickX();
            right_stick_y = gamepad.getRightStickY();
            left_trigger = gamepad.getLeftTrigger();
            right_trigger = gamepad.getRightTrigger();
            a = gamepad.getA();
            b = gamepad.getB();
            x = gamepad.getX();
            y = gamepad.getY();
            dpad_up = gamepad.getDpadUp();
            dpad_down = gamepad.getDpadDown();
            dpad_left = gamepad.getDpadLeft();
            dpad_right = gamepad.getDpadRight();
            left_bumper = gamepad.getLeftBumper();
            right_bumper = gamepad.getRightBumper();
            left_stick_button = gamepad.getLeftStickButton();
            right_stick_button = gamepad.getRightStickButton();
        }
    }
    ControllerState lastState1;
    ControllerState lastState2;

    public ControllerMapping(Gamepad gamepad1, Gamepad gamepad2) {
        controller1 = new HardwareController(gamepad1);
        controller2 = new HardwareController(gamepad2);
        lastState1 = new ControllerState(controller1);
        lastState2 = new ControllerState(controller2);
    }
    public Intent get_intent() {
        Intent intent = new Intent();
        //drive direction + magnitude: controller 1 left stick, feild centric
        ControllerState state1 = new ControllerState(controller1);
        ControllerState state2 = new ControllerState(controller2);

        double x = state1.left_stick_x;
        double y = state1.left_stick_y;

        intent.move_dir = Math.atan2(y, x);
        intent.move_speed = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        //turn speed: controller 1 right stick left/right
        intent.turn_speed = state1.right_stick_x;
        intent.centric = Intent.Centric.FIELD;
        //TODO: do literally all of the rest of the controls
        lastState1 = state1;
        lastState2 = state2;
        return intent;
    }

}