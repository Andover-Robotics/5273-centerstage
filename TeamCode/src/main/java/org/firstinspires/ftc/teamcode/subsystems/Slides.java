package org.firstinspires.ftc.teamcode.subsystems;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.HardwareSlides;


public class Slides {
    private final HardwareSlides hardwareSlides;
    private static final double ENCODER_RES = 384.5;
    private static final int MIN_FLIP_HEIGHT = 2350;
    private static final int OFFSET = 4300; // max height when minHeight = 0
    private int minHeight=0; // can be overridden
    private static final double SPEED_LIMIT_FACTOR = 0.2;
    private int startingPositionLeft = 0;
    private int startingPositionRight = 0;
    private final Telemetry telemetry;
    private ElapsedTime timer;
    private int lastPos;

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

    private boolean between(int value, int limit1, int limit2){
        if(limit1 > limit2){
            return value >= limit2 && value <= limit1;
        }else{
            return value >= limit1 && value <= limit2;
        }
    }

    private int clamp(int value, int limit1, int limit2){
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

    public void executeIntent(double power, boolean override){
        int[] positions = hardwareSlides.getSlidesPositions();
        positions[0] = -positions[0];//for some reason these are negitive by default(up is negitive)
        positions[1] = -positions[1];//this fixes them so that up is positive
        int pos=Math.max(positions[0], positions[1]);
        double dt = timer.seconds();
        timer.reset();
        double dx = pos - lastPos;
        lastPos = pos;

        double speed = dx/dt;

//        telemetry.addData("speed", speed);

//        telemetry.addData("real power", power);

        //prevent pos from going above 0
        if(pos < minHeight && power < 0){
            if(override){
                minHeight = pos;
            }else {
                telemetry.addData("limiting", "lower bound");
                power = 0;
            }
        }
        //prevent pos from going below MAX_SLIDES_POSITION
        if((pos > minHeight + OFFSET) && (power > 0)){
            if(override){
                minHeight = pos - OFFSET;
            }else {
                telemetry.addData("limiting", "upper bound");
                power = 0;
            }
        }

//        power *= 0.5;
        telemetry.addData("power", power);
        telemetry.addData("pos", pos);

        hardwareSlides.setPowers(power, power);


    }

    public void resetEncoders() {
        hardwareSlides.setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER, DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardwareSlides.setModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
