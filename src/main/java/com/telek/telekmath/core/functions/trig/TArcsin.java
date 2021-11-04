package com.telek.telekmath.core.functions.trig;


import com.telek.telekmath.core.functions.TRange;


/**
 * Defines  A * arcsin(mx+n)
 */
public class TArcsin extends AbsTrigFunc {


    /*  CONSTRUCTORS  */

    public TArcsin(TRange range, double A, double m, double n) {super(TrigType.ARCSIN, range, A, m, n);}
    public TArcsin(TRange range, double A, double m) {super(TrigType.ARCSIN, range, A, m);}
    public TArcsin(TRange range, double A) {super(TrigType.ARCSIN, range, A);}
    public TArcsin(TRange range) {super(TrigType.ARCSIN, range);}
    public TArcsin(double A, double m, double n) {super(TrigType.ARCSIN, A, m, n);}
    public TArcsin(double A, double m) {super(TrigType.ARCSIN, A, m);}
    public TArcsin(double m) {super(TrigType.ARCSIN, m);}
    public TArcsin(){super(TrigType.ARCSIN);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.asin(this.m * degInRadians + this.n); // A * arcsin(mx+n)
    }



    /**
     * This method was written by Tommy Ettinger.
     * @param a any float
     * @return arcsin(a), it is faster than Math.asin(a) but not as accurate
     */
    public static float asin(float a) {
        float a2 = a * a;  // a squared
        float a3 = a * a2; // a cubed
        if (a >= 0f) {
            return 1.5707963267948966f - (float) Math.sqrt(1f - a) * (1.5707288f - 0.2121144f * a + 0.0742610f * a2 - 0.0187293f * a3);
        }
        else {
            return -1.5707963267948966f + (float) Math.sqrt(1f + a) * (1.5707288f + 0.2121144f * a + 0.0742610f * a2 + 0.0187293f * a3);
        }
    }



}
