package org.firstinspires.ftc.teamcode.hardware;

import com.example.commonlogic.hardwareInterfaces.HardwareSlides;
import com.example.commonlogic.hardwareInterfaces.Logger;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class RealHardwareSlides implements HardwareSlides {


        private final DcMotor slidesMotorLeft;
        private final DcMotor slidesMotorRight;
        private final Logger logger;
        public RealHardwareSlides(HardwareMap hardwareMap, Logger logger){
                this.logger = logger;
                slidesMotorLeft=hardwareMap.get(DcMotor.class, "slidesWheelMotorLeft");
                slidesMotorRight=hardwareMap.get(DcMotor.class, "slidesWheelMotorRight");
        }
        @Override
        public void resetEncoders() {
                slidesMotorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slidesMotorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                slidesMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                slidesMotorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        @Override
        public void setDirections(Direction directionLeft, Direction directionRight){
                slidesMotorLeft.setDirection(directionLeft == Direction.FORWARD ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
                slidesMotorRight.setDirection(directionRight == Direction.FORWARD ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
        }

        @Override
        public int[] getSlidesPositions() {
            return new int[]{slidesMotorLeft.getCurrentPosition(), slidesMotorRight.getCurrentPosition()};
        }
        @Override
        public void setPowers(double powerLeft, double powerRight){
                slidesMotorLeft.setPower(powerLeft);
                slidesMotorRight.setPower(powerRight);
        }
        @Override
        public double[] getPowers(){
                return new double[]{slidesMotorLeft.getPower(), slidesMotorRight.getPower()};
        }
}
