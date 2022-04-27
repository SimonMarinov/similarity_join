package com.marinsim.similarity_join.backEnd.ImgComparators;

import com.marinsim.similarity_join.backEnd.*;

import java.time.temporal.ValueRange;

public class EuclideanComparator implements ImgComparator {


    @Override
    public double compare(MyImage lfs, MyImage rhs) {
        var lClust = lfs.getFeatures();
        var rClust = rhs.getFeatures();

        int count = 0;

        for (Cluster lPos : lClust) {
            for (Cluster rPos : rClust) {

                if (Position.distanceBetween(lPos.calcCenterOfMass(), rPos.calcCenterOfMass()) < Values.getDistanceLimit()) {
                    count++;
                    break;
                }

            }
        }

        return 1 - ((double) count / (double) Values.getNumOfClusters());
    }
}
