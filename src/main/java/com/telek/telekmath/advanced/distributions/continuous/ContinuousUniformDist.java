package com.telek.telekmath.advanced.distributions.continuous;



public class ContinuousUniformDist  {

    private ContinuousUniformDist(){}

    ///////////////
    /*  METHODS  */
    ///////////////

    public static double expectedValue(double alpha, double beta){
        return (alpha+beta) / 2d;
    }


    public static double variance(double alpha, double beta){
        return (beta-alpha) * (beta-alpha) / 12d;
    }


}
