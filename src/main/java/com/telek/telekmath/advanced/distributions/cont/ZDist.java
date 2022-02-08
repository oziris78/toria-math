package com.telek.telekmath.advanced.distributions.cont;


import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.exceptions.InvalidValueException;

public class ZDist {


    //////////////
    /*  FIELDS  */
    //////////////

    public static final double MEAN = 0, VARIANCE = 1;


    /* No constructor */
    private ZDist(){}


    ///////////////
    /*  METHODS  */
    ///////////////



    /**
     * Returns the probability of x <br>
     * Probability function.
     * @param x any value
     * @return f(x)
     */
    public static double density(double x){
        return 0.3989422804d * Math.exp(-0.5d * x * x);
    }


    /**
     * Returns P(X <= x) <br>
     * Cumulative probability function.
     * @param x any value
     * @return P(X <= x)
     */
    public static double areaUnder(double x)  {
        return 0.5d * (1 + TMath.erf(x / TMathConstants.SQRT2));
    }


    /**
     * Returns P(a <= X <= b). <br>
     * Cumulative probability function.
     * @param a any value
     * @param b any value
     * @return P(a <= X <= b)
     */
    public static double areaBetween(double a, double b) {
        if(a == b) return 0;
        if(b < a) return 0;
        return areaUnder(b) - areaUnder(a);
    }



    /**
     * Returns the point that corresponds to a shaded area of "probability" (the parameter). <br>
     * The shaded area starts from the left and goes to the right
     * @param probability a value in range [0, 1]
     * @return the point that corresponds to a shaded area
     */
    public static double invCumLeftToRight(double probability){
        if(!TRange.ZERO_TO_ONE.isInRange(probability))
            throw new InvalidValueException("probability", probability);
        return TMath.probit(probability);
    }


    /**
     * Returns the point that corresponds to a shaded area of "probability" (the parameter). <br>
     * The shaded area starts from the left and goes to the right
     * @param probability a value in range [0, 0.5]
     * @return the point that corresponds to a shaded area
     */
    public static double invCumZeroToRight(double probability){
        if( !(0d <= probability && probability <= 0.5d) )
            throw new InvalidValueException("probability", probability);
        return TMath.probit(probability + 0.5d);
    }




}
