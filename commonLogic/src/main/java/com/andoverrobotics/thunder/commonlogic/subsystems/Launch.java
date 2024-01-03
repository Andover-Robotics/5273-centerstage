package com.andoverrobotics.thunder.commonlogic.subsystems;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareLaunch;

public class Launch {
    private class LaunchThread extends Thread{
        public void run(){
            launching=true;
            hardwareLaunch.setPosition(LAUNCH_ON);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hardwareLaunch.setPosition(LAUNCH_OFF);
            launching=false;
        }
    }
    private static final double LAUNCH_ON = 0.5;
    private static final double LAUNCH_OFF = 0.0;
    private boolean launching;
    private final HardwareLaunch hardwareLaunch;

    public Launch (HardwareLaunch hardwareLaunch) {
        this.hardwareLaunch = hardwareLaunch;
        hardwareLaunch.setPosition(LAUNCH_OFF);
    }
    public void executeIntent(boolean launch){
        if(launch && !launching){
            new LaunchThread().start();
        }
    }
}
