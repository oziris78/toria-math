package com.telek.telekmath.advanced.statistics.measures;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class DataSet {


    //////////////
    /*  FIELDS  */
    //////////////

    private DataDescription dataDesc;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public DataSet(Number[] sortedData){
        this.dataDesc = getDataDesc(sortedData);
    }


    public <T> DataSet(T[] sortedData, Class<T> clazz, String fieldName){
        try{
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            this.dataDesc = getDataDesc(sortedData, field);
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    ///////////////////////////////////////////
    /* USED FOR CALCULATIONS IN CONSTRUCTORS */
    ///////////////////////////////////////////

    /*

    http://www.alcula.com/calculators/statistics/mode/

    https://mathcracker.com/descriptive-statistics-calculator

    // mode
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1;
        String modeStr = "bruh";
        for (int i = 0; i < count; i++) {
            double dbl = getValue(sortedData, field, i);

            if (frequencyMap.get(dbl) != null) {
                int current = frequencyMap.get(dbl) + 1;
                frequencyMap.put(dbl, current);

                if(current > maxModeFrequency) {
                    maxModeFrequency  = current;
                    modeStr = String.valueOf(dbl);
                }
            }
            else frequencyMap.put(dbl, 1);
        }
        double mode, modeCount;
        if(modeStr == "bruh"){
            mode = Double.NaN;
            modeCount = 0;
        }
        else{
            mode = Double.parseDouble(modeStr);
            modeCount = frequencyMap.get(mode);
        }
     */

    private DataDescription getDataDesc(Number[] sortedData){
        // count
        double count = sortedData.length;

        // min, max, range
        double min = sortedData[0].doubleValue();
        double max = sortedData[sortedData.length-1].doubleValue();
        double range = max - min;

        // sum, mean, geomean
        double geomean = 1, sum = 0;
        for (int i = 0; i < count; i++) {
            double val = sortedData[i].doubleValue();

            geomean *= val;
            sum += val;
        }

        geomean = Math.pow(geomean, 1d / count);
        double mean = sum / count;

        // stddev, variance
        double variance = 0;
        for (int i = 0; i < count; i++) {
            double val = sortedData[i].doubleValue();
            double ximean = val - mean;
            variance += ximean * ximean;
        }
        variance /= count;
        double stddev = Math.sqrt(variance);

        // mode
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1, mode = 0;
        boolean hasMode = false;

        for (int i = 0; i < count; i++) {
            double dbl = sortedData[i].doubleValue();

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
        double modeCount = hasMode ? frequencyMap.get(mode) : 0;


        // median and quartile stuff
        double quartile1 = getQuartile(sortedData, 1d);
        double quartile2 = getQuartile(sortedData, 2d);
        double quartile3 = getQuartile(sortedData, 3d);
        double median = quartile2;
        double interquartileRange = quartile3 - quartile1;

        // skewness coefficients
        double pearsonSkewCoef = 3 * (mean - median) / stddev;
        double bowleySkewCoef = (quartile3 + quartile1 - 2 * quartile2) / (quartile3 - quartile1);

        return new DataDescription(variance, mean, geomean, sum, interquartileRange,
                count, quartile1, mode,  median,  modeCount,
                quartile2,  quartile3,  min,  max, range, stddev, pearsonSkewCoef, bowleySkewCoef);
    }




    private <T> DataDescription getDataDesc(T[] sortedData, Field field) throws IllegalAccessException {
        // count
        double count = sortedData.length;

        // min, max, range
        double min = getValue(sortedData, field, 0);
        double max = getValue(sortedData, field, sortedData.length-1);
        double range = max - min;

        // sum, mean, geomean
        double geomean = 1, sum = 0;
        for (int i = 0; i < count; i++) {
            double val = getValue(sortedData, field, i);

            geomean *= val;
            sum += val;
        }
        geomean = Math.pow(geomean, 1d / count);
        double mean = sum / count;

        // stddev, variance
        double variance = 0;
        for (int i = 0; i < count; i++) {
            double val = getValue(sortedData, field, i);
            double ximean = val - mean;
            variance += ximean * ximean;
        }
        variance /= count;
        double stddev = Math.sqrt(variance);

        // mode
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1, mode = 0;
        boolean hasMode = false;

        for (int i = 0; i < count; i++) {
            double dbl = getValue(sortedData, field, i);

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
        double modeCount = hasMode ? frequencyMap.get(mode) : 0;


        // median and quartile stuff
        double quartile1 = getQuartile(sortedData, field, 1d);
        double quartile2 = getQuartile(sortedData, field, 2d);
        double quartile3 = getQuartile(sortedData, field, 3d);
        double median = quartile2;
        double interquartileRange = quartile3 - quartile1;

        // skewness coefficients
        double pearsonSkewCoef = 3 * (mean - median) / stddev;
        double bowleySkewCoef = (quartile3 + quartile1 - 2 * quartile2) / (quartile3 - quartile1);

        return new DataDescription(variance, mean, geomean, sum, interquartileRange,
                count, quartile1,  mode,  median,  modeCount,
                quartile2,  quartile3,  min,  max, range, stddev, pearsonSkewCoef, bowleySkewCoef);
    }



    ///////////////
    /*  METHODS  */
    ///////////////


    public DataDescription getDataDesc() {
        return dataDesc;
    }

    public double getVariance(){
        return this.dataDesc.variance;
    }

    public double getMean(){
        return this.dataDesc.mean;
    }

    public double getGeomean(){
        return this.dataDesc.geomean;
    }

    public double getSum(){
        return this.dataDesc.sum;
    }

    public double getInterquartileRange(){
        return this.dataDesc.interquartileRange;
    }

    public double getCount(){
        return this.dataDesc.count;
    }

    public double getQuartile1(){
        return this.dataDesc.quartile1;
    }

    public double getMode(){
        return this.dataDesc.mode;
    }

    public double getMedian(){
        return this.dataDesc.median;
    }

    public double getModeCount(){
        return this.dataDesc.modeCount;
    }

    public double getQuartile2(){
        return this.dataDesc.quartile2;
    }

    public double getQuartile3(){
        return this.dataDesc.quartile3;
    }

    public double getMin(){
        return this.dataDesc.min;
    }

    public double getMax(){
        return this.dataDesc.max;
    }

    public double getRange(){
        return this.dataDesc.range;
    }

    public double getStddev(){
        return this.dataDesc.stddev;
    }

    public double getPearsonSkewCoef(){
        return this.dataDesc.pearsonSkewCoef;
    }

    public double getBowleySkewCoef(){
        return this.dataDesc.bowleySkewCoef;
    }


    ///////////////
    /*  HELPERS  */
    ///////////////



    private <T> double getValue(T[] sortedData, Field field, int index) throws IllegalAccessException {
        T currentTerm = sortedData[index];
        Number value = (Number) field.get(currentTerm);
        return value.doubleValue();
    }


    private <T> double getQuartile(T[] sortedData, Field field, double quartileNumber) throws IllegalAccessException {
        double quartileIndex = (sortedData.length + 1d) * quartileNumber / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double lowValue = getValue(sortedData, field, lowIndex - 1);
        // also -1 here...
        double highValue = getValue(sortedData, field, lowIndex);
        return lowValue + (highValue - lowValue) * percentage;
    }


    private double getQuartile(Number[] sortedData, double quartileNumber) {
        double quartileIndex = (sortedData.length + 1d) * quartileNumber / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double lowValue = sortedData[lowIndex - 1].doubleValue();
        // also -1 here...
        double highValue = sortedData[lowIndex].doubleValue();
        return lowValue + (highValue - lowValue) * percentage;
    }



}