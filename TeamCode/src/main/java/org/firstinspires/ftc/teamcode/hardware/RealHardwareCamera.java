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
    private static final Rect leftBound = new Rect(1025,300,200,200); // TODO: tune each for x,y,width,height
    private static final Rect centerBound = new Rect(375,325,200,200);
    private static final int COLOR_THRESHOLD = 5700000; // if both are below threshold then it is right
    private final int alliance; // 1 is red, 2 is blue
    private MarkerPos markerPos;
    private final OpenCvCamera camera;
    private final Logger logger;
    private final int[] logVals = new int[2];
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

        Imgproc.rectangle(input,new Point(leftBound.x,leftBound.y),new Point(leftBound.x+leftBound.width,leftBound.y+leftBound.height),new Scalar(0,255,0),5);
        Imgproc.rectangle(input,new Point(centerBound.x,centerBound.y),new Point(centerBound.x+centerBound.width,centerBound.y+centerBound.height),new Scalar(0,255,255),5);

        int[] markerPoints = new int[2];
        for(int i = 0; i < 2; i++) {
            for (int j = 0; j < frames[i].rows(); j++) {
                for (int k = 0; k < frames[i].cols(); k++) {
                    markerPoints[i] += (int)frames[i].get(j, k)[alliance];
                }
            }
            logVals[i] = markerPoints[i];
        }
        if(markerPoints[0]<COLOR_THRESHOLD&&markerPoints[1]<COLOR_THRESHOLD){
            markerPos = MarkerPos.RIGHT;
        }else if(markerPoints[1]>=markerPoints[0]){
            markerPos = MarkerPos.CENTER;
        }else{
            markerPos = MarkerPos.LEFT;
        }
        camera.closeCameraDeviceAsync(() -> {});
        return input;
    }
    public MarkerPos getMarkerPos(){
        return markerPos;
    }
    public int[] getLogVals(){
        return logVals;
    }
}