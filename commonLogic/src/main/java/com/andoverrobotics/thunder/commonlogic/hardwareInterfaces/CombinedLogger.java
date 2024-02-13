package com.andoverrobotics.thunder.commonlogic.hardwareInterfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinedLogger extends Logger {
    private final List<Logger> loggers;

    public CombinedLogger(Logger... loggers) {
        this.loggers = new ArrayList<>(Arrays.asList(loggers));
    }

    public void addLogger(Logger logger) {
        this.loggers.add(logger);
    }

    @Override
    public void setProp(String label, Object value) {
        for (Logger logger : loggers) {
            logger.setProp(label, value);
        }
    }

    @Override
    public void close() {
        for (Logger logger : loggers) {
            logger.close();
        }
    }

    @Override
    public void push() {
        for (Logger logger : loggers) {
            logger.push();
        }
    }
}
