package org.firstinspires.ftc.teamcode.hardware;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class RealHardwareIMU {
    private final BNO055IMU imu;
    public RealHardwareIMU(){
        imu=hardwareMap.get(BNO055IMU.class, "imu");
        final BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;
        imu.initialize(parameters);
    }
    public Orientation getAnglularOrientation(){
        return imu.getAngularOrientation();
    }
}