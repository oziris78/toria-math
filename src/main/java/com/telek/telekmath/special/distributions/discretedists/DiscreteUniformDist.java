package com.telek.telekmath.special.distributions.discretedists;


import com.telek.telekmath.special.distributions.Experiment;

public class DiscreteUniformDist extends Experiment  {

    private double n;

    public DiscreteUniformDist(double n){
        if( n < 1 ) throw new RuntimeException("Invalid value for n : " + n);
        this.n = n;
        this.setE(calculateE(n));
        this.setE2(calculateE_2(n));
        this.setVar(calculateVAR(n));
    }

    /* Methods */

    public double probability(int x){
        return 1/n;
    }

    /* Helpers */

    private static double calculateE(double n){
        return (n+1)/2;
    }

    private static double calculateE_2(double n){
        return (n+1)*(2*n+1) / 6;
    }

    private static double calculateVAR(double n){
        return (n * n - 1 ) / 12;
    }

}
