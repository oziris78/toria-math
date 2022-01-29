package com.telek.telekmath.advanced.statistics.descriptive;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;


public class DataDescription {

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


    /** A value in range [-1,1] to estimate the skewness of this data set. <br>
     * skewness > 0 means it's skewed to right. <br>
     * skewness = 0 means it's symmetrical. <br>
     * skewness < 0 means it's skewed to left.
     * */
    public double pearsonSkewCoef, bowleySkewCoef;



    <T> DataDescription(DataSet<T> dataSet, Field field) throws IllegalAccessException {
        // references
        T[] sortedData = dataSet.getSortedData();

        // count
        this.count = sortedData.length;

        // min, max, range
        this.min = getValue(sortedData, field, 0);
        this.max = getValue(sortedData, field, sortedData.length-1);
        this.range = this.max - this.min;

        // sum, mean, geomean
        geomean = 1;
        for (int i = 0; i < count; i++) {
            double val = getValue(sortedData, field, i);

            geomean *= val;
            sum += val;
        }

        geomean = Math.pow(geomean, 1d / count);
        mean = sum / count;

        // stddev, variance
        for (int i = 0; i < count; i++) {
            double val = getValue(sortedData, field, i);
            double ximean = val - mean;
            variance += ximean * ximean;
        }
        variance /= count;
        stddev = Math.sqrt(variance);

        // mode
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1;
        for (int i = 0; i < count; i++) {
            double dbl = getValue(sortedData, field, i);

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
        quartile1 = getQuartile(sortedData, field, 1d);
        quartile2 = getQuartile(sortedData, field, 2d);
        quartile3 = getQuartile(sortedData, field, 3d);
        median = quartile2;
        interquartileRange = quartile3 - quartile1;

        // skewness coefficients
        pearsonSkewCoef = 3 * (mean - median) / stddev;
        bowleySkewCoef = (quartile3 + quartile1 - 2 * quartile2) / (quartile3 - quartile1);
    }


    DataDescription(int[] sortedData)  {
        // count
        this.count = sortedData.length;

        // min, max, range
        this.min = sortedData[0];
        this.max = sortedData[sortedData.length-1];
        this.range = this.max - this.min;

        // sum, mean, geomean
        geomean = 1;
        for (int i = 0; i < count; i++) {
            double val = sortedData[i];

            geomean *= val;
            sum += val;
        }

        geomean = Math.pow(geomean, 1d / count);
        mean = sum / count;

        // stddev, variance
        for (int i = 0; i < count; i++) {
            double val = sortedData[i];
            double ximean = val - mean;
            variance += ximean * ximean;
        }
        variance /= count;
        stddev = Math.sqrt(variance);

        // mode
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1;
        for (int i = 0; i < count; i++) {
            double dbl = sortedData[i];

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
        quartile1 = getQuartile(sortedData, 1d);
        quartile2 = getQuartile(sortedData, 2d);
        quartile3 = getQuartile(sortedData, 3d);
        median = quartile2;
        interquartileRange = quartile3 - quartile1;

        // skewness coefficients
        pearsonSkewCoef = 3 * (mean - median) / stddev;
        bowleySkewCoef = (quartile3 + quartile1 - 2 * quartile2) / (quartile3 - quartile1);
    }

    DataDescription(float[] sortedData)  {
        // count
        this.count = sortedData.length;

        // min, max, range
        this.min = sortedData[0];
        this.max = sortedData[sortedData.length-1];
        this.range = this.max - this.min;

        // sum, mean, geomean
        geomean = 1;
        for (int i = 0; i < count; i++) {
            double val = sortedData[i];

            geomean *= val;
            sum += val;
        }

        geomean = Math.pow(geomean, 1d / count);
        mean = sum / count;

        // stddev, variance
        for (int i = 0; i < count; i++) {
            double val = sortedData[i];
            double ximean = val - mean;
            variance += ximean * ximean;
        }
        variance /= count;
        stddev = Math.sqrt(variance);

        // mode
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1;
        for (int i = 0; i < count; i++) {
            double dbl = sortedData[i];

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
        quartile1 = getQuartile(sortedData, 1d);
        quartile2 = getQuartile(sortedData, 2d);
        quartile3 = getQuartile(sortedData, 3d);
        median = quartile2;
        interquartileRange = quartile3 - quartile1;

        // skewness coefficients
        pearsonSkewCoef = 3 * (mean - median) / stddev;
        bowleySkewCoef = (quartile3 + quartile1 - 2 * quartile2) / (quartile3 - quartile1);
    }

    DataDescription(double[] sortedData)  {
        // count
        this.count = sortedData.length;

        // min, max, range
        this.min = sortedData[0];
        this.max = sortedData[sortedData.length-1];
        this.range = this.max - this.min;

        // sum, mean, geomean
        geomean = 1;
        for (int i = 0; i < count; i++) {
            double val = sortedData[i];

            geomean *= val;
            sum += val;
        }

        geomean = Math.pow(geomean, 1d / count);
        mean = sum / count;

        // stddev, variance
        for (int i = 0; i < count; i++) {
            double val = sortedData[i];
            double ximean = val - mean;
            variance += ximean * ximean;
        }
        variance /= count;
        stddev = Math.sqrt(variance);

        // mode
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1;
        for (int i = 0; i < count; i++) {
            double dbl = sortedData[i];

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
        quartile1 = getQuartile(sortedData, 1d);
        quartile2 = getQuartile(sortedData, 2d);
        quartile3 = getQuartile(sortedData, 3d);
        median = quartile2;
        interquartileRange = quartile3 - quartile1;

        // skewness coefficients
        pearsonSkewCoef = 3 * (mean - median) / stddev;
        bowleySkewCoef = (quartile3 + quartile1 - 2 * quartile2) / (quartile3 - quartile1);
    }

    DataDescription(long[] sortedData)  {
        // count
        this.count = sortedData.length;

        // min, max, range
        this.min = sortedData[0];
        this.max = sortedData[sortedData.length-1];
        this.range = this.max - this.min;

        // sum, mean, geomean
        geomean = 1;
        for (int i = 0; i < count; i++) {
            double val = sortedData[i];

            geomean *= val;
            sum += val;
        }

        geomean = Math.pow(geomean, 1d / count);
        mean = sum / count;

        // stddev, variance
        for (int i = 0; i < count; i++) {
            double val = sortedData[i];
            double ximean = val - mean;
            variance += ximean * ximean;
        }
        variance /= count;
        stddev = Math.sqrt(variance);

        // mode
        HashMap<Double, Integer> frequencyMap = new HashMap<>();
        double maxModeFrequency  = 1;
        for (int i = 0; i < count; i++) {
            double dbl = sortedData[i];

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
        quartile1 = getQuartile(sortedData, 1d);
        quartile2 = getQuartile(sortedData, 2d);
        quartile3 = getQuartile(sortedData, 3d);
        median = quartile2;
        interquartileRange = quartile3 - quartile1;

        // skewness coefficients
        pearsonSkewCoef = 3 * (mean - median) / stddev;
        bowleySkewCoef = (quartile3 + quartile1 - 2 * quartile2) / (quartile3 - quartile1);
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
        double quartileIndex = (count + 1d) * quartileNumber / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        double lowValue = getValue(sortedData, field, lowIndex - 1); // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double highValue = getValue(sortedData, field, lowIndex); // also -1 here...
        return lowValue + (highValue - lowValue) * percentage;
    }

    private double getQuartile(int[] sortedData, double quartileNumber) {
        double quartileIndex = (count + 1d) * quartileNumber / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        double lowValue = sortedData[lowIndex - 1]; // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double highValue = sortedData[lowIndex]; // also -1 here...
        return lowValue + (highValue - lowValue) * percentage;
    }

    private double getQuartile(double[] sortedData, double quartileNumber) {
        double quartileIndex = (count + 1d) * quartileNumber / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        double lowValue = sortedData[lowIndex - 1]; // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double highValue = sortedData[lowIndex]; // also -1 here...
        return lowValue + (highValue - lowValue) * percentage;
    }

    private double getQuartile(float[] sortedData, double quartileNumber) {
        double quartileIndex = (count + 1d) * quartileNumber / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        double lowValue = sortedData[lowIndex - 1]; // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double highValue = sortedData[lowIndex]; // also -1 here...
        return lowValue + (highValue - lowValue) * percentage;
    }

    private double getQuartile(long[] sortedData, double quartileNumber) {
        double quartileIndex = (count + 1d) * quartileNumber / 4d;
        double percentage = quartileIndex % 1;
        int lowIndex = (int) Math.floor(quartileIndex);
        double lowValue = sortedData[lowIndex - 1]; // -1 because in statistics we have 1-based indexes but in Java it's 0-based
        double highValue = sortedData[lowIndex]; // also -1 here...
        return lowValue + (highValue - lowValue) * percentage;
    }




}
