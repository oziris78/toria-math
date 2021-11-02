package com.telek.telekmath.special.distributions.contdist;


import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.special.distributions.ContinuousDistribution;


public class ContinuousUniformDist extends ContinuousDistribution {

    private final TRange range;
    private double a, b;

    public ContinuousUniformDist(double a, double b){
        this.a = a;
        this.b = b;
        this.range = new TRange(a, b);
        this.E = (a+b) / 2d;
        this.E2 = ( (b*b*b) - (a*a*a) ) / ( 3d * (b-a) );
        this.Var = (b-a) * (b-a) / 12d;
    }


    /*  METHODS  */

    @Override
    public double probability(double x) {
        if( !this.range.isInRange(x) ) return 0d;
        return 1d / ( b - a ) ;
    }


}
