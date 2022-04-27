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
            //using linear mapping
            double x =  point.getX() * Values.IMG_WIDTH_LEN / imgWidth;
            double y = point.getY() * Values.IMG_WIDTH_LEN / imgHeight;

            Position normalPoint = new Position(x, y);
            normalPoints.add(normalPoint);
        }
        points.clear();
        points.addAll(normalPoints);
    }

    public int nofExtractedPoints(){
        return points.size();
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
