package org.firstinspires.ftc.teamcode.hardware;

public interface HardwareClaw {
    public enum Claw{
        OPEN,
        CLOSE
    }

    public void open();
    public void close();
    public HardwareClaw getState();

}