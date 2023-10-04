package org.firstinspires.ftc.teamcode.hardware;

public interface FlyWheel {
    public enum onOffState {
        ON,
        OFF
    }

    public void on();
    public void off();
    public onOffState getState();
}

