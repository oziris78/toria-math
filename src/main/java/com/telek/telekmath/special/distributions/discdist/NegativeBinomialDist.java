package com.telek.telekmath.special.distributions.discdist;


import com.telek.telekmath.core.functions.general.TRange;
import com.telek.telekmath.exceptions.InvalidValueException;
import com.telek.telekmath.special.distributions.DiscreteDistribution;
import com.telek.telekmath.helpers.TMath;

public class NegativeBinomialDist extends DiscreteDistribution {

    private int k;
    private double p;

    public NegativeBinomialDist(int k, double p){
        if( k < 0 ) throw new InvalidValueException("k", k);
        if( !TRange.ZERO_TO_ONE.isInRange(p)) throw new InvalidValueException("p", p);
        this.k = k;
        this.p = p;
        this.E = k / p;
        this.E2 = (k * (1-p) / ( p * p )) + (k / p) * (k / p);
        this.Var = k * (1-p) / ( p * p );
    }

    /*  METHODS  */

    @Override
    public double probability(int x){
        if( x < k ) return 0;
        return TMath.combination(x-1, k-1) * Math.pow(p, k) * Math.pow(1-p, x-k);
    }



}
