package com.telek.telekmath.core.functions.other;

import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.functions.TRange;



/**
 *  Defines  1 / (a * x^n)
 */
public class TInverseFunc extends AbstractFunction {

    private double coef;
    private int degree;

    /*  CONSTRUCTORS  */


    public TInverseFunc(TRange range, double coef, int degree) {
        super(range);
        this.coef = coef;
        this.degree = degree;
    }

    public TInverseFunc(TRange range, double coefficient) {
        this(range, coefficient, 1);
    }

    public TInverseFunc(TRange range) {
        this(range, 1d, 1);
    }

    public TInverseFunc(double coefficient, int degree) {
        this(TRange.REEL_NUMBERS, coefficient, degree);
    }

    public TInverseFunc(double coefficient) {
        this(TRange.REEL_NUMBERS, coefficient, 1);
    }

    public TInverseFunc() {
        this(TRange.REEL_NUMBERS, 1d, 1);
    }


    /*  METHODS  */

    @Override
    public double value(double x) {
        if( !this.range.isInRange(x) ) return 0;
        return 1d / (this.coef * Math.pow(x, this.degree) ) ; // 1 / (a * x^n)
    }



    @Override
    public TFunction derivative() {
        return new TFunction( //  1 / a'x^n' =  1 / ( (-a/n) x^n+1 )
            new TInverseFunc(this.range, - this.coef / this.degree, this.degree + 1)
        );
    }


    @Override
    public String toString() {
        double a = 1d / this.coef;
        int n = this.degree;

        if( a == 0) return "0";

        if( a == 1 ){
            if(n == -1) return String.format("%.3fx", a);
            if(n == 0) return String.format("%.3f", a);
            if(n == 1) return String.format("%.3f / x", a);
        }
        else if (a == -1){
            if(n == -1) return String.format("-%.3fx", a);
            if(n == 0) return String.format("-%.3f", a);
            if(n == 1) return String.format("-%.3f / x", a);
        }

        return String.format("%f / x^%d", a, n);
    }

}
