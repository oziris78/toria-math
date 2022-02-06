package com.telek.telekmath.advanced.distributions.cont;


import com.telek.telekmath.TMath;
import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.functions.other.TPolynomial;


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
        double v1over2 = (v+1)/2d;
        return 0.5d + x * TMath.gamma(v1over2) * TMath.hypergeometric(0.5d, v1over2, 1.5d, -x*x/v)
                / TMath.gamma(v/2d) / Math.sqrt(v * TMathConstants.PI);
    }



    public static double inverseCumulativeProbability(double v, double x){
        TPolynomial g1 = new TPolynomial(0, 0.25d, 0, 0.25d);
        TPolynomial g2 = new TPolynomial(0, 3d / 96d, 0, 16d / 96d, 0, 5 / 96d);
        TPolynomial g3 = new TPolynomial(0, -15d / 384d, 0, 17d / 384d, 0, 19d / 384d, 0, 3d / 384d);
        TPolynomial g4 = new TPolynomial(0, -945d / 92160d, 0, -1920d / 92160d, 0, 1482d / 92160d,
                0, 776d / 92160d, 0, 79d / 92160d);

        double xalpha = Math.pow(v/2d, v/2d) * TMath.gamma((v-1)/2d) / x / Math.sqrt(2 * Math.PI) / Math.pow(2, (1-v)/2d)
                / TMath.gamma(v/2d);
        xalpha = Math.pow(xalpha, 1d/v);

        return xalpha + (g1.value(xalpha) / v) + (g2.value(xalpha) / v) + (g3.value(xalpha) / v) + (g4.value(xalpha) / v);
    }




    ///////////////
    /*  HELPERS  */
    ///////////////







}
