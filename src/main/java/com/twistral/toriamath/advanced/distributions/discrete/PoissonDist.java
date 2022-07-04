package com.twistral.toriamath.advanced.distributions.discrete;


import com.twistral.toriamath.utils.TMath;
import com.twistral.toriamath.utils.ToriaMathException.*;

public class PoissonDist {


    /* No constructor */
    private PoissonDist(){}


    ///////////////
    /*  METHODS  */
    ///////////////

    public static double probability(double lambda, int val){
        checkForInput(lambda);
        return Math.pow(lambda, val) * Math.exp(-lambda) / TMath.factorialInt(val); // l^x * e^-l / x!
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
