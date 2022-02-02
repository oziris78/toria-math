package com.telek.telekmath.advanced.distributions.continuous.normal;



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


    public static double areaFromZeroTo(double value) {
        value = Double.parseDouble(String.format("%.2f", Math.abs(value)));
        return SNormalTable.areaFromZeroTo(value);
    }






    ///////////////
    /*  HELPERS  */
    ///////////////



}
