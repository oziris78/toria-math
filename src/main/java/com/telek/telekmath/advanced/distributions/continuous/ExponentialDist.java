package com.telek.telekmath.advanced.distributions.continuous;


import com.telek.telekmath.exceptions.TelekMathException.NotGreaterThanZeroException;


public class ExponentialDist  {


    private ExponentialDist(){}

    ///////////////
    /*  METHODS  */
    ///////////////


    public static double expectedValue(double beta){
        checkForInput(beta);
        return beta;
    }


    public static double variance(double beta){
        checkForInput(beta);
        return beta * beta;
    }


    ///////////////
    /*  HELPERS  */
    ///////////////


    public static void checkForInput(double beta){
        if( beta <= 0) throw new NotGreaterThanZeroException("Beta");
    }






}
