package com.telek.telekmath.special;

import com.telek.telekmath.core.functions.general.TFunction;


public class NumericalAnalysis {


    public static double regulaFalse(TFunction f, double a, double b, int iterationCount){
        double bigNum = Math.max(a,b);
        double smallNum = Math.min(a,b);
        double c = 0;
        double fa, fb, fc;
        for(int i = 0; i < iterationCount; i++){
            fa = f.value(smallNum);
            fb = f.value(bigNum);
            if( fa * fb >= 0) break;
            else{
                c = bigNum - ( fb * ( (bigNum-smallNum) / (fb - fa) ) );
                fc = f.value(c);
                if( fa * fc < 0){ smallNum = Math.min(smallNum,bigNum); bigNum = c; }
                else if(fb * fc < 0){ smallNum = c; bigNum = Math.max(smallNum,bigNum);}
            }
        }
        return c;
    }


    public static double bisectionMethod(TFunction f, double a, double b, int iterationCount){
        double bigNum = Math.max(a,b);
        double smallNum = Math.min(a,b);
        double c = 0;
        double fa, fb, fc;
        for(int i = 0; i < iterationCount; i++){
            fa = f.value(smallNum);
            fb = f.value(bigNum);
            if( fa * fb >= 0) break;
            else{
                c = (bigNum + smallNum)/2;
                fc = f.value(c);
                if( fa * fc < 0){ smallNum = Math.min(smallNum,bigNum); bigNum = c; }
                else if(fb * fc < 0){ smallNum = c; bigNum = Math.max(smallNum,bigNum);}
            }
        }
        return c;
    }


    public static double newtonsMethod(TFunction f, double a, double b, int iterationCount){
        TFunction df = f.derivative();
        double xn1 = Math.min(a,b);
        for(int i = 0; i < iterationCount; i++){
            xn1 = xn1 - ( f.value(xn1) / df.value(xn1) );
        }
        return xn1;
    }


}
