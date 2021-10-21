package com.telek.telekmath.special.distributions.continuousdists;


import com.telek.telekmath.special.distributions.Experiment;
import com.telek.telekmath.helpers.TMath;
import static com.telek.telekmath.exceptions.TelekMathException.*;

public class ChiSquaredDist extends Experiment  {

    private double alpha, v;

    public ChiSquaredDist(double alpha){
        if( alpha <= 0 ) throw new NotGreaterThanZeroException("Alpha");
        this.alpha = alpha;
        this.v = 2 * this.alpha;
        this.setE(calculateE(v));
        this.setE2(calculateE_2(v));
        this.setVar(calculateVAR(v));
    }


    /* Methods */

    public static double calculateAlpha(double v){
        return v / 2;
    }

    public double probability(double x) {
        if( x <= 0) return 0;
        return ( 1 / ( Math.pow(2, v/2) * TMath.gamma(v/2) ) ) * Math.pow(x, (v-2)/2 ) * Math.exp(-x/2);
    }

    /* Helpers */

    private static double calculateE(double v){
        return v;
    }

    private static double calculateE_2(double v){
        return (2 * v) + (v * v);
    }

    private static double calculateVAR(double v){
        return 2 * v;
    }


}
