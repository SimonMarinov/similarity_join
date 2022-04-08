package com.marinsim.similarity_join.backEnd;

import java.util.ArrayList;
import java.util.List;

public class PictureComparator {
    private SimilarityFunction function;
    private int numberOfClusters;
    private List<MyImage> imgsToComp;

    public PictureComparator(List<MyImage> imagesToCompere,
                             SimilarityFunction function) {
        this.function = function;
        imgsToComp = imagesToCompere;

        if (imagesToCompere.size() < 2) {
            System.err.print("not enough pictures to compare");
        }
    }

    public List<CompImgs> getComparisons() {
        for (MyImage img : imgsToComp) {
            img.calculatePoints();
            img.calcFetures();
        }

        List<CompImgs> comparisons = new ArrayList<>();
        for (int i = 0; i < imgsToComp.size(); i++) {
            for (int j = 0; j < imgsToComp.size(); j++) {
                if (j == i) {
                    continue;
                } else {
                    double per = SqfdFunction.calculateSim(imgsToComp.get(i), imgsToComp.get(j), function);
                    comparisons.add(new CompImgs(imgsToComp.get(i), imgsToComp.get(j), per));
                }
            }
        }

        return comparisons;
    }
}
