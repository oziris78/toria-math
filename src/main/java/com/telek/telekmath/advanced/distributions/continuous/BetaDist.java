package com.telek.telekmath.advanced.distributions.continuous;


import com.telek.telekmath.exceptions.TelekMathException.NotGreaterThanZeroException;



public class BetaDist  {


    /* No constructor */
    private BetaDist(){}


    ///////////////
    /*  METHODS  */
    ///////////////

    public static double expectedValue(double alpha, double beta){
        checkForInput(alpha, beta);
        return alpha / (alpha + beta);
    }


    public static double variance(double alpha, double beta){
        checkForInput(alpha, beta);
        return alpha * beta / ( (alpha+beta) * (alpha+beta) * (alpha+beta+1d) );
    }



    ///////////////
    /*  HELPERS  */
    ///////////////

    private static void checkForInput(double alpha, double beta){
        if( alpha <= 0 || beta <= 0) throw new NotGreaterThanZeroException("Alpha and beta");
    }


}




