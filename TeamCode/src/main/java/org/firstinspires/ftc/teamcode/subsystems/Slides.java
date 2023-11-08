package org.firstinspires.ftc.teamcode.subsystems;


import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.hardware.HardwareSlides;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Slides {

    public float extend; // Amount that the slides have been extended by.
    private HardwareSlides hardwareSlides;

    public Slides(float extend, HardwareSlides hardwareSlides){
        this.extend = extend;
        this.hardwareSlides = hardwareSlides;
        hardwareSlides.setMode(RUN_WITHOUT_ENCODER);
    }
}
