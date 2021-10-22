package com.telek.telekmath.special.distributions.discdist;


import com.telek.telekmath.core.functions.general.TRange;
import com.telek.telekmath.exceptions.InvalidValueException;
import com.telek.telekmath.special.distributions.DiscreteDistribution;
import com.telek.telekmath.helpers.TMath;

public class PoissonDist extends DiscreteDistribution {

    private double lambda;

    /*  CONSTRUCTORS  */

    public PoissonDist(double lambda){
        this.lambda = lambda;
        this.E = lambda;
        this.E2 = lambda * (1 + lambda); // l + l^2 = l (l+1);
        this.Var = lambda;
    }

    public PoissonDist(int n, double p){
        this(n * p);
        if( n < 1 ) throw new InvalidValueException("n", n);
        if( !TRange.ZERO_TO_ONE.isInRange(p) ) throw new InvalidValueException("p", p);
    }

    /*  METHODS  */

    @Override
    public double probability(int x){
        return Math.pow(lambda, x) * Math.exp(-lambda) / TMath.factorial(x); // l^x * e^-l / x!
    }


}
