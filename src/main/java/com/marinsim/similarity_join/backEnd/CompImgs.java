package com.marinsim.similarity_join.backEnd;

public class CompImgs {
    public MyImage leftImg;
    public MyImage rightImg;
    public double similarityPer;

    public CompImgs(MyImage leftImg, MyImage rightImg, double similarityPer) {
        this.leftImg = leftImg;
        this.rightImg = rightImg;
        this.similarityPer = similarityPer;
    }
}
