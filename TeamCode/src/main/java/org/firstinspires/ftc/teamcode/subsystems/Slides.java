package org.firstinspires.ftc.teamcode.subsystems;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.HardwareSlides;
import org.firstinspires.ftc.teamcode.input.Intent;


//up is negitive for some reason
public class Slides {
    private final HardwareSlides hardwareSlides;
    private static final double ENCODER_RES = 384.5;
    private static final double REAL_MAX_SLIDES_POSITION = -3900;
    private static final double MAX_SLIDES_POSITION = REAL_MAX_SLIDES_POSITION + 250;
    private static final int CLAW_FLIP_BOUND1 = -200; // above is in, below or equal is inin
    private static final int CLAW_FLIP_BOUND2 = -1000; // above or equal is inin, below is out
    private static final double SPEED_LIMIT_FACTOR = 0.2;
    private double startingPositionLeft = 0;
    private double startingPositionRight = 0;
    private final Telemetry telemetry;
    private ElapsedTime timer;
    private double lastPos;

    public Slides(HardwareSlides hardwareSlides, Telemetry telemetry){
        this.telemetry = telemetry;
        this.hardwareSlides = hardwareSlides;
        hardwareSlides.setModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hardwareSlides.setDirections(DcMotor.Direction.FORWARD, DcMotor.Direction.REVERSE);
        int[] positions = hardwareSlides.getSlidesPositions();
        startingPositionLeft = positions[0];
        startingPositionRight = positions[1];
        timer = new ElapsedTime();
        lastPos = 0;
    }

    private boolean between(double value, double limit1, double limit2){
        if(limit1 > limit2){
            return value >= limit2 && value <= limit1;
        }else{
            return value >= limit1 && value <= limit2;
        }
    }

    private double clamp(double value, double limit1, double limit2){
        if(limit1 > limit2){
            if(value < limit2){
                return limit2;
            }else if(value > limit1){
                return limit1;
            }else{
                return value;
            }
        }else{
            if(value < limit1){
                return limit1;
            }else if(value > limit2){
                return limit2;
            }else{
                return value;
            }
        }
    }

    public Intent.ClawFlipIntent executeIntent(double power){
        int[] positions = hardwareSlides.getSlidesPositions();
        double pos=(positions[0]+positions[1])/2.0;
        double dt = timer.seconds();
        timer.reset();
        double dx = pos - lastPos;
        lastPos = pos;

        double speed = dx/dt;

        telemetry.addData("speed", speed);

        telemetry.addData("real power", power);

        //prevent pos from going above 0
        if((pos > 0) && (power < 0)){
            telemetry.addData("limiting", "lower bound");
            power = 0;
        }
        //prevent pos from going below MAX_SLIDES_POSITION
        if((pos < MAX_SLIDES_POSITION) && (power > 0)){
            telemetry.addData("limiting", "upper bound");
            power = 0;
        }
        //if above CLAW_FLIP_BOUND2, dont limit power
        if(pos < CLAW_FLIP_BOUND2 && power > 0) {
        }else if(between(pos, CLAW_FLIP_BOUND2, CLAW_FLIP_BOUND1) && power < 0){
            power *= 0.3;
        }else{
            power *= 0.4;
        }
        telemetry.addData("power", power);
        telemetry.addData("pos", pos);

        hardwareSlides.setPowers(power, power);

        if(pos>CLAW_FLIP_BOUND1) {
            return Intent.ClawFlipIntent.IN;
        }else if(pos<CLAW_FLIP_BOUND2) {
            return Intent.ClawFlipIntent.OUT;
        }else {
            return Intent.ClawFlipIntent.IN_IN;
        }
    }

}
