package com.telek.telekmath.advanced.distributions.cont;


import com.telek.telekmath.TMath;
import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.exceptions.TelekMathException.NotGreaterThanZeroException;


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
        return Math.pow(x / 2d, alpha - 1) / 2d * Math.exp(-x / 2d) / TMath.gamma(alpha);
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




}
