package com.telek.telekmath.special.distributions.discretedists;


import com.telek.telekmath.special.distributions.Experiment;
import com.telek.telekmath.helpers.TMath;

public class BinomialDist extends Experiment  {

    private double p;
    private int n;

    public BinomialDist(int n, double p){
        if( n < 1 ) throw new RuntimeException("Invalid value for n : " + n);
        if( !(p >= 0 && p <= 1) ) throw new RuntimeException("Invalid value for p : " + p);
        this.n = n;
        this.p = p;
        this.setE(calculateE(n, p));
        this.setE2(calculateE_2(n, p));
        this.setVar(calculateVAR(n, p));
    }


    /* Methods */

    public double probability(int x){
        if( x < 0 || x > n ) return 0;
        return TMath.combination(n,x) * Math.pow(p,x) * Math.pow(1-p, n-x);
    }

    /* Helpers */

    private static double calculateE(double n, double p) {
        return n * p;
    }

    private static double calculateE_2(double n, double p) {
        return (n * n * p * p) + ( n * p * (1-p) );
    }

    private static double calculateVAR(double n, double p) {
        return n * p * (1-p);
    }



}
