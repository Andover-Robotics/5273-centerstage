package com.andoverrobotics.thunder.simulator;

import com.andoverrobotics.thunder.simulator.hardwareInterfaces.SimHardwareController;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.ArrayList;

public class GamepadManager implements Runnable {
    private final ArrayList<Controller> controllers;
    private final SimHardwareController[] simGamepads;
    private final int[] assignments;

    public GamepadManager() {
        controllers = new ArrayList<>();
        simGamepads = new SimHardwareController[2];
        Controller[] rawControllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (Controller controller : rawControllers) {
            System.out.println(controller.getName());
            if (controller.getType() == Controller.Type.GAMEPAD) {
                controllers.add(controller);
            }
        }
        assignments = new int[controllers.size()];
        for (int i = 0; i < controllers.size(); i++) {
            assignments[i] = -1;
        }
        for (Controller controller : controllers) {
            System.out.println(controller.getName());
        }
        //start a new thread to constantly poll and process input events for all controllers connected
        Thread thread = new Thread(this);
        thread.start();
    }

    //this function takes in a 1 indexed index of which controller to get.
    //for now there is only controller 1 and 2 accessed with indices 1 and 2 respectively
    public SimHardwareController getGamepad(int index) {
        assert 0 < index && index <= simGamepads.length;
        return simGamepads[index-1];
    }
    @Override
    public void run() {
        while (true) {
            //only print every 10000 ms
            for (int i = 0; i < controllers.size(); i++) {
                Controller controller = controllers.get(i);
                boolean valid = controller.poll();
                if (!valid) {
                    continue;
                }
                if (
                    controller.getComponent(Component.Identifier.Button.A).getPollData() == 1 &&
                    controller.getComponent(Component.Identifier.Button.START).getPollData() == 1
                ){
                    assignments[i] = 0;
                }else if(
                    controller.getComponent(Component.Identifier.Button.B).getPollData() == 1 &&
                    controller.getComponent(Component.Identifier.Button.START).getPollData() == 1
                ){
                    assignments[i] = 1;
                }
                if (assignments[i] == -1) {
                    continue;
                }
                SimHardwareController simGamepad = simGamepads[assignments[i]];
                if(simGamepad == null){
                    simGamepad = new SimHardwareController();
                    simGamepads[assignments[i]] = simGamepad;
                }
                simGamepad.leftStickX = controller.getComponent(Component.Identifier.Axis.X).getPollData();
                simGamepad.leftStickY = controller.getComponent(Component.Identifier.Axis.Y).getPollData();
                simGamepad.rightStickX = controller.getComponent(Component.Identifier.Axis.RX).getPollData();
                simGamepad.rightStickY = controller.getComponent(Component.Identifier.Axis.RY).getPollData();

                simGamepad.leftTrigger = (controller.getComponent(Component.Identifier.Axis.Z).getPollData() + 1) / 2;
                simGamepad.rightTrigger = (controller.getComponent(Component.Identifier.Axis.RZ).getPollData() + 1) / 2;

                simGamepad.leftBumper = controller.getComponent(Component.Identifier.Button.LEFT_THUMB).getPollData() == 1;
                simGamepad.rightBumper = controller.getComponent(Component.Identifier.Button.RIGHT_THUMB).getPollData() == 1;

                simGamepad.a = controller.getComponent(Component.Identifier.Button.A).getPollData() == 1;
                simGamepad.b = controller.getComponent(Component.Identifier.Button.B).getPollData() == 1;
                simGamepad.x = controller.getComponent(Component.Identifier.Button.X).getPollData() == 1;
                simGamepad.y = controller.getComponent(Component.Identifier.Button.Y).getPollData() == 1;

                simGamepad.dpadUp = controller.getComponent(Component.Identifier.Axis.POV).getPollData() == 0.25;
                simGamepad.dpadDown = controller.getComponent(Component.Identifier.Axis.POV).getPollData() == 0.75;
                simGamepad.dpadLeft = controller.getComponent(Component.Identifier.Axis.POV).getPollData() == 1;
                simGamepad.dpadRight = controller.getComponent(Component.Identifier.Axis.POV).getPollData() == 0.5;

                simGamepad.leftStickButton = controller.getComponent(Component.Identifier.Button.LEFT_THUMB3).getPollData() == 1;
                simGamepad.rightStickButton = controller.getComponent(Component.Identifier.Button.RIGHT_THUMB3).getPollData() == 1;

            }
        }
    }
}
