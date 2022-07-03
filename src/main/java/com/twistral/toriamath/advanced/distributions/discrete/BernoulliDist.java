package com.twistral.toriamath.advanced.distributions.discrete;


import com.twistral.toriamath.core.functions.TRange;
import com.twistral.toriamath.utils.ToriaMathException.*;


public class BernoulliDist {


    /* No constructor */
    private BernoulliDist(){}


    ///////////////
    /*  METHODS  */
    ///////////////


    public static double probability(double p, int x){
        checkForInput(p);
        if( x == 0 ) return 1d - p;
        if( x == 1 ) return p;
        return 0d;
    }

    public static double expectedValue(double p){
        checkForInput(p);
        return p;
    }

    public static double variance(double p){
        checkForInput(p);
        return p * (1d-p);
    }


    ///////////////
    /*  HELPERS  */
    ///////////////

    private static void checkForInput(double p){
        if( !TRange.ZERO_TO_ONE.isInRange(p)) throw new InvalidValueException("p", p);
    }



}
