package com.telek.telekmath.core.functions.trig;

import com.telek.telekmath.core.functions.AbstractFunction;

public class AbstractTrigFunc extends AbstractFunction {





    protected static String getTrigString(String funcName, double A, double m, double n){
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
