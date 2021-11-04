package com.telek.telekmath.core.functions.amnfuncs;


import com.telek.telekmath.core.constants.InternalConstants;
import com.telek.telekmath.core.functions.TRange;


/**
 * Defines  A * dirac(mx+n)
 */
public class TDiracDeltaFunc extends AbstractAMNFunction{



    /*  CONSTRUCTORS  */

    public TDiracDeltaFunc(TRange range, double A, double m, double n) {super(FuncType.DIRAC, range, A, m, n);}
    public TDiracDeltaFunc(TRange range, double A, double m) {super(FuncType.DIRAC, range, A, m);}
    public TDiracDeltaFunc(TRange range, double A) {super(FuncType.DIRAC, range, A);}
    public TDiracDeltaFunc(TRange range) {super(FuncType.DIRAC, range);}
    public TDiracDeltaFunc(double A, double m, double n) {super(FuncType.DIRAC, A, m, n);}
    public TDiracDeltaFunc(double A, double m) {super(FuncType.DIRAC, A, m);}
    public TDiracDeltaFunc(double m) {super(FuncType.DIRAC, m);}
    public TDiracDeltaFunc(){super(FuncType.DIRAC);}



    /*  METHODS  */


    /**
     * Returns dirac(x).
     * @param x any x value
     * @return dirac(x), if x=0 returns the max value of the defined range since TMath doesn't have a Inf constant
     */
    @Override
    public double value(double x) {
        if( !this.range.isInRange(x) ) return 0;
        if(Math.abs(m * x + n) < InternalConstants.DIRAC_DELTA_THRESHHOLD) // is zero?
            return this.range.right; // returns the biggest value in range
        else
            return 0d;
    }




}
