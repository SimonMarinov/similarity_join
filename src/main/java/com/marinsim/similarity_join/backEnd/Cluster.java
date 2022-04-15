package com.marinsim.similarity_join.backEnd;

import com.stromberglabs.jopensurf.SURFInterestPoint;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private List<SURFInterestPoint> points;
    private Double weight= Double.MIN_VALUE;

    public Cluster() {
        points = new ArrayList<>();
    }

    public Cluster(List<SURFInterestPoint> points) {
        this.points = points;
    }

    void calcWeight(int totalNumOfPointsInImg) {
        weight = (double) points.size() / (double) totalNumOfPointsInImg;
    }

    public final double getWeight() throws IllegalStateException {
        if (weight.equals(Double.MIN_VALUE)) {
            throw new IllegalStateException();
        }
        return weight;
    }

    public Position calcCenterOfMass(){
        if (points.isEmpty()) {
            return null;
        }
        float sumX = 0;
        float sumY = 0;
        for (SURFInterestPoint point : points) {
            sumX += point.getX();
            sumY += point.getY();
        }
        return new Position(sumX/points.size(), sumY/points.size());
    }


    public List<SURFInterestPoint> getPoints() {
        return points;
    }
}
