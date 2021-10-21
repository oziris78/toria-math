package com.telek.telekmath.special.distributions.discretedists;


import com.telek.telekmath.exceptions.InvalidValueException;
import com.telek.telekmath.special.distributions.Experiment;
import com.telek.telekmath.helpers.TMath;

public class PoissonDist extends Experiment  {

    private double lambda;

    public PoissonDist(double lambda){
        this.lambda = lambda;
        this.setE(calculateE(lambda));
        this.setE2(calculateE_2(lambda));
        this.setVar(calculateVAR(lambda));
    }

    public PoissonDist(int n, double p){
        if( n < 1 ) throw new InvalidValueException("n", n);
        if( !(p >= 0 && p <= 1) ) throw new InvalidValueException("p", p);
        this.lambda = n * p;
        this.setE(calculateE(lambda));
        this.setE2(calculateE_2(lambda));
        this.setVar(calculateVAR(lambda));
    }


    /* Methods */

    public double probability(int x){
        return Math.pow(lambda, x) * Math.exp(-lambda) / TMath.factorial(x); // l^x * e^-l / x!
    }

    /* Helpers */

    private static double calculateE(double lambda){
        return lambda;
    }

    private static double calculateE_2(double lambda){
        return lambda * (1 + lambda); // l + l^2 = l (l+1)
    }

    private static double calculateVAR(double lambda){
        return lambda;
    }


}
