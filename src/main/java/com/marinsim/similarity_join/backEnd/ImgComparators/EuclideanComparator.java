package com.marinsim.similarity_join.backEnd.ImgComparators;

import com.marinsim.similarity_join.backEnd.*;

import java.time.temporal.ValueRange;

public class EuclideanComparator implements ImgComparator {


    @Override
    public double calcComparison(MyImage lfs, MyImage rhs) {
        var lClust = lfs.getFeatures();
        var rClust = rhs.getFeatures();

        int count = 0;

        for (Cluster lPos: lClust) {
            for (Cluster rPos : rClust){

                if (Position.distanceBetween(lPos.getCenter(), rPos.getCenter()) < Values.getDistanceLimit()) {
                    count++;
                    break;
                }

            }
        }

        return (double) count / (double) Values.getNumOfClusters();
    }
}
