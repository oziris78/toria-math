package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.core.functions.other.TConstantFunc;


/**
 *  Defines  A * ceil(mx+n)
 */
public class TCeil extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */

    public TCeil(TRange range, double A, double m, double n) {super(FuncType.CEIL, range, A, m, n);}
    public TCeil(TRange range, double A, double m) {super(FuncType.CEIL, range, A, m);}
    public TCeil(TRange range, double A) {super(FuncType.CEIL, range, A);}
    public TCeil(TRange range) {super(FuncType.CEIL, range);}
    public TCeil(double A, double m, double n) {super(FuncType.CEIL, A, m, n);}
    public TCeil(double A, double m) {super(FuncType.CEIL, A, m);}
    public TCeil(double m) {super(FuncType.CEIL, m);}
    public TCeil(){super(FuncType.CEIL);}



    /*  METHODS  */

    @Override
    public double value(double x) {
        if( !this.range.isInRange(x) ) return 0;
        return A * Math.ceil(m * x + n); // A * ceil(mx+n)
    }


    /**
     * The ceil function has a derivative of 0 everywhere but the jumps and the derivative simply doesn't
     * exist at the jumps since the function isn't continuous at those points.
     * This method returns f(x) = 0 on purpose instead of returning null.
     * @return f(x) = 0 on purpose
     */
    @Override
    public TFunction derivative() {
        return new TFunction(  new TConstantFunc(0d));
    }



}
