package com.telek.telekmath.core.functions.other;


import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.functions.*;


/**
 * a * b^mx
 */
public class TPowerFunc extends AbstractFunction {


    private final double a,b,m;

    /*  CONSTRUCTORS  */

    public TPowerFunc(TRange range, double a, double b, double m) {
        super(range);
        this.a = a;
        this.b = b;
        this.m = m;
    }

    public TPowerFunc(TRange range, double b, double m) {
        this(range, 1d, b, m);
    }

    public TPowerFunc(TRange range, double m) {
        this(range, 1d, TMathConstants.E, m);
    }

    public TPowerFunc(TRange range) {
        this(range, 1d, TMathConstants.E, 1d);
    }

    public TPowerFunc(double a, double b, double m) {
        this(TRange.REEL_NUMBERS, a, b, m);
    }

    public TPowerFunc(double b, double m) {
        this(TRange.REEL_NUMBERS, 1d, b, m);
    }

    public TPowerFunc(double m) {
        this(TRange.REEL_NUMBERS, 1d, TMathConstants.E, m);
    }

    public TPowerFunc() {
        this(TRange.REEL_NUMBERS, 1d, TMathConstants.E, 1d);
    }




    /*  METHODS  */


    @Override
    public double value(double x) {
        if( a == 0 || b == 0 || !this.range.isInRange(x) ) return 0;
        if( m == 0 ) return a;
        return a * Math.pow(b, m * x); // a * b^mx
    }




    @Override
    public String toString() {
        if( a == 0 || b == 0 ) return "0";
        if( m == 0 ) return String.valueOf(a);

        if( a == 1 ){
            if( b == 1) return "1";
            else if (b == TMathConstants.E) return String.format("e^%fx", this.b, this.m);
            else return String.format("%f^%fx", this.b, this.m);
        }
        else if( a == -1){
            if( b == 1) return "-1";
            else if (b == TMathConstants.E) return String.format("-e^%fx", this.b, this.m);
            else return String.format("-%f^%fx", this.b, this.m);
        }

        return String.format("%f %f^%fx", this.a, this.b, this.m);
    }


}
