package com.twistral.toriamath.advanced.statistics.regression;

import com.twistral.toriamath.advanced.distributions.cont.TDist;
import com.twistral.toriamath.core.geometry.vectors.TVec2;
import com.twistral.toriamath.core.functions.TRange;
import com.twistral.toriamath.core.functions.oned.TPolynomial;
import com.twistral.toriamath.utils.TMath;
import com.twistral.toriamath.utils.ToriaMathException.*;
import com.twistral.toriautils.plain.TClassUtils;
import java.lang.reflect.Field;
import java.util.Arrays;


public class SimpleRegression {


    /* No constructor */
    private SimpleRegression(){}


    ///////////////
    /*  METHODS  */
    ///////////////



    public static RegressionResult getResult(double[] array, double alpha){
        // error checking
        final int len = array.length;
        checkEven(len);

        // n & sums
        double n = len / 2d;
        double sumX = 0, sumY = 0, sumX2 = 0, sumY2 = 0, sumXY = 0;
        for (int i = 0; i < len; i+=2) {
            double x = array[i];
            double y = array[i+1];
            sumX += x;
            sumY += y;
            sumX2 += x * x;
            sumY2 += y * y;
            sumXY += x * y;
        }

        // line
        double down = n * sumX2 - sumX * sumX;
        double b0 = (sumY * sumX2 - sumXY * sumX) / down;
        double b1 = (n * sumXY - sumY * sumX) / down;
        TPolynomial line = new TPolynomial(b0, b1); // y = b1 x + b0

        // standardError
        double sumE2 = 0;
        for (int i = 0; i < len; i+=2) {
            double x = array[i];
            double y = array[i+1];
            double error = y - line.value(x);
            sumE2 += error * error;
        }

        return result(n, sumX, sumY, sumXY, sumX2, sumY2, sumE2, line, alpha);
    }




    public static RegressionResult getResult(double[][] array, double alpha){
        // error checking
        checkPointLength(array.length);

        // n & sums
        double n = array.length;

        double sumX = 0, sumY = 0, sumX2 = 0, sumY2 = 0, sumXY = 0;
        for (int i = 0; i < n; i++) {
            double x = array[i][0];
            double y = array[i][1];
            sumX += x;
            sumY += y;
            sumX2 += x * x;
            sumY2 += y * y;
            sumXY += x * y;
        }

        // line
        double down = n * sumX2 - sumX * sumX;
        double b0 = (sumY * sumX2 - sumXY * sumX) / down;
        double b1 = (n * sumXY - sumY * sumX) / down;
        TPolynomial line = new TPolynomial(b0, b1); // y = b1 x + b0

        // standardError
        double sumE2 = 0;
        for (int i = 0; i < n; i++) {
            double x = array[i][0];
            double y = array[i][1];
            double error = y - line.value(x);
            sumE2 += error * error;
        }

        return result(n,sumX,sumY,sumXY,sumX2,sumY2,sumE2,line,alpha);
    }






    /* ----------- TVec2[] ----------- */

    public static RegressionResult getResult(TVec2[] array, double alpha){
        // error checking
        checkPointLength(array.length);

        // n & sums
        double n = array.length;
        double sumX = Arrays.stream(array).mapToDouble(value -> value.getX()).sum();
        double sumY = Arrays.stream(array).mapToDouble(value -> value.getY()).sum();
        double sumX2 = Arrays.stream(array).mapToDouble(value -> value.getX() * value.getX()).sum();
        double sumY2 = Arrays.stream(array).mapToDouble(value -> value.getY() * value.getY()).sum();
        double sumXY = Arrays.stream(array).mapToDouble(value -> value.getX() * value.getY()).sum();

        // line
        double down = n * sumX2 - sumX * sumX;
        double b0 = (sumY * sumX2 - sumXY * sumX) / down;
        double b1 = (n * sumXY - sumY * sumX) / down;
        TPolynomial line = new TPolynomial(b0, b1); // y = b1 x + b0

        // standardError
        double sumE2 = Arrays.stream(array).mapToDouble(value -> {
            double error = value.getY() - line.value(value.getX());
            return error * error;
        }).sum();


        return result(n,sumX,sumY,sumXY,sumX2,sumY2,sumE2,line,alpha);
    }


    /* ----------- T[] ----------- */

    public static <T> RegressionResult getResult(T[] array, Field xField, Field yField, double alpha){
        try{
            // error checking
            checkPointLength(array.length);

            // n & sums
            double n = array.length;
            double sumX = 0, sumY = 0, sumX2 = 0, sumY2 = 0, sumXY = 0;
            for (int i = 0; i < array.length; i++) {
                double x = TClassUtils.getValue(array, xField, i);
                double y = TClassUtils.getValue(array, yField, i);
                sumX += x;
                sumY += y;
                sumX2 += x * x;
                sumY2 += y * y;
                sumXY += x * y;
            }

            // line
            double down = n * sumX2 - sumX * sumX;
            double b0 = (sumY * sumX2 - sumXY * sumX) / down;
            double b1 = (n * sumXY - sumY * sumX) / down;
            TPolynomial line = new TPolynomial(b0, b1); // y = b1 x + b0

            // standardError
            double sumE2 = 0;
            for (int i = 0; i < array.length; i++) {
                double x = TClassUtils.getValue(array, xField, i);
                double y = TClassUtils.getValue(array, yField, i);
                double error = y - line.value(x);
                sumE2 += error * error;
            }

            return result(n,sumX,sumY,sumXY,sumX2,sumY2,sumE2,line,alpha);
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
            return null;
        }
    }



    ///////////////
    /*  HELPERS  */
    ///////////////


    private static RegressionResult result(double n, double sumX, double sumY, double sumXY,
                                           double sumX2, double sumY2, double sumE2, TPolynomial line, double alpha)
    {
        double b0 = line.getCoefficientOfDegree(0), b1 = line.getCoefficientOfDegree(1);

        double variance = sumE2 / (n - 2d);
        double standardError = TMath.sqrt(variance);
        double STS = sumY2 - sumY * sumY / n;
        double SRS = b1 * b1 * (sumX2 - sumX * sumX / n);
        double SES = STS - SRS;
        double R2 = SRS / STS;
        double r = Math.copySign(Math.sqrt(R2), b1); // r = sqrt(R2) with the sign of b1

        boolean hasPositiveDirection = b1 > 0;
        double standardErrorOfCorCoef = TMath.sqrt((1d - r * r) / (n - 2d));
        double t = TDist.invCumLeftTailed(n - 2d, 1d - alpha / 2d);
        TRange confIntOfCorrelationCoef = new TRange(r - t * standardErrorOfCorCoef, r + t * standardErrorOfCorCoef);

        double testStatForR = r / standardErrorOfCorCoef;
        // reverse because if hypothesis test is correct there will be no correlation
        boolean areTheyCorrelated = !(-t <= testStatForR && testStatForR <= t);

        return new RegressionResult(n, sumX, sumY, sumX2, sumY2, sumXY, line, sumE2,
                variance, standardError, STS, SRS, SES, R2, r, standardErrorOfCorCoef,
                hasPositiveDirection, confIntOfCorrelationCoef, areTheyCorrelated);
    }


    private static void checkEven(int arrLen) {
        if(arrLen <= 4)
            throw new NotGreaterThanNException("array.length", String.valueOf(arrLen));
        if(arrLen % 2 != 0)
            throw new InvalidValueException("array.length", arrLen);
    }


    private static void checkPointLength(int arrLen) {
        if(arrLen <= 2)
            throw new NotGreaterThanNException("array.length", String.valueOf(arrLen));
    }


}
