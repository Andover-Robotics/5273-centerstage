package org.firstinspires.ftc.teamcode.hardware;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.example.commonlogic.hardwareInterfaces.Logger;

import java.util.Date;
import java.util.HashMap;

public class FtcTelemetryLogger extends Logger {
    private final Telemetry telemetry;

    private static class LogEntry {
        public final Object value;
        public final Date timestamp;

        public LogEntry(Object value, Date timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    protected HashMap<String, LogEntry> data;

    public FtcTelemetryLogger(Telemetry telemetry) {
        super();
        this.telemetry = telemetry;
        this.data = new HashMap<>();
    }

    @Override
    public void setProp(String label, Object value) {
        data.put(label, new LogEntry(value, new Date()));
    }

    @Override
    public void close() {
    }

    @Override
    public void push() {
        // loop over all entries in data and push them to the telemetry
        for (String key : data.keySet()) {
            LogEntry logEntry = data.get(key);
            if (logEntry == null) {
                continue;
            }
            telemetry.addData(key, logEntry.value.toString());
        }
        telemetry.update();
    }
}
