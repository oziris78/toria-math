package com.telek.telekmath.core.functions.explog;

import com.telek.telekmath.core.functions.general.TFunction;
import com.telek.telekmath.core.functions.general.TRange;
import com.telek.telekmath.core.functions.polynomials.PolynomialTerm;



/**
 *  Defines  1 / (a * x^n)
 */
public class TInverseFunc extends TFunction {

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
        return 1d / (term.getCoefficient() * Math.pow(x, term.getDegree()) ) ; // 1 / (a * x^n)
    }


    @Override
    public TFunction derivative() {
        return new TInverseFunc(this.range, - term.getCoefficient() / term.getDegree(), term.getDegree() + 1);
    }


    @Override
    public String toString() {
        return String.format("%f / x^%d", 1d / term.getCoefficient(), term.getDegree());
    }

}
