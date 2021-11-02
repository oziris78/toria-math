package com.telek.telekmath.core.functions.trig;

import com.telek.telekmath.exceptions.WrongFunctionException;
import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.TRange;


/**
 * Defines  A * cos(mx+n)
 */
public class TCos extends AbstractFunction {

    private final double A, m, n;


    /*  CONSTRUCTORS  */


    public TCos(TRange range, double A, double m, double n) {
        super(range);
        if(A == 0) throw new WrongFunctionException("A", "Acos(mx+n)", "0");
        if(m == 0) throw new WrongFunctionException("m", "Acos(mx+n)", "0");
        this.A = A;  this.m = m;  this.n = n;
    }

    public TCos(TRange range, double A, double m) {
        this(range, A, m, 0d);
    }

    public TCos(TRange range, double A) {
        this(range, A, 1d, 0d);
    }

    public TCos(TRange range) {
        this(range, 1d, 1d, 0d);
    }

    public TCos(double A, double m, double n) {
        this(TRange.REEL_NUMBERS, A, m, n);
    }

    public TCos(double A, double m) {
        this(TRange.REEL_NUMBERS, A, m, 0d);
    }

    public TCos(double m) {
        this(TRange.REEL_NUMBERS, 1d, m, 0d);
    }

    public TCos() {
        this(TRange.REEL_NUMBERS, 1d, 1d, 0d);
    }



    /*  METHODS  */

    @Override
    public double value(double x) {
        if( !this.range.isInRange(x) ) return 0;
        return this.A * Math.cos(this.m * x + this.n); // A * cos(mx+n)
    }



    /*  HELPERS  */

    @Override
    public String toString() {
        if(A == 0) return "0";
        if( A == 1 ){
            if(n == 0){
                if( m == 1) return "cos(x)";
                if( m == -1) return "cos(-x)";
                return String.format("cos(%.3fx)", this.m);
            }
            else{
                if( m == 1) return String.format("cos(x+%.3f)", this.n);
                if( m == -1) return String.format("cos(-x+%.3f)", this.n);
                return String.format("cos(%.3fx+.3f)", this.m, this.n);
            }
        }
        else if( A == -1 ){
            if(n == 0){
                if( m == 1) return "-cos(x)";
                if( m == -1) return "-cos(-x)";
                return String.format("-cos(%.3fx)", this.m);
            }
            else{
                if( m == 1) return String.format("-cos(x+%.3f)", this.n);
                if( m == -1) return String.format("-cos(-x+%.3f)", this.n);
                return String.format("-cos(%.3fx+.3f)", this.m, this.n);
            }

        }

        return String.format("%.3f cos(%.3fx+%.3f)", this.A, this.m, this.n);
    }



}
