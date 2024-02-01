package org.firstinspires.ftc.teamcode.hardware;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwarePivot;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.util.DSConfig;
import com.andoverrobotics.thunder.commonlogic.util.Direction;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RealHardwarePivot implements HardwarePivot {
    private final DcMotor pivotMotor;
    private final Logger logger;

    public RealHardwarePivot(HardwareMap hardwareMap, Logger logger) {
        this.logger = logger;
        pivotMotor = hardwareMap.get(DcMotor.class, DSConfig.PIVOT_MOTOR);
    }
    @Override
    public int getPosition() {
        return pivotMotor.getCurrentPosition();
    }

    @Override
    public void setDirection(Direction direction) {
        pivotMotor.setDirection(direction == Direction.FORWARD ? DcMotor.Direction.FORWARD : DcMotor.Direction.REVERSE);
    }

    @Override
    public void setPower(double power) {
        pivotMotor.setPower(power);
    }

    @Override
    public void resetEncoders() {
        pivotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pivotMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
