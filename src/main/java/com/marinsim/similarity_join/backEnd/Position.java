package com.marinsim.similarity_join.backEnd;

import com.stromberglabs.jopensurf.SURFInterestPoint;

public class Position {
    public float x;
    public float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Position(SURFInterestPoint point){
        x = point.getX();
        y = point.getY();
    }

    public static double distanceBetween(Position lfs, Position rhs){
       double dis = Math.sqrt(Math.pow(lfs.x - rhs.x, 2) + Math.pow(lfs.y + rhs.y, 2));
       if (dis == Double.NaN ){
           double kk = Math.pow(lfs.x - rhs.x, 2) + Math.pow(lfs.y + rhs.y, 2);
           return 0;
       }
        return dis;
    }
}
