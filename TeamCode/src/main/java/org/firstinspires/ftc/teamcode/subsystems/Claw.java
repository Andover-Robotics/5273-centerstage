package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.hardware.HardwareClaw;

public class Claw {
    private static final double CLAW_OPEN = 0.5;
    private static final double CLAW_HALF_OPEN = 0.25;
    private static final double CLAW_CLOSED = 0.0;

    private final HardwareClaw hardwareClaw;
    private ClawState state;

    public enum ClawState {
        OPEN,
        HALF_OPEN,
        CLOSED
    }


    public Claw(HardwareClaw hardwareClaw) {
        this.hardwareClaw = hardwareClaw;
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
