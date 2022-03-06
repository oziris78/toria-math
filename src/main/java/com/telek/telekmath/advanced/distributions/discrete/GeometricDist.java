package com.telek.telekmath.advanced.distributions.discrete;


import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.utils.TelekMathException.*;


public class GeometricDist  {

    /* No constructor */
    private GeometricDist(){}


    ///////////////
    /*  METHODS  */
    ///////////////


    public static double probability(double p, int x){
        checkForInput(p);
        if( x < 1 ) return 0;
        return Math.pow(1-p, x-1) * p;
    }

    public static double expectedValue(double p){
        checkForInput(p);
        return 1d / p;
    }

    public static double variance(double p){
        checkForInput(p);
        return (1d-p) / (p * p);
    }


    ///////////////
    /*  HELPERS  */
    ///////////////

    private static void checkForInput(double p){
        if( !TRange.ZERO_TO_ONE.isInRange(p) || TMath.areEqual(p, 0d)) throw new InvalidValueException("p", p);
    }


}
