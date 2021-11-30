package com.telek.telekmath.special.distributions.discdist;


import com.telek.telekmath.TMath;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.exceptions.InvalidValueException;
import com.telek.telekmath.special.distributions.DiscreteDistribution;

public class GeometricDist extends DiscreteDistribution {

    private double p;

    public GeometricDist(double p){
        if( !TRange.ZERO_TO_ONE.isInRange(p) || TMath.areEqual(p, 0d)) throw new InvalidValueException("p", p);
        this.p = p;
        this.E = 1d / p;
        this.Var = (1d-p) / (p * p);
    }

    /*  METHODS  */

    @Override
    public double probability(int x){
        if( x < 1 ) return 0;
        return Math.pow(1-p, x-1) * p;
    }



}
