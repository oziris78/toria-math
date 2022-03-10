package com.telek.telekmath.core.functions.amnfuncs;


import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.core.functions.other.TConstantFunc;


/**
 * Defines  A * rect(mx+n). <br>
 * <a href="https://en.wikipedia.org/wiki/Rectangular_function">Wikipedia Link</a>
 */
public class TRectFunc extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */
    public TRectFunc(TRange range, double A, double m, double n) {super(FuncType.RECT, range, A, m, n);}
    public TRectFunc(TRange range, double A, double m) {super(FuncType.RECT, range, A, m);}
    public TRectFunc(TRange range, double A) {super(FuncType.RECT, range, A);}
    public TRectFunc(TRange range) {super(FuncType.RECT, range);}
    public TRectFunc(double A, double m, double n) {super(FuncType.RECT, A, m, n);}
    public TRectFunc(double A, double m) {super(FuncType.RECT, A, m);}
    public TRectFunc(double m) {super(FuncType.RECT, m);}
    public TRectFunc(){super(FuncType.RECT);}



    /*  METHODS  */

    @Override
    public double value(double x) {
        if( !this.range.isInRange(x) ) return 0;


        double absX = Math.abs(m * x + n);

        if(absX > 0.5d)
            return A *  0d;
        else if(TMath.areEqual(absX, 0.5d))
            return A *  0.5d;
        else
            return A *  1d;


    }


    @Override
    public TFunction derivative() {
        return new TFunction(  new TConstantFunc(0d));
    }




}
