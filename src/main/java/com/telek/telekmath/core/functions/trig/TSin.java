package com.telek.telekmath.core.functions.trig;

import com.telek.telekmath.core.functions.TRange;


/**
 * Defines  A * sin(mx+n)
 */
public class TSin extends AbsTrigFunc {


    /*  CONSTRUCTORS  */

    public TSin(TRange range, double A, double m, double n) {super(TrigType.SIN, range, A, m, n);}
    public TSin(TRange range, double A, double m) {super(TrigType.SIN, range, A, m);}
    public TSin(TRange range, double A) {super(TrigType.SIN, range, A);}
    public TSin(TRange range) {super(TrigType.SIN, range);}
    public TSin(double A, double m, double n) {super(TrigType.SIN, A, m, n);}
    public TSin(double A, double m) {super(TrigType.SIN, A, m);}
    public TSin(double m) {super(TrigType.SIN, m);}
    public TSin(){super(TrigType.SIN);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.sin(this.m * degInRadians + this.n); // A * sin(mx+n)
    }



}
