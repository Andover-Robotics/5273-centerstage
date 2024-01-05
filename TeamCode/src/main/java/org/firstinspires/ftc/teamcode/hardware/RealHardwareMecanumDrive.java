package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareMecanumDrive;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;

public class RealHardwareMecanumDrive implements HardwareMecanumDrive {
    private final DcMotor leftFrontMotor;
    private final DcMotor rightFrontMotor;
    private final DcMotor leftBackMotor;
    private final DcMotor rightBackMotor;
    private final Logger logger;
    public RealHardwareMecanumDrive(HardwareMap hardwareMap, Logger logger){
        this.logger = logger;
        leftFrontMotor=hardwareMap.get( DcMotor.class, "driveFL");
        rightFrontMotor=hardwareMap.get(DcMotor.class, "driveFR");
        leftBackMotor=hardwareMap.get(  DcMotor.class, "driveBL");
        rightBackMotor=hardwareMap.get( DcMotor.class, "driveBR");
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
    public boolean[] isBusy(){
        return new boolean[]{leftFrontMotor.isBusy(),rightFrontMotor.isBusy(),leftBackMotor.isBusy(),rightBackMotor.isBusy()};
    }
    private Direction[] convertDirections(DcMotorSimple.Direction[] directions){
        Direction[] convertedDirections = new Direction[directions.length];
        for(int i = 0; i < directions.length; i++){
            convertedDirections[i] = directions[i] == DcMotorSimple.Direction.FORWARD ? Direction.FORWARD : Direction.REVERSE;
        }
        return convertedDirections;
    }
    @Override
    public Direction[] getDirection(){
        return convertDirections(new DcMotor.Direction[]{leftFrontMotor.getDirection(),rightFrontMotor.getDirection(),leftBackMotor.getDirection(),rightBackMotor.getDirection()});
    }
    @Override
    public void setDirection(Direction leftFrontDir, Direction rightFrontDir, Direction leftBackDir, Direction rightBackDir){
        leftFrontMotor.setDirection(leftFrontDir == Direction.FORWARD ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
        rightFrontMotor.setDirection(rightFrontDir == Direction.FORWARD ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
        leftBackMotor.setDirection(leftBackDir == Direction.FORWARD ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
        rightBackMotor.setDirection(rightBackDir == Direction.FORWARD ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
    }
    public void resetEncoders(){
        leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void resetEncoders(boolean leftFrontReset, boolean rightFrontReset, boolean leftBackReset, boolean rightBackReset){
        if(leftFrontReset){
            leftFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if(rightFrontReset){
            rightFrontMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if(leftBackReset){
            leftBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        if(rightBackReset){
            rightBackMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
}
