package com.marinsim.similarity_join.backEnd.MyImages;

import com.marinsim.similarity_join.backEnd.MyImage;
import com.marinsim.similarity_join.backEnd.Position;
import com.stromberglabs.jopensurf.Surf;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class MyImageSurf extends MyImage {
    private BufferedImage image;

    public MyImageSurf(BufferedImage image, String name) {
        super(name);
        this.image = image;
    }

    @Override
    public void calculatePoints() {
        Surf surf = new Surf(image);
        var surfPoints = surf.getFreeOrientedInterestPoints();
        if (surfPoints.isEmpty()) throw new IllegalStateException();
        for (var sPoint : surfPoints) {
            points.add(new Position(sPoint));
        }
        keyPointsNormalization(image.getWidth(), image.getHeight());
    }
}
