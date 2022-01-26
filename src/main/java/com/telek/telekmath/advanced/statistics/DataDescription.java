package com.telek.telekmath.advanced.statistics;

import java.lang.reflect.Field;
import java.util.HashMap;


public class DataDescription<T, FT extends Number> {

    private final Field field;
    private final T[] sortedData;

    // values without javadocs (self-explanatory variable names)
    public double count, mean, geomean, sum, interquartileRange, mode, modeCount;

    /** The value that divides the whole data set into 2 equal parts, also it's equal to {@link #quartile2}. */
    public double median;

    /** Quartiles are calculated with exclusive percentages. <br> Same as QUARTILE.EXC(arr, i) in Excel. */
    public double quartile1, quartile2, quartile3;

    /** Maximum value */
    public double min;

    /** Minimum value */
    public double max;

    /** Specifies (maxValue - minValue) value. */
    public double range;

    /** Standard deviation */
    public double stddev;
    public double variance;


    public DataDescription(DataSet<T> dataSet, Field field) throws IllegalAccessException {
        // references
        this.field = field;
        this.sortedData = dataSet.getSortedData();

        // count
        this.count = sortedData.length;

        // min, max, range
        this.min = getValue(0);
        this.max = getValue(sortedData.length-1);
        this.range = this.max - this.min;

        // sum, mean, geomean
        geomean = 1;
        for (int i = 0; i < count; i++) {
            double val = getValue(i);

            geomean *= val;
            sum += val;
        }

        geomean = Math.pow(geomean, 1d / count);
        mean = sum / count;

        // stddev, variance
        for (int i = 0; i < count; i++) {
            double val = getValue(i);

            double xiMinusMean = (val - mean);
            variance += xiMinusMean * xiMinusMean;
        }
        variance /= count;
        stddev = Math.sqrt(variance);

        // mode
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1;
        for (int i = 0; i < count; i++) {
            double dbl = getValue(i);

            if (frequencyMap.get(dbl) != null) {
                int current = frequencyMap.get(dbl) + 1;
                frequencyMap.put(dbl, current);

                if(current > maxModeFrequency) {
                    maxModeFrequency  = current;
                    mode = dbl;
                }
            }
            else frequencyMap.put(dbl, 1);
        }
        modeCount = frequencyMap.get(mode);

        // median and quartile stuff
        quartile1 = getQuartile(1d);
        quartile2 = getQuartile(2d);
        quartile3 = getQuartile(3d);
        median = quartile2;
        interquartileRange = quartile3 - quartile1;

    }



    ///////////////
    /*  HELPERS  */
    ///////////////


    private double getQuartile(double quartileNumber) throws IllegalAccessException {
        double quartileIndex = (count + 1d) * quartileNumber / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        double lowValue = getValue(lowIndex - 1); // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double highValue = getValue(lowIndex); // also -1 here...
        return lowValue + (highValue - lowValue) * percentage;
    }


    private double getValue(int index) throws IllegalAccessException {
        T currentTerm = this.sortedData[index];
        FT value = (FT) this.field.get(currentTerm);
        return value.doubleValue();
    }



}
