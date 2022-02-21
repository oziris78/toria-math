package com.telek.telekmath.advanced.statistics.regression;

import com.telek.telekmath.advanced.distributions.cont.TDist;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.core.functions.other.TPolynomial;
import com.telek.telekmath.core.geometry.points.TPoint2D;
import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.utils.TelekMathException.*;
import com.telek.telekutils.plain.TClassUtils;

import java.lang.reflect.Field;
import java.util.Arrays;


public class SimpleRegression {


    /* No constructor */
    private SimpleRegression(){}


    ///////////////
    /*  METHODS  */
    ///////////////


    /* ----------- double[], float[], int[] ----------- */

    public static RegressionResult getResult(double[] array, double alpha){
        // error checking
        checkEven(array.length);

        // n & sums
        double n = array.length / 2d;
        double sumX = 0, sumY = 0, sumX2 = 0, sumY2 = 0, sumXY = 0;
        for (int i = 0; i < array.length; i+=2) {
            double x = array[i], y = array[i+1];
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
        for (int i = 0; i < array.length; i+=2) {
            double x = array[i], y = array[i+1];
            double error = y - line.value(x);
            sumE2 += error * error;
        }


        return result(n,sumX,sumY,sumXY,sumX2,sumY2,sumE2,line,alpha);
    }

    // EXACT COPY OF getResult(double[] arr)
    public static RegressionResult getResult(float[] array, double alpha){
        // error checking
        checkEven(array.length);

        // n & sums
        double n = array.length / 2d;
        double sumX = 0, sumY = 0, sumX2 = 0, sumY2 = 0, sumXY = 0;
        for (int i = 0; i < array.length; i+=2) {
            double x = array[i], y = array[i+1];
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
        for (int i = 0; i < array.length; i+=2) {
            double x = array[i], y = array[i+1];
            double error = y - line.value(x);
            sumE2 += error * error;
        }


        return result(n,sumX,sumY,sumXY,sumX2,sumY2,sumE2,line,alpha);
    }

    // EXACT COPY OF getResult(double[] arr)
    public static RegressionResult getResult(int[] array, double alpha){
        // error checking
        checkEven(array.length);

        // n & sums
        double n = array.length / 2d;
        double sumX = 0, sumY = 0, sumX2 = 0, sumY2 = 0, sumXY = 0;
        for (int i = 0; i < array.length; i+=2) {
            double x = array[i], y = array[i+1];
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
        for (int i = 0; i < array.length; i+=2) {
            double x = array[i], y = array[i+1];
            double error = y - line.value(x);
            sumE2 += error * error;
        }


        return result(n,sumX,sumY,sumXY,sumX2,sumY2,sumE2,line,alpha);
    }


    /* ----------- double[][], float[][], int[][] ----------- */

    public static RegressionResult getResult(double[][] array, double alpha){
        // error checking
        checkPointLength(array.length);

        // n & sums
        double n = array.length;
        double sumX = Arrays.stream(array).mapToDouble(value -> value[0]).sum();
        double sumY = Arrays.stream(array).mapToDouble(value -> value[1]).sum();
        double sumX2 = Arrays.stream(array).mapToDouble(value -> value[0] * value[0]).sum();
        double sumY2 = Arrays.stream(array).mapToDouble(value -> value[1] * value[1]).sum();
        double sumXY = Arrays.stream(array).mapToDouble(value -> value[0] * value[1]).sum();

        // line
        double down = n * sumX2 - sumX * sumX;
        double b0 = (sumY * sumX2 - sumXY * sumX) / down;
        double b1 = (n * sumXY - sumY * sumX) / down;
        TPolynomial line = new TPolynomial(b0, b1); // y = b1 x + b0

        // standardError
        double sumE2 = Arrays.stream(array).mapToDouble(value -> {
            double error = value[1] - line.value(value[0]);
            return error * error;
        }).sum();


        return result(n,sumX,sumY,sumXY,sumX2,sumY2,sumE2,line,alpha);
    }

    // EXACT COPY OF getResult(double[][] arr)
    public static RegressionResult getResult(float[][] array, double alpha){
        // error checking
        checkPointLength(array.length);

        // n & sums
        double n = array.length;
        double sumX = Arrays.stream(array).mapToDouble(value -> value[0]).sum();
        double sumY = Arrays.stream(array).mapToDouble(value -> value[1]).sum();
        double sumX2 = Arrays.stream(array).mapToDouble(value -> value[0] * value[0]).sum();
        double sumY2 = Arrays.stream(array).mapToDouble(value -> value[1] * value[1]).sum();
        double sumXY = Arrays.stream(array).mapToDouble(value -> value[0] * value[1]).sum();

        // line
        double down = n * sumX2 - sumX * sumX;
        double b0 = (sumY * sumX2 - sumXY * sumX) / down;
        double b1 = (n * sumXY - sumY * sumX) / down;
        TPolynomial line = new TPolynomial(b0, b1); // y = b1 x + b0

        // standardError
        double sumE2 = Arrays.stream(array).mapToDouble(value -> {
            double error = value[1] - line.value(value[0]);
            return error * error;
        }).sum();


        return result(n,sumX,sumY,sumXY,sumX2,sumY2,sumE2,line,alpha);
    }

    // EXACT COPY OF getResult(double[][] arr)
    public static RegressionResult getResult(int[][] array, double alpha){
        // error checking
        checkPointLength(array.length);

        // n & sums
        double n = array.length;
        double sumX = Arrays.stream(array).mapToDouble(value -> value[0]).sum();
        double sumY = Arrays.stream(array).mapToDouble(value -> value[1]).sum();
        double sumX2 = Arrays.stream(array).mapToDouble(value -> value[0] * value[0]).sum();
        double sumY2 = Arrays.stream(array).mapToDouble(value -> value[1] * value[1]).sum();
        double sumXY = Arrays.stream(array).mapToDouble(value -> value[0] * value[1]).sum();

        // line
        double down = n * sumX2 - sumX * sumX;
        double b0 = (sumY * sumX2 - sumXY * sumX) / down;
        double b1 = (n * sumXY - sumY * sumX) / down;
        TPolynomial line = new TPolynomial(b0, b1); // y = b1 x + b0

        // standardError
        double sumE2 = Arrays.stream(array).mapToDouble(value -> {
            double error = value[1] - line.value(value[0]);
            return error * error;
        }).sum();


        return result(n,sumX,sumY,sumXY,sumX2,sumY2,sumE2,line,alpha);
    }


    /* ----------- TPoint2D[] ----------- */

    public static RegressionResult getResult(TPoint2D[] array, double alpha){
        // error checking
        checkPointLength(array.length);

        // n & sums
        double n = array.length;
        double sumX = Arrays.stream(array).mapToDouble(value -> value.x).sum();
        double sumY = Arrays.stream(array).mapToDouble(value -> value.y).sum();
        double sumX2 = Arrays.stream(array).mapToDouble(value -> value.x * value.x).sum();
        double sumY2 = Arrays.stream(array).mapToDouble(value -> value.y * value.y).sum();
        double sumXY = Arrays.stream(array).mapToDouble(value -> value.x * value.y).sum();

        // line
        double down = n * sumX2 - sumX * sumX;
        double b0 = (sumY * sumX2 - sumXY * sumX) / down;
        double b1 = (n * sumXY - sumY * sumX) / down;
        TPolynomial line = new TPolynomial(b0, b1); // y = b1 x + b0

        // standardError
        double sumE2 = Arrays.stream(array).mapToDouble(value -> {
            double error = value.y - line.value(value.x);
            return error * error;
        }).sum();


        return result(n,sumX,sumY,sumXY,sumX2,sumY2,sumE2,line,alpha);
    }


    /* ----------- Number[] ----------- */

    // COPY OF getResult(double[] arr), only .doubleValue() methods were added
    public static RegressionResult getResult(Number[] array, double alpha){
        // error checking
        checkEven(array.length);

        // n & sums
        double n = array.length / 2d;
        double sumX = 0, sumY = 0, sumX2 = 0, sumY2 = 0, sumXY = 0;
        for (int i = 0; i < array.length; i+=2) {
            double x = array[i].doubleValue(), y = array[i+1].doubleValue();
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
        for (int i = 0; i < array.length; i+=2) {
            double x = array[i].doubleValue(), y = array[i+1].doubleValue();
            double error = y - line.value(x);
            sumE2 += error * error;
        }


        return result(n,sumX,sumY,sumXY,sumX2,sumY2,sumE2,line,alpha);
    }


    /* ----------- T[] ----------- */

    public static <T> RegressionResult getResult(T[] array, Class<T> clazz, String xFieldStr, String yFieldStr, double alpha){
        try{
            // error checking
            checkPointLength(array.length);
            Field xField = TClassUtils.getField(clazz, xFieldStr);
            Field yField = TClassUtils.getField(clazz, yFieldStr);

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
        catch (NoSuchFieldException | IllegalAccessException e){
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
