package com.telek.telekmath.advanced.distributions.cont;


import com.telek.telekmath.TMath;
import com.telek.telekmath.core.constants.TMathConstants;

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


    public static double cumulativeProbability(/* ??? */){
        return 0;
    }



    public static double inverseCumulativeProbability(/* ??? */){
        return 0;
    }




    ///////////////
    /*  HELPERS  */
    ///////////////







}
