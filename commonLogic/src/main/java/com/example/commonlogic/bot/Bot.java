package com.example.commonlogic.bot;

import com.example.commonlogic.hardwareInterfaces.Logger;


import com.example.commonlogic.subsystems.Camera;
import com.example.commonlogic.subsystems.Claw;
import com.example.commonlogic.subsystems.Hanger;
import com.example.commonlogic.subsystems.Intake;
import com.example.commonlogic.subsystems.Launch;
import com.example.commonlogic.subsystems.Movement;
import com.example.commonlogic.subsystems.Slides;


public class Bot {
    public Claw claw;
    public Camera camera;
    public Hanger hanger;
    public Launch launch;
    public Movement movement;
    public Slides slides;
    public Intake intake;

    public Bot(HardwareBot hardwareBot, Logger logger) {
        assert hardwareBot.claw != null;
        assert hardwareBot.clawFlipper != null;
        assert hardwareBot.flyWheel != null;
//        assert hardwareBot.hanger != null;
        assert hardwareBot.launch != null;
        assert hardwareBot.mecanumDrive != null;
        assert hardwareBot.slides != null;
        claw = new Claw(hardwareBot.claw, hardwareBot.clawFlipper, logger);
        intake = new Intake(hardwareBot.flyWheel);
//        camera = new Camera();
//        hanger = new Hanger(hardwareBot.hanger);
        launch = new Launch(hardwareBot.launch);
        movement = new Movement(0, 0, 0, hardwareBot.mecanumDrive, logger);
        slides = new Slides(hardwareBot.slides, logger);
    }

    public void place_pixel_ground() {
        //TODO
    }
    public void place_pixel_backboard(int x, int height) {
        //TODO
    }
}
