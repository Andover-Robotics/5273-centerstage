package com.andoverrobotics.thunder.commonlogic.hardwareInterfaces;

public abstract class Logger {
    public abstract void setProp(String label, Object value);

    public void log(String message) {
        setProp(message, null);
    }

    public abstract void close();

    public abstract void push();
}
