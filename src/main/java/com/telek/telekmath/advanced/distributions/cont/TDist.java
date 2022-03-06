package com.telek.telekmath.advanced.distributions.cont;


import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.utils.TelekMathException.*;
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
     * @return probability density function (PDF) result
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
     * @return cumulative density function (CDF) result
     */
    public static double cumulativeProbability(double v, double x){
        if (x == 0d)
            return 0.5d;

        double t = TMath.regularizedBeta(v / (v + (x * x)), 0.5d * v, 0.5d);
        return (x < 0.0d) ? 0.5d * t : 1.0d - 0.5d * t;
    }




    /**
     * Left tailed inverse cumulative density function (ICDF). <br>
     * Returns the variable "val" for this equation:  p = P(X <= val)
     * @param v degrees of freedom
     * @param p any value in range [0,1]
     * @return inverse cumulative density function (ICDF | quantile) result
     */
    public static double invCumLeftTailed(double v, double p) {
        if(p < 0d || p > 1d) throw new NotInRangeException(TRange.ZERO_TO_ONE, p);
        if(v < 1d) throw new InvalidValueException("degreesOfFreedom (v)", v);

        if(p == 0d) return Double.NEGATIVE_INFINITY;
        if(p == 1d) return Double.POSITIVE_INFINITY;

        double ret = iribfForTDist(0.5d * v, 0.5d, 2d * Math.min(p, 1d - p));
        ret = Math.sqrt(v * (1d - ret) / ret);

        return Math.copySign(ret, p - 0.5d);
    }





    /**
     * Right tailed inverse cumulative density function. (ICDF)<br>
     * Returns the variable "val" for this equation:  p = P(X >= val)
     * @param v degrees of freedom
     * @param p any value in range [0,1]
     * @return inverse cumulative density function (ICDF) result
     */
    public static double invCumRightTailed(double v, double p){
        return invCumLeftTailed(v, 1d - p);
    }




    ///////////////
    /*  HELPERS  */
    ///////////////


    /*
        Writing this function gave me cancer so im glad that it works hehe
        For more information: https://core.ac.uk/download/pdf/82140723.pdf
        inverse regularized incomplete beta function
     */
    private static double iribfForTDist(double alpha, double beta, double probability) {
        final double EPSILON = 1E-18;

        double t = TMath.exp(alpha * TMath.log(alpha / (alpha + beta))) / alpha;
        double u = TMath.exp(beta * TMath.log(beta / (alpha + beta))) / beta;

        double ret = probability < t / (t + u) ?
                TMath.pow(alpha * (t + u) * probability, 1d / alpha) :
                1d - TMath.pow(beta * (t + u) * (1d - probability), 1d / beta);

        double logBeta = TMath.logBeta(alpha, beta);

        double error;
        double alphaMOne = alpha - 1d;
        double betaMOne = beta - 1d;
        for (int j = 0; j < 10; j++) {
            if (ret == 0d || ret == 1d) return ret;
            error = TMath.regularizedBeta(ret, alpha, beta) - probability;
            t = TMath.exp(alphaMOne * TMath.log(ret) + betaMOne * TMath.log(1d - ret) - logBeta);
            u = error / t;
            ret -= (t = u / (1d - 0.5d * Math.min(1d, u * (alphaMOne / ret - betaMOne / (1d - ret)))));
            if (ret <= 0d) ret = 0.5d * (ret + t);
            if (ret >= 1d) ret = 0.5d * (ret + t + 1d);
            if (TMath.abs(t) < EPSILON * ret && j > 0) break;
        }
        return ret;
    }




}
