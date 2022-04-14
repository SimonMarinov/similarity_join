package com.marinsim.similarity_join.backEnd.simFunctions;

import com.marinsim.similarity_join.backEnd.Position;
import com.marinsim.similarity_join.backEnd.SimilarityFunction;
import com.marinsim.similarity_join.backEnd.Values;

public class HeuristicFunction implements SimilarityFunction {
    @Override
    public double getValue(Position lfs, Position rhs) {
        return (double) 1 / ( Values.getAlphaConstant() + Position.distanceBetween(lfs, rhs));
    }
}
