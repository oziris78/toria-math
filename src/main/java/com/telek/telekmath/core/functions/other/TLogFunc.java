package com.telek.telekmath.core.functions.other;

import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TRange;


/**
 * Defines a * log_b_(mx)
 */
public class TLogFunc extends AbstractFunction {

    private final double a,b,m;


    /*  CONSTRUCTORS  */

    public TLogFunc(TRange range, double a, double b, double m) {
        super(range);
        this.a = a;
        this.b = b;
        this.m = m;
    }

    public TLogFunc(TRange range, double b, double m) {
        this(range, 1d, b, m);
    }

    public TLogFunc(TRange range, double b) {
        this(range, 1d, b, 1d);
    }

    public TLogFunc(TRange range) {
        this(range, 1d, TMathConstants.E, 1d);
    }

    public TLogFunc(double a, double b, double m) {
        this(TRange.REEL_NUMBERS, a, b, m);
    }

    public TLogFunc(double b, double m) {
        this(TRange.REEL_NUMBERS, 1d, b, m);
    }

    public TLogFunc(double b) {
        this(TRange.REEL_NUMBERS, 1d, b, 1d);
    }


    /**  Natural logarithm, ln(x)  */
    public TLogFunc() {
        this(TRange.REEL_NUMBERS, 1d, TMathConstants.E, 1d);
    }



    /*  METHODS  */

    @Override
    public double value(double x) {
        // log_b_a = loga / logb = lna / lnb
        if(x <= 0 || !this.range.isInRange(x)) return 0;
        return a * Math.log(m * x) / Math.log(b); // a * log_b_(mx)
    }



    @Override
    public String toString() {
        if( a == 0 ) return "0";

        if( a == 1 ){
            if( m == 1){
                if( b == TMathConstants.E) return "ln(x)";
                else return String.format("log_%.3f_(x)", b);
            }
            else if (m == -1){
                if( b == TMathConstants.E) return "ln(-x)";
                else return String.format("log_%.3f_(-x)", b);
            }
        }
        else if ( a == -1){
            if( m == 1){
                if( b == TMathConstants.E) return "-ln(x)";
                else return String.format("-log_%.3f_(x)", b);
            }
            else if (m == -1){
                if( b == TMathConstants.E) return "-ln(-x)";
                else return String.format("-log_%.3f_(-x)", b);
            }
        }
        return String.format("%.3f log_%.3f_(%.3fx)", a, b, m);
    }


}
