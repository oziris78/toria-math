package com.telek.telekmath.advanced.statistics;

import java.lang.reflect.Field;
import java.util.HashMap;


public class DataDescription<T, FT extends Number> {

    private final DataSet<T> dataSet;
    private final Class<FT> fieldClass;
    private final Field field;
    private final T[] sortedData;

    public double count, mean, geomean, sum, median,
            mode, modeCount, max, min, range, stddev, variance;


    public DataDescription(DataSet<T> dataSet, Class<FT> fieldClass, Field field) throws IllegalAccessException {
        // references
        this.dataSet = dataSet;
        this.fieldClass = fieldClass;
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




        // dataset'te bulunan her terim için bu field değerine ulaşmam gerek

        // https://stackoverflow.com/questions/2127197/java-how-can-i-access-a-classs-field-by-a-name-stored-in-a-variable


    }





    private double getValue(int index) throws IllegalAccessException {
        T currentTerm = sortedData[index];
        FT value = (FT) field.get(currentTerm);
        return value.doubleValue();
    }



}
