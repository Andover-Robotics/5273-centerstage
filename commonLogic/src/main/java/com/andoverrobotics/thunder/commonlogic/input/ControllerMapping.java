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

        double speedMod1 = 1 - state1.right_trigger * 0.75;
        double speedMod2 = 1 - state2.right_trigger * 0.75;

        intent.movement.moveDirection = Math.atan2(y, x); // pi/2 is forwards
        // right trigger scales down movement and turn speeds
        intent.movement.moveSpeed = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) * speedMod1;
        //turn speed: controller 1 right stick left/right
        intent.movement.turnSpeed = state1.right_stick_x * speedMod1;
        intent.movement.centric = Intent.Centric.FIELD;
        // y resets heading for field centric angle correction

        //dpad directions resets the heading in the different directions
        if(state1.dpad_up && !lastState1.dpad_up){
            intent.movement.resetHeading = Intent.MovementIntent.HeadingReset.UP;
        }else if(state1.dpad_right && !lastState1.dpad_right){
            intent.movement.resetHeading = Intent.MovementIntent.HeadingReset.RIGHT;
        }else if(state1.dpad_down && !lastState1.dpad_down){
            intent.movement.resetHeading = Intent.MovementIntent.HeadingReset.DOWN;
        }else if(state1.dpad_left && !lastState1.dpad_left){
            intent.movement.resetHeading = Intent.MovementIntent.HeadingReset.LEFT;
        }else{
            intent.movement.resetHeading = Intent.MovementIntent.HeadingReset.NONE;
        }

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
        intent.slides = -state2.left_stick_y * speedMod2;
        intent.slidesIntent = Intent.SlidesIntent.POWER;
        intent.override = state2.b;

        if(state2.a && !lastState2.a) {
            intent.clawFlip.preset = Intent.ClawFlipPreset.INTAKE_POS;
        } else if(state2.y && !lastState2.y){
            intent.clawFlip.preset = Intent.ClawFlipPreset.SCORING_POS;
        }else if(state2.left_stick_x > 0.5){
            intent.clawFlip.preset = Intent.ClawFlipPreset.MOVE_UP;
        } else if(state2.left_stick_x < -0.5){
            intent.clawFlip.preset = Intent.ClawFlipPreset.MOVE_DOWN;
        }else{
            intent.clawFlip.preset = Intent.ClawFlipPreset.NONE;
        }

        intent.pivot = -state2.right_stick_y * speedMod2;
        intent.pivotIntent = Intent.PivotIntent.POWER;

        if(state2.a && !lastState2.a){
            intent.slides = 0;
            intent.slidesIntent = Intent.SlidesIntent.TARGET;
            intent.clawPincher = Intent.ClawPincherIntent.OPEN_FULL;
            intent.pivot = 0;
            intent.pivotIntent = Intent.PivotIntent.TARGET;
        }


        intent.launch = state2.x && !lastState2.x;


        lastState1 = state1;
        lastState2 = state2;
        return intent;
    }

}
