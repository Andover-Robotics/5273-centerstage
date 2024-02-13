package com.andoverrobotics.thunder.commonlogic.subsystems;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareClaw;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareClawFlipper;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.input.Intent;

public class Claw {
    private final Logger logger;
    private static final double CLAW_OPEN = 0.727;
    private static final double CLAW_HALF_OPEN = 0.574;
    private static final double CLAW_CLOSED = 0.572;
    private static final double FLIP_STARTING_POS =
            0.091; // assumed to be 180 degrees form the intake pos
    private static final double FLIP_INTAKE_POS = 0.7416; // asumed to be parallel to the ground
    private Intent.ClawFlipPreset targetPreset = Intent.ClawFlipPreset.NONE;

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

    public void gotoStartingPos() {
        this.clawFlipper.setPosition(FLIP_STARTING_POS);
    }

    public void gotoIntakePos() {
        this.clawFlipper.setPosition(FLIP_INTAKE_POS);
    }

    public void executeIntent(
            Intent.ClawPincherIntent pincherIntent, Intent.ClawFlipIntent flipIntent) {
        if (flipIntent.preset != Intent.ClawFlipPreset.NONE) {
            targetPreset = flipIntent.preset;
        }
        if (flipIntent.preset == Intent.ClawFlipPreset.NONE
                && (targetPreset == Intent.ClawFlipPreset.MOVE_DOWN
                        || targetPreset == Intent.ClawFlipPreset.MOVE_UP)) {
            targetPreset = Intent.ClawFlipPreset.NONE;
        }
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
        }
        switch (targetPreset) {
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
            case SCORING_POS:
                {
                    double degree0 = FLIP_INTAKE_POS;
                    double degree180 = FLIP_STARTING_POS;
                    double targetAngle = 60 - flipIntent.referenceAngle;
                    clawFlipper.setPosition(degree0 + (targetAngle / 180) * (degree180 - degree0));
                    break;
                }
        }
        if (targetPreset == Intent.ClawFlipPreset.MOVE_UP) {
            clawFlipper.setPosition(clawFlipper.getPosition() + 0.01);
        } else if (targetPreset == Intent.ClawFlipPreset.MOVE_DOWN) {
            clawFlipper.setPosition(clawFlipper.getPosition() - 0.01);
        }

        logger.setProp("flipper pos", clawFlipper.getPosition());
    }
}
