package org.firstinspires.ftc.teamcode.input;

public class Intent {
    public Intent() {
        movement = new MovementIntent();
        launch = false;
        intake = IntakeIntent.STOP;
        clawPincher = ClawPincherIntent.NONE;
        clawFlip = ClawFlipIntent.NONE;
        slides = 0;
        slides_absolute = 0;
    }
    public enum Centric {
        ROBOT,
        FIELD
    }
    public static class MovementIntent {
        public Centric centric;
        public double moveDirection;
        public double moveSpeed;
        public double turnSpeed;
        public boolean resetHeading;

        @Override
        public String toString() {
            return "MovementIntent{" +
                    "centric=" + centric +
                    ", moveDirection=" + moveDirection +
                    ", moveSpeed=" + moveSpeed +
                    ", turnSpeed=" + turnSpeed +
                    '}';
        }
    }
    public MovementIntent movement;
    public boolean launch;
    public enum IntakeIntent {
        FORWARD,
        STOP,
        BACKWARD,
    }
    public IntakeIntent intake;
    public enum ClawPincherIntent {
        OPEN_HALF_RELATIVE,
        CLOSE_HALF_RELATIVE,
        OPEN_FULL,
        CLOSE_FULL,
        OPEN_HALF,
        NONE,
    }
    public ClawFlipIntent clawFlip;
    public ClawPincherIntent clawPincher;
    public enum ClawFlipIntent{
        FLIP,
        NONE,
        TWEAK_UP,
        TWEAK_DOWN
    }
    public enum SlidesIntent {
        UP_RELATIVE_HALF,
        DOWN_RELATIVE_HALF,
        UP_RELATIVE_FULL,
        DOWN_RELATIVE_FULL,
        TOP,
        BOTTOM,
        ABSOLUTE,
    }
    public double slides;
    public boolean slidesOverride;
    public int slides_absolute;
    public boolean launch;


    @Override
    public String toString() {
        return "Intent{" +
                "movement=" + movement +
                ", launch=" + launch +
                ", intake=" + intake +
                ", claw=" + clawPincher +
                ", slides=" + slides +
                ", slides_absolute=" + slides_absolute +
                '}';
    }
}
