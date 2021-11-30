package com.telek.telekmath.special.distributions.contdist;


import static com.telek.telekmath.exceptions.TelekMathException.*;

import com.telek.telekmath.special.distributions.ContinuousDistribution;


public class ExponentialDist extends ContinuousDistribution {

    private double beta;

    public ExponentialDist(double beta){
        if( beta <= 0) throw new NotGreaterThanZeroException("Beta");
        this.beta = beta;
        this.E = beta;
        this.Var = beta * beta;
    }



}
