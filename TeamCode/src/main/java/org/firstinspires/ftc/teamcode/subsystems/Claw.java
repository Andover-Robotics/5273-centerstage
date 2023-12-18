package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.HardwareClaw;
import org.firstinspires.ftc.teamcode.hardware.HardwareClawFlipper;
import org.firstinspires.ftc.teamcode.input.Intent;

public class Claw {
    private Telemetry telemetry;
    private static final double CLAW_OPEN = 0.501;
    private static final double CLAW_HALF_OPEN = 0.424;
    private static final double CLAW_CLOSED = 0.353;
    private static final double FLIP_IN = 0.182;
    private static final double FLIP_OUT = 0.512;
    private static final double BOTTOM_FLIP_LIMIT = 2000;

    private final HardwareClaw hardwareClaw;
    private final HardwareClawFlipper clawFlipper;
    private PincherState pincherState;
    private FlipState flipState;
    public enum FlipState{
        IN,
        OUT,
    }

    public enum PincherState {
        OPEN,
        HALF_OPEN,
        CLOSED
    }


    public Claw(HardwareClaw hardwareClaw, HardwareClawFlipper clawFlipper, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.hardwareClaw = hardwareClaw;
        this.clawFlipper = clawFlipper;
        this.pincherState = PincherState.OPEN;
        this.flipState = FlipState.IN;
        this.open();
        this.setFlipState(FlipState.IN);
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
    public void setFlipState(FlipState state){
        switch (state) {
            case IN:
                clawFlipper.setPosition(FLIP_IN);
                flipState = FlipState.IN;
                break;
            case OUT:
                clawFlipper.setPosition(FLIP_OUT);
                flipState = FlipState.OUT;
                break;
        }
    }
    public FlipState getFlipState(){
        return flipState;
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
        if(flipIntent == Intent.ClawFlipIntent.FLIP){
            if(flipState == FlipState.IN){
                setFlipState(FlipState.OUT);
            }else if(flipState == FlipState.OUT){
                if(pincherState == PincherState.OPEN){
                    setPincherState(PincherState.HALF_OPEN);
                }
                setFlipState(FlipState.IN);
            }
        }else if(flipIntent == Intent.ClawFlipIntent.TWEAK_UP){
            clawFlipper.setPosition(clawFlipper.getPosition() + 0.01);
        }else if(flipIntent == Intent.ClawFlipIntent.TWEAK_DOWN){
            clawFlipper.setPosition(clawFlipper.getPosition() - 0.01);
        }

        telemetry.addData("flipper pos: ",clawFlipper.getPosition());
    }

}
