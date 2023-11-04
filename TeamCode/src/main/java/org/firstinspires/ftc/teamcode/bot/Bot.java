package org.firstinspires.ftc.teamcode.bot;

import org.firstinspires.ftc.teamcode.subsystems.*;

public class Bot {
    public Claw claw;
    public Camera camera;
    public Hanger hanger;
    public Launch launch;
    public Movement movement;
    public Slides slides;

    public Bot(HardwareBot hardwareBot) {
        assert hardwareBot.claw != null;
        assert hardwareBot.flyWheel != null;
        assert hardwareBot.hanger != null;
        assert hardwareBot.launch != null;
        assert hardwareBot.mecanumDrive != null;
        assert hardwareBot.slides != null;
//        claw = new Claw(hardwareBot.claw);
//        camera = new Camera();
//        hanger = new Hanger(hardwareBot.hanger);
//        launch = new Launch(hardwareBot.launch);
        movement = new Movement(0, 0, 0, hardwareBot.mecanumDrive);
//        slides = new Slides(hardwareBot.slides);
    }

    public void place_pixel_ground() {}
    public void place_pixel_backboard(int height) {}
}
