package org.firstinspires.ftc.teamcode.hardware;

import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.Logger;
import com.andoverrobotics.thunder.commonlogic.util.Alliance;
import com.andoverrobotics.thunder.commonlogic.util.MarkerPos;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import com.andoverrobotics.thunder.commonlogic.hardwareInterfaces.HardwareCamera;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.opencv.core.Point;
import org.opencv.core.Scalar;

public class RealHardwareCamera extends OpenCvPipeline implements HardwareCamera {
    private static final Rect leftBound = new Rect(975,280,300,320);
    private static final Rect centerBound = new Rect(275,350,550,200);
    private static final int percent_diff_limit = 2;
    private final int alliance; // 1 is red, 2 is blue
    private MarkerPos markerPos;
    private final OpenCvCamera camera;
    private final Logger logger;
    private final double[] logVals = new double[2];
    public RealHardwareCamera(HardwareMap hardwareMap, Alliance alliance, Logger logger){
        this.logger = logger;
        this.alliance = alliance==Alliance.RED?1:2;
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "camera"));
        camera.setPipeline(this);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {
                logger.setProp("Camera error:", errorCode); logger.push();
            }
        });
    }
    public Mat processFrame(Mat input){

        Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2YCrCb);
        Mat[] frames = {
                input.submat(leftBound),
                input.submat(centerBound)
        };

//        Imgproc.rectangle(input,new Point(leftBound.x,leftBound.y),new Point(leftBound.x+leftBound.width,leftBound.y+leftBound.height),new Scalar(0,255,0),5);
//        Imgproc.rectangle(input,new Point(centerBound.x,centerBound.y),new Point(centerBound.x+centerBound.width,centerBound.y+centerBound.height),new Scalar(0,255,255),5);
        double[] tmp_vals = new double[2];
        int[] markerPoints = new int[2];
        for(int i = 0; i < 2; i++) {
            for (int j = 0; j < frames[i].rows(); j++) {
                for (int k = 0; k < frames[i].cols(); k++) {
                    markerPoints[i] += (int)frames[i].get(j, k)[alliance];
                    double val = frames[i].get(j, k)[alliance];
                    frames[i].put(j, k, val, val, val);
                }
            }
            tmp_vals[i] = markerPoints[i];
        }


        //normalize the values for the size of the detenction area
        tmp_vals[0] /= (double)leftBound.width * leftBound.height;
        tmp_vals[1] /= (double)centerBound.width * centerBound.height;
        //assign the final values
        //can't use the logVals as intermediate vars because they might be read during processing
        logVals[0] = tmp_vals[0];
        logVals[1] = tmp_vals[1];
        //percent difference: (|a-b|/((a+b)/2))*100
        double percent_diff = Math.abs(logVals[0]-logVals[1])/((logVals[0]+logVals[1])/2.0)*100;
        if(percent_diff < percent_diff_limit){
            markerPos = MarkerPos.RIGHT;
        }else{
            if(logVals[0]>logVals[1]){
                markerPos = MarkerPos.LEFT;
            }else{
                markerPos = MarkerPos.CENTER;
            }
        }
//        camera.closeCameraDeviceAsync(() -> {});
        return input;
    }
    public MarkerPos getMarkerPos(){
        return markerPos;
    }
    public double[] getLogVals(){
        return logVals;
    }
}