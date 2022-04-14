package com.marinsim.similarity_join.backEnd;


/**
 * interface to implent sqfd a simple euclidian function
 */
public interface ImgComparator {
    /**
     * @param lfs left image
     * @param rhs right image
     * @return returns percentage of similiraty between left image to right image
     */
    double calcComparison(MyImage lfs, MyImage rhs);
}
