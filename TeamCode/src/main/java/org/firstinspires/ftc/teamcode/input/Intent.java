package org.firstinspires.ftc.teamcode.input;

public class Intent {
    public enum Centric {
        ROBOT,
        FIELD
    }
    public static class MovementIntent {
        public Centric centric;
        public double moveDirection;
        public double moveSpeed;
        public double turnSpeed;

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
    public enum ClawIntent {
        OPEN_HALF_RELATIVE,
        CLOSE_HALF_RELATIVE,
        OPEN_FULL,
        CLOSE_FULL,
        OPEN_HALF,
        NONE,
    }
    public ClawIntent claw;
    public boolean clawFlip;
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
    public int slides_absolute;

    @Override
    public String toString() {
        return "Intent{" +
                "movement=" + movement +
                ", launch=" + launch +
                ", intake=" + intake +
                ", claw=" + claw +
                ", slides=" + slides +
                ", slides_absolute=" + slides_absolute +
                '}';
    }
}
