package com.marinsim.similarity_join.backEnd;

import java.util.ArrayList;
import java.util.List;

public abstract class MyImage {
    protected String name;
    protected List<Position> points = null;
    protected List<Cluster> features = null;

    public MyImage(String name) {
        this.name = name;
        points = new ArrayList<>();
        features = new ArrayList<>();
    }

    abstract public void calculatePoints();

    protected void keyPointsNormalization(int imgWidth, int imgHeight) {
        List<Position> normalPoints = new ArrayList<>();
        for (Position point : points) {
            float x = ((float) point.getX() / (float) imgWidth) * (float) 100;
            float y = ((float) point.getY()/ (float) imgHeight) * (float) 100;

            Position normalPoint = new Position(x, y);
            normalPoints.add(normalPoint);
        }
        points.clear();
        points.addAll(normalPoints);
    }

    public void calcFeatures(){
       features = KMeans.getFetures(points);
    }

    public final List<Cluster> getFeatures() {
        return features;
    }

    public String getName() {
        return name;
    }
}
