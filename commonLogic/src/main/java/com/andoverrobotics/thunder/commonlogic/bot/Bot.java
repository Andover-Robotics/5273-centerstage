package com.andoverrobotics.thunder.commonlogic.bot;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;


import com.andoverrobotics.thunder.commonlogic.subsystems.Camera;
import com.andoverrobotics.thunder.commonlogic.subsystems.Claw;
import com.andoverrobotics.thunder.commonlogic.subsystems.Intake;
import com.andoverrobotics.thunder.commonlogic.subsystems.Launch;
import com.andoverrobotics.thunder.commonlogic.subsystems.Movement;
import com.andoverrobotics.thunder.commonlogic.subsystems.Pivot;
import com.andoverrobotics.thunder.commonlogic.subsystems.Slides;


public class Bot {
    public Claw claw;
    public Camera camera;
    public Launch launch;
    public Movement movement;
    public Slides slides;
    public Intake intake;
    public Pivot pivot;

    public Bot(HardwareBot hardwareBot, Logger logger) {
        assert hardwareBot.claw != null;
        assert hardwareBot.clawFlipper != null;
        assert hardwareBot.flyWheel != null;
        assert hardwareBot.launch != null;
        assert hardwareBot.mecanumDrive != null;
        assert hardwareBot.slides != null;
        assert hardwareBot.pivot!=null;
        claw = new Claw(hardwareBot.claw, hardwareBot.clawFlipper, logger);
        intake = new Intake(hardwareBot.flyWheel);
        launch = new Launch(hardwareBot.launch);
        movement = new Movement(0, 0, 0, hardwareBot.mecanumDrive, logger);
        slides = new Slides(hardwareBot.slides, logger);
        pivot = new Pivot(hardwareBot.pivot, logger);
        if(hardwareBot.camera!=null) {
            camera = new Camera(hardwareBot.camera);
        }
    }

    public void place_pixel_ground() {
        //TODO
    }
    public void place_pixel_backboard(int x, int height) {
        //TODO
    }
}
