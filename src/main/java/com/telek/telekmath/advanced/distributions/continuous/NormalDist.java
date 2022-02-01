package com.telek.telekmath.advanced.distributions.continuous;


import com.telek.telekmath.exceptions.TelekMathException.NotGreaterThanZeroException;


public class NormalDist  {


    //////////////
    /*  FIELDS  */
    //////////////

    public static final double SNORMAL_MEAN = 0d, SNORMAL_SIGMA = 1d;


    /* No constructor */
    private NormalDist(){}


    ///////////////
    /*  METHODS  */
    ///////////////

    public static double expectedValue(double mean){
        return mean;
    }


    public static double variance(double sigma){
        checkForInput(sigma);
        return sigma * sigma;
    }




    ///////////////
    /*  HELPERS  */
    ///////////////

    private static void checkForInput(double sigma){
        if( sigma <= 0 ) throw new NotGreaterThanZeroException("Sigma");
    }


}
