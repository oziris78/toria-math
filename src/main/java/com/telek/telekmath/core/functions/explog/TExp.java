package com.telek.telekmath.core.functions.explog;


import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.functions.general.TFunction;
import com.telek.telekmath.core.functions.general.TRange;
import com.telek.telekmath.TMath;


/**
 * a * b^mx
 */
public class TExp extends TFunction {


    private final double a,b,m;

    /*  CONSTRUCTORS  */

    public TExp(TRange range, double a, double b, double m) {
        super(range);
        this.a = a;
        this.b = b;
        this.m = m;
    }

    public TExp(TRange range, double b, double m) {
        this(range, 1d, b, m);
    }

    public TExp(TRange range, double m) {
        this(range, 1d, TMathConstants.E, m);
    }

    public TExp(TRange range) {
        this(range, 1d, TMathConstants.E, 1d);
    }

    public TExp(double a, double b, double m) {
        this(TRange.REEL_NUMBERS, a, b, m);
    }

    public TExp(double b, double m) {
        this(TRange.REEL_NUMBERS, 1d, b, m);
    }

    public TExp(double m) {
        this(TRange.REEL_NUMBERS, 1d, TMathConstants.E, m);
    }

    public TExp() {
        this(TRange.REEL_NUMBERS, 1d, TMathConstants.E, 1d);
    }




    /*  METHODS  */


    @Override
    public double value(double x) {
        return a * Math.pow(b, m * x); // a * b^mx
    }


    @Override
    public TFunction derivative() {
        if( Math.abs(b - TMathConstants.E) <= 0.0005 )
            return new TExp(this.range, a*m, TMathConstants.E, m);
        return new TExp(this.range, a * m * Math.log(b), b, m);
    }


    @Override
    public String toString() {
        return String.format("%f %f^%fx", this.a, this.b, this.m);
    }


}
