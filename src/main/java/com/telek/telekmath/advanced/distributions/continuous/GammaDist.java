package com.telek.telekmath.advanced.distributions.continuous;


import com.telek.telekmath.exceptions.TelekMathException;

public class GammaDist  {


    /* No constructor */
    private GammaDist(){}


    ///////////////
    /*  METHODS  */
    ///////////////


    public static double expectedValue(double alpha, double beta){
        checkForInput(alpha, beta);
        return alpha * beta;
    }


    public static double variance(double alpha, double beta){
        checkForInput(alpha, beta);
        return alpha * beta * beta;
    }


    ///////////////
    /*  HELPERS  */
    ///////////////

    public static void checkForInput(double alpha, double beta){
        if( alpha <= 0 || beta <= 0 ) throw new TelekMathException.NotGreaterThanZeroException("Alpha and beta");
    }




}
