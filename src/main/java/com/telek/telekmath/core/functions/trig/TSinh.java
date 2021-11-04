package com.telek.telekmath.core.functions.trig;


import com.telek.telekmath.core.functions.TRange;



/**
 * Defines  A * sinh(mx+n)
 */
public class TSinh extends AbsTrigFunc {


    /*  CONSTRUCTORS  */

    public TSinh(TRange range, double A, double m, double n) {super(TrigType.SINH, range, A, m, n);}
    public TSinh(TRange range, double A, double m) {super(TrigType.SINH, range, A, m);}
    public TSinh(TRange range, double A) {super(TrigType.SINH, range, A);}
    public TSinh(TRange range) {super(TrigType.SINH, range);}
    public TSinh(double A, double m, double n) {super(TrigType.SINH, A, m, n);}
    public TSinh(double A, double m) {super(TrigType.SINH, A, m);}
    public TSinh(double m) {super(TrigType.SINH, m);}
    public TSinh(){super(TrigType.SINH);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.sinh(this.m * degInRadians + this.n); // A * sinh(mx+n)
    }



}
