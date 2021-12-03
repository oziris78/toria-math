package com.telek.telekmath.advanced.distributions.contdist;


import static com.telek.telekmath.exceptions.TelekMathException.*;

import com.telek.telekmath.advanced.distributions.ContinuousDistribution;

public class BetaDist extends ContinuousDistribution {

    private double alpha, beta;

    public BetaDist(double alpha, double beta){
        if( alpha <= 0 || beta <= 0) throw new NotGreaterThanZeroException("Alpha and beta");
        this.alpha = alpha;
        this.beta = beta;
        this.E = alpha / (alpha + beta);
        this.Var = alpha * beta / ( (alpha+beta) * (alpha+beta) * (alpha+beta+1d) );
    }




}
