package com.telek.telekmath.advanced.distributions.discdist;


import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.exceptions.InvalidValueException;
import com.telek.telekmath.advanced.distributions.DiscreteDistribution;

public class BernoulliDist extends DiscreteDistribution {

    private double p;

    public BernoulliDist(double p){
        if( !TRange.ZERO_TO_ONE.isInRange(p)) throw new InvalidValueException("p", p);
        this.p = p;
        this.E = p;
        this.Var = p * (1d-p);
    }

    /* Methods */

    @Override
    public double probability(int x){
        if( x == 0 ) return 1d - p;
        if( x == 1 ) return p;
        return 0d;
    }



}
