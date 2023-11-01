package org.firstinspires.ftc.teamcode.input;

public class Intent {
    public enum Centric {
        ROBOT,
        FIELD
    }

    Centric centric;
    double x_dir;
    double y_dir;
    double move_speed;
    double turn_speed;
    boolean launch;
    public enum IntakeIntent {
        START,
        STOP,
        NONE,
    }
    IntakeIntent intake;
    public enum ClawIntent {
        OPEN_HALF_RELATIVE,
        CLOSE_HALF_RELATIVE,
        OPEN_FULL,
        CLOSE_FULL,
        OPEN_HALF
    }
    ClawIntent claw;

}
