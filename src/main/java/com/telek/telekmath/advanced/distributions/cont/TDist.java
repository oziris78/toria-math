package com.telek.telekmath.advanced.distributions.cont;


import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.exceptions.InvalidValueException;
import com.telek.telekmath.exceptions.NotInRangeException;
import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.constants.TMathConstants;


/**
 * Student's T Distribution
 */
public class TDist {


    /* No constructor */
    private TDist(){}



    ///////////////
    /*  METHODS  */
    ///////////////


    /**
     * @param v degrees of freedom
     * @param x any value
     * @return probability density function result for v and x
     */
    public static double density(double v, double x){
        final double nPlus1Over2 = (v + 1d) / 2d;
        return TMath.exp(TMath.logGamma(nPlus1Over2) - 0.5d * (TMath.log(TMathConstants.PI) + TMath.log(v))
                - TMath.logGamma(v / 2d) - nPlus1Over2 * TMath.log(1d + x * x / v));
    }



    /**
     * Returns P(X <= x) <br>
     * @param v degrees of freedom
     * @param x any value
     * @return cumulative probability function result for v and x
     */
    public static double cumulativeProbability(double v, double x){
        if (x == 0d)
            return 0.5d;

        double t = TMath.regularizedBeta(v / (v + (x * x)), 0.5d * v, 0.5d);
        return (x < 0.0d) ? 0.5d * t : 1.0d - 0.5d * t;
    }



    /**
     * Returns value for this equation:  p = P(X <= val)
     * @param v degrees of freedom
     * @param p any value in range [0,1]
     * @return inverse cumulative probability function result
     */
    public static double inverseCumulativeProbability(double v, double p) {
        if(p < 0d || p > 1d) throw new NotInRangeException(TRange.ZERO_TO_ONE, p);
        if(v < 1d) throw new InvalidValueException("degreesOfFreedom (v)", v);

        if(p == 0d) return Double.NEGATIVE_INFINITY;
        if(p == 1d) return Double.POSITIVE_INFINITY;

        double ret = TMath.inverseRegularizedIncompleteBetaFunction(0.5d * v, 0.5d, 2d * Math.min(p, 1d - p));
        ret = Math.sqrt(v * (1d - ret) / ret);

        return Math.copySign(ret, p - 0.5d);
    }



    /**
     * This function is the right tailed version of the default inverse cumulative probability function. <br>
     * Returns value for this equation:  p = P(X >= val) <br>
     * @param v degrees of freedom
     * @param p any value in range [0,1]
     * @return inverse cumulative probability function result
     */
    public static double invCumZeroToRight(double v, double p){
        return TMath.abs(inverseCumulativeProbability(v, p));
    }




}
