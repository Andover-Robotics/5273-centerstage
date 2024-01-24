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

public class RealHardwareCamera extends OpenCvPipeline implements HardwareCamera {
    private static final Rect leftBound = new Rect(); // TODO: tune each for x,y,width,height
    private static final Rect centerBound = new Rect();
    private static final Rect rightBound = new Rect();
    private final int alliance; // 1 is red, 2 is blue
    private final int[] totalMarkerPoints = new int[3];
    private OpenCvCamera camera;
    public RealHardwareCamera(HardwareMap hardwareMap, Alliance alliance, Logger logger){
        this.alliance = alliance==Alliance.RED?1:2;
        WebcamName camName = hardwareMap.get(WebcamName.class, "camera");
        camera = OpenCvCameraFactory.getInstance().createWebcam(camName);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {
                logger.setProp("Camera error:", errorCode);
            }
        });
        camera.setPipeline(this);
    }
    public Mat processFrame(Mat input){
        Mat frame = new Mat();
        Imgproc.cvtColor(input, frame, Imgproc.COLOR_RGB2YCrCb);
        Mat[] frames = {
            input.submat(leftBound),
            input.submat(centerBound),
            input.submat(rightBound)
        };
        double[] markerPoints = new double[3];
        double max = 0;
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < frames[i].rows(); j++) {
                for (int k = 0; k < frames[i].cols(); k++) {
                    markerPoints[i] += frames[i].get(j, k)[alliance];
                }
            }
            max = Math.max(max, markerPoints[i]);
        }
        for(int i=0; i<3; i++){
            if(markerPoints[i]==max){
                totalMarkerPoints[i]++;
            }
        }
        return input;
    }
    public MarkerPos getMarkerPos(){
        camera = null; // probably stops it
        int max=0;
        for(int i=0; i<3; i++){
            max=Math.max(max,totalMarkerPoints[i]);
        }
        if(max==totalMarkerPoints[2]){
            return MarkerPos.RIGHT;
        }else if(max==totalMarkerPoints[1]){
            return MarkerPos.CENTER;
        }else{
            return MarkerPos.LEFT;
        }
    }
}