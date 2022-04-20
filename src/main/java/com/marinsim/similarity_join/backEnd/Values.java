package com.marinsim.similarity_join.backEnd;


/**
 * class to setup values defiend default or by user
 * could be called settings
 */
public class Values {
    public static final String IMG_PATH = "tmp/";


    private static int numOfClusters;
    private static int MaxNumOfIterations;
    private static double alphaConstant;
    private static double percentageLimit;
    private static double distanceLimit;

    private static boolean initOnlyOnce = false;

    public Values(int numOfClus, int maxNumOfIterations, double alpha, double percentageLim, double distanceLim) {
      //  if (initOnlyOnce) throw new IllegalArgumentException();

        distanceLimit = distanceLim;
        percentageLimit = percentageLim;
        numOfClusters = numOfClus;
        MaxNumOfIterations = maxNumOfIterations;
        alphaConstant = alpha;
        initOnlyOnce = true;
    }

    public static double getAlphaConstant() {
        return alphaConstant;
    }

    public static int getNumOfClusters() {
        return numOfClusters;
    }

    public static int getMaxNumOfIterations() {
        return MaxNumOfIterations;
    }

    public static double getPercentageLimit() {
        return percentageLimit;
    }

    public static double getDistanceLimit() {
        return distanceLimit;
    }
}
