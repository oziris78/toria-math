package com.telek.telekmath.advanced.distributions.continuous.normal;


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


    // fractional error in math formula less than 1.2 * 10 ^ -7
    // although subject to catastrophic cancellation when z in very close to 0
    // from Chebyshev fitting formula for erf(z) from Numerical Recipes, 6.2
    public static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));

        // use Horner's method
        double ans = 1 - t * Math.exp(-z*z - 1.26551223 + t * (1.00002368 + t *
                (0.37409196 + t * (0.09678418 + t * (-0.18628806 + t * (0.27886807 +
                        t * (-1.13520398 + t * (1.48851587 + t * (-0.82215223 + t * (0.17087277))))))))));
        return Math.copySign(ans, z);
    }

    // NO MAP, P(X <= x)
    public static double areaUnder(double x)  {
        return 0.5d * (1 + erf(x / TMathConstants.SQRT2));
    }


    // MAP, P(a <= X <= b)
    /**
     * Returns the area of [a,b] interval.
     * @param a left side of the interval
     * @param b right side of the interval
     * @return the area under the curve in range [a,b]
     */
    public static double areaBetween(double a, double b) {
        if(a == b) return 0;
        if(b < a) return 0;
        if(a == 0) return areaFromZeroTo(b);
        if(a < 0 && b == 0) return areaFromZeroTo(a);
        if(a > 0 && b > 0) return areaFromZeroTo(b) - areaFromZeroTo(a);
        if(a < 0 && b < 0) return areaFromZeroTo(a) - areaFromZeroTo(b);
        if(a < 0 && b > 0) return areaFromZeroTo(a) + areaFromZeroTo(b);
        return 0;
    }


    // MAP, P(0 <= X <= value)
    public static double areaFromZeroTo(double value) {
        value = Double.parseDouble(String.format("%.2f", Math.abs(value)));
        return SNormalTable.areaFromZeroTo(value);
    }


    public static double inverseCumulative(double probability){
        return 0;
    }





    ///////////////
    /*  HELPERS  */
    ///////////////



}
