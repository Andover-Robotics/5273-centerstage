package com.andoverrobotics.thunder.simulator.hardwareInterfaces;

import java.util.Date;
import java.util.HashMap;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;

public class SimTelemetry extends Logger {
    public String finalData = "";

    private static class LogEntry {
        public Object value;
        public final Date timestamp;

        public LogEntry(Object value, Date timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    protected HashMap<String, LogEntry> data;

    public SimTelemetry() {
        super();
        this.data = new HashMap<>();
    }

    @Override
    public void setProp(String label, Object value) {
        data.put(label, new LogEntry(value, new Date()));
    }

    @Override
    public void close() {}

    @Override
    public void push() {
        StringBuilder sb = new StringBuilder();
        // loop over all entries in data and push them to the telemetry
        for (String key : data.keySet()) {
            LogEntry logEntry = data.get(key);
            if (logEntry == null) {
                continue;
            }
            if (logEntry.value == null) {
                logEntry.value = "null";
            }
            sb.append(key).append(": ").append(logEntry.value.toString()).append("\n");
        }
        synchronized (this) {
            finalData = sb.toString();
        }
    }
}
