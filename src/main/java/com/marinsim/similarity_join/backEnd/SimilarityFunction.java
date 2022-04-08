package com.marinsim.similarity_join.backEnd;

public interface SimilarityFunction {
    double getValue(Position lfs, Position rhs);
}


