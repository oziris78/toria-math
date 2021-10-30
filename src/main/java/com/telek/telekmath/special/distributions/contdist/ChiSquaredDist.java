package com.telek.telekmath.special.distributions.contdist;


import com.telek.telekmath.special.distributions.ContinuousDistribution;
import com.telek.telekmath.TMath;
import static com.telek.telekmath.exceptions.TelekMathException.*;

public class ChiSquaredDist extends ContinuousDistribution {

    private double v;

    public ChiSquaredDist(double alpha){
        if( alpha <= 0 ) throw new NotGreaterThanZeroException("Alpha");
        this.v = 2d * alpha;
        this.E = v;
        this.E2 = (2d * v) + (v * v);
        this.Var = 2d * v;
    }


    /*  METHODS  */

    public static double calculateAlpha(double v){
        return v / 2d;
    }

    @Override
    public double probability(double x) {
        if( x <= 0) return 0;
        return ( 1d / ( Math.pow(2d, v/2d) * TMath.gamma(v/2d) ) ) * Math.pow(x, (v-2d)/2d ) * Math.exp(-x/2d);
    }



}
