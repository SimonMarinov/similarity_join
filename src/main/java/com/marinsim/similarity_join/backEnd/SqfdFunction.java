package com.marinsim.similarity_join.backEnd;

import java.util.ArrayList;
import java.util.List;

/**
 * source for math is : http://siret.ms.mff.cuni.cz/skopal/pub/GPU_CIKM.pdf
 */
public class SqfdFunction {
    public static double calculateSim(MyImage lfs, MyImage rhs, SimilarityFunction function) {
        List<Double> weightVec = calcWeightvec(lfs.getFeatures(), rhs.getFeatures());
        double [][] matrix = calcMatrix(lfs.getFeatures(), rhs.getFeatures(), function);
        return sqfdFormulaRes(weightVec, matrix);
    }


    private static double sqfdFormulaRes(List<Double> weightVec, double[][] matrix) {
        List<Double> interRes = new ArrayList<>(weightVec.size());
        double sum = 0;

        //first the second and third argument of sqfd is multiplyed to create intermidiatete resulet
        for (int i = 0; i < matrix.length ; i++) {
            sum = 0;
            for (int j = 0; j < matrix[j].length ; j++) {
                sum +=  matrix[i][j] * weightVec.get(j);
            }
            interRes.add(sum);
        }

        //then mult weightVec with interRes to create
        double res = 0;
        for (int i = 0; i < weightVec.size(); i++) {
            res += weightVec.get(i) * interRes.get(i);
        }

        res= Math.sqrt(res);
        return res;
    }

    private static double[][] calcMatrix(List<Cluster> lfsFeatures, List<Cluster> rhsFeatures, SimilarityFunction function ) {
        List<Position> vector = new ArrayList<>(lfsFeatures.size() + rhsFeatures.size());
        double [][] ret = new double[vector.size()][vector.size()];

        for (Cluster feature : lfsFeatures) {
            vector.add(feature.getCenterOfMass());
        }
        for ( Cluster feature : rhsFeatures) {
            vector.add(feature.getCenterOfMass());
        }

        for (int i = 0; i < vector.size() ; i++) {
            for (int j = 0; j < vector.size() ; j++) {
                double element = function.getValue(vector.get(i), vector.get(j));
                ret[i][j] = element;
            }
        }

        return ret;
    }

    private static List<Double> calcWeightvec(List<Cluster> lfsFeatures, List<Cluster> rhsFeatures) {
            List<Double> weightVector = new ArrayList<>(lfsFeatures.size() + rhsFeatures.size());
            for (Cluster feature : lfsFeatures) {
                weightVector.add(feature.getWeight());
            }
            for ( Cluster feature : rhsFeatures) {
                weightVector.add(-feature.getWeight());
            }
            return weightVector;
    }


}
