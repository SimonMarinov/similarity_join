package com.marinsim.similarity_join.backEnd;


/**
 * class to setup values defiend default or by user
 * could be called settings
 */
public class Values {
    public static final String IMG_PATH = "tmp/";
    public static final int IMG_WIDTH_LEN = 200;
    public static final int IMG_SIZE = 40000;
    public static final double PER_OF_DISTANCE_DISTRIBUTION = 0.003;

    private static int numOfClusters;
    private static int MaxNumOfIterations;
    private static double alphaConstant;
    private static double distanceLimitPer;

    public Values(int numOfClus, int maxNumOfIterations, double alpha, double distanceLim) {

        distanceLimitPer = distanceLim;
        numOfClusters = numOfClus;
        MaxNumOfIterations = maxNumOfIterations;
        alphaConstant = alpha;
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

    public static double getDistanceLimit(){
        return distanceLimitPer * IMG_WIDTH_LEN;
    }
}
