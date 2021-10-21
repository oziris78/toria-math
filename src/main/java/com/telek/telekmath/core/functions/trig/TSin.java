package com.telek.telekmath.core.functions.trig;

import com.telek.telekmath.exceptions.WrongFunctionException;
import com.telek.telekmath.core.functions.general.*;


/**
 * Defines  A * sin(mx+n)
 */
public class TSin extends TFunction {

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

    public TSin(double A) {
        this(TRange.REEL_NUMBERS, A, 1d, 0d);
    }

    public TSin() {
        this(TRange.REEL_NUMBERS, 1d, 1d, 0d);
    }



    /*  METHODS  */

    @Override
    public double value(double x) {
        if( !this.range.isInRange(x) ) return 0;
        return this.A * Math.sin(this.m * x + this.n); // A * sin(mx+n)
    }


    @Override
    public TCos derivative() {
        // A sin(mx+n)  =>  derivative is  A*m cos(mx+n)
        return new TCos(this.range, A*m, m, n);
    }


    /*  HELPERS  */

    @Override
    public String toString() {
        return String.format("%f sin(%fx+%f)", this.A, this.m, this.n);
    }



}
