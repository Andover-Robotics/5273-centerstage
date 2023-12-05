package org.firstinspires.ftc.teamcode.bot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.*;

public class Bot {
    public Claw claw;
    public Camera camera;
    public Hanger hanger;
    public Launch launch;
    public Movement movement;
    public Slides slides;
    public Telemetry telemetry;
    public Intake intake;

    public Bot(HardwareBot hardwareBot, Telemetry telemetry) {
        assert hardwareBot.claw != null;
//        assert hardwareBot.clawFlipper != null;
        assert hardwareBot.flyWheel != null;
//        assert hardwareBot.hanger != null;
//        assert hardwareBot.launch != null;
        assert hardwareBot.mecanumDrive != null;
//        assert hardwareBot.slides != null;
        claw = new Claw(hardwareBot.claw, hardwareBot.clawFlipper, telemetry);
        intake = new Intake(hardwareBot.flyWheel);
//        camera = new Camera();
//        hanger = new Hanger(hardwareBot.hanger);
//        launch = new Launch(hardwareBot.launch);
        movement = new Movement(12, 60, Math.PI, hardwareBot.mecanumDrive, telemetry);
        slides = new Slides(hardwareBot.slides, telemetry);
    }

    public void place_pixel_ground() {}
    public void place_pixel_backboard(int height) {}
}
