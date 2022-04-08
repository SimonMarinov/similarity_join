package com.marinsim.similarity_join.backEnd;


/**
 * class to setup values defiend default or by user
 * could be called settings
 */
public class Values {
    private static int numOfClusters;
    private static int MaxNumOfIterations;
    private static int alphaConstant;
    private static boolean initOnlyOnce = false;

    public Values(int numOfClusters, int maxNumOfIterations, int alpha) {
        if (initOnlyOnce) throw new IllegalCallerException();

        this.numOfClusters = numOfClusters;
        MaxNumOfIterations = maxNumOfIterations;
        alphaConstant = alpha;
        initOnlyOnce = true;
    }

    public static int getAlphaConstant() {
        return alphaConstant;
    }

    public static int getNumOfClusters() {
        return numOfClusters;
    }

    public static int getMaxNumOfIterations() {
        return MaxNumOfIterations;
    }
}
