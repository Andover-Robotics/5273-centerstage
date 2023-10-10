package org.firstinspires.ftc.teamcode.hardware;

public interface HardwareFlyWheel {
    public enum onOffState {
        ON,
        OFF
    }

    public void on();
    public void off();
    public onOffState getState();
}

