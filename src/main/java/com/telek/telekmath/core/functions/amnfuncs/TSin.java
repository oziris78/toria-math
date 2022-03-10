package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;


/**
 * Defines  A * sin(mx+n)
 */
public class TSin extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */
    public TSin(TRange range, double A, double m, double n) {super(FuncType.SIN, range, A, m, n);}
    public TSin(TRange range, double A, double m) {super(FuncType.SIN, range, A, m);}
    public TSin(TRange range, double A) {super(FuncType.SIN, range, A);}
    public TSin(TRange range) {super(FuncType.SIN, range);}
    public TSin(double A, double m, double n) {super(FuncType.SIN, A, m, n);}
    public TSin(double A, double m) {super(FuncType.SIN, A, m);}
    public TSin(double m) {super(FuncType.SIN, m);}
    public TSin(){super(FuncType.SIN);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.sin(this.m * degInRadians + this.n); // A * sin(mx+n)
    }


    @Override
    public TFunction derivative() {
        return new TFunction(
            new TCos(this.range, A * m, m, n)
        );
    }


}
