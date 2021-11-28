package com.telek.telekmath.core.functions.amnfuncs;

import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.exceptions.WrongFunctionException;


/**
 * Defines all the functions in the form  "A * f(mx+n)"
 */
abstract class AbstractAMNFunction extends AbstractFunction {

    protected enum FuncType {
        SIN("sin"), COS("cos"), TAN("tan"),
        SINH("sinh"), COSH("cosh"),
        CEIL("ceil"), FLOOR("floor"), SIGN("sign"),
        HEAVISIDE("u"), DIRAC("\u03B4"), RECT("rect");

        /*  "\u03B4"  is  greek lowercase delta */

        final String funcName;
        FuncType(String funcName) {
            this.funcName = funcName;
        }
    }

    protected final double A, m, n;
    private FuncType funcType;


    /*  CONSTRUCTORS  */

    public AbstractAMNFunction(FuncType funcType, TRange range, double A, double m, double n) {
        super(range);
        this.funcType = funcType;
        if(A == 0) throw new WrongFunctionException("A", String.format("A%s(mx+n)", this.funcType.funcName), "0");
        if(m == 0) throw new WrongFunctionException("m", String.format("A%s(mx+n)", this.funcType.funcName), "0");
        this.A = A;  this.m = m;  this.n = n;
    }

    public AbstractAMNFunction(FuncType funcType, TRange range, double A, double m) {
        this(funcType, range, A, m, 0d);
    }

    public AbstractAMNFunction(FuncType funcType, TRange range, double A) {
        this(funcType, range, A, 1d, 0d);
    }

    public AbstractAMNFunction(FuncType funcType, TRange range) {
        this(funcType, range, 1d, 1d, 0d);
    }

    public AbstractAMNFunction(FuncType funcType, double A, double m, double n) {
        this(funcType, TRange.REEL_NUMBERS, A, m, n);
    }

    public AbstractAMNFunction(FuncType funcType, double A, double m) {
        this(funcType, TRange.REEL_NUMBERS, A, m, 0d);
    }

    public AbstractAMNFunction(FuncType funcType, double m) {
        this(funcType, TRange.REEL_NUMBERS, 1d, m, 0d);
    }

    public AbstractAMNFunction(FuncType funcType) {
        this(funcType, TRange.REEL_NUMBERS, 1d, 1d, 0d);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return String.format("%f * %s(%fx+%f)", A, this.funcType.funcName, m, n)
                .replaceAll("1.000000 \\* ", "") // A => -1, 1
                .replaceAll("1.000000x", "x") // M => -1, 1
                .replaceAll("\\+\\-", "\\-")
                .replaceAll("\\+0.000000", "")
                .replaceAll("\\+1.000000", "\\+1")
                .replaceAll("\\-1.000000", "\\-1");
    }


}
