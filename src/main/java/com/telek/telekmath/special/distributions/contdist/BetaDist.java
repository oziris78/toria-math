package com.telek.telekmath.special.distributions.contdist;


import static com.telek.telekmath.exceptions.TelekMathException.*;

import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.special.distributions.ContinuousDistribution;
import com.telek.telekmath.TMath;

public class BetaDist extends ContinuousDistribution {

    private double alpha, beta;

    public BetaDist(double alpha, double beta){
        if( alpha <= 0 || beta <= 0) throw new NotGreaterThanZeroException("Alpha and beta");
        this.alpha = alpha;
        this.beta = beta;
        this.E = alpha / (alpha + beta);
        this.E2 = ( alpha * (alpha+1d) ) / ( (alpha+beta) * (alpha+beta+1d) );
        this.Var = alpha * beta / ( (alpha+beta) * (alpha+beta) * (alpha+beta+1d) );
    }


    /*  METHODS  */

    @Override
    public double probability(double x) {
        if( !TRange.ZERO_TO_ONE.isInRange(x) ) return 0;
        return TMath.beta(alpha,beta) * Math.pow(x, alpha-1) * Math.pow(1-x, beta-1);
    }



}
