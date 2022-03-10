package com.telek.telekmath.core.functions.other;

import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;

import java.util.Objects;


/**
 *  Defines  1 / (a * x^n)
 */
public class TInverseFunc extends AbstractFunction {

    private final double coef;
    private final int degree;

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

        return String.format("%f / x^%d", a, n)
                .replaceAll("1.000000", "1")
                .replaceAll("\\^1", "")
                .replaceAll(" / x\\^0", "")
                .replaceAll(" / x\\^-", " x^");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TInverseFunc that = (TInverseFunc) o;
        return Double.compare(that.coef, coef) == 0 && degree == that.degree;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coef, degree);
    }
}
