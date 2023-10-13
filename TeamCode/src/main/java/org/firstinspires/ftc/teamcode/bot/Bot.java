package org.firstinspires.ftc.teamcode.bot;

public class Bot {
    public HardwareBot hardwareBot;
    public Bot(HardwareBot hardwareBot) {
        assert hardwareBot.claw != null;
        assert hardwareBot.flyWheel != null;
        assert hardwareBot.hanger != null;
        assert hardwareBot.launch != null;
        assert hardwareBot.mechanumDrive != null;
        assert hardwareBot.slides != null;
        this.hardwareBot = hardwareBot;
    }

    public void place_pixel_ground() {}
    public void place_pixel_backboard(int height) {}
}
