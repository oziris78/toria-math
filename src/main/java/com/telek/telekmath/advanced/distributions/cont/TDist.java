package com.telek.telekmath.advanced.distributions.cont;


import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.exceptions.InvalidValueException;
import com.telek.telekmath.exceptions.NotInRangeException;
import com.telek.telekmath.exceptions.TelekMathException;
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
        final double nPlus1Over2 = (v + 1) / 2;
        return TMath.exp(TMath.logGamma(nPlus1Over2) - 0.5 * (TMath.log(TMathConstants.PI) + TMath.log(v))
                - TMath.logGamma(v / 2) - nPlus1Over2 * TMath.log(1 + x * x / v));
    }



    /**
     * Returns P(X <= x) <br>
     * @param v degrees of freedom
     * @param x any value
     * @return cumulative probability function result for v and x
     */
    public static double cumulativeProbability(double v, double x){
        if (x == 0)
            return 0.5d;

        double t = TMath.regularizedBeta(v / (v + (x * x)), 0.5 * v, 0.5);
        return (x < 0.0d) ? 0.5d * t : 1.0d - 0.5d * t;
    }






    ///////////////
    /*  HELPERS  */
    ///////////////




    public static double inverseCumulativeProbability(double v, double p) {
        if (p < 0.0 || p > 1.0) throw new NotInRangeException(TRange.ZERO_TO_ONE, p);

        if (p == 0d) return Double.NEGATIVE_INFINITY;
        if (p == 1d) return Double.POSITIVE_INFINITY;

        double lowerBound;
        double upperBound;


        final double sig = (v > 2) ? Math.sqrt(v / (v - 2)) : Double.POSITIVE_INFINITY;
        final boolean chebyshevApplies = !(Double.isInfinite(sig) || Double.isNaN(sig));

        if (chebyshevApplies) {
            lowerBound = -sig * Math.sqrt((1. - p) / p);
        } else {
            lowerBound = -1.0;
            while (cumulativeProbability(v, lowerBound) >= p) {
                lowerBound *= 2.0;
            }
        }

        if (chebyshevApplies) {
            upperBound = sig * Math.sqrt(p / (1. - p));
        } else {
            upperBound = 1.0;
            while (cumulativeProbability(v, upperBound) < p) {
                upperBound *= 2.0;
            }
        }

        // ...?

        double x = 0;
        final double dx = 1E-6;
        if (x - dx >= lowerBound) {
            double px = cumulativeProbability(v, x);
            if (cumulativeProbability(v, x - dx) == px) {
                upperBound = x;
                while (upperBound - lowerBound > dx) {
                    final double midPoint = 0.5 * (lowerBound + upperBound);
                    if (cumulativeProbability(v, midPoint) < px) {
                        lowerBound = midPoint;
                    } else {
                        upperBound = midPoint;
                    }
                }
                return upperBound;
            }
        }


        return x;
    }










}
