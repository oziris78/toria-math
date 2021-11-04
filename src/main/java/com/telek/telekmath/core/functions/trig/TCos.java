package com.telek.telekmath.core.functions.trig;

import com.telek.telekmath.core.functions.TRange;



/**
 * Defines  A * cos(mx+n)
 */
public class TCos extends AbsTrigFunc {


    /*  CONSTRUCTORS  */

    public TCos(TRange range, double A, double m, double n) {super(TrigType.COS, range, A, m, n);}
    public TCos(TRange range, double A, double m) {super(TrigType.COS, range, A, m);}
    public TCos(TRange range, double A) {super(TrigType.COS, range, A);}
    public TCos(TRange range) {super(TrigType.COS, range);}
    public TCos(double A, double m, double n) {super(TrigType.COS, A, m, n);}
    public TCos(double A, double m) {super(TrigType.COS, A, m);}
    public TCos(double m) {super(TrigType.COS, m);}
    public TCos(){super(TrigType.COS);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.cos(this.m * degInRadians + this.n); // A * cos(mx+n)
    }



}

