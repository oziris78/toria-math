package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.core.functions.TRange;


/**
 *  Defines  A * sign(mx+n)
 */
public class TSign extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */

    public TSign(TRange range, double A, double m, double n) {super(FuncType.SIGN, range, A, m, n);}
    public TSign(TRange range, double A, double m) {super(FuncType.SIGN, range, A, m);}
    public TSign(TRange range, double A) {super(FuncType.SIGN, range, A);}
    public TSign(TRange range) {super(FuncType.SIGN, range);}
    public TSign(double A, double m, double n) {super(FuncType.SIGN, A, m, n);}
    public TSign(double A, double m) {super(FuncType.SIGN, A, m);}
    public TSign(double m) {super(FuncType.SIGN, m);}
    public TSign(){super(FuncType.SIGN);}



    /*  METHODS  */

    @Override
    public double value(double x) {
        if( !this.range.isInRange(x) ) return 0;
        double value = m * x + n;
        if(value == 0) return 0;
        else if(value < 0) return -A;
        else return A;
    }





}

