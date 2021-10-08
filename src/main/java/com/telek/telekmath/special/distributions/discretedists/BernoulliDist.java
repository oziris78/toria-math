package com.telek.telekmath.special.distributions.discretedists;


import com.telek.telekmath.special.distributions.Experiment;

public class BernoulliDist extends Experiment  {

    private double p;

    public BernoulliDist(double p){
        this.p = p;
        if( !(p >= 0 && p <= 1) ) throw new RuntimeException("Invalid value for p : " + p);
        this.setE(calculateE(p));
        this.setE2(calculateE_2(p));
        this.setVar(calculateVAR(p));
    }

    /* Methods */

    public double probability(int x){
        if( x!= 0 || x!=1 ) return 0;
        return Math.pow(p,x) * Math.pow(1-p, 1-x);
    }

    /* Helpers */

    private static double calculateE(double p){
        return p;
    }

    private static double calculateE_2(double p){
        return p;
    }

    private static double calculateVAR(double p){
        return p * (1-p);
    }

}
