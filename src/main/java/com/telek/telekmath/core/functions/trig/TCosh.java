package com.telek.telekmath.core.functions.trig;

import com.telek.telekmath.core.functions.TRange;



/**
 * Defines  A * cosh(mx+n)
 */
public class TCosh extends AbsTrigFunc {


    /*  CONSTRUCTORS  */

    public TCosh(TRange range, double A, double m, double n) {super(TrigType.COSH, range, A, m, n);}
    public TCosh(TRange range, double A, double m) {super(TrigType.COSH, range, A, m);}
    public TCosh(TRange range, double A) {super(TrigType.COSH, range, A);}
    public TCosh(TRange range) {super(TrigType.COSH, range);}
    public TCosh(double A, double m, double n) {super(TrigType.COSH, A, m, n);}
    public TCosh(double A, double m) {super(TrigType.COSH, A, m);}
    public TCosh(double m) {super(TrigType.COSH, m);}
    public TCosh(){super(TrigType.COSH);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.cosh(this.m * degInRadians + this.n); // A * cosh(mx+n)
    }



}
