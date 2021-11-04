package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.core.functions.TRange;


/**
 *  Defines  A * ceil(mx+n)
 */
public class TCeil extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */

    public TCeil(TRange range, double A, double m, double n) {super(FuncType.CEIL, range, A, m, n);}
    public TCeil(TRange range, double A, double m) {super(FuncType.CEIL, range, A, m);}
    public TCeil(TRange range, double A) {super(FuncType.CEIL, range, A);}
    public TCeil(TRange range) {super(FuncType.CEIL, range);}
    public TCeil(double A, double m, double n) {super(FuncType.CEIL, A, m, n);}
    public TCeil(double A, double m) {super(FuncType.CEIL, A, m);}
    public TCeil(double m) {super(FuncType.CEIL, m);}
    public TCeil(){super(FuncType.CEIL);}



    /*  METHODS  */

    @Override
    public double value(double x) {
        if( !this.range.isInRange(x) ) return 0;
        return A * Math.ceil(m * x + n); // A * ceil(mx+n)
    }





}
