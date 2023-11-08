package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.hardware.HardwareClaw;
import org.firstinspires.ftc.teamcode.hardware.HardwareClawFlipper;
import org.firstinspires.ftc.teamcode.input.Intent;

public class Claw {
    private static final double CLAW_OPEN = 0.4;
    private static final double CLAW_HALF_OPEN = 0.37;
    private static final double CLAW_CLOSED = 0.25;
    private static final double CLAW_FLIPPED = 0.6; // guessed
    private static final double CLAW_UNFLIPPED = 0; // guessed

    private final HardwareClaw hardwareClaw;
    private final HardwareClawFlipper clawFlipper;
    private ClawState state;
    private boolean isFlipped;

    public enum ClawState {
        OPEN,
        HALF_OPEN,
        CLOSED
    }


    public Claw(HardwareClaw hardwareClaw, HardwareClawFlipper clawFlipper) {
        this.hardwareClaw = hardwareClaw;
        this.clawFlipper = clawFlipper;
        this.state = ClawState.OPEN;
        this.open();
    }

    public void setState(ClawState state) {
        this.state = state;
        switch (state) {
            case OPEN:
                this.open();
                break;
            case HALF_OPEN:
                this.halfOpen();
                break;
            case CLOSED:
                this.close();
                break;
        }
    }

    public void open() {
        this.hardwareClaw.setPosition(CLAW_OPEN);
    }

    public void halfOpen() {
        this.hardwareClaw.setPosition(CLAW_HALF_OPEN);
    }

    public void close() {
        this.hardwareClaw.setPosition(CLAW_CLOSED);
    }

    public ClawState getState() {
        return this.state;
    }
    public void flip(){
        if(isFlipped){
            clawFlipper.setPosition(CLAW_UNFLIPPED);
            isFlipped = false;
        }else{
            clawFlipper.setPosition(CLAW_FLIPPED);
            isFlipped = true;
        }
    }
    public boolean isFlipped(){
        return isFlipped;
    }

    public void executeIntent(Intent.ClawIntent intent) {
        switch (intent) {
            case OPEN_HALF_RELATIVE:
                if (this.state == ClawState.CLOSED) {
                    this.setState(ClawState.HALF_OPEN);
                } else if (this.state == ClawState.HALF_OPEN) {
                    this.setState(ClawState.OPEN);
                }
                break;
            case CLOSE_HALF_RELATIVE:
                if (this.state == ClawState.OPEN) {
                    this.setState(ClawState.HALF_OPEN);
                } else if (this.state == ClawState.HALF_OPEN) {
                    this.setState(ClawState.CLOSED);
                }
                break;
            case OPEN_FULL:
                this.setState(ClawState.OPEN);
                break;
            case CLOSE_FULL:
                this.setState(ClawState.CLOSED);
                break;
            case OPEN_HALF:
                this.setState(ClawState.HALF_OPEN);
                break;
            case NONE:
                break;
        }
    }

}
