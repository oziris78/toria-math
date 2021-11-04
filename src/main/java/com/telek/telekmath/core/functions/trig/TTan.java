package com.telek.telekmath.core.functions.trig;

import com.telek.telekmath.core.functions.TRange;



/**
 * Defines  A * tan(mx+n)
 */
public class TTan extends AbsTrigFunc {


    /*  CONSTRUCTORS  */

    public TTan(TRange range, double A, double m, double n) {super(TrigType.TAN, range, A, m, n);}
    public TTan(TRange range, double A, double m) {super(TrigType.TAN, range, A, m);}
    public TTan(TRange range, double A) {super(TrigType.TAN, range, A);}
    public TTan(TRange range) {super(TrigType.TAN, range);}
    public TTan(double A, double m, double n) {super(TrigType.TAN, A, m, n);}
    public TTan(double A, double m) {super(TrigType.TAN, A, m);}
    public TTan(double m) {super(TrigType.TAN, m);}
    public TTan(){super(TrigType.TAN);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.tan(this.m * degInRadians + this.n); // A * tan(mx+n)
    }



}

