package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class RealHardwareMechanumDrive implements HardwareMechanumDrive {
    private final DcMotor leftFrontMotor;
    private final DcMotor rightFrontMotor;
    private final DcMotor leftBackMotor;
    private final DcMotor rightBackMotor;
    public RealHardwareMechanumDrive(){
        leftFrontMotor=hardwareMap.get(DcMotor.class, "motorLF");
        rightFrontMotor=hardwareMap.get(DcMotor.class, "motorRF");
        leftBackMotor=hardwareMap.get(DcMotor.class, "motorLB");
        rightBackMotor=hardwareMap.get(DcMotor.class, "motorRB");
    }

    @Override
    public void setPower(double leftFrontPower, double rightFrontPower, double leftBackPower, double rightBackPower){
        leftFrontMotor.setPower(leftFrontPower);
        rightFrontMotor.setPower(rightFrontPower);
        leftBackMotor.setPower(leftBackPower);
        rightBackMotor.setPower(rightBackPower);
    }
    @Override
    public double[] getPower(){
        return new double[]{leftFrontMotor.getPower(),rightFrontMotor.getPower(),leftBackMotor.getPower(),rightBackMotor.getPower()};
    }
    @Override
    public int[] getCurrentPosition(){
        return new int[]{leftFrontMotor.getCurrentPosition(),rightFrontMotor.getCurrentPosition(),leftBackMotor.getCurrentPosition(),rightBackMotor.getCurrentPosition()};
    }
    @Override
    public int[] getTargetPosition(){
        return new int[]{leftFrontMotor.getTargetPosition(),rightFrontMotor.getTargetPosition(),leftBackMotor.getTargetPosition(),rightBackMotor.getTargetPosition()};
    }
    @Override
    public void setTargetPosition(int leftFrontPos, int rightFrontPos, int leftBackPos, int rightBackPos){
        leftFrontMotor.setTargetPosition(leftFrontPos);
        rightFrontMotor.setTargetPosition(rightFrontPos);
        leftBackMotor.setTargetPosition(leftBackPos);
        rightBackMotor.setTargetPosition(rightBackPos);
    }
    @Override
    public boolean[] isBusy(){
        return new boolean[]{leftFrontMotor.isBusy(),rightFrontMotor.isBusy(),leftBackMotor.isBusy(),rightBackMotor.isBusy()};
    }
    @Override
    public DcMotorSimple.Direction[] getDirection(){
        return new DcMotorSimple.Direction[]{leftFrontMotor.getDirection(),rightFrontMotor.getDirection(),leftBackMotor.getDirection(),rightBackMotor.getDirection()};
    }
    @Override
    public void setDirection(DcMotorSimple.Direction leftFrontDir, DcMotorSimple.Direction rightFrontDir, DcMotorSimple.Direction leftBackDir, DcMotorSimple.Direction rightBackDir){
        leftFrontMotor.setDirection(leftFrontDir);
        rightFrontMotor.setDirection(rightFrontDir);
        leftBackMotor.setDirection(leftBackDir);
        rightBackMotor.setDirection(rightBackDir);
    }
}
