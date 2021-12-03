package com.telek.telekmath.advanced.distributions.contdist;


import com.telek.telekmath.advanced.distributions.ContinuousDistribution;

import static com.telek.telekmath.exceptions.TelekMathException.*;

public class ChiSquaredDist extends ContinuousDistribution {

    private double v;

    public ChiSquaredDist(double alpha){
        if( alpha <= 0 ) throw new NotGreaterThanZeroException("Alpha");
        this.v = 2d * alpha;
        this.E = v;
        this.Var = 2d * v;
    }


    /*  METHODS  */

    /**
     * @param degreeOfFreedom usually called v
     * @return alpha
     */
    public static double calculateAlpha(double degreeOfFreedom){
        return degreeOfFreedom / 2d;
    }



}
