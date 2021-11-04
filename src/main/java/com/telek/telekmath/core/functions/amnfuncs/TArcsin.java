package com.telek.telekmath.core.functions.amnfuncs;


import com.telek.telekmath.TMath;
import com.telek.telekmath.core.functions.TRange;


/**
 * Defines  A * arcsin(mx+n)
 */
public class TArcsin extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */

    public TArcsin(TRange range, double A, double m, double n) {super(FuncType.ARCSIN, range, A, m, n);}
    public TArcsin(TRange range, double A, double m) {super(FuncType.ARCSIN, range, A, m);}
    public TArcsin(TRange range, double A) {super(FuncType.ARCSIN, range, A);}
    public TArcsin(TRange range) {super(FuncType.ARCSIN, range);}
    public TArcsin(double A, double m, double n) {super(FuncType.ARCSIN, A, m, n);}
    public TArcsin(double A, double m) {super(FuncType.ARCSIN, A, m);}
    public TArcsin(double m) {super(FuncType.ARCSIN, m);}
    public TArcsin(){super(FuncType.ARCSIN);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.asin(this.m * degInRadians + this.n); // A * arcsin(mx+n)
    }



    /**  @see TMath#floor(double) */
    public static float asin(float a) {
        return TMath.asin(a);
    }



}
