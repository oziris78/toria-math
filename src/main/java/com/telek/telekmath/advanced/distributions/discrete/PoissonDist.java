package com.telek.telekmath.advanced.distributions.discrete;


import com.telek.telekmath.exceptions.InvalidValueException;
import com.telek.telekmath.utils.TMath;

public class PoissonDist {

    /*  CONSTRUCTORS  */

    private PoissonDist(){}


    ///////////////
    /*  METHODS  */
    ///////////////

    public static double probability(double lambda, int val){
        checkForInput(lambda);
        return Math.pow(lambda, val) * Math.exp(-lambda) / TMath.factorial(val); // l^x * e^-l / x!
    }

    public static double expectedValue(double lambda){
        return lambda;
    }

    public static double variance(double lambda){
        return lambda;
    }

    public static double getLambda(int n, double p){
        return n * p;
    }


    ///////////////
    /*  HELPERS  */
    ///////////////

    private static void checkForInput(double lambda){
        if( lambda <= 0 ) throw new InvalidValueException("lambda", lambda);
    }



}
