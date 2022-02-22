package com.telek.telekmath.special;

import com.telek.telekmath.core.functions.AbstractFunction;
import com.telek.telekmath.core.functions.other.TPolynomial;
import com.telek.telekmath.core.geometry.points.TPoint2D;
import com.telek.telekmath.utils.TelekMathException.*;


public class NumericalAnalysis {

    /*  No constructor  */
    private NumericalAnalysis(){}


    ///////////////
    /*  METHODS  */
    ///////////////


    public static TPolynomial getLagrangePolynomial(TPoint2D... points){
        TPolynomial res = new TPolynomial(0);
        for (int i = 0; i < points.length; i++) {
            TPoint2D curPoint = points[i];
            TPolynomial p = getPolyForLagrange(points, i).multiply(new TPolynomial(curPoint.y));
            double denom = getDenomForLagrange(points, i);
            p = p.multiply(new TPolynomial(1d / denom));
            res = res.add(p);
        }
        return res;
    }

    public static TPolynomial getLagrangePolynomial(double... points){
        if(points.length % 2 != 0)
            throw new InvalidValueException("points.length", points.length);
        TPolynomial res = new TPolynomial(0);
        for (int i = 0; i < points.length; i+=2) {
//            double curX = points[i];
            double curY = points[i+1];
            TPolynomial p = getPolyForLagrange(points, i).multiply(new TPolynomial(curY));
            double denom = getDenomForLagrange(points, i);
            p = p.multiply(new TPolynomial(1d / denom));
            res = res.add(p);
        }
        return res;
    }

    public static double regulaFalse(AbstractFunction f, double a, double b, int iterationCount){
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
                if( fa * fc < 0){
                    smallNum = Math.min(smallNum, bigNum);
                    bigNum = c;
                }
                else if(fb * fc < 0){
                    smallNum = c;
                    bigNum = Math.max(smallNum, bigNum);
                }
            }
        }
        return c;
    }


    public static double bisectionMethod(AbstractFunction f, double a, double b, int iterationCount){
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
                if( fa * fc < 0){
                    smallNum = Math.min(smallNum, bigNum);
                    bigNum = c;
                }
                else if(fb * fc < 0){
                    smallNum = c;
                    bigNum = Math.max(smallNum, bigNum);
                }
            }
        }
        return c;
    }


    public static double newtonsMethod(AbstractFunction f, double a, double b, int iterationCount){
        AbstractFunction df = f.derivative();
        double xn1 = Math.min(a,b);
        for(int i = 0; i < iterationCount; i++)
            xn1 = xn1 - ( f.value(xn1) / df.value(xn1) );

        return xn1;
    }


    ///////////////
    /*  HELPERS  */
    ///////////////

    private static TPolynomial getPolyForLagrange(double[] points, int dontTake){
        TPolynomial res = new TPolynomial(1); // f(x) = 1
        for (int i = 0; i < points.length; i+=2) {
            if(i == dontTake)
                continue;
            res = res.multiply(new TPolynomial(-points[i], 1)); // x - x_i
        }
        return res;
    }

    private static double getDenomForLagrange(double[] points, int dontTake){
        double d = 1d;
        final double dontTakeX = points[dontTake];
        for (int i = 0; i < points.length; i+=2) {
            if(i == dontTake)
                continue;
            d *= (dontTakeX - points[i]);
        }
        return d;
    }

    private static TPolynomial getPolyForLagrange(TPoint2D[] points, int dontTake){
        TPolynomial res = new TPolynomial(1); // f(x) = 1
        for (int i = 0; i < points.length; i++) {
            if(i == dontTake)
                continue;
            TPoint2D curPoint = points[i];
            res = res.multiply(new TPolynomial(-curPoint.x, 1)); // x - x_i
        }
        return res;
    }

    private static double getDenomForLagrange(TPoint2D[] points, int dontTake){
        double d = 1d;
        final double dontTakeX = points[dontTake].x;
        for (int i = 0; i < points.length; i++) {
            if(i == dontTake)
                continue;
            d *= (dontTakeX - points[i].x);
        }
        return d;
    }



}
