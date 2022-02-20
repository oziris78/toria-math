package com.telek.telekmath.advanced.statistics.regression;

import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.core.functions.other.TPolynomial;
import com.telek.telekmath.core.geometry.points.TPoint2D;
import com.telek.telekmath.utils.TelekMathException.*;

import java.util.Arrays;


public class SimpleRegression {


    /* No constructor */
    private SimpleRegression(){}


    ///////////////
    /*  METHODS  */
    ///////////////

    // double[], float[], int[]
    // double[][], float[][], int[][]
    // TPoint2D[]
    // Number[]
    // T[]





    public static RegressionResult getResult(TPoint2D[] array){
        // error checking
        checkForSize(array);

        // n & sums
        double n = array.length;
        double sumX = Arrays.stream(array).mapToDouble(value -> value.x).sum();
        double sumY = Arrays.stream(array).mapToDouble(value -> value.y).sum();
        double sumX2 = Arrays.stream(array).mapToDouble(value -> value.x * value.x).sum();
        double sumY2 = Arrays.stream(array).mapToDouble(value -> value.y * value.y).sum();
        double sumXY = Arrays.stream(array).mapToDouble(value -> value.x * value.y).sum();

        // line
        double down = n * sumX2 - sumX * sumX;
        if(down == 0)
            throw new DivisionByZeroException();
        double b0 = (sumY * sumX2 - sumXY * sumX) / down;
        double b1 = (n * sumXY - sumY * sumX) / down;
        TPolynomial line = new TPolynomial(b0, b1); // y = b1 x + b0

        // standardError
        double sumE2 = Arrays.stream(array).mapToDouble(value -> {
            double error = value.y - line.value(value.x);
            return error * error;
        }).sum();

        double variance = sumE2 / (n-2);
        double standardError = DescStats.getStddev(variance);
        double varianceOfB0 = n * sumX2 * variance / (n * sumX2 - sumX * sumX);
        double varianceOfB1 = varianceOfB0 / n / sumX2;
        double STS = sumY2 - sumY * sumY / n;
        double SRS = b1 * b1 * (sumX2 - sumX * sumX / n);
        double SES = STS - SRS;
        double R2 = SRS / STS;
        double r = Math.copySign(Math.sqrt(R2), b1); // r = sqrt(R2) with the sign of b1

        //Birol hocanın verdiği regression-example'da şunlar yapılmadı (gerisi yapıldı ve kontrol edildi):

        TRange confIntervalForBeta0, confIntervalForBeta1; // 5
        boolean beta0IsStatisticallySignificant, beta1IsStatisticallySignificant; // 6, 7
        TRange confIntervalOfEstimation; // 12

        return null;
    }






    ///////////////
    /*  HELPERS  */
    ///////////////


    private static void checkForSize(TPoint2D[] array) {
        if(array.length <= 2)
            throw new NotGreaterThanNException("array.length", "2");
    }
    private void checkForSize(double[] array) {
        if(array.length <= 2)
            throw new NotGreaterThanNException("array.length", "2");
    }
    private void checkForSize(double[][] array) {
        if(array.length <= 2)
            throw new NotGreaterThanNException("array.length", "2");
    }
    private <T> void checkForSize(T[] array) {
        if(array.length <= 2)
            throw new NotGreaterThanNException("array.length", "2");
    }


}
