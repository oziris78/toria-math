package com.telek.telekmath.special.distributions.continuousdists;


import static com.telek.telekmath.exceptions.TelekMathException.*;
import com.telek.telekmath.special.distributions.Experiment;


public class ExponentialDist extends Experiment  {

    private double beta;

    public ExponentialDist(double beta){
        if( beta <= 0) throw new NotGreaterThanZeroException("Beta");
        this.beta = beta;
        this.setE(calculateE(beta));
        this.setE2(calculateE_2(beta));
        this.setVar(calculateVAR(beta));
    }


    /* Methods */

    public double probability(double x) {
        if( x <= 0) return 0;
        return Math.exp( - x / beta ) / beta;
    }

    /* Helpers */

    private static double calculateE(double beta){
        return beta;
    }

    private static double calculateE_2(double beta){
        return 2 * beta * beta;
    }

    private static double calculateVAR(double beta){
        return beta * beta;
    }

}
