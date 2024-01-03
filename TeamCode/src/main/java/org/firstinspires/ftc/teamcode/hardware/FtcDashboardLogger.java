package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;

public class FtcDashboardLogger extends Logger {
    private final FtcDashboard ftcDashboard;
    private TelemetryPacket packet;
    public FtcDashboardLogger(){
        ftcDashboard = FtcDashboard.getInstance();
        packet = new TelemetryPacket();
    }


    @Override
    public void setProp(String label, Object value) {
        packet.put(label, value);
    }

    @Override
    public void close() {
        this.ftcDashboard.sendTelemetryPacket(packet);
    }

    @Override
    public void push() {
        ftcDashboard.sendTelemetryPacket(packet);
        packet = new TelemetryPacket();
    }

    public void drawSquare(double x, double y, double size, double rotation) {
        double[] xCoords = {x + size/2, x + size/2, x - size/2, x - size/2};
        double[] yCoords = {y + size/2, y - size/2, y - size/2, y + size/2};

        //rotate the vectors
        for(int i = 0; i < 4; i++) {
            double xCoord = xCoords[i];
            double yCoord = yCoords[i];
            xCoords[i] = xCoord * Math.cos(rotation) - yCoord * Math.sin(rotation);
            yCoords[i] = xCoord * Math.sin(rotation) + yCoord * Math.cos(rotation);
        }

        packet.fieldOverlay()
            .setFill("#ff5e97")
            .strokePolyline(xCoords, yCoords);

    }
}
