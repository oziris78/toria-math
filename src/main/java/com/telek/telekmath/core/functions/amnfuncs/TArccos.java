package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.TMath;
import com.telek.telekmath.core.functions.TRange;


/**
 * Defines  A * arccos(mx+n)
 */
public class TArccos extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */

    public TArccos(TRange range, double A, double m, double n) {super(FuncType.ARCCOS, range, A, m, n);}
    public TArccos(TRange range, double A, double m) {super(FuncType.ARCCOS, range, A, m);}
    public TArccos(TRange range, double A) {super(FuncType.ARCCOS, range, A);}
    public TArccos(TRange range) {super(FuncType.ARCCOS, range);}
    public TArccos(double A, double m, double n) {super(FuncType.ARCCOS, A, m, n);}
    public TArccos(double A, double m) {super(FuncType.ARCCOS, A, m);}
    public TArccos(double m) {super(FuncType.ARCCOS, m);}
    public TArccos(){super(FuncType.ARCCOS);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.acos(this.m * degInRadians + this.n); // A * arccos(mx+n)
    }



    /**  @see TMath#floor(double) */
    public static float acos(float a) {
        return TMath.acos(a);
    }







}
