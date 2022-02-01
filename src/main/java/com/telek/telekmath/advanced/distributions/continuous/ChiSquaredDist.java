package com.telek.telekmath.advanced.distributions.continuous;


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


    ///////////////
    /*  HELPERS  */
    ///////////////

    private static void checkForInput(double alpha){
        if(alpha <= 0) throw new NotGreaterThanZeroException("Alpha");
    }



}
