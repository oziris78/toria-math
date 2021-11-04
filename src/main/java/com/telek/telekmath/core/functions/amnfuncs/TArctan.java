package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.core.functions.TRange;


/**
 * Defines  A * arctan(mx+n)
 */
public class TArctan extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */

    public TArctan(TRange range, double A, double m, double n) {super(FuncType.ARCTAN, range, A, m, n);}
    public TArctan(TRange range, double A, double m) {super(FuncType.ARCTAN, range, A, m);}
    public TArctan(TRange range, double A) {super(FuncType.ARCTAN, range, A);}
    public TArctan(TRange range) {super(FuncType.ARCTAN, range);}
    public TArctan(double A, double m, double n) {super(FuncType.ARCTAN, A, m, n);}
    public TArctan(double A, double m) {super(FuncType.ARCTAN, A, m);}
    public TArctan(double m) {super(FuncType.ARCTAN, m);}
    public TArctan(){super(FuncType.ARCTAN);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.atan(this.m * degInRadians + this.n); // A * arctan(mx+n)
    }



}
