package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="NotRealOpmode", group="Linear Opmode")
public class NotRealOpmode extends LinearOpMode {
    public void runOpMode() {
        telemetry.addLine("doing a whole lot of nothing");
    }
}
