package com.andoverrobotics.thunder.simulator;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;

public class ConsoleLogger extends Logger {
    private String buffer;
    @Override
    public void setProp(String label, Object value) {
        if(value != null){
            buffer += label + ": " + value.toString() + "\n";
        } else {
            buffer += label;
        }
    }

    @Override
    public void close() {
        System.out.println(buffer);
    }

    @Override
    public void push() {
        System.out.println(buffer);
        buffer = "";
    }
}
