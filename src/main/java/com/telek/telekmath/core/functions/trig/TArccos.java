package com.telek.telekmath.core.functions.trig;

import com.telek.telekmath.core.functions.TRange;


/**
 * Defines  A * arccos(mx+n)
 */
public class TArccos extends AbsTrigFunc {


    /*  CONSTRUCTORS  */

    public TArccos(TRange range, double A, double m, double n) {super(TrigType.ARCCOS, range, A, m, n);}
    public TArccos(TRange range, double A, double m) {super(TrigType.ARCCOS, range, A, m);}
    public TArccos(TRange range, double A) {super(TrigType.ARCCOS, range, A);}
    public TArccos(TRange range) {super(TrigType.ARCCOS, range);}
    public TArccos(double A, double m, double n) {super(TrigType.ARCCOS, A, m, n);}
    public TArccos(double A, double m) {super(TrigType.ARCCOS, A, m);}
    public TArccos(double m) {super(TrigType.ARCCOS, m);}
    public TArccos(){super(TrigType.ARCCOS);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.acos(this.m * degInRadians + this.n); // A * arccos(mx+n)
    }



    /**
     * This method was written by Tommy Ettinger.
     * @param a any float
     * @return arccos(a), it is faster than Math.acos(a) but not as accurate
     */
    public static float acos(float a) {
        float a2 = a * a;  // a squared
        float a3 = a * a2; // a cubed
        if (a >= 0f) {
            return (float) Math.sqrt(1f - a) *
                    (1.5707288f - 0.2121144f * a + 0.0742610f * a2 - 0.0187293f * a3);
        }
        else {
            return 3.14159265358979323846f - (float) Math.sqrt(1f + a) *
                    (1.5707288f + 0.2121144f * a + 0.0742610f * a2 + 0.0187293f * a3);
        }
    }



}
