package com.telek.telekmath.special.distributions.discretedists;


import com.telek.telekmath.special.distributions.Experiment;
import com.telek.telekmath.helpers.TMath;

public class NegativeBinomialDist extends Experiment  {

    private int k;
    private double p;

    public NegativeBinomialDist(int k, double p){
        this.k = k;
        this.p = p;
        if( k < 0 ) throw new RuntimeException("Invalid value for k : " + k);
        if( !(p >= 0 && p <= 1) ) throw new RuntimeException("Invalid value for p : " + p);
        this.setE(calculateE(k,p));
        this.setE2(calculateE_2(k,p));
        this.setVar(calculateVAR(k,p));
    }

    /* Methods */

    public double probability(int x){
        if( x < k ) return 0;
        return TMath.combination(x-1, k-1) * Math.pow(p, k) * Math.pow(1-p, x-k);
    }

    /* Helpers */

    private static double calculateE(int k, double p){
        return k / p;
    }

    private static double calculateE_2(int k, double p){
        return (k * (1-p) / ( p * p )) + (k / p) * (k / p);
    }

    private static double calculateVAR(int k, double p){
        return k * (1-p) / ( p * p );
    }

}
