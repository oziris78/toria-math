package com.telek.tests.stats.exampledata;

import com.telek.telekmath.core.geometry.points.TPoint2D;

public class RegData {


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

    public static class YearResult{
        int year;
        double income, numOfTourists;
        public YearResult(int year, double income, double numOfTourists){
            this.year = year;
            this.income = income;
            this.numOfTourists = numOfTourists;
        }
    }

    public static YearResult[] getYearResultArr(){
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


    public static Number[] getNumberArr() {
        return new Number[]{
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

    public static double[] getDoubleArr(){
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

    public static float[] getFloatArr(){
        return new float[]{
                8.614f, 5.650f,
                9.689f, 7.008f,
                9.752f, 7.177f,
                7.464f, 5.193f,
                10.412f, 7.636f,
                11.569f, 8.090f,
                13.247f, 8.481f,
                14.030f, 9.677f,
                17.517f, 12.125f,
                21.122f, 13.929f
        };
    }



    public static double[][] getDouble2Arr(){
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

    public static float[][] getFloat2Arr(){
        return new float[][]{
                {8.614f, 5.650f},
                {9.689f, 7.008f},
                {9.752f, 7.177f},
                {7.464f, 5.193f},
                {10.412f, 7.636f},
                {11.569f, 8.090f},
                {13.247f, 8.481f},
                {14.030f, 9.677f},
                {17.517f, 12.125f},
                {21.122f, 13.929f}
        };
    }


    public static TPoint2D[] getPointArr() {
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

}
