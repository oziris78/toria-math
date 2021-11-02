package com.telek.telekmath.core.functions.trig;

import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.exceptions.WrongFunctionException;


/**
 * Defines  A * sin(mx+n)
 */
public class TSin extends AbstractFunction {

    private final double A, m, n;


    /*  CONSTRUCTORS  */


    public TSin(TRange range, double A, double m, double n) {
        super(range);
        if(A == 0) throw new WrongFunctionException("A", "Asin(mx+n)", "0");
        if(m == 0) throw new WrongFunctionException("m", "Asin(mx+n)", "0");
        this.A = A;  this.m = m;  this.n = n;
    }

    public TSin(TRange range, double A, double m) {
        this(range, A, m, 0d);
    }

    public TSin(TRange range, double A) {
        this(range, A, 1d, 0d);
    }

    public TSin(TRange range) {
        this(range, 1d, 1d, 0d);
    }

    public TSin(double A, double m, double n) {
        this(TRange.REEL_NUMBERS, A, m, n);
    }

    public TSin(double A, double m) {
        this(TRange.REEL_NUMBERS, A, m, 0d);
    }

    public TSin(double m) {
        this(TRange.REEL_NUMBERS, 1d, m, 0d);
    }

    public TSin() {
        this(TRange.REEL_NUMBERS, 1d, 1d, 0d);
    }



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.sin(this.m * degInRadians + this.n); // A * sin(mx+n)
    }



    /*  HELPERS  */

    @Override
    public String toString() {
        return ZTrigFuncStr.getTrigString("sin", A, m, n);
    }




}
