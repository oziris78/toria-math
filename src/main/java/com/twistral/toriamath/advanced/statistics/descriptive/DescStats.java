package com.twistral.toriamath.advanced.statistics.descriptive;

import com.twistral.toriamath.core.functions.TRange;
import com.twistral.toriamath.utils.TMath;
import java.util.HashMap;

import com.twistral.toriamath.utils.ToriaMathException.*;

// import Mode to get rid of poor syntax
import com.twistral.toriamath.advanced.statistics.descriptive.DataDescription.Mode;


public class DescStats {


    /* No constructor and fields */
    private DescStats(){}



    /////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////    GENERAL METHODS    ///////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////


    public static double getRange(double min, double max){
        return max - min;
    }

    public static double getMean(double sum, double count){
        return sum / count;
    }

    /**
     * @param variance the variance of this data set
     * @return the standard deviation of this data set
     */
    public static double getStddev(double variance) {
        return TMath.sqrt(variance);
    }

    public static double getInterquartileRange(double quartile1, double quartile3) {
        return quartile3 - quartile1;
    }

    public static double getPearsonSkewnessCoefficient(double median, double mean, double stddev) {
        return 3 * (mean - median) / stddev;
    }

    public static double getBowleySkewnessCoefficient(double quartile1, double quartile2, double quartile3) {
        return (quartile3 + quartile1 - 2 * quartile2) / (quartile3 - quartile1);
    }


    /////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////    VALIDNESS METHODS    //////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////



    public static void verifyProportion(double pHat) {
        if(!TRange.ZERO_TO_ONE.isInRange(pHat))
            throw new InvalidValueException("pHat", pHat);
    }

    public static void verifyAlpha(double alpha){
        if(!TRange.ZERO_TO_ONE.isInRange(alpha))
            throw new InvalidValueException("alpha", alpha);
    }

    public static void verifyVariance(double variance){
        if(variance < 0)
            throw new IsNegativeException("variance");
    }

    public static void verifyStddev(double stddev){
        if(stddev < 0)
            throw new IsNegativeException("standard deviation");
    }

    public static void verifyCount(double count){
        if(count < 0)
            throw new IsNegativeException("count (array size)");
    }



    /////////////////////////////////////////////////////////////////////////////
    ////////////////////    TYPELESS ARRAY IMPLEMENTATION    ////////////////////
    /////////////////////////////////////////////////////////////////////////////


    public static double getCount(double[] data){
        return data.length;
    }


    public static double getMin(double[] sortedData){
        return sortedData[0];
    }


    public static double getMax(double[] sortedData){
        return sortedData[sortedData.length - 1];
    }

    public static double getSum(double[] data){
        double sum = 0;
        int len = data.length;
        for (int i = 0; i < len; i++)
            sum += data[i];
        return sum;
    }


    public static double getVariance(double[] data, double mean, boolean isSample){
        double variance = 0;
        int len = data.length;
        for (int i = 0; i < len; i++) {
            double val = data[i];
            double ximean = val - mean;
            variance += ximean * ximean;
        }
        variance /= isSample ? len - 1 : len;
        return variance;
    }


    public static Mode getModeAndModeCount(double[] data){
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1, mode = 0;
        boolean hasMode = false;
        int len = data.length;

        for (int i = 0; i < len; i++) {
            double dbl = data[i];

            if (frequencyMap.get(dbl) != null) {
                int current = frequencyMap.get(dbl) + 1;
                frequencyMap.put(dbl, current);

                if(current > maxModeFrequency) {
                    maxModeFrequency  = current;
                    mode = dbl;
                    hasMode = true;
                }
            }
            else frequencyMap.put(dbl, 1);
        }
        mode = hasMode ? mode : Double.NaN;
        double modeCount = hasMode ? frequencyMap.get(mode) : Double.NaN;
        return new Mode(mode, modeCount);
    }


    /**
     * Uses linear interpolation to calculate the quartiles. <br>
     * Same as QUARTILE.EXC in Excel.
     * @param sortedData the sorted data set
     * @param nthQuartile an integer (1,2 or 3)
     * @return the nth quartile
     */
    public static double getQuartile(double[] sortedData, int nthQuartile) {
        checkArraySize(sortedData);
        double quartileIndex = (sortedData.length + 1d) * nthQuartile / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double lowValue = sortedData[lowIndex - 1];
        // also -1 here...
        double highValue = sortedData[lowIndex];
        return lowValue + (highValue - lowValue) * percentage;
    }





    /////////////////////////////////////////////////////////////////////////////////////////////
    ////////    METHODS THAT CALCULATE EVERYTHING AND RETURN A DATADESCRIPTION OBJECT    ////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public static DataDescription getDataDesc(double[] sortedData){
        checkArraySize(sortedData);
        double count = getCount(sortedData);
        double min = getMin(sortedData);
        double max = getMax(sortedData);
        double range = getRange(min, max);
        double sum = getSum(sortedData);
        double mean = getMean(sum, count);
        double variance = getVariance(sortedData, mean, false);
        double sampleVariance = getVariance(sortedData, mean, true);
        double stddev = getStddev(variance);
        Mode mode = getModeAndModeCount(sortedData);
        double quartile1 = getQuartile(sortedData, 1);
        double quartile2 = getQuartile(sortedData, 2);
        double quartile3 = getQuartile(sortedData, 3);
        double median = quartile2;
        double interquartileRange = getInterquartileRange(quartile1, quartile3);
        double pearsonSkewCoef = getPearsonSkewnessCoefficient(median, mean, stddev);
        double bowleySkewCoef = getBowleySkewnessCoefficient(quartile1, quartile2, quartile3);

        return new DataDescription(variance, sampleVariance,
                mean, sum, interquartileRange, count,
                quartile1, mode, median, quartile2, quartile3, min, max,
                range, stddev, pearsonSkewCoef, bowleySkewCoef);
    }


    ///////////////
    /*  HELPERS  */
    ///////////////


    private static void checkArraySize(double[] sortedData) {
        if(sortedData.length <= 3){
            throw new SortedDataLengthIsTooSmallException();
        }
    }



}