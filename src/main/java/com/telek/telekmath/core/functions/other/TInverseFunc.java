package com.telek.telekmath.core.functions.other;

import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.core.functions.polynomials.PolynomialTerm;



/**
 *  Defines  1 / (a * x^n)
 */
public class TInverseFunc extends AbstractFunction {

    private final PolynomialTerm term;


    /*  CONSTRUCTORS  */


    public TInverseFunc(TRange range, double coefficient, int degree) {
        super(range);
        this.term = new PolynomialTerm(coefficient, degree);
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
        return 1d / (term.getCoefficient() * Math.pow(x, term.getDegree()) ) ; // 1 / (a * x^n)
    }




    @Override
    public String toString() {
        double a = 1d / term.getCoefficient();
        int n = term.getDegree();

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
