package com.telek.telekmath.core.functions.amnfuncs;


import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.core.functions.other.TConstantFunc;


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
     * Returns dirac(x). <br>
     * This method is written so that it will return 1 instead of infinity when it receives 0. <br>
     * Think of it as a unit impulse.
     * @param x any x value
     * @return dirac(x), if x=0 returns the max value of the defined range since TMath doesn't have a Inf constant
     */
    @Override
    public double value(double x) {
        if( !this.range.isInRange(x) ) return 0;

        return  TMath.areEqual(m * x + n, 0) ? 1d : 0d;
    }


    /**
     * See <a href="https://dsp.stackexchange.com/a/68777">stackexchange link</a> for more info.
     * @return df/dx
     */
    @Override
    public TFunction derivative() {
        return new TFunction( new TConstantFunc(-A * m) );
    }





}
