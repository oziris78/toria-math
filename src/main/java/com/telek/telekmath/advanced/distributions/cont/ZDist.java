package com.telek.telekmath.advanced.distributions.cont;


import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.utils.TelekMathException.*;


public class ZDist {



    /* No constructor */
    private ZDist(){}


    ///////////////
    /*  METHODS  */
    ///////////////




    /**
     * @param x any value
     * @return probability density function (PDF) result
     */
    public static double density(double x){
        return 0.3989422804d * Math.exp(-0.5d * x * x);
    }



    /**
     * Returns the shaded area created by P(X <= x). <br>
     * @param x any value
     * @return cumulative density function (CDF) result
     */
    public static double cumulativeProbability(double x)  {
        return 0.5d * (1 + TMath.erf(x / TMathConstants.SQRT2));
    }




    /**
     * Returns the shaded area created by P(a <= X <= b). <br>
     * @param a any value
     * @param b any value
     * @return cumulative density function (CDF) result
     */
    public static double cumulativeProbabilityBetween(double a, double b) {
        if(a == b) return 0;
        if(b < a) return 0;
        return cumulativeProbability(b) - cumulativeProbability(a);
    }






    /**
     * Left tailed inverse cumulative density function (ICDF). <br>
     * Returns the variable "val" for this equation:  p = P(X <= val)
     * @param p any value in range [0,1]
     * @return inverse cumulative density function (ICDF) result
     */
    public static double invCumLeftTailed(double p){
        if(!TRange.ZERO_TO_ONE.isInRange(p)) throw new InvalidValueException("p", p);
        return TMath.probit(p);
    }




    /**
     * Right tailed inverse cumulative density function. (ICDF)<br>
     * Returns the variable "val" for this equation:  p = P(X >= val)
     * @param p a value in range [0, 0.5]
     * @return inverse cumulative density function (ICDF) result
     */
    public static double invCumRightTailed(double p){
        if( !(-0.5d <= p && p <= 0.5d) ) throw new InvalidValueException("p", p);
        return TMath.probit(p + 0.5d);
    }




}
