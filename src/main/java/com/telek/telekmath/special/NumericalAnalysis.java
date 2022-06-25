package com.telek.telekmath.special;

import com.telek.telekmath.core.functions.oned.AbstractSingleVarFunc;
import com.telek.telekmath.core.functions.oned.TPolynomial;
import com.telek.telekmath.core.geometry.vectors.TVec2;
import com.telek.telekmath.utils.TelekMathException.*;
import com.telek.telekutils.arrayref.oned.DoubleArrRef;
import com.telek.telekutils.arrayref.oned.FloatArrRef;
import com.telek.telekutils.arrayref.oned.IntArrRef;
import com.telek.telekutils.arrayref.oned.ArrayRef;


public class NumericalAnalysis {

    /*  No constructor  */
    private NumericalAnalysis(){}


    ///////////////
    /*  METHODS  */
    ///////////////

    ////////////////////////    LAGRANGE POLYNOMIALS    ////////////////////////

    public static TPolynomial getLagrangePolynomial(ArrayRef array){
        if(array.getSize() % 2 != 0)
            throw new InvalidValueException("points.length", array.getSize());
        TPolynomial res = new TPolynomial();
        for (int i = 0; i < array.getSize(); i+=2) {
            double curY = array.getValue(i+1);
            TPolynomial p = getPolyForLagrange(array, i).multiply(new TPolynomial(curY));
            double denom = getDenomForLagrange(array, i);
            p = p.multiply(new TPolynomial(1d / denom));
            res = res.add(p);
        }
        return res;
    }

    public static TPolynomial getLagrangePolynomial(TVec2... points){
        TPolynomial res = new TPolynomial();
        for (int i = 0; i < points.length; i++) {
            TVec2 curPoint = points[i];
            TPolynomial p = getPolyForLagrange(points, i).multiply(new TPolynomial(curPoint.getY()));
            double denom = getDenomForLagrange(points, i);
            p = p.multiply(new TPolynomial(1d / denom));
            res = res.add(p);
        }
        return res;
    }

    public static TPolynomial getLagrangePolynomial(double... array){
        return getLagrangePolynomial(new DoubleArrRef(array));
    }
    public static TPolynomial getLagrangePolynomial(float... array){
        return getLagrangePolynomial(new FloatArrRef(array));
    }
    public static TPolynomial getLagrangePolynomial(int... array){
        return getLagrangePolynomial(new IntArrRef(array));
    }



    ////////////////////////    NUMERICAL METHODS FOR FINDING ROOTS    ////////////////////////


    public static double regulaFalse(AbstractSingleVarFunc f, double a, double b, int iterationCount){
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


    public static double bisectionMethod(AbstractSingleVarFunc f, double a, double b, int iterationCount){
        double bigNum = Math.max(a, b);
        double smallNum = Math.min(a,b);
        double c = 0;
        double fa, fb, fc;
        for(int i = 0; i < iterationCount; i++){
            fa = f.value(smallNum);
            fb = f.value(bigNum);
            if( fa * fb >= 0d) break;
            else{
                c = (bigNum + smallNum) / 2d;
                fc = f.value(c);
                if( fa * fc < 0d){
                    smallNum = Math.min(smallNum, bigNum);
                    bigNum = c;
                }
                else if(fb * fc < 0d){
                    smallNum = c;
                    bigNum = Math.max(smallNum, bigNum);
                }
            }
        }
        return c;
    }


    public static double newtonsMethod(AbstractSingleVarFunc f, double a, double b, int iterationCount){
        AbstractSingleVarFunc df = f.derivative();
        double xn1 = Math.min(a,b);
        for(int i = 0; i < iterationCount; i++)
            xn1 = xn1 - ( f.value(xn1) / df.value(xn1) );

        return xn1;
    }


    ///////////////
    /*  HELPERS  */
    ///////////////


    private static TPolynomial getPolyForLagrange(ArrayRef array, int dontTake){
        TPolynomial res = new TPolynomial(1); // f(x) = 1
        for (int i = 0; i < array.getSize(); i+=2) {
            if(i == dontTake)
                continue;
            res = res.multiply(new TPolynomial(-array.getValue(i), 1)); // x - x_i
        }
        return res;
    }

    private static double getDenomForLagrange(ArrayRef array, int dontTake){
        double d = 1d;
        final double dontTakeX = array.getValue(dontTake);
        for (int i = 0; i < array.getSize(); i+=2) {
            if(i == dontTake)
                continue;
            d *= (dontTakeX - array.getValue(i));
        }
        return d;
    }

    private static TPolynomial getPolyForLagrange(TVec2[] points, int dontTake){
        TPolynomial res = new TPolynomial(1); // f(x) = 1
        for (int i = 0; i < points.length; i++) {
            if(i == dontTake)
                continue;
            TVec2 curPoint = points[i];
            res = res.multiply(new TPolynomial(-curPoint.getX(), 1)); // x - x_i
        }
        return res;
    }

    private static double getDenomForLagrange(TVec2[] points, int dontTake){
        double d = 1d;
        final double dontTakeX = points[dontTake].getX();
        for (int i = 0; i < points.length; i++) {
            if(i == dontTake)
                continue;
            d *= (dontTakeX - points[i].getX());
        }
        return d;
    }



}
