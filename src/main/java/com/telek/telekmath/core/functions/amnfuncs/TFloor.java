package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.core.functions.TRange;


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





}
