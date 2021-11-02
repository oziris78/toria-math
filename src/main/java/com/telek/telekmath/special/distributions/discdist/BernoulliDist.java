package com.telek.telekmath.special.distributions.discdist;


import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.exceptions.InvalidValueException;
import com.telek.telekmath.special.distributions.DiscreteDistribution;

public class BernoulliDist extends DiscreteDistribution {

    private double p;

    public BernoulliDist(double p){
        if( !TRange.ZERO_TO_ONE.isInRange(p)) throw new InvalidValueException("p", p);
        this.p = p;
        this.E = p;
        this.E2 = p;
        this.Var = p * (1d-p);
    }

    /* Methods */

    @Override
    public double probability(int x){
        if( x != 0 || x != 1 ) return 0;
        return Math.pow(p,x) * Math.pow(1-p, 1-x);
    }



}
