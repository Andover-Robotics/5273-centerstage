package com.example.commonlogic.hardwareInterfaces;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.io.File;
import java.util.HashMap;

public class FileLogger extends Logger {
    private static BufferedOutputStream logFile;

    private static class LogEntry {
        public final Object value;
        public final long timestamp;

        public LogEntry(Object value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    protected HashMap<String, LogEntry> data;

    public FileLogger(String logFilePath) {
        super();
        this.data = new HashMap<>();
        Date now = new Date();
        String fileName = logFilePath + "log" + now + ".txt";
        try {
            logFile = new BufferedOutputStream(new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
            File file = new File(logFilePath);
            if (file.mkdirs()) {
                System.out.println("Directory created: " + logFilePath);
            } else {
                System.out.println("Failed to create directory: " + logFilePath);
            }
        }
    }

    @Override
    public void setProp(String label, Object value) {
        data.put(label, new LogEntry(value, System.currentTimeMillis()));
    }

    @Override
    public void close() {
        try {
            logFile.flush();
            logFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void push() {
        // loop over all entries in data and push them to the log file
        for (String key : data.keySet()) {
            LogEntry logEntry = data.get(key);
            if(logEntry == null){
                continue;
            }
            try {
                if (logEntry.value != null) {
                    logFile.write(String.format("[%s] %s: %s\n", logEntry.timestamp, key, logEntry.value).getBytes());
                } else {
                    logFile.write(String.format("[%s] %s\n", logEntry.timestamp, key).getBytes());
                }
            } catch (IOException e) {
                System.out.println("Failed to write to log file");
            }
        }
    }
}
