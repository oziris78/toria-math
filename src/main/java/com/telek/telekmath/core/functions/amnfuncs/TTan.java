package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.core.functions.other.TConstantFunc;


/**
 * Defines  A * tan(mx+n)
 */
public class TTan extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */
    public TTan(TRange range, double A, double m, double n) {super(FuncType.TAN, range, A, m, n);}
    public TTan(TRange range, double A, double m) {super(FuncType.TAN, range, A, m);}
    public TTan(TRange range, double A) {super(FuncType.TAN, range, A);}
    public TTan(TRange range) {super(FuncType.TAN, range);}
    public TTan(double A, double m, double n) {super(FuncType.TAN, A, m, n);}
    public TTan(double A, double m) {super(FuncType.TAN, A, m);}
    public TTan(double m) {super(FuncType.TAN, m);}
    public TTan(){super(FuncType.TAN);}



    /*  METHODS  */

    @Override
    public double value(double degInRadians) {
        if( !this.range.isInRange(degInRadians) ) return 0;
        return this.A * Math.tan(this.m * degInRadians + this.n); // A * tan(mx+n)
    }


    @Override
    public TFunction derivative() {
        // Am tan^2(mx+n)  + Am
        TFunction df = new TFunction();
        df.addProduct(
            new TTan(this.range, A, m, n), // A tan(mx+n)
            new TTan(this.range, m, m, n)  // m tan(mx+n)
                                           // Am tan^2(mx+n)
        );
        df.addProduct( new TConstantFunc(A * m) ); // Am
        return df;
    }




}

