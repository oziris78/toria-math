package com.telek.telekmath.core.functions.other;

import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.utils.TelekMathException.*;

import java.util.Objects;


/**
 * Defines a * log_b_(mx)
 */
public class TLog extends AbstractFunction {

    private final double a,b,m;


    /*  CONSTRUCTORS  */

    public TLog(TRange range, double a, double b, double m) {
        super(range);
        if(b == 1 || b < 0) throw new InvalidBaseException();
        if(m == 0) throw new InvalidValueException("m", m);
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
        double mx = m * x;
        if(mx <= 0 || !this.range.isInRange(x)) return 0;
        return a * Math.log(mx) / Math.log(b); // a * log_b_(mx)
    }


    @Override
    public TFunction derivative() {
        return new TFunction( // 1/a x^n  =  1 / ((lnb / a) x)
            new TInverseFunc(this.range, Math.log(b) / a, 1)
        );
    }

    @Override
    public String toString() {

        if(a == 0) return "0"; // a => 0

        String text = String.format("%f funcName_%f_(%fx)", a, b, m)
                .replaceAll("1.000000x", "x") // m => 1, -1
                .replaceAll("1.000000 funcName", "funcName"); // a => 1, -1

        // b => E, 2, 10
        if( b == 2d )
            text = text.replaceAll("funcName_2.000000_", "lg");
        else if( b == TMathConstants.E )
            text = text.replaceAll("funcName_2.718282_", "ln");
        else if( b == 10d )
            text = text.replaceAll("funcName_10.000000_", "log");
        else
            text = text.replaceAll("funcName", "log");

        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TLog tLog = (TLog) o;
        return Double.compare(tLog.a, a) == 0 && Double.compare(tLog.b, b) == 0 && Double.compare(tLog.m, m) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), a, b, m);
    }
}
