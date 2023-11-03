package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;



public class RealHardwareSlides implements HardwareSlides{


        private DcMotor slidesMotor;

        public RealHardwareSlides(){
                slidesMotor=hardwareMap.get(DcMotor.class, "flyWheelMotor");
        }

        public void setDirection(DcMotorSimple.Direction direction){
                slidesMotor.setDirection(direction);
        }

        public DcMotorSimple.Direction getDirection(){
                return slidesMotor.getDirection();
        }




        @Override
        public void setSlidesPosition(int position) {
                slidesMotor.setTargetPosition(position);
        }

        @Override
        public double getSlidesPosition() {
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

        }

        public int getTargetPosition(){
                return slidesMotor.getTargetPosition();
        }

        public void setTargetPosition(int position){
                slidesMotor.setTargetPosition(position);
        }
}
