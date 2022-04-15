package com.marinsim.similarity_join.backEnd.ImgComparators;

import com.marinsim.similarity_join.backEnd.*;

import java.util.ArrayList;
import java.util.List;

/**
 * source for math is : http://siret.ms.mff.cuni.cz/skopal/pub/GPU_CIKM.pdf
 */
public class SqfdComparator implements ImgComparator {
    private SimilarityFunction function = null;

    public SqfdComparator(SimilarityFunction fun) {
        function = fun;
    }

    @Override
    public double calcComparison(MyImage lfs, MyImage rhs) {
        return calculateSim(lfs, rhs);
    }

    public double calculateSim(MyImage lfs, MyImage rhs) {
        List<Double> weightVec = calcWeightvec(lfs.getFeatures(), rhs.getFeatures());
        double [][] matrix = calcMatrix(lfs.getFeatures(), rhs.getFeatures(), function);
        return sqfdFormulaRes(weightVec, matrix);
    }


    private double sqfdFormulaRes(List<Double> weightVec, double[][] matrix) {
        List<Double> interRes = new ArrayList<>(weightVec.size());

        //first the second and third argument of sqfd is multiplyed to create intermidiatete resulet
        for (int i = 0; i < matrix.length ; i++) {
            double sum = 0;
            for (int j = 0; j < matrix[i].length ; j++) {
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

    private double[][] calcMatrix(List<Cluster> lfsFeatures, List<Cluster> rhsFeatures, SimilarityFunction function ) {
        List<Position> vector = new ArrayList<>(lfsFeatures.size() + rhsFeatures.size());
        double [][] ret = new double[lfsFeatures.size() + rhsFeatures.size()][lfsFeatures.size() + rhsFeatures.size()];

        for (Cluster feature : lfsFeatures) {
            vector.add(feature.calcCenterOfMass());
        }
        for ( Cluster feature : rhsFeatures) {
            vector.add(feature.calcCenterOfMass());
        }

        for (int i = 0; i < vector.size() ; i++) {
            for (int j = 0; j < vector.size() ; j++) {
                double element = function.getValue(vector.get(i), vector.get(j));
                ret[i][j] = element;
            }
        }

        return ret;
    }

    private List<Double> calcWeightvec(List<Cluster> lfsFeatures, List<Cluster> rhsFeatures) {
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
