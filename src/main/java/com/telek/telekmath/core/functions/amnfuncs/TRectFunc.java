package com.telek.telekmath.core.functions.amnfuncs;


import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.core.functions.polynomials.PolynomialTerm;
import com.telek.telekmath.core.functions.polynomials.TPolynomial;


/**
 * Defines  A * rect(mx+n). <br>
 * <a href="https://en.wikipedia.org/wiki/Rectangular_function">Wikipedia Link</a>
 */
public class TRectFunc extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */

    public TRectFunc(TRange range, double A, double m, double n) {super(FuncType.RECT, range, A, m, n);}
    public TRectFunc(TRange range, double A, double m) {super(FuncType.RECT, range, A, m);}
    public TRectFunc(TRange range, double A) {super(FuncType.RECT, range, A);}
    public TRectFunc(TRange range) {super(FuncType.RECT, range);}
    public TRectFunc(double A, double m, double n) {super(FuncType.RECT, A, m, n);}
    public TRectFunc(double A, double m) {super(FuncType.RECT, A, m);}
    public TRectFunc(double m) {super(FuncType.RECT, m);}
    public TRectFunc(){super(FuncType.RECT);}



    /*  METHODS  */

    @Override
    public double value(double x) {
        if( !this.range.isInRange(x) ) return 0;

        double rectX;
        double absX = Math.abs(m * x + n);
        if(absX > 0.5d) rectX = 0d;
        else if(absX == 0.5d) rectX = 0.5d;
        else rectX = 1d;

        return A * rectX; // A * rect(mx+n)

    }


    @Override
    public TFunction derivative() {
        return new TFunction(
            new TPolynomial( new PolynomialTerm() )
        );
    }




}
