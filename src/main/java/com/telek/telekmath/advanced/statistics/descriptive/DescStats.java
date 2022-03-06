package com.telek.telekmath.advanced.statistics.descriptive;

import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.utils.TMath;
import java.lang.reflect.Field;
import java.util.HashMap;
import com.telek.telekmath.utils.TelekMathException.*;

// import Mode to get rid of poor syntax
import com.telek.telekmath.advanced.statistics.descriptive.DataDescription.Mode;
import com.telek.telekutils.arrayref.oned.*;


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


    /////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////    UNUSED ADDITIONAL METHODS    ////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////


    public static double getMedian(ArrayRef sortedData){
        return getQuartile(sortedData, 2);
    }
    public static double getMedian(Number[] sortedData){
        return getQuartile(sortedData, 2);
    }
    public static double getMedian(double[] sortedData){
        return getQuartile(sortedData, 2);
    }
    public static double getMedian(int[] sortedData){
        return getQuartile(sortedData, 2);
    }
    public static double getMedian(float[] sortedData) {
        return getQuartile(sortedData, 2);
    }


    /////////////////////////////////////////////////////////////////////////////
    ////////////////////    TYPELESS ARRAY IMPLEMENTATION    ////////////////////
    /////////////////////////////////////////////////////////////////////////////


    public static double getCount(ArrayRef data){
        return data.getSize();
    }

    public static double getMin(ArrayRef sortedData){
        return sortedData.getValue(0);
    }

    public static double getMax(ArrayRef sortedData){
        return sortedData.getValue(sortedData.getSize() - 1);
    }

    public static double getSum(ArrayRef data){
        double sum = 0;
        int len = data.getSize();
        for (int i = 0; i < len; i++)
            sum += data.getValue(i);
        return sum;
    }

    public static double getVariance(ArrayRef data, double mean, boolean isSample){
        double variance = 0;
        int len = data.getSize();
        for (int i = 0; i < len; i++) {
            double val = data.getValue(i);
            double ximean = val - mean;
            variance += ximean * ximean;
        }
        variance /= isSample ? len - 1 : len;
        return variance;
    }

    public static Mode getModeAndModeCount(ArrayRef data){
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1, mode = 0;
        boolean hasMode = false;
        int len = data.getSize();

        for (int i = 0; i < len; i++) {
            double dbl = data.getValue(i);

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
    public static double getQuartile(ArrayRef sortedData, int nthQuartile) {
        double quartileIndex = (sortedData.getSize() + 1d) * nthQuartile / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double lowValue = sortedData.getValue(lowIndex - 1);
        // also -1 here...
        double highValue = sortedData.getValue(lowIndex);
        return lowValue + (highValue - lowValue) * percentage;
    }


    //////////////////////////////////////////////////////////////////////////////
    /////////////    OTHER TYPES THAT CONVERT TO TYPELESS ARRAYS    //////////////
    //////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////    NUMBER[]    ///////////////////////////////


    public static double getCount(Number[] data){
        return getCount(new NumberArrRef(data));
    }
    public static double getMin(Number[] sortedData){
        return getMin(new NumberArrRef(sortedData));
    }
    public static double getMax(Number[] sortedData){
        return getMax(new NumberArrRef(sortedData));
    }
    public static double getSum(Number[] data){
        return getSum(new NumberArrRef(data));
    }
    public static double getVariance(Number[] data, double mean, boolean isSample){
        return getVariance(new NumberArrRef(data), mean, isSample);
    }
    public static Mode getModeAndModeCount(Number[] data){
        return getModeAndModeCount(new NumberArrRef(data));
    }
    public static double getQuartile(Number[] sortedData, int nthQuartile) {
        return getQuartile(new NumberArrRef(sortedData), nthQuartile);
    }

    ///////////////////////////////    double[]    ///////////////////////////////

    public static double getCount(double[] data){
        return getCount(new DoubleArrRef(data));
    }
    public static double getMin(double[] sortedData){
        return getMin(new DoubleArrRef(sortedData));
    }
    public static double getMax(double[] sortedData){
        return getMax(new DoubleArrRef(sortedData));
    }
    public static double getSum(double[] data){
        return getSum(new DoubleArrRef(data));
    }
    public static double getVariance(double[] data, double mean, boolean isSample){
        return getVariance(new DoubleArrRef(data), mean, isSample);
    }
    public static Mode getModeAndModeCount(double[] data){
        return getModeAndModeCount(new DoubleArrRef(data));
    }
    public static double getQuartile(double[] sortedData, int nthQuartile) {
        return getQuartile(new DoubleArrRef(sortedData), nthQuartile);
    }

    /////////////////////////////////    int[]    ////////////////////////////////

    public static double getCount(int[] data){
        return getCount(new IntArrRef(data));
    }
    public static double getMin(int[] sortedData){
        return getMin(new IntArrRef(sortedData));
    }
    public static double getMax(int[] sortedData){
        return getMax(new IntArrRef(sortedData));
    }
    public static double getSum(int[] data){
        return getSum(new IntArrRef(data));
    }
    public static double getVariance(int[] data, double mean, boolean isSample){
        return getVariance(new IntArrRef(data), mean, isSample);
    }
    public static Mode getModeAndModeCount(int[] data){
        return getModeAndModeCount(new IntArrRef(data));
    }
    public static double getQuartile(int[] sortedData, int nthQuartile) {
        return getQuartile(new IntArrRef(sortedData), nthQuartile);
    }

    /////////////////////////////////    float[]    //////////////////////////////

    public static double getCount(float[] data){
        return getCount(new FloatArrRef(data));
    }
    public static double getMin(float[] sortedData){
        return getMin(new FloatArrRef(sortedData));
    }
    public static double getMax(float[] sortedData){
        return getMax(new FloatArrRef(sortedData));
    }
    public static double getSum(float[] data){
        return getSum(new FloatArrRef(data));
    }
    public static double getVariance(float[] data, double mean, boolean isSample){
        return getVariance(new FloatArrRef(data), mean, isSample);
    }
    public static Mode getModeAndModeCount(float[] data){
        return getModeAndModeCount(new FloatArrRef(data));
    }
    public static double getQuartile(float[] sortedData, int nthQuartile) {
        return getQuartile(new FloatArrRef(sortedData), nthQuartile);
    }


    //////////////////////////////////    T[]    /////////////////////////////////


    public static <T> double getCount(T[] data, Field field){
        return getCount(new GenericArrRef<>(data, field));
    }
    public static <T> double getMin(T[] data, Field field){
        return getMin(new GenericArrRef<>(data, field));
    }
    public static <T> double getMax(T[] data, Field field){
        return getMax(new GenericArrRef<>(data, field));
    }
    public static <T> double getSum(T[] data, Field field) {
        return getSum(new GenericArrRef<>(data, field));
    }
    public static <T> double getVariance(T[] data, Field field, double mean, boolean isSample) {
        return getVariance(new GenericArrRef<>(data, field), mean, isSample);
    }
    public static <T> Mode getModeAndModeCount(T[] data, Field field) {
        return getModeAndModeCount(new GenericArrRef<>(data, field));
    }
    public static <T> double getQuartile(T[] sortedData, Field field, int nthQuartile) throws IllegalAccessException {
        return getQuartile(new GenericArrRef<>(sortedData, field), nthQuartile);
    }



    /////////////////////////////////////////////////////////////////////////////////////////////
    ////////    METHODS THAT CALCULATE EVERYTHING AND RETURN A DATADESCRIPTION OBJECT    ////////
    /////////////////////////////////////////////////////////////////////////////////////////////

    public static DataDescription getDataDesc(ArrayRef sortedData){
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

    // these all convert themselves to TypelessArrays
    public static DataDescription getDataDesc(Number[] sortedData){
        return getDataDesc(new NumberArrRef(sortedData));
    }
    public static DataDescription getDataDesc(int[] sortedData){
        return getDataDesc(new IntArrRef(sortedData));
    }
    public static DataDescription getDataDesc(double[] sortedData){
        return getDataDesc(new DoubleArrRef(sortedData));
    }
    public static DataDescription getDataDesc(float[] sortedData){
        return getDataDesc(new FloatArrRef(sortedData));
    }
    public static <T> DataDescription getDataDesc(T[] sortedData, Field field) {
        return getDataDesc(new GenericArrRef<>(sortedData, field));
    }




}