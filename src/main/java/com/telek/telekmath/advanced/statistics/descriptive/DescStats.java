package com.telek.telekmath.advanced.statistics.descriptive;

import com.telek.telekmath.utils.TMath;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;

// import Mode to get rid of poor syntax
import com.telek.telekmath.advanced.statistics.descriptive.DataDescription.Mode;


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
    ////////////////////////    UNUSED ADDITIONAL METHODS    ////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////


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



    //////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////    NUMBER[]    ///////////////////////////////
    //////////////////////////////////////////////////////////////////////////////


    public static double getCount(Number[] data){
        return data.length;
    }

    public static double getMin(Number[] sortedData){
        return sortedData[0].doubleValue();
    }

    public static double getMax(Number[] sortedData){
        return sortedData[sortedData.length-1].doubleValue();
    }

    public static double getSum(Number[] data){
        double sum = 0;
        for (int i = 0; i < data.length; i++)
            sum += data[i].doubleValue();
        return sum;
    }

    public static double getVariance(Number[] data, double mean){
        double variance = 0;
        for (int i = 0; i < data.length; i++) {
            double val = data[i].doubleValue();
            double ximean = val - mean;
            variance += ximean * ximean;
        }
        variance /= data.length;
        return variance;
    }

    public static Mode getModeAndModeCount(Number[] data){
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1, mode = 0;
        boolean hasMode = false;

        for (int i = 0; i < data.length; i++) {
            double dbl = data[i].doubleValue();

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
    public static double getQuartile(Number[] sortedData, int nthQuartile) {
        double quartileIndex = (sortedData.length + 1d) * nthQuartile / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double lowValue = sortedData[lowIndex - 1].doubleValue();
        // also -1 here...
        double highValue = sortedData[lowIndex].doubleValue();
        return lowValue + (highValue - lowValue) * percentage;
    }



    //////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////    double[]    ///////////////////////////////
    //////////////////////////////////////////////////////////////////////////////


    public static double getCount(double[] data){
        return data.length;
    }

    public static double getMin(double[] sortedData){
        return sortedData[0];
    }

    public static double getMax(double[] sortedData){
        return sortedData[sortedData.length-1];
    }

    public static double getSum(double[] data){
        return Arrays.stream(data).sum();
    }

    public static double getVariance(double[] data, double mean){
        double variance = 0;
        for (int i = 0; i < data.length; i++) {
            double val = data[i];
            double ximean = val - mean;
            variance += ximean * ximean;
        }
        variance /= data.length;
        return variance;
    }

    public static Mode getModeAndModeCount(double[] data){
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1, mode = 0;
        boolean hasMode = false;

        for (int i = 0; i < data.length; i++) {
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
        double quartileIndex = (sortedData.length + 1d) * nthQuartile / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double lowValue = sortedData[lowIndex - 1];
        // also -1 here...
        double highValue = sortedData[lowIndex];
        return lowValue + (highValue - lowValue) * percentage;
    }



    //////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////    int[]    ////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////


    public static double getCount(int[] data){
        return data.length;
    }

    public static double getMin(int[] sortedData){
        return sortedData[0];
    }

    public static double getMax(int[] sortedData){
        return sortedData[sortedData.length-1];
    }

    public static double getSum(int[] data){
        return Arrays.stream(data).sum();
    }

    public static double getVariance(int[] data, double mean){
        double variance = 0;
        for (int i = 0; i < data.length; i++) {
            double val = data[i];
            double ximean = val - mean;
            variance += ximean * ximean;
        }
        variance /= data.length;
        return variance;
    }

    public static Mode getModeAndModeCount(int[] data){
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1, mode = 0;
        boolean hasMode = false;

        for (int i = 0; i < data.length; i++) {
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
    public static double getQuartile(int[] sortedData, int nthQuartile) {
        double quartileIndex = (sortedData.length + 1d) * nthQuartile / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double lowValue = sortedData[lowIndex - 1];
        // also -1 here...
        double highValue = sortedData[lowIndex];
        return lowValue + (highValue - lowValue) * percentage;
    }



    //////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////    float[]    //////////////////////////////
    //////////////////////////////////////////////////////////////////////////////


    public static double getCount(float[] data){
        return data.length;
    }

    public static double getMin(float[] sortedData){
        return sortedData[0];
    }

    public static double getMax(float[] sortedData){
        return sortedData[sortedData.length-1];
    }

    public static double getSum(float[] data){
        double sum = 0;
        for (int i = 0; i < data.length; i++)
            sum += data[i];
        return sum;
    }

    public static double getVariance(float[] data, double mean){
        double variance = 0;
        for (int i = 0; i < data.length; i++) {
            double val = data[i];
            double ximean = val - mean;
            variance += ximean * ximean;
        }
        variance /= data.length;
        return variance;
    }

    public static Mode getModeAndModeCount(float[] data){
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1, mode = 0;
        boolean hasMode = false;

        for (int i = 0; i < data.length; i++) {
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
    public static double getQuartile(float[] sortedData, int nthQuartile) {
        double quartileIndex = (sortedData.length + 1d) * nthQuartile / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double lowValue = sortedData[lowIndex - 1];
        // also -1 here...
        double highValue = sortedData[lowIndex];
        return lowValue + (highValue - lowValue) * percentage;
    }



    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////    T[]    /////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////


    public static <T> double getCount(T[] data){
        return data.length;
    }

    public static <T> double getMin(T[] sortedData, Field field) throws IllegalAccessException {
        return getValue(sortedData, field, 0);
    }

    public static <T> double getMax(T[] sortedData, Field field) throws IllegalAccessException {
        return getValue(sortedData, field, sortedData.length-1);
    }

    public static <T> double getSum(T[] data, Field field) throws IllegalAccessException {
        double sum = 0;
        for (int i = 0; i < data.length; i++)
            sum += getValue(data, field, i);
        return sum;
    }

    public static <T> double getVariance(T[] data, Field field, double mean) throws IllegalAccessException {
        double variance = 0;
        for (int i = 0; i < data.length; i++) {
            double val = getValue(data, field, i);
            double ximean = val - mean;
            variance += ximean * ximean;
        }
        variance /= data.length;
        return variance;
    }

    public static <T> Mode getModeAndModeCount(T[] data, Field field) throws IllegalAccessException {
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1, mode = 0;
        boolean hasMode = false;

        for (int i = 0; i < data.length; i++) {
            double dbl = getValue(data, field, i);

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
     * @param field the field that extends the Number class (values of this field will be used for the calculation)
     * @param nthQuartile an integer (1,2 or 3)
     * @param <T> any class
     * @return the nth quartile
     * @throws IllegalAccessException
     */
    public static <T> double getQuartile(T[] sortedData, Field field, int nthQuartile) throws IllegalAccessException {
        double quartileIndex = (sortedData.length + 1d) * nthQuartile / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double lowValue = getValue(sortedData, field, lowIndex - 1);
        // also -1 here...
        double highValue = getValue(sortedData, field, lowIndex);
        return lowValue + (highValue - lowValue) * percentage;
    }



    /////////////////////////////////////////////////////////////////////////////////////////////
    ////////    METHODS THAT CALCULATE EVERYTHING AND RETURN A DATADESCRIPTION OBJECT    ////////
    /////////////////////////////////////////////////////////////////////////////////////////////


    public static DataDescription getDataDesc(Number[] sortedData){
        double count = getCount(sortedData);
        double min = getMin(sortedData);
        double max = getMax(sortedData);
        double range = getRange(min, max);
        double sum = getSum(sortedData);
        double mean = getMean(sum, count);
        double variance = getVariance(sortedData, mean);
        double stddev = getStddev(variance);
        Mode mode = getModeAndModeCount(sortedData);
        double quartile1 = getQuartile(sortedData, 1);
        double quartile2 = getQuartile(sortedData, 2);
        double quartile3 = getQuartile(sortedData, 3);
        double median = quartile2;
        double interquartileRange = getInterquartileRange(quartile1, quartile3);
        double pearsonSkewCoef = getPearsonSkewnessCoefficient(median, mean, stddev);
        double bowleySkewCoef = getBowleySkewnessCoefficient(quartile1, quartile2, quartile3);

        return new DataDescription(variance, mean, sum, interquartileRange, count,
                quartile1, mode, median, quartile2, quartile3, min, max,
                range, stddev, pearsonSkewCoef, bowleySkewCoef);
    }


    public static <T> DataDescription getDataDesc(T[] sortedData, Class<T> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);

            double count = getCount(sortedData);
            double min = getMin(sortedData, field);
            double max = getMax(sortedData, field);
            double range = getRange(min, max);
            double sum = getSum(sortedData, field);
            double mean = getMean(sum, count);
            double variance = getVariance(sortedData, field, mean);
            double stddev = getStddev(variance);
            Mode mode = getModeAndModeCount(sortedData, field);
            double quartile1 = getQuartile(sortedData, field, 1);
            double quartile2 = getQuartile(sortedData, field, 2);
            double quartile3 = getQuartile(sortedData, field, 3);
            double median = quartile2;
            double interquartileRange = getInterquartileRange(quartile1, quartile3);
            double pearsonSkewCoef = getPearsonSkewnessCoefficient(median, mean, stddev);
            double bowleySkewCoef = getBowleySkewnessCoefficient(quartile1, quartile2, quartile3);

            return new DataDescription(variance, mean, sum, interquartileRange, count,
                    quartile1, mode, median, quartile2, quartile3, min, max,
                    range, stddev, pearsonSkewCoef, bowleySkewCoef);
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////    HELPERS    //////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////


    private static <T> double getValue(T[] sortedData, Field field, int index) throws IllegalAccessException {
        T currentTerm = sortedData[index]; // get the xxx object
        Number value = (Number) field.get(currentTerm); // get that object's field's value (assuming that it extends Number)
        return value.doubleValue(); // return that Number's value as a double
    }




}