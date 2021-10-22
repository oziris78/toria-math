package com.telek.telekmath.special.distributions.contdist;


import static com.telek.telekmath.exceptions.TelekMathException.*;

import com.telek.telekmath.special.distributions.ContinuousDistribution;


public class ExponentialDist extends ContinuousDistribution {

    private double beta;

    public ExponentialDist(double beta){
        if( beta <= 0) throw new NotGreaterThanZeroException("Beta");
        this.beta = beta;
        this.E = beta;
        this.E2 = 2d * beta * beta;
        this.Var = beta * beta;
    }


    /*  METHODS  */

    @Override
    public double probability(double x) {
        if( x <= 0) return 0;
        return Math.exp( - x / beta ) / beta;
    }


}
