package com.telek.telekmath.core.functions.other;


import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.functions.*;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekutils.containers.TArrays;

import java.util.Objects;


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
    public TFunction derivative() {
        return new TFunction(  // a' b'^m'x  =  a*m*lnb * b^mx
            new TPowerFunc(this.range, a * m * Math.log(b), b, m)
        );
    }

    @Override
    public String toString() {

        if(a == 0 || b == 0) return "0";
        if(m == 0 || b == 1) return String.valueOf(a);

        // * a * b^mx
        String text = String.format("%f * %f^%fx", a, b, m)
                .replaceAll("1.000000 \\*", "1 \\*") // a => 1 || -1
                .replaceAll("1.000000\\^", "1\\^") // b => 1, -1
                .replaceAll("1.000000x", "x"); // m => 1, -1

        if( Math.abs(a) == 1 && text.contains("-1^"))
            text = text.substring(text.indexOf("-1^"));

        if( Math.abs(a) == 1 && text.contains("1^") && !text.contains("-1^"))
            text = text.substring(text.indexOf("1^"));

        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TPowerFunc that = (TPowerFunc) o;
        return Double.compare(that.a, a) == 0 && Double.compare(that.b, b) == 0 && Double.compare(that.m, m) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), a, b, m);
    }
}
