package com.marinsim.similarity_join.backEnd;

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

    public List<Pair<MyImage, List<Pair<MyImage, Double>>>> getComparisons(String resType, int resVal) {
        for (MyImage img : lfsImgs) {
            img.calculatePoints();
            img.calcFeatures();
        }

        for (MyImage img : rhsImgs) {
            img.calculatePoints();
            img.calcFeatures();
        }

        List<Pair<MyImage, List<Pair<MyImage, Double>>>> comparisons = new ArrayList<>();

        for (MyImage lImg: lfsImgs) {
            List<Pair<MyImage, Double>> imgsRes = new ArrayList<>();

            for (MyImage rImg: rhsImgs) {
                double disDif = imgComparator.compare(lImg, rImg);

                if (resType.equals("max")) {
                    if (disDif < resVal) {
                        imgsRes.add(new Pair<>(rImg, disDif));
                    }
                } else if (resType.equals("min")){
                    if (disDif > resVal) {
                        imgsRes.add(new Pair<>(rImg, disDif));
                    }
                } else {
                    imgsRes.add(new Pair<>(rImg, disDif));
                }
            }
            imgsRes.sort(new Comparator<Pair<MyImage, Double>>() {
                @Override
                public int compare(Pair<MyImage, Double> o1, Pair<MyImage, Double> o2) {
                    return Double.compare(o1.second, o2.second);
                }
            });

            if (resType.equals("knn") && imgsRes.size()>resVal){
                comparisons.add(new Pair<>(lImg, imgsRes.subList(0,resVal)));
            } else {
                comparisons.add(new Pair<>(lImg, imgsRes));
            }
        }

        return comparisons;
    }
}
