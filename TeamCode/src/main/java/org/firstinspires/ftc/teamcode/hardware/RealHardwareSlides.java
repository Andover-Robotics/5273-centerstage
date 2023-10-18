package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class RealHardwareSlides implements HardwareSlides{

        private Servo slidesServo;
        @Override
        public void setSlidesPosition(double position) {
                slidesServo.setPosition(position);
        }

        @Override
        public double getSlidesPosition() {
            //TODO: Implement for real hardware motors
            return slidesServo.getPosition();
        }
}
