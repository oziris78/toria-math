package com.telek.telekmath.core.functions.amnfuncs;


import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;



/**
 * Defines  A * sinh(mx+n)
 */
public class TSinh extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */
    public TSinh(TRange range, double A, double m, double n) {super(FuncType.SINH, range, A, m, n);}
    public TSinh(TRange range, double A, double m) {super(FuncType.SINH, range, A, m);}
    public TSinh(TRange range, double A) {super(FuncType.SINH, range, A);}
    public TSinh(TRange range) {super(FuncType.SINH, range);}
    public TSinh(double A, double m, double n) {super(FuncType.SINH, A, m, n);}
    public TSinh(double A, double m) {super(FuncType.SINH, A, m);}
    public TSinh(double m) {super(FuncType.SINH, m);}
    public TSinh(){super(FuncType.SINH);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.sinh(this.m * degInRadians + this.n); // A * sinh(mx+n)
    }


    @Override
    public TFunction derivative() {
        return new TFunction(
                new TCosh(this.range, A * m, m, n)
        );
    }


}
