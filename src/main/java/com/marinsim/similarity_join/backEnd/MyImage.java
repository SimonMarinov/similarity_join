package com.marinsim.similarity_join.backEnd;

import com.stromberglabs.jopensurf.SURFInterestPoint;
import com.stromberglabs.jopensurf.Surf;

import java.awt.image.BufferedImage;
import java.util.List;

public class MyImage {
    private BufferedImage image;
    private String name;
    private List<SURFInterestPoint> points = null;
    private List<Cluster> fetures = null;

    public MyImage(BufferedImage image, String name) {
        this.image = image;
        this.name = name;
    }

    List<SURFInterestPoint> calculatePoints(){
      Surf surf = new Surf(image);
      points = surf.getFreeOrientedInterestPoints();
      return points;
    }

    void calcFetures(){
       fetures = KMeans.getFetures(points);
    }

    public final List<Cluster> getFeatures() {
        return fetures;
    }

    public String getName() {
        return name;
    }
}
