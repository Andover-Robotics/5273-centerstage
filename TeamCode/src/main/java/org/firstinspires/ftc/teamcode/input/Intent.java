package org.firstinspires.ftc.teamcode.input;

public class Intent {
    public enum Centric {
        ROBOT,
        FIELD
    }

    public Centric centric;
    public double move_dir;
    public double move_speed;
    public double turn_speed;
    public boolean launch;
    public enum IntakeIntent {
        START,
        STOP,
        NONE,
    }
    public IntakeIntent intake;
    public enum ClawIntent {
        OPEN_HALF_RELATIVE,
        CLOSE_HALF_RELATIVE,
        OPEN_FULL,
        CLOSE_FULL,
        OPEN_HALF
    }
    public ClawIntent claw;
    public enum SlidesIntent {
        UP_RELATIVE_HALF,
        DOWN_RELATIVE_HALF,
        UP_RELATIVE_FULL,
        DOWN_RELATIVE_FULL,
        TOP,
        BOTTOM,
        ABSOLUTE,
    }
    public SlidesIntent slides;
    public int slides_absolute;

}
