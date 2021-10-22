package com.telek.telekmath.special.distributions.discdist;


import com.telek.telekmath.exceptions.InvalidValueException;
import com.telek.telekmath.special.distributions.DiscreteDistribution;


public class DiscreteUniformDist extends DiscreteDistribution {

    private double n;

    public DiscreteUniformDist(double n){
        if( n < 1 ) throw new InvalidValueException("n", n);
        this.n = n;
        this.E = (n+1)/2;
        this.E2 = (n+1)*(2*n+1) / 6;
        this.Var = (n * n - 1 ) / 12;
    }

    /*  METHODS  */

    @Override
    public double probability(int x){
        return 1d / n;
    }



}
