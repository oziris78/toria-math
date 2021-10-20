package com.telek.telekmath.core.functions.trig;

import com.telek.telekmath.core.functions.general.TFunction;
import com.telek.telekmath.core.functions.general.TRange;


/**
 * Defines  A * cos(mx+n)
 */
public class TCos extends TFunction {

    private final double A, m, n;


    /*  CONSTRUCTORS  */


    public TCos(TRange range, double A, double m, double n) {
        super(range);
        if(A == 0) throw new RuntimeException("A value for Acos(mx+n) can't be 0, use TPolynomials for constant functions.");
        if(m == 0) throw new RuntimeException("m value for Acos(mx+n) can't be 0");
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

    public TCos(double A) {
        this(TRange.REEL_NUMBERS, A, 1d, 0d);
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


    @Override
    public TSin derivative() {
        // Acos(mx+n)  =>  derivative is  -A*m * sin(mx+n)
        return new TSin(this.range, -A*m, m, n);

    }


    /*  HELPERS  */

    @Override
    public String toString() {
        return String.format("%f cos(%fx+%f)", this.A, this.m, this.n);
    }



}
