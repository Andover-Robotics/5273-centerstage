package com.andoverrobotics.thunder.simulator;

import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareController;
import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimTelemetry;

public abstract class SimLinearOpMode {
    private Simulation sim;
    public SimHardwareMap hardwareMap;
    private GamepadManager gamepadManager;
    public SimHardwareController gamepad1;
    public SimHardwareController gamepad2;
    public boolean waiting = false;
    public SimTelemetry telemetry = new SimTelemetry();
    public SimLinearOpMode() {
        sim = new Simulation(18, 18, 20);
        hardwareMap = new SimHardwareMap(sim);
        gamepadManager = new GamepadManager();
        gamepad1 = gamepadManager.getGamepad(1);
        gamepad2 = gamepadManager.getGamepad(2);
    }

    public boolean opModeIsActive() {
        return true;
    }

    public synchronized void waitForStart(){
        waiting = true;
        while (waiting) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public abstract void runOpmode();
}
