package org.firstinspires.ftc.teamcode.bot;

import org.firstinspires.ftc.teamcode.hardware.FlyWheel;
import org.firstinspires.ftc.teamcode.hardware.HangingMotor;
import org.firstinspires.ftc.teamcode.hardware.HardwareClaw;
import org.firstinspires.ftc.teamcode.hardware.HardwareLaunch;
import org.firstinspires.ftc.teamcode.hardware.HardwareMechanumDrive;
import org.firstinspires.ftc.teamcode.hardware.HardwareSlides;
import org.firstinspires.ftc.teamcode.hardware.RealFlyWheel;
import org.firstinspires.ftc.teamcode.hardware.RealHangingMotor;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareClaw;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareLaunch;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareMechanumDrive;
import org.firstinspires.ftc.teamcode.hardware.RealHardwareSlides;

public class RealBot implements Bot {
    FlyWheel flyWheel;
    HangingMotor hangingMotor;
    HardwareSlides hardwareSlides;
    HardwareMechanumDrive hardwareMechanumDrive;
    HardwareClaw hardwareClaw;
    HardwareLaunch hardwareLaunch;

    public RealBot() {
        flyWheel = new RealFlyWheel();
        hangingMotor = new RealHangingMotor();
        hardwareSlides = new RealHardwareSlides();
        hardwareMechanumDrive = new RealHardwareMechanumDrive();
        hardwareClaw = new RealHardwareClaw();
        hardwareLaunch = new RealHardwareLaunch();
    }
}
