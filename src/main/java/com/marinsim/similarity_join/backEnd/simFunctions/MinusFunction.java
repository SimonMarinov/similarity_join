package com.marinsim.similarity_join.backEnd.simFunctions;

import com.marinsim.similarity_join.backEnd.Position;
import com.marinsim.similarity_join.backEnd.SimilarityFunction;

public class MinusFunction implements SimilarityFunction {

    @Override
    public double getValue(Position lfs, Position rhs) {
        return - Position.distanceBetween(lfs,rhs);
    }
}
