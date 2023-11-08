package org.firstinspires.ftc.teamcode.subsystems;


import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;
import org.firstinspires.ftc.teamcode.hardware.HardwareSlides;
import org.firstinspires.ftc.teamcode.input.Intent;


public class Slides {

    public double extend; // Amount to extend the slides by.
    private final HardwareSlides hardwareSlides;

    public Slides(double extend, HardwareSlides hardwareSlides){
        this.extend = extend;
        this.hardwareSlides = hardwareSlides;
        hardwareSlides.setMode(RUN_WITHOUT_ENCODER);
    }

    public void executeIntent(double power){
        hardwareSlides.setPower(power);
    }

}
