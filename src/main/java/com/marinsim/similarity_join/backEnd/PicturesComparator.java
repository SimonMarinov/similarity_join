package com.marinsim.similarity_join.backEnd;

import com.marinsim.similarity_join.backEnd.ImgComparators.SqfdComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PicturesComparator {
    private List<MyImage> lfsImgs;
    private List<MyImage> rhsImgs;
    private ImgComparator imgComparator;

    public PicturesComparator(List<MyImage> lfsImgs, List<MyImage> rhsImgs, ImgComparator imgComparator) {
        this.lfsImgs = lfsImgs;
        this.rhsImgs = rhsImgs;
        this.imgComparator = imgComparator;
    }

    public List<Pair<MyImage, List<Pair<MyImage, Double>>>> getComparisons() {
        for (MyImage img : lfsImgs) {
            img.calculatePoints();
            img.calcFetures();
        }

        for (MyImage img : rhsImgs) {
            img.calculatePoints();
            img.calcFetures();
        }

        List<Pair<MyImage, List<Pair<MyImage, Double>>>> comparisons = new ArrayList<>();

        for (MyImage lImg: lfsImgs) {
            List<Pair<MyImage, Double>> imgsRes = new ArrayList<>();

            for (MyImage rImg: rhsImgs) {
                double per = imgComparator.calcComparison(lImg, rImg);
                if (per > Values.getPercentageLimit()) {
                    imgsRes.add(new Pair<>(rImg, per));
                }
            }
            imgsRes.sort(new Comparator<Pair<MyImage, Double>>() {
                @Override
                public int compare(Pair<MyImage, Double> o1, Pair<MyImage, Double> o2) {
                    return -Double.compare(o1.second, o2.second);
                }
            });

            comparisons.add(new Pair<>(lImg, imgsRes));
        }

        return comparisons;
    }
}
