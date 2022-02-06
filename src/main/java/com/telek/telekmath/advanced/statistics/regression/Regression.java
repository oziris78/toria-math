package com.telek.telekmath.advanced.statistics.regression;

import com.telek.telekmath.core.functions.other.TPolynomial;
import com.telek.telekmath.core.geometry.points.TPoint2D;
import com.telek.telekmath.exceptions.TelekMathException.NotGreaterThanNException;


public class Regression {

    //////////////
    /*  FIELDS  */
    //////////////

    private RegressionResult regressionResult;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    /**
     * @param array An array in form [x1, y1, x2, y2, x3, y3, ...]
     */
    public Regression(double[] array){

    }


    /**
     * @param array An array in form [[x1,y1], [x2,y2], [x3,y3], ...]
     */
    public Regression(double[][] array){
        double sumX, sumXY, sumY;


    }


    /**
     * @param array An array in form [[x1,y1], [x2,y2], [x3,y3], ...]
     */
    public Regression(TPoint2D[] array){
        checkForSize(array);
        // n & sums
        double n = array.length;
        double sumX = 0, sumXY = 0, sumY = 0, sumX2 = 0, sumY2 = 0;
        for(TPoint2D point : array){
            double x = point.x;
            double y = point.y;
            sumX += x;
            sumXY += x * y;
            sumY += y;
            sumX2 += x * x;
            sumY2 += y * y;
        }

        // line
        double down = n * sumX2 - sumX * sumX;
        if(down == 0) {
            System.err.println("Such line does not exist, divide by zero error...");
            return;
        }
        double b0 = (sumY * sumX2 - sumXY * sumX) / down;
        double b1 = (n * sumXY - sumY * sumX) / down;
        TPolynomial line = new TPolynomial(new double[]{b0, b1}); // y = b1 x + b0

        // standardError
        double sumE2 = 0;
        for(TPoint2D point : array){
            double x = point.x, y = point.y;
            double error = y - line.value(x);  // y - yHat
            sumE2 += error * error;
        }
        double variance = sumE2 / (n-2);
        double standardError = Math.sqrt(variance);
        double varianceOfB0 = n * sumX2 * variance / (n * sumX2 - sumX * sumX);
        double varianceOfB1 = varianceOfB0 / n / sumX2;
        double STS = sumY2 - (sumY * sumY / n);
        double SRS = b1 * b1 * (sumX2 - (sumX * sumX / n));
        double SES = STS - SRS;
        double R2 = SRS / STS;
        double r = Math.copySign(Math.sqrt(R2), b1); // r = sqrt(R2) with the sign of b1

        /* TODO: regression devam
        Birol hocanın verdiği regression-example'da şunlar yapılmadı (gerisi yapıldı ve kontrol edildi):
            5, 6, 7, 11, 12
        */

    }



    public <T> Regression(T[] array, Class<T> clazz, String xFieldStr, String yFieldStr) {

    }


    ///////////////
    /*  METHODS  */
    ///////////////

    public RegressionResult getRegressionResult() {
        return regressionResult;
    }



    ///////////////
    /*  HELPERS  */
    ///////////////


    private void checkForSize(TPoint2D[] array) {
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
