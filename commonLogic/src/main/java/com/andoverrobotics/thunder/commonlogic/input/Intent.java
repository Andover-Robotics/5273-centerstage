package com.andoverrobotics.thunder.commonlogic.input;

public class Intent {
    public Intent() {
        movement = new MovementIntent();
        launch = false;
        intake = IntakeIntent.STOP;
        clawPincher = ClawPincherIntent.NONE;
        clawFlip = new ClawFlipIntent(ClawFlipPreset.NONE);
        slides = 0;
        slidesIntent = SlidesIntent.POWER;
        pivotIntent = PivotIntent.POWER;
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
        public enum HeadingReset {
            UP,
            RIGHT,
            DOWN,
            LEFT,
            NONE
        }
        public HeadingReset resetHeading;

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
    public final MovementIntent movement;
    public boolean launch;
    public enum IntakeIntent {
        FORWARD,
        STOP,
        BACKWARD,
    }
    public IntakeIntent intake;
    public ClawPincherIntent clawPincher;
    public enum ClawPincherIntent {
        OPEN_HALF_RELATIVE,
        CLOSE_HALF_RELATIVE,
        OPEN_FULL,
        CLOSE_FULL,
        OPEN_HALF,
        NONE,
    }
    public ClawFlipIntent clawFlip;
    public static class ClawFlipIntent {
        public ClawFlipIntent(ClawFlipPreset preset){
            this.referenceAngle = 0;
            this.preset = preset;
        }
        public double referenceAngle;
        public ClawFlipPreset preset;
    }
    public enum ClawFlipPreset{
        NONE,
        MOVE_UP,
        MOVE_DOWN,
        STARTING_POS,
        INTAKE_POS,
        SCORING_POS
    }

    public enum SlidesIntent {
        POWER,
        TARGET
    }
    public double slides;
    public SlidesIntent slidesIntent;
    public boolean override;

    public enum PivotIntent {
        POWER,
        TARGET
    }


    public double pivot;
    public PivotIntent pivotIntent;

    @Override
    public String toString() {
        return "Intent{" +
                "movement=" + movement +
                ", launch=" + launch +
                ", intake=" + intake +
                ", clawFlip=" + clawFlip +
                ", clawPincher=" + clawPincher +
                ", slides=" + slides +
                ", slidesOverride=" + override +
                '}';
    }
}
