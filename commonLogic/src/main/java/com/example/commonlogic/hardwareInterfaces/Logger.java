package com.example.commonlogic.hardwareInterfaces;

public abstract class Logger {
    public abstract void setProp(String label, Object value);
    public abstract void close();
    public abstract void push();
}
