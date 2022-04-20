package com.marinsim.similarity_join.backEnd.MyImages;

import com.marinsim.similarity_join.backEnd.MyImage;
import com.marinsim.similarity_join.backEnd.Position;
import org.opencv.core.KeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.SIFT;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.util.List;

public class MyImageSift extends MyImage {
    private Mat matImg;

    public MyImageSift(Mat matImage, String name) {
        super(name);
        matImg = matImage;
    }

    @Override
    public void calculatePoints() {
        MatOfKeyPoint keyPoints = new MatOfKeyPoint();

        SIFT sift = SIFT.create();
        sift.detect(matImg, keyPoints);

        List<KeyPoint> keyPointsList = keyPoints.toList();
        for (var point : keyPointsList) {
            points.add(new Position(point));
        }

      //  keyPointsNormalization(matImg.width(), matImg.height());
    }
}
