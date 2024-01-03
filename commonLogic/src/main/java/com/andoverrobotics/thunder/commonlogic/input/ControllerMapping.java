package com.andoverrobotics.thunder.commonlogic.input;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareController;

public class ControllerMapping {
    final HardwareController controller1;
    final HardwareController controller2;
    private static class ControllerState {
        public final double left_stick_x;
        public final double left_stick_y;
        public final double right_stick_x;
        public final double right_stick_y;
        public final double left_trigger;
        public final double right_trigger;
        public final boolean a;
        public final boolean b;
        public final boolean x;
        public final boolean y;
        public final boolean dpad_up;
        public final boolean dpad_down;
        public final boolean dpad_left;
        public final boolean dpad_right;
        public final boolean left_bumper;
        public final boolean right_bumper;
        public final boolean left_stick_button;
        public final boolean right_stick_button;
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

    public ControllerMapping(HardwareController controller1, HardwareController controller2) {
        this.controller1 = controller1;
        this.controller2 = controller2;
        lastState1 = new ControllerState(controller1);
        lastState2 = new ControllerState(controller2);
    }
    public Intent get_intent() {
        Intent intent = new Intent();
        //drive direction + magnitude: controller 1 left stick, feild centric
        ControllerState state1 = new ControllerState(controller1);
        ControllerState state2 = new ControllerState(controller2);

        double x = state1.left_stick_x;
        double y = -state1.left_stick_y;

        intent.movement.moveDirection = Math.atan2(y, x); // pi/2 is forwards
        // right trigger scales down movement and turn speeds
        intent.movement.moveSpeed = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * (1 - state1.right_trigger * 0.75);
        //turn speed: controller 1 right stick left/right
        intent.movement.turnSpeed = state1.right_stick_x * (1 - state1.right_trigger * 0.75);
        intent.movement.centric = Intent.Centric.FIELD;
        // y resets heading for field centric angle correction
        intent.movement.resetHeading = state1.y && !lastState1.y;

        //claw
        if(state2.dpad_up && !lastState2.dpad_up){
            intent.clawPincher = Intent.ClawPincherIntent.CLOSE_FULL;
        }else if(state2.dpad_down && !lastState2.dpad_down){
            intent.clawPincher = Intent.ClawPincherIntent.OPEN_HALF_RELATIVE;
        }else{
            intent.clawPincher = Intent.ClawPincherIntent.NONE;
        }


        //intake
        if(state2.left_trigger > 0.5){
            intent.intake = Intent.IntakeIntent.FORWARD;
        } else if(state2.right_trigger > 0.5){
            intent.intake = Intent.IntakeIntent.BACKWARD;
        } else {
            intent.intake = Intent.IntakeIntent.STOP;
        }

        // slides
        intent.slides = -state2.left_stick_y;
        intent.slidesOverride = state2.b;

        if(state2.a && !lastState2.a) {
            intent.clawFlip = Intent.ClawFlipIntent.FLIP;
            intent.clawPincher = Intent.ClawPincherIntent.CLOSE_FULL;
        } else if((-state2.right_stick_y) > 0.5){
            intent.clawFlip = Intent.ClawFlipIntent.TWEAK_UP;
        }else if((-state2.right_stick_y) < -0.5){
            intent.clawFlip = Intent.ClawFlipIntent.TWEAK_DOWN;
        }else{
            intent.clawFlip = Intent.ClawFlipIntent.NONE;
        }

        intent.launch = state2.x && !lastState2.x;

        //TODO: do literally all of the rest of the controls
        lastState1 = state1;
        lastState2 = state2;
        return intent;
    }

}
