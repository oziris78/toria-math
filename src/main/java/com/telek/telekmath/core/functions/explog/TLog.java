package com.telek.telekmath.core.functions.explog;

import com.telek.telekmath.core.functions.general.TFunction;
import com.telek.telekmath.core.functions.general.TRange;
import com.telek.telekmath.TMath;


/**
 * Defines a * log_b_(mx)
 */
public class TLog extends TFunction {

    private final double a,b,m;


    /*  CONSTRUCTORS  */

    public TLog(TRange range, double a, double b, double m) {
        super(range);
        this.a = a;
        this.b = b;
        this.m = m;
    }

    public TLog(TRange range, double b, double m) {
        this(range, 1d, b, m);
    }

    public TLog(TRange range, double b) {
        this(range, 1d, b, 1d);
    }

    public TLog(TRange range) {
        this(range, 1d, TMath.E, 1d);
    }

    public TLog(double a, double b, double m) {
        this(TRange.REEL_NUMBERS, a, b, m);
    }

    public TLog(double b, double m) {
        this(TRange.REEL_NUMBERS, 1d, b, m);
    }

    public TLog(double b) {
        this(TRange.REEL_NUMBERS, 1d, b, 1d);
    }

    public TLog() {
        this(TRange.REEL_NUMBERS, 1d, TMath.E, 1d);
    }



    /*  METHODS  */

    @Override
    public double value(double x) {
        // log_b_a = loga / logb = lna / lnb
        return a * Math.log(m * x) / Math.log(b); // a * log_b_(mx)
    }


    @Override
    public TFunction derivative() {
        return new TInverseFunc(this.range, Math.log(b) / a, 1);
    }


    @Override
    public String toString() {
        return String.format("%f log_%f_(%fx)", a, b, m); // a * log_b_(mx)
    }


}
