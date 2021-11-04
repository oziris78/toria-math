package com.telek.telekmath.core.functions.trig;

import com.telek.telekmath.core.functions.TRange;


/**
 * Defines  A * arctan(mx+n)
 */
public class TArctan extends AbsTrigFunc {


    /*  CONSTRUCTORS  */

    public TArctan(TRange range, double A, double m, double n) {super(TrigType.ARCTAN, range, A, m, n);}
    public TArctan(TRange range, double A, double m) {super(TrigType.ARCTAN, range, A, m);}
    public TArctan(TRange range, double A) {super(TrigType.ARCTAN, range, A);}
    public TArctan(TRange range) {super(TrigType.ARCTAN, range);}
    public TArctan(double A, double m, double n) {super(TrigType.ARCTAN, A, m, n);}
    public TArctan(double A, double m) {super(TrigType.ARCTAN, A, m);}
    public TArctan(double m) {super(TrigType.ARCTAN, m);}
    public TArctan(){super(TrigType.ARCTAN);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.atan(this.m * degInRadians + this.n); // A * arctan(mx+n)
    }



}
