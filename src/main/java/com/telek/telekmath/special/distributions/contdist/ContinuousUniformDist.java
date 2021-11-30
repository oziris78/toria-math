package com.telek.telekmath.special.distributions.contdist;


import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.special.distributions.ContinuousDistribution;


public class ContinuousUniformDist extends ContinuousDistribution {

    private double a, b;

    public ContinuousUniformDist(double alpha, double beta){
        this.a = alpha;
        this.b = beta;
        this.E = (alpha+beta) / 2d;
        this.Var = (beta-alpha) * (beta-alpha) / 12d;
    }


}
