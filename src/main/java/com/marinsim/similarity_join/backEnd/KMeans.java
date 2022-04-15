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
    static List<Cluster> getFetures(List<Position> imgPoints){

        int iteration = 0;

        if (Values.getNumOfClusters() > imgPoints.size()){
            throw new IllegalArgumentException("not enough points from surf need to lower number of clusters");
        } else if (Values.getNumOfClusters() == imgPoints.size()){
            List<Cluster> ret = new ArrayList<>(imgPoints.size());
            for (var point : imgPoints){
                Cluster ins = new Cluster();
                ins.getPoints().add(point);
                ret.add(ins);
            }
            return ret;
        }

        List<Position> oldCenters;
        //first is center of centroid
        List<Pair<Position, Cluster>> centroids = randomCentroids(imgPoints);

        while (iteration < Values.getMaxNumOfIterations()){

            calcCentroids(centroids, imgPoints);

            //dont know how to act here if Clusters are empty random positions will be picked as features of image
            //decided to redistrubete rand points to emty clusters sou features of image wont be random points in image radher points

            oldCenters = recalcNewCenterOfCentroids(centroids, imgPoints);

            if (!clusterChaged(oldCenters, centroids)){
                break;
            }

            iteration++;

        }

        List<Cluster> ret = new ArrayList<>();

        calcCentroids(centroids, imgPoints);

        for (Pair<Position, Cluster> centroid: centroids) {
                if (centroid.second.calcCenterOfMass() == null) {
                    return getFetures(imgPoints);
                }
            centroid.second.calcWeight(imgPoints.size());
            ret.add(centroid.second);

        }


        return ret;
    }

//    private static void redistributePoints(List<Pair<Position, Cluster>> centroids) {
//        List<Cluster> emptyCluster = new ArrayList<>();
//        for (Pair<Position, Cluster> centroid: centroids) {
//           if (centroid.second.getPoints().size() == 0){
//               emptyCluster.add(centroid.second);
//           }
//        }
//
//        Random rand = new Random();
//
//        // pick random point form ranom centroid which is not empty
//        for (int i = 0; i < emptyCluster.size(); i++) {
//            int idxOfRandCentroid = rand.nextInt(centroids.size());
//            while (centroids.get(idxOfRandCentroid).second.getPoints().size() < 2 ) {
//                idxOfRandCentroid = rand.nextInt(centroids.size());
//            }
//            int idxOfRandPoint = rand.nextInt(centroids.get(idxOfRandCentroid).second.getPoints().size());
//            var removedPoint = centroids.get(idxOfRandCentroid).second.getPoints().remove(idxOfRandPoint);
//            emptyCluster.get(i).getPoints().add(removedPoint);
//        }
//
//    }

    /**
     * if centroid centers did not change we can assume first that clusters within centroids won't change either in next iteration
     * or second only insignificant points will switch within cluster if process of recalculation of cluster within cycle will continue
     * @param oldCenters
     * @param centroids
     * @return
     */
    private static boolean clusterChaged(List<Position> oldCenters, List<Pair<Position, Cluster>> centroids) {
        for (int i = 0; i < centroids.size(); i++) {
            if (Position.distanceBetween(oldCenters.get(i), centroids.get(i).first) >  Math.ulp(2.0)) {
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
    private static List<Position> recalcNewCenterOfCentroids(List<Pair<Position, Cluster>> centroids, List<Position> imgPoints) {
        List<Pair<Position, Cluster>> newCentroids = new ArrayList<>();
        List<Position> oldCenters = new ArrayList<>();

        for (Pair<Position, Cluster> centroid: centroids) {
            var center = centroid.second.calcCenterOfMass();
            if (center == null){
                newCentroids.add(new Pair<>(randPos(imgPoints, new Random()), new Cluster()));
            } else {
                newCentroids.add(new Pair<>(centroid.second.calcCenterOfMass(), new Cluster()));
            }
            oldCenters.add(centroid.first);
        }
        centroids.clear();
        centroids.addAll(newCentroids);

        return oldCenters;
    }

    private static void calcCentroids(List<Pair<Position, Cluster>> centroids, List<Position> imgPoints) {

        for (var point : imgPoints) {

            double clousestDis = Double.MAX_VALUE;
            int idxOfFoundCentroid = 0;

            for (int idx = 0; idx < centroids.size(); idx++) {

                Position center = centroids.get(idx).first;
                double curDis= Position.distanceBetween(point, center);

                if (curDis < clousestDis){
                    clousestDis= curDis;
                    idxOfFoundCentroid = idx;
                }
            }


            centroids.get(idxOfFoundCentroid).second.getPoints().add(point);
        }
    }

    private static List<Pair<Position,Cluster>> randomCentroids(List<Position> imgPoints) {
        List<Pair<Position, Cluster>> res = new ArrayList<>();

        for (int i = 0; i < Values.getNumOfClusters(); i++) {
            Pair<Position, Cluster> ins = new Pair<>(randPos(imgPoints, new Random()), new Cluster());
            res.add(ins);
        }

        return res;
    }

    private static Position randPos(List<Position> imgPoints, Random rand) {
        double x = imgPoints.get(Math.abs(rand.nextInt()) % imgPoints.size()).getX() + rand.nextFloat();
        double y = imgPoints.get(Math.abs(rand.nextInt()) % imgPoints.size()).getY() + rand.nextFloat();
        return new Position(x, y);
    }


}
