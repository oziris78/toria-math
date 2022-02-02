package com.telek.telekmath.advanced.distributions.continuous.normal;


import com.telek.telekmath.exceptions.TelekMathException.NotGreaterThanZeroException;


public class NormalDist  {

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
