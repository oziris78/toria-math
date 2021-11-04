package com.telek.telekmath.core.functions.trig;

import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.exceptions.WrongFunctionException;



abstract class AbsTrigFunc extends AbstractFunction {

    protected enum TrigType{
        SIN("sin"), COS("cos"), TAN("tan"),
        SINH("sinh"), COSH("cosh"),
        ARCSIN("arcsin"), ARCCOS("arccos"), ARCTAN("arctan");

        final String funcName;
        TrigType(String funcName) {
            this.funcName = funcName;
        }
    }

    protected final double A, m, n;
    private TrigType trigType;


    /*  CONSTRUCTORS  */

    public AbsTrigFunc(TrigType trigType, TRange range, double A, double m, double n) {
        super(range);
        this.trigType = trigType;
        if(A == 0) throw new WrongFunctionException("A", String.format("A%s(mx+n)", this.trigType.funcName), "0");
        if(m == 0) throw new WrongFunctionException("m", String.format("A%s(mx+n)", this.trigType.funcName), "0");
        this.A = A;  this.m = m;  this.n = n;
    }

    public AbsTrigFunc(TrigType trigType, TRange range, double A, double m) {
        this(trigType, range, A, m, 0d);
    }

    public AbsTrigFunc(TrigType trigType, TRange range, double A) {
        this(trigType, range, A, 1d, 0d);
    }

    public AbsTrigFunc(TrigType trigType, TRange range) {
        this(trigType, range, 1d, 1d, 0d);
    }

    public AbsTrigFunc(TrigType trigType, double A, double m, double n) {
        this(trigType, TRange.REEL_NUMBERS, A, m, n);
    }

    public AbsTrigFunc(TrigType trigType, double A, double m) {
        this(trigType, TRange.REEL_NUMBERS, A, m, 0d);
    }

    public AbsTrigFunc(TrigType trigType, double m) {
        this(trigType, TRange.REEL_NUMBERS, 1d, m, 0d);
    }

    public AbsTrigFunc(TrigType trigType) {
        this(trigType, TRange.REEL_NUMBERS, 1d, 1d, 0d);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        String funcName = this.trigType.funcName;
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
