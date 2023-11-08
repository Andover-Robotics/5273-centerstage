package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class RealHardwareSlides implements HardwareSlides{


        private DcMotor slidesMotor;

        public RealHardwareSlides(HardwareMap hardwareMap){
                slidesMotor=hardwareMap.get(DcMotor.class, "flyWheelMotor");
        }

        public void setDirection(DcMotorSimple.Direction direction){
                slidesMotor.setDirection(direction);
        }

        @Override
        public void setSlidesPosition(int position) {
                slidesMotor.setTargetPosition(position);
        }

        @Override
        public int getSlidesPosition() {
            return slidesMotor.getCurrentPosition();
        }

        public void setPower(double power){
                slidesMotor.setPower(power);
        }
        public double getPower(){
                return slidesMotor.getPower();
        }

        @Override
        public void setMode(DcMotor.RunMode runMode) {
                slidesMotor.setMode(runMode);
        }

        public int getTargetPosition(){
                return slidesMotor.getTargetPosition();
        }

        public void setTargetPosition(int position){
                slidesMotor.setTargetPosition(position);
        }
}
