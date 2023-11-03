package org.firstinspires.ftc.teamcode.input;

public class ControllerMapping {
    HardwareController controller;
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
        public ControllerState(int controller) {
            if (controller == 1) {
                left_stick_x = HardwareController.getLeftStickX1();
                left_stick_y = HardwareController.getLeftStickY1();
                right_stick_x = HardwareController.getRightStickX1();
                right_stick_y = HardwareController.getRightStickY1();
                left_trigger = HardwareController.getLeftTrigger1();
                right_trigger = HardwareController.getRightTrigger1();
                a = HardwareController.getA1();
                b = HardwareController.getB1();
                x = HardwareController.getX1();
                y = HardwareController.getY1();
                dpad_up = HardwareController.getDpadUp1();
                dpad_down = HardwareController.getDpadDown1();
                dpad_left = HardwareController.getDpadLeft1();
                dpad_right = HardwareController.getDpadRight1();
                left_bumper = HardwareController.getLeftBumper1();
                right_bumper = HardwareController.getRightBumper1();
                left_stick_button = HardwareController.getLeftStickButton1();
                right_stick_button = HardwareController.getRightStickButton1();
            } else if (controller == 2) {
                left_stick_x = HardwareController.getLeftStickX2();
                left_stick_y = HardwareController.getLeftStickY2();
                right_stick_x = HardwareController.getRightStickX2();
                right_stick_y = HardwareController.getRightStickY2();
                left_trigger = HardwareController.getLeftTrigger2();
                right_trigger = HardwareController.getRightTrigger2();
                a = HardwareController.getA2();
                b = HardwareController.getB2();
                x = HardwareController.getX2();
                y = HardwareController.getY2();
                dpad_up = HardwareController.getDpadUp2();
                dpad_down = HardwareController.getDpadDown2();
                dpad_left = HardwareController.getDpadLeft2();
                dpad_right = HardwareController.getDpadRight2();
                left_bumper = HardwareController.getLeftBumper2();
                right_bumper = HardwareController.getRightBumper2();
                left_stick_button = HardwareController.getLeftStickButton2();
                right_stick_button = HardwareController.getRightStickButton2();
            }
        }
    }
    ControllerState lastState1;
    ControllerState lastState2;
    public static Intent get_intent() {
        Intent intent = new Intent();
        //drive direction + magnitude: controller 1 left stick, feild centric
        double x = HardwareController.getLeftStickX1();
        double y = HardwareController.getLeftStickY1();

        intent.move_dir = Math.atan2(y, x);
        intent.move_speed = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        //turn speed: controller 1 right stick left/right
        intent.turn_speed = HardwareController.getRightStickX1();
        intent.centric = Intent.Centric.FIELD;
        //TODO: do literally all of the rest of the controls
        return intent;
    }

}
