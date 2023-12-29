package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardwareInterfaces.HardwareSlides;
import org.firstinspires.ftc.teamcode.hardwareInterfaces.Logger;


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
        public void setDirections(DcMotorSimple.Direction directionLeft, DcMotorSimple.Direction directionRight){
                slidesMotorLeft.setDirection(directionLeft);
                slidesMotorRight.setDirection(directionRight);
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

        @Override
        public void setModes(DcMotor.RunMode runModeLeft, DcMotor.RunMode runModeRight) {
            slidesMotorLeft.setMode(runModeLeft);
            slidesMotorRight.setMode(runModeRight);
        }
}
