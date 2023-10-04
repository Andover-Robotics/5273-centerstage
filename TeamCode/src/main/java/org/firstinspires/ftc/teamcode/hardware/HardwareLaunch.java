package org.firstinspires.ftc.teamcode.hardware;

public interface HardwareLaunch {
    public enum openCloseState{
        OPEN,
        CLOSE
    }

    public void open();
    public openCloseState getState();
}
