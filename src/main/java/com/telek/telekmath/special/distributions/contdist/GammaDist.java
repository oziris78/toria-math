package com.telek.telekmath.special.distributions.contdist;


import com.telek.telekmath.special.distributions.ContinuousDistribution;
import com.telek.telekmath.TMath;
import static com.telek.telekmath.exceptions.TelekMathException.*;


public class GammaDist extends ContinuousDistribution {

    private double alpha, beta;


    public GammaDist(double alpha, double beta){
        if( alpha <= 0 || beta <= 0 ) throw new NotGreaterThanZeroException("Alpha and beta");
        this.alpha = alpha;
        this.beta = beta;
        this.E = alpha * beta;
        this.Var = alpha * beta * beta;
    }





}
