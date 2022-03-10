package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.core.functions.other.TConstantFunc;


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



    /**
     * The derivative of the sign function is just equal to zero, except at zero, where the derivative does not exist
     * @return f(x) = 0 on purpose
     */
    @Override
    public TFunction derivative() {
        return new TFunction( new TConstantFunc(0d) );
    }




}

