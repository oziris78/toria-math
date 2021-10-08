package com.telek.telekmath.special.distributions.continuousdists;


import com.telek.telekmath.special.distributions.Experiment;
import com.telek.telekmath.helpers.TMath;

public class BetaDist extends Experiment  {

    private double alpha, beta;

    public BetaDist(double alpha, double beta){
        if( alpha <= 0 || beta <= 0) throw new RuntimeException("Alpha and beta has to be greater than zero");
        this.alpha = alpha;
        this.beta = beta;
        this.setE(calculateE(alpha,beta));
        this.setE2(calculateE_2(alpha,beta));
        this.setVar(calculateVAR(alpha,beta));
    }


    /* Methods */

    public double probability(double x) {
        if( !(0 <= x && x <= 1) ) return 0;
        return TMath.beta(alpha,beta) * Math.pow(x, alpha-1) * Math.pow(1-x, beta-1);
    }

    /* Helpers */

    private static double calculateE(double alpha, double beta){
        return alpha / (alpha + beta);
    }

    private static double calculateE_2(double alpha, double beta){
        return ( alpha * (alpha+1) ) / ( (alpha+beta) * (alpha+beta+1) );
    }

    private static double calculateVAR(double alpha, double beta){
        return  alpha * beta / ( (alpha+beta) * (alpha+beta) * (alpha+beta+1) );
    }


}
