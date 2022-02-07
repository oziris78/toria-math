package com.telek.telekmath.advanced.distributions.cont;


import com.telek.telekmath.TMath;
import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.functions.other.TPolynomial;
import com.telek.telekmath.special.NumericalAnalysis;


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
        return TMath.gamma((v + 1) / 2d) * Math.pow(1 + x * x / v, -(v+1) / 2d)
                / Math.sqrt(v * TMathConstants.PI) / TMath.gamma(v / 2d);
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




    public static double inverseCumulativeProbability(double v, double p){
        if (p < 0.0 || p > 1.0) {
//            throw new OutOfRangeException(p, 0, 1);
        }

        if (p == 0d) return Double.NEGATIVE_INFINITY;
        if (p == 1d) return Double.POSITIVE_INFINITY;

        double lowerBound;
        double upperBound;


        final double sig = (v > 2) ? Math.sqrt(v / (v-2)) : Double.POSITIVE_INFINITY;
        final boolean chebyshevApplies = !(Double.isInfinite(sig) || Double.isNaN(sig));

        if (chebyshevApplies) {
            lowerBound = -sig * Math.sqrt((1. - p) / p);
        }
        else {
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



        //  function.value()  =>  cumulativeProbability(v, x) - p

        return 0; // sil bunu da
//        return solve(toSolve, lowerBound, upperBound, 1E-8);
    }

//    public static double solve(UnivariateFunction function,
//                               double x0, double x1) {
//        return new BrentSolver(1E-8).solve(Integer.MAX_VALUE, function, x0, x1);
//    }











}
