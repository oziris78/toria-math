package com.telek.tests.stats;

import com.telek.telekmath.advanced.statistics.regression.SimpleRegression;
import com.telek.telekmath.advanced.statistics.regression.RegressionResult;
import com.telek.telekmath.core.geometry.points.TPoint2D;
import com.telek.telekutils.plain.TArrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SimpleRegressionTest {

    /*
        Years   income   # of tourists
         1996    5.650    8.614
         1997    7.008    9.689
         1998    7.177    9.752
         1999    5.193    7.464
         2000    7.636    10.412
         2001    8.090    11.569
         2002    8.481    13.247
         2003    9.677    14.030
         2004    12.125   17.517
         2005    13.929   21.122
    */

    static class YearResult{
        int year;
        double income, numOfTourists;
        public YearResult(int year, double income, double numOfTourists){
            this.year = year;
            this.income = income;
            this.numOfTourists = numOfTourists;
        }
    }

    static YearResult[] getYearResultArr(){
        return new YearResult[]{
                new YearResult(1996, 5.650, 8.614),
                new YearResult(1997, 7.008, 9.689),
                new YearResult(1998, 7.177, 9.752),
                new YearResult(1999, 5.193, 7.464),
                new YearResult(2000, 7.636, 10.412),
                new YearResult(2001, 8.090, 11.569),
                new YearResult(2002, 8.481, 13.247),
                new YearResult(2003, 9.677, 14.030),
                new YearResult(2004, 12.125, 17.517),
                new YearResult(2005, 13.929, 21.122)
        };
    }


    static double[] getDoubleArr(){
        return new double[]{
                8.614, 5.650,
                9.689, 7.008,
                9.752, 7.177,
                7.464, 5.193,
                10.412, 7.636,
                11.569, 8.090,
                13.247, 8.481,
                14.030, 9.677,
                17.517, 12.125,
                21.122, 13.929
        };
    }


    static double[][] getDouble2Arr(){
        return new double[][]{
                {8.614, 5.650},
                {9.689, 7.008},
                {9.752, 7.177},
                {7.464, 5.193},
                {10.412, 7.636},
                {11.569, 8.090},
                {13.247, 8.481},
                {14.030, 9.677},
                {17.517, 12.125},
                {21.122, 13.929}
        };
    }


    private static TPoint2D[] getPointArr() {
        return new TPoint2D[]{
                new TPoint2D(8.614, 5.650),
                new TPoint2D(9.689, 7.008),
                new TPoint2D(9.752, 7.177),
                new TPoint2D(7.464, 5.193),
                new TPoint2D(10.412, 7.636),
                new TPoint2D(11.569, 8.090),
                new TPoint2D(13.247, 8.481),
                new TPoint2D(14.030, 9.677),
                new TPoint2D( 17.517, 12.125),
                new TPoint2D( 21.122, 13.929)
        };
    }


    @Test
    @DisplayName("regressionTest")
    void regressionTest() {

//        // single dimensional array
//        double[] arr1 = getDoubleArr();
//        Regression reg1 = new Regression(arr1);
//        RegressionResult res1 = reg1.getRegressionResult();
//
//        // two dimensional array
//        double[][] arr2 = getDouble2Arr();
//        RegressionResult res2 = new Regression(arr2).getRegressionResult();

        // tpoint2d array
        RegressionResult res3 = SimpleRegression.getResult(getPointArr());

//        // any class array
//        YearResult[] classArray = getYearResultArr();
//        Regression reg4 = new Regression(classArray, YearResult.class, "numOfTourists", "income");
//        RegressionResult res4 = reg4.getRegressionResult();

        TPoint2D[] arr = new TPoint2D[]{
                new TPoint2D(1,1),
                new TPoint2D(1,2),
                new TPoint2D(1,3),
                new TPoint2D(1,4)
        };
        System.out.println(Arrays.stream(arr).mapToDouble(value -> value.x).sum());
        System.out.println(Arrays.stream(arr).mapToDouble(value -> value.y).sum());



    }



}
