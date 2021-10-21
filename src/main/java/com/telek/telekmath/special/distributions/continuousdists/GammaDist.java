package com.telek.telekmath.special.distributions.continuousdists;


import com.telek.telekmath.special.distributions.Experiment;
import static com.telek.telekmath.exceptions.TelekMathException.*;
import com.telek.telekmath.helpers.TMath;

public class GammaDist extends Experiment  {

    private double alpha, beta;

    public GammaDist(double alpha, double beta){
        if( alpha <= 0 || beta <= 0) throw new NotGreaterThanZeroException("Alpha and beta");
        this.alpha = alpha;
        this.beta = beta;
        this.setE(calculateE(alpha,beta));
        this.setE2(calculateE_2(alpha,beta));
        this.setVar(calculateVAR(alpha,beta));
    }


    /* Methods */

    public double probability(double x) {
        if( x <= 0) return 0;
        return ( 1 / (Math.pow(beta,alpha) * TMath.gamma(alpha)) ) * Math.pow(x, alpha-1) * Math.exp(-x / beta);
    }

    /* Helpers */

    private static double calculateE(double alpha, double beta){
        return alpha * beta;
    }

    private static double calculateE_2(double alpha, double beta){
        return alpha * (alpha+1) * beta * beta;
    }

    private static double calculateVAR(double alpha, double beta){
        return alpha * beta * beta;
    }



}
