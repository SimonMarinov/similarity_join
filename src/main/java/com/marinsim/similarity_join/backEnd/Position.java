package com.marinsim.similarity_join.backEnd;

import com.stromberglabs.jopensurf.SURFInterestPoint;
import org.opencv.core.KeyPoint;

public class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(KeyPoint point) {
        x = point.pt.x;
        y = point.pt.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Position(SURFInterestPoint point){
        x = point.getX();
        y = point.getY();
    }

    public static double distanceBetween(Position lfs, Position rhs){
        return Math.sqrt(Math.pow(lfs.x - rhs.x, 2) + Math.pow(lfs.y - rhs.y, 2));
    }
}
