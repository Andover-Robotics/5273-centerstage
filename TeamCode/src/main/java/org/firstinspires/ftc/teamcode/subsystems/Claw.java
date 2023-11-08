package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.hardware.HardwareClaw;
import org.firstinspires.ftc.teamcode.hardware.HardwareClawFlipper;
import org.firstinspires.ftc.teamcode.input.Intent;

public class Claw {
    private static final double CLAW_OPEN = 0.4;
    private static final double CLAW_HALF_OPEN = 0.37;
    private static final double CLAW_CLOSED = 0.25;
    private static final double FLIP_IN = 0.6; // guessed
    private static final double FLIP_OUT = 0; // guessed

    private final HardwareClaw hardwareClaw;
    private final HardwareClawFlipper clawFlipper;
    private PincherState pincherState;
    private FlipState flipState;
    public enum FlipState{
        IN,
        OUT
    }

    public enum PincherState {
        OPEN,
        HALF_OPEN,
        CLOSED
    }


    public Claw(HardwareClaw hardwareClaw, HardwareClawFlipper clawFlipper) {
        this.hardwareClaw = hardwareClaw;
        this.clawFlipper = clawFlipper;
        this.pincherState = PincherState.OPEN;
        this.flipState = FlipState.IN;
        this.open();
    }

    public void setPincherState(PincherState pincherState) {
        this.pincherState = pincherState;
        switch (pincherState) {
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

    public PincherState getPincherState() {
        return this.pincherState;
    }
    public void flip(){
        if(flipState==FlipState.IN){
            clawFlipper.setPosition(FLIP_OUT);
            flipState=FlipState.OUT;
        }else{
            clawFlipper.setPosition(FLIP_IN);
            flipState=FlipState.IN;
        }
    }
    public FlipState getFlipState(){
        return flipState;
    }

    public void executeIntent(Intent.ClawIntent intent, boolean clawFlip) {
        switch (intent) {
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
        if(clawFlip){
            flip();
        }
    }

}
