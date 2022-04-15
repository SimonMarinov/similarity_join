package com.marinsim.similarity_join.backEnd.simFunctions;

import com.marinsim.similarity_join.backEnd.Position;
import com.marinsim.similarity_join.backEnd.SimilarityFunction;
import com.marinsim.similarity_join.backEnd.Values;

/**
 * source of function from : http://siret.ms.mff.cuni.cz/skopal/pub/GPU_CIKM.pdf
 */
public class GuassianFunction implements SimilarityFunction {

    @Override
    public double getValue(Position lfs, Position rhs) {
        var k =Math.exp(-Values.getAlphaConstant() * Math.pow(Position.distanceBetween(lfs, rhs), 2));
        return Math.exp(-Values.getAlphaConstant() * Math.pow(Position.distanceBetween(lfs, rhs), 2));
    }
}
