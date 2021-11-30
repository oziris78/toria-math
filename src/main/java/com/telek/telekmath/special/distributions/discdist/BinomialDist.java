package com.telek.telekmath.special.distributions.discdist;


import com.telek.telekmath.exceptions.InvalidValueException;
import com.telek.telekmath.special.distributions.DiscreteDistribution;
import com.telek.telekmath.TMath;

public class BinomialDist extends DiscreteDistribution {

    private double p;
    private int n;

    public BinomialDist(int n, double p){
        if( n < 1 ) throw new InvalidValueException("n", n);
        if( !(p >= 0 && p <= 1) ) throw new InvalidValueException("p", p);
        this.n = n;
        this.p = p;
        this.E = n * p;
        this.Var = n * p * (1-p);
    }


    /*  METHODS  */

    @Override
    public double probability(int x){
        if( x < 0 || x > n ) return 0;
        return TMath.combination(n,x) * Math.pow(p,x) * Math.pow(1-p, n-x);
    }



}
