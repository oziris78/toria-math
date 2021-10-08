package com.telek.telekmath.special.distributions.discretedists;


import com.telek.telekmath.special.distributions.Experiment;

public class GeometricDist extends Experiment  {

    private double p;

    public GeometricDist(double p){
        this.p = p;
        if( !(p >= 0 && p <= 1) ) throw new RuntimeException("Invalid value for p : " + p);
        this.setE(calculateE(p));
        this.setE2(calculateE_2(p));
        this.setVar(calculateVAR(p));
    }

    /* Methods */

    public double probability(int x){
        if( x < 1 ) return 0;
        return Math.pow(1-p, x-1) * p;
    }

    /* Helpers */

    private static double calculateE(double p){
        return 1 / p;
    }

    private static double calculateE_2(double p){
        return ( 2 * (1-p) + p ) / (p * p);
    }

    private static double calculateVAR(double p){
        return  (1-p) / (p * p);
    }

}
