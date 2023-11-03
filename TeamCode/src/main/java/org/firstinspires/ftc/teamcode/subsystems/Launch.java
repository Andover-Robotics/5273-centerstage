package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.HardwareLaunch;

public class Launch {
    private static final double LAUNCH_ON = 0.5;
    private static final double LAUNCH_OFF = 0.0;
    private HardwareLaunch hardwareLaunch;
    private LaunchState state;

    public enum LaunchState {
        ON,
        OFF
    }
    public void setState(LaunchState state) {
        this.state = state;
        switch (state) {
            case ON:
                this.on();
                break;
            case OFF:
                this.close();
                break;
        }
    }

    public Launch (HardwareLaunch hardwareLaunch) {
        this.hardwareLaunch = hardwareLaunch;
        this.state = LaunchState.ON;
        this.on();
    }
    public void on () {
        this.hardwareLaunch.setPosition(LAUNCH_ON);
    }
    public void close () {
        this.hardwareLaunch.setPosition(LAUNCH_OFF);
    }
    public LaunchState getState() {
        return this.state;
    }
}
