package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;



/**
 * Defines  A * cosh(mx+n)
 */
public class TCosh extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */

    public TCosh(TRange range, double A, double m, double n) {super(FuncType.COSH, range, A, m, n);}
    public TCosh(TRange range, double A, double m) {super(FuncType.COSH, range, A, m);}
    public TCosh(TRange range, double A) {super(FuncType.COSH, range, A);}
    public TCosh(TRange range) {super(FuncType.COSH, range);}
    public TCosh(double A, double m, double n) {super(FuncType.COSH, A, m, n);}
    public TCosh(double A, double m) {super(FuncType.COSH, A, m);}
    public TCosh(double m) {super(FuncType.COSH, m);}
    public TCosh(){super(FuncType.COSH);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.cosh(this.m * degInRadians + this.n); // A * cosh(mx+n)
    }


    @Override
    public TFunction derivative() {
        return new TFunction(
            new TSinh(this.range, A * m, m, n)
        );
    }


}
