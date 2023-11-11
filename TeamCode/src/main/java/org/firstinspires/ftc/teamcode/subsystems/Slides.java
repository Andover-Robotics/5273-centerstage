package org.firstinspires.ftc.teamcode.subsystems;



import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.HardwareSlides;


public class Slides {
    private final HardwareSlides hardwareSlides;
    private static final double ENCODER_RES = 384.5;
    private static final double REAL_MAX_SLIDES_POSITION = 3900;
    private static final double MAX_SLIDES_POSITION = REAL_MAX_SLIDES_POSITION - 250;
    private double startingPositionLeft = 0;
    private double startingPositionRight = 0;
    private final Telemetry telemetry;

    public Slides(HardwareSlides hardwareSlides, Telemetry telemetry){
        this.telemetry = telemetry;
        this.hardwareSlides = hardwareSlides;
        hardwareSlides.setModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hardwareSlides.setDirections(DcMotor.Direction.FORWARD, DcMotor.Direction.REVERSE);
        int[] positions = hardwareSlides.getSlidesPositions();
        startingPositionLeft = positions[0];
        startingPositionRight = positions[1];

    }

    public void executeIntent(double power){
        int[] positions = hardwareSlides.getSlidesPositions();
        //positions[0] - startingPositionLeft > MAX_SLIDES_POSITION ||
        if(positions[1] - startingPositionRight > MAX_SLIDES_POSITION) {
            power = 0;
        }else if(positions[1] - startingPositionRight < 0){
            power = 0;
        }
        telemetry.addData("Slides Position Left", positions[0] - startingPositionLeft);
        telemetry.addData("Slides Position Right", positions[1] - startingPositionRight);
        hardwareSlides.setPowers(power, power);
    }

}
