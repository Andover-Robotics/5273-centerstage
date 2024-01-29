package com.andoverrobotics.thunder.commonlogic.subsystems;

import com.andoverrobotics.thunder.commonlogic.input.Intent;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareClawFlipper;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareClaw;

public class Claw {
    private final Logger logger;
    private static final double CLAW_OPEN = 0.501;
    private static final double CLAW_HALF_OPEN = 0.424;
    private static final double CLAW_CLOSED = 0.372777777777777777777777777777777;
    private static final double FLIP_STARTING_POS = 0.0344444;
    private static final double FLIP_INTAKE_POS = 0.7183333;

    private final HardwareClaw hardwareClaw;
    private final HardwareClawFlipper clawFlipper;
    private PincherState pincherState;
    public enum PincherState {
        OPEN,
        HALF_OPEN,
        CLOSED
    }


    public Claw(HardwareClaw hardwareClaw, HardwareClawFlipper clawFlipper, Logger logger) {
        this.logger = logger;
        this.hardwareClaw = hardwareClaw;
        this.clawFlipper = clawFlipper;
        this.pincherState = PincherState.OPEN;
        this.closePincher();
        this.gotoStartingPos();
    }

    public void setPincherState(PincherState pincherState) {
        this.pincherState = pincherState;
        switch (pincherState) {
            case OPEN:
                this.openPincher();
                break;
            case HALF_OPEN:
                this.halfOpenPincher();
                break;
            case CLOSED:
                this.closePincher();
                break;
        }
    }

    public void openPincher() {
        this.hardwareClaw.setPosition(CLAW_OPEN);
    }

    public void halfOpenPincher() {
        this.hardwareClaw.setPosition(CLAW_HALF_OPEN);
    }

    public void closePincher() {
        this.hardwareClaw.setPosition(CLAW_CLOSED);
    }

    public PincherState getPincherState() {
        return this.pincherState;
    }

    public void gotoStartingPos(){
        this.clawFlipper.setPosition(FLIP_STARTING_POS);
    }
    public void gotoIntakePos(){
        this.clawFlipper.setPosition(FLIP_INTAKE_POS);
    }
    public void executeIntent(Intent.ClawPincherIntent pincherIntent, Intent.ClawFlipIntent flipIntent) {
        switch (pincherIntent) {
            case OPEN_HALF_RELATIVE:
                if (this.pincherState == PincherState.CLOSED) {
                    this.setPincherState(PincherState.HALF_OPEN);
                } else if (this.pincherState == PincherState.HALF_OPEN) {
                    this.setPincherState(PincherState.OPEN);
                }
                break;
            case CLOSE_HALF_RELATIVE:
                if (this.pincherState == PincherState.OPEN) {
                    this.setPincherState(PincherState.HALF_OPEN);
                } else if (this.pincherState == PincherState.HALF_OPEN) {
                    this.setPincherState(PincherState.CLOSED);
                }
                break;
            case OPEN_FULL:
                this.setPincherState(PincherState.OPEN);
                break;
            case CLOSE_FULL:
                this.setPincherState(PincherState.CLOSED);
                break;
            case OPEN_HALF:
                this.setPincherState(PincherState.HALF_OPEN);
                break;
            case NONE:
                break;
        }
        switch (flipIntent) {
            case MOVE_UP:
                clawFlipper.setPosition(clawFlipper.getPosition() + 0.01);
                break;
            case MOVE_DOWN:
                clawFlipper.setPosition(clawFlipper.getPosition() - 0.01);
                break;
            case STARTING_POS:
                gotoStartingPos();
                break;
            case INTAKE_POS:
                gotoIntakePos();
                break;
            case NONE:
                break;
        }
        if (flipIntent == Intent.ClawFlipIntent.MOVE_UP) {
            clawFlipper.setPosition(clawFlipper.getPosition() + 0.01);
        } else if (flipIntent == Intent.ClawFlipIntent.MOVE_DOWN) {
            clawFlipper.setPosition(clawFlipper.getPosition() - 0.01);
        }

        logger.setProp("flipper pos", clawFlipper.getPosition());
    }

}
