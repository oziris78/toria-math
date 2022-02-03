package com.telek.telekmath.advanced.distributions.continuous.normal;


import com.telek.telekmath.TMath;
import com.telek.telekmath.core.constants.TMathConstants;

public class StandardNormalDist {


    //////////////
    /*  FIELDS  */
    //////////////

    public static final double MEAN = 0, VARIANCE = 1;


    /* No constructor */
    private StandardNormalDist(){}


    ///////////////
    /*  METHODS  */
    ///////////////



    /**
     * Returns P(X <= x)
     * @param x any value
     * @return P(X <= x)
     */
    public static double areaUnder(double x)  {
        return 0.5d * (1 + TMath.erf(x / TMathConstants.SQRT2));
    }


    /**
     * Returns P(a <= X <= b)
     * @param a any value
     * @param b any value
     * @return P(a <= X <= b)
     */
    public static double areaBetween(double a, double b) {
        if(a == b) return 0;
        if(b < a) return 0;
        return areaUnder(b) - areaUnder(a);
    }




    // not implemented yet
    public static double inverseCumulative(double probability){
        return 0;
    }





    ///////////////
    /*  HELPERS  */
    ///////////////






}
