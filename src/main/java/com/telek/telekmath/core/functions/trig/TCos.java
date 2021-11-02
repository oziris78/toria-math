package com.telek.telekmath.core.functions.trig;

import com.telek.telekmath.exceptions.WrongFunctionException;
import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TRange;


/**
 * Defines  A * cos(mx+n)
 */
public class TCos extends AbstractFunction {

    private final double A, m, n;


    /*  CONSTRUCTORS  */


    public TCos(TRange range, double A, double m, double n) {
        super(range);
        if(A == 0) throw new WrongFunctionException("A", "Acos(mx+n)", "0");
        if(m == 0) throw new WrongFunctionException("m", "Acos(mx+n)", "0");
        this.A = A;  this.m = m;  this.n = n;
    }

    public TCos(TRange range, double A, double m) {
        this(range, A, m, 0d);
    }

    public TCos(TRange range, double A) {
        this(range, A, 1d, 0d);
    }

    public TCos(TRange range) {
        this(range, 1d, 1d, 0d);
    }

    public TCos(double A, double m, double n) {
        this(TRange.REEL_NUMBERS, A, m, n);
    }

    public TCos(double A, double m) {
        this(TRange.REEL_NUMBERS, A, m, 0d);
    }

    public TCos(double m) {
        this(TRange.REEL_NUMBERS, 1d, m, 0d);
    }

    public TCos() {
        this(TRange.REEL_NUMBERS, 1d, 1d, 0d);
    }



    /*  METHODS  */

    @Override
    public double value(double x) {
        if( !this.range.isInRange(x) ) return 0;
        return this.A * Math.cos(this.m * x + this.n); // A * cos(mx+n)
    }



    /*  HELPERS  */

    @Override
    public String toString() {
        return ZTrigFuncStr.getTrigString("cos", A, m, n);
    }



}
