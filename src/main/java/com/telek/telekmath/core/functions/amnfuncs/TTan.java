package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.core.functions.TRange;



/**
 * Defines  A * tan(mx+n)
 */
public class TTan extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */

    public TTan(TRange range, double A, double m, double n) {super(FuncType.TAN, range, A, m, n);}
    public TTan(TRange range, double A, double m) {super(FuncType.TAN, range, A, m);}
    public TTan(TRange range, double A) {super(FuncType.TAN, range, A);}
    public TTan(TRange range) {super(FuncType.TAN, range);}
    public TTan(double A, double m, double n) {super(FuncType.TAN, A, m, n);}
    public TTan(double A, double m) {super(FuncType.TAN, A, m);}
    public TTan(double m) {super(FuncType.TAN, m);}
    public TTan(){super(FuncType.TAN);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.tan(this.m * degInRadians + this.n); // A * tan(mx+n)
    }



}

