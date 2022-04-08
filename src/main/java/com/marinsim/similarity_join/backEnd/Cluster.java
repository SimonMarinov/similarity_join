package com.marinsim.similarity_join.backEnd;

import com.stromberglabs.jopensurf.SURFInterestPoint;

import java.util.List;

public class Cluster {
    private List<SURFInterestPoint> points;
    private Position centerOfMass;
    private Double weight= Double.MIN_VALUE;

    public Cluster() {
        centerOfMass=null;
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

    private void calcCenterOfMass(){
        if (points.isEmpty()) {
            throw new IllegalStateException();
        }
        float sumX = 0;
        float sumY = 0;
        for (SURFInterestPoint point : points) {
            sumX += point.getX();
            sumY += point.getY();
        }
        centerOfMass = new Position(sumX/points.size(), sumY/points.size());
    }


    public Position getCenterOfMass() {
        if (centerOfMass==null){
            calcCenterOfMass();
        }
        return centerOfMass;
    }

    public List<SURFInterestPoint> getPoints() {
        return points;
    }
}
