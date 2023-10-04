This is an example for what each hardware component should have:

package org.firstinspires.ftc.teamcode.hardware;

public interface HardwareClaw {
    public enum openCloseState {
        OPEN,
        CLOSED
    }

    public void open();
    public void close();
    public openCloseState getState();
}


public class RealHardwareClaw implements HardwareClaw{
    @Override
    public void open() {
        // TODO: write for real hardware
    }
}
