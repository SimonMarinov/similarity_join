package com.marinsim.similarity_join.backEnd;

import com.stromberglabs.jopensurf.SURFInterestPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * class to encapsulate functions to calculate main features of img by Kmeans algorithm
 */
public class KMeans {
    /**
     * implementation of Kmeans algorythm
     * @param imgPoints
     * @return main features of imgPoints calculated by Kmeans alogrthm
     */
    static List<Cluster> getFetures(List<SURFInterestPoint> imgPoints){

        int iteration = 0;

        //first is center of centroid
        List<Position> oldCenters;
        List<Pair<Position, Cluster>> centroids = randomCentroids(imgPoints);

        while (iteration < Values.getMaxNumOfIterations()){

            calcCentroids(centroids, imgPoints);

            oldCenters = recalcNewCenterOfCentroids(centroids);

            if (!clusterChaged(oldCenters, centroids)){
                break;
            }

            iteration++;

        }

        List<Cluster> ret = new ArrayList<>();

        for (Pair<Position, Cluster> centroid: centroids) {
            centroid.second.calcWeight(imgPoints.size());
            ret.add(centroid.second);

        }


        return ret;
    }

    /**
     * if centroid centers did not change we can assume first that clusters within centroids won't change either in next iteration
     * or second only insignificant points will switch within cluster if process of recalculation of cluster within cycle will continue
     * @param oldCenters
     * @param centroids
     * @return
     */
    private static boolean clusterChaged(List<Position> oldCenters, List<Pair<Position, Cluster>> centroids) {
        for (int i = 0; i < centroids.size(); i++) {
            if (!oldCenters.get(i).isOnSamePlace(centroids.get(i).first)) {
                return true;
            }
        }
        return false;
    }

    /**
     * reasign centroids and returns old centers of centroids
     * @param centroids
     * @return old centers of centroids
     */
    private static List<Position> recalcNewCenterOfCentroids(List<Pair<Position, Cluster>> centroids) {
        List<Pair<Position, Cluster>> newCentroids = new ArrayList<>();
        List<Position> oldCenters = new ArrayList<>();

        for (Pair<Position, Cluster> centroid: centroids) {
            newCentroids.add(new Pair<>(centroid.second.getCenterOfMass(), new Cluster()));
            oldCenters.add(centroid.first);
        }
        centroids = newCentroids;

        return oldCenters;
    }

    private static void calcCentroids(List<Pair<Position, Cluster>> centroids, List<SURFInterestPoint> imgPoints) {

        for (SURFInterestPoint surfPos : imgPoints) {
            Position position = new Position(surfPos);

            double clousestDis = Double.MAX_VALUE;
            int idxOfFoundCentroid = 0;

            for (int idx = 0; idx < centroids.size(); idx++) {

                Position center = centroids.get(idx).first;
                double curDis= Position.distanceBetween(position, center);

                if (curDis < clousestDis){
                    clousestDis= curDis;
                    idxOfFoundCentroid = idx;
                }
            }

            centroids.get(idxOfFoundCentroid).second.getPoints().add(surfPos);
        }
    }

    private static List<Pair<Position,Cluster>> randomCentroids(List<SURFInterestPoint> imgPoints) {
        List<Pair<Position, Cluster>> res = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < Values.getNumOfClusters(); i++) {
            Pair<Position, Cluster> ins = new Pair<>(randPos(imgPoints, rand), new Cluster());
            res.add(ins);
        }

        return res;
    }

    private static Position randPos(List<SURFInterestPoint> imgPoints, Random rand) {
        float x = imgPoints.get(Math.abs(rand.nextInt()) % imgPoints.size()).getX() + rand.nextFloat();
        float y = imgPoints.get(Math.abs(rand.nextInt()) % imgPoints.size()).getY() + rand.nextFloat();
        return new Position(x, y);
    }


}
