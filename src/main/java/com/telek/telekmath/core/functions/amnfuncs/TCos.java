package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;



/**
 * Defines  A * cos(mx+n)
 */
public class TCos extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */

    public TCos(TRange range, double A, double m, double n) {super(FuncType.COS, range, A, m, n);}
    public TCos(TRange range, double A, double m) {super(FuncType.COS, range, A, m);}
    public TCos(TRange range, double A) {super(FuncType.COS, range, A);}
    public TCos(TRange range) {super(FuncType.COS, range);}
    public TCos(double A, double m, double n) {super(FuncType.COS, A, m, n);}
    public TCos(double A, double m) {super(FuncType.COS, A, m);}
    public TCos(double m) {super(FuncType.COS, m);}
    public TCos(){super(FuncType.COS);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.cos(this.m * degInRadians + this.n); // A * cos(mx+n)
    }


    @Override
    public TFunction derivative() {
        return new TFunction(
            new TSin(this.range, -A*m, m, n)
        );
    }

}

