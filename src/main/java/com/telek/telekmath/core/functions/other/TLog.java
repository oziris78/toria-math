package com.telek.telekmath.core.functions.other;

import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.functions.TRange;


/**
 * Defines a * log_b_(mx)
 */
public class TLog extends AbstractFunction {

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
        this(range, 1d, TMathConstants.E, 1d);
    }

    public TLog(double a, double b, double m) {
        this(TRange.REEL_NUMBERS, a, b, m);
    }

    public TLog(double b, double m) {
        this(TRange.REEL_NUMBERS, 1d, b, m);
    }


    /**  Defines ln(mx)  */
    public TLog(double m) {
        this(TRange.REEL_NUMBERS, 1d, TMathConstants.E, m);
    }


    /**  Natural logarithm, ln(x)  */
    public TLog() {
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
    public TFunction derivative() {
        return new TFunction( // 1/a x^n  =  1 / ((lnb / a) x)
            new TInverseFunc(this.range, Math.log(b) / a, 1)
        );
    }

    @Override
    public String toString() {
        String str = String.format("%.3f log_%.3f_(%.3fx)", a, b, m);
        if(str.startsWith("1.000 ")) str.replaceFirst("1.000 ", "");
        if(str.startsWith("-1.000 ")) str.replaceFirst("-1.000 ", "-");
        if(str.contains("log_2.718_")) str.replaceFirst("log_2.718_", "ln");
        if(str.contains("1.000x")) str.replaceFirst("1.000x", "x");
        if(str.contains("-1.000x")) str.replaceFirst("-1.000x", "-x");
        return str;
    }


}
