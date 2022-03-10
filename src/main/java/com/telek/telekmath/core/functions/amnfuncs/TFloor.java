package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.core.functions.other.TConstantFunc;


/**
 *  Defines  A * floor(mx+n)
 */
public class TFloor extends AbstractAMNFunction {


    /*  CONSTRUCTORS  */
    public TFloor(TRange range, double A, double m, double n) {super(FuncType.FLOOR, range, A, m, n);}
    public TFloor(TRange range, double A, double m) {super(FuncType.FLOOR, range, A, m);}
    public TFloor(TRange range, double A) {super(FuncType.FLOOR, range, A);}
    public TFloor(TRange range) {super(FuncType.FLOOR, range);}
    public TFloor(double A, double m, double n) {super(FuncType.FLOOR, A, m, n);}
    public TFloor(double A, double m) {super(FuncType.FLOOR, A, m);}
    public TFloor(double m) {super(FuncType.FLOOR, m);}
    public TFloor(){super(FuncType.FLOOR);}



    /*  METHODS  */

    @Override
    public double value(double x) {
        if( !this.range.isInRange(x) ) return 0;
        return A * Math.floor(m * x + n); // A * floor(mx+n)
    }


    /**
     * The floor function has a derivative of 0 everywhere but the jumps and the derivative simply doesn't
     * exist at the jumps since the function isn't continuous at those points.
     * This method returns f(x) = 0 on purpose instead of returning null.
     * @return f(x) = 0 on purpose
     */
    @Override
    public TFunction derivative() {
        return new TFunction( new TConstantFunc(0d) );
    }



}
