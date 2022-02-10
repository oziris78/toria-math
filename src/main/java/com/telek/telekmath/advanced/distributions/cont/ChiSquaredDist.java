package com.telek.telekmath.advanced.distributions.cont;


import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.exceptions.NotInRangeException;
import com.telek.telekmath.utils.TMath;


public class ChiSquaredDist  {


    ///////////////
    /*  METHODS  */
    ///////////////

    public static double expectedValue(double degOfFreedom){
        return degOfFreedom;
    }

    public static double variance(double degOfFreedom){
        return 2d * degOfFreedom;
    }

    public static double getAlpha(double degOfFreedom){
        return degOfFreedom / 2d;
    }

    public static double getDegreeOfFreedom(double alpha){
        return 2d * alpha;
    }





    /**
     * Warning: this method doesn't work with very high x values. (higher than 130 is usually problematic)
     * @param v degrees of freedom
     * @param x any value
     * @return probability density function result for v and x
     */
    public static double density(double v, double x){
        if(x < 0) return 0;
        double alpha = v / 2d;
        return TMath.pow(x / 2d, alpha - 1) / 2d * TMath.exp(-x / 2d) / TMath.gamma(alpha);
    }



    /**
     * Returns P(X <= x) <br>
     * @param v degrees of freedom
     * @param x any value
     * @return cumulative probability function result for v and x
     */
    public static double cumulativeProbability(double v, double x){
        if(x <= 0)
            return 0;

        return TMath.regularizedGammaP(v / 2d, x / 2d);
    }



    public static double inverseCumulativeProbability(double v, double p) {
        if (p < 0.0 || p > 1.0) throw new NotInRangeException(TRange.ZERO_TO_ONE, p);

        double lowerBound = 0;
        double upperBound = Double.POSITIVE_INFINITY;

        if (p == 0.0) return lowerBound;
        if (p == 1.0) return upperBound;


        final double sig = Math.sqrt(2 * v);
        final boolean chebyshevApplies;
        chebyshevApplies = !(Double.isInfinite(v) || Double.isNaN(v) || Double.isInfinite(sig) || Double.isNaN(sig));

        if (lowerBound == Double.NEGATIVE_INFINITY) {
            if (chebyshevApplies) {
                lowerBound = v - sig * Math.sqrt((1. - p) / p);
            } else {
                lowerBound = -1.0;
                while (cumulativeProbability(v, lowerBound) >= p) {
                    lowerBound *= 2.0;
                }
            }
        }

        if (upperBound == Double.POSITIVE_INFINITY) {
            if (chebyshevApplies) {
                upperBound = v + sig * Math.sqrt(p / (1. - p));
            } else {
                upperBound = 1.0;
                while (cumulativeProbability(v, upperBound) < p) {
                    upperBound *= 2.0;
                }
            }
        }

        return 0;
    }



    ///////////////
    /*  HELPERS  */
    ///////////////



}
