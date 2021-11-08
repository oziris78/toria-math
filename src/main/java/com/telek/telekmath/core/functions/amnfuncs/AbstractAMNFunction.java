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
        String funcName = this.funcType.funcName;
        if(A == 0) return "0";
        if( A == 1 ){
            if(n == 0){
                if( m == 1) return String.format("%s(x)", funcName);
                if( m == -1) return String.format("%s(-x)", funcName);
                return String.format("%s(%.3fx)", funcName, m);
            }
            else{
                if( m == 1) return String.format("%s(x+%.3f)", funcName, n);
                if( m == -1) return String.format("%s(-x+%.3f)", funcName, n);
                return String.format("%s(%.3fx+.3f)", funcName, m, n);
            }
        }
        else if( A == -1 ){
            if(n == 0){
                if( m == 1) return String.format("-%s(x)", funcName);
                if( m == -1) return String.format("-%s(-x)", funcName);
                return String.format("-%s(%.3fx)", funcName, m);
            }
            else{
                if( m == 1) return String.format("-%s(x+%.3f)", funcName, n);
                if( m == -1) return String.format("-%s(-x+%.3f)", funcName, n);
                return String.format("-%s(%.3fx+.3f)", funcName, m, n);
            }

        }

        return String.format("%.3f %s(%.3fx+%.3f)", A, funcName, m, n);
    }


}
