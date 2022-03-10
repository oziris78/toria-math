package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;


/**
 * Defines  A * u(mx+n). <br>
 * Also known as the "unit step function". <br>
 */
public class THeavisideFunc extends AbstractAMNFunction {

    /*  CONSTRUCTORS  */
    public THeavisideFunc(TRange range, double A, double m, double n) {super(FuncType.HEAVISIDE, range, A, m, n);}
    public THeavisideFunc(TRange range, double A, double m) {super(FuncType.HEAVISIDE, range, A, m);}
    public THeavisideFunc(TRange range, double A) {super(FuncType.HEAVISIDE, range, A);}
    public THeavisideFunc(TRange range) {super(FuncType.HEAVISIDE, range);}
    public THeavisideFunc(double A, double m, double n) {super(FuncType.HEAVISIDE, A, m, n);}
    public THeavisideFunc(double A, double m) {super(FuncType.HEAVISIDE, A, m);}
    public THeavisideFunc(double m) {super(FuncType.HEAVISIDE, m);}
    public THeavisideFunc(){super(FuncType.HEAVISIDE);}



    /*  METHODS  */

    @Override
    public double value(double x) {
        if( !this.range.isInRange(x) ) return 0;
        double hStepResult = (m * x + n > 0) ? 1d : 0d; // u(mx+n)
        return A * hStepResult; // A * u(mx+n)
    }


    @Override
    public TFunction derivative() {
        return new TFunction(
            new TDiracDeltaFunc(this.range, A * m, m, n)
        );
    }



    //    @Override
//    public String toString() {
//        String hStepSide = (n != 0) ? String.format("u(x+%f)", n) : "u(x)";
//        if(A == 1) return hStepSide;
//        else if(A == -1) return "-" + hStepSide;
//        else return String.format("%f * %s", A, hStepSide);
//    }



}

