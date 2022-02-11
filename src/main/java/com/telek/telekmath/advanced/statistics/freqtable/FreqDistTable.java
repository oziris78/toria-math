package com.telek.telekmath.advanced.statistics.freqtable;


import com.telek.telekmath.advanced.statistics.measures.DescStats;
import com.telek.telekmath.exceptions.TelekMathException.NotGreaterThanZeroException;
import com.telek.telekutils.plain.TCollections;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;


public class FreqDistTable {


    //////////////
    /*  FIELDS  */
    //////////////

    private FrequencyClass[] table;
    private final int classCount;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    public FreqDistTable(Number[] population, int classCount) {
        // error checking & references
        checkForClassCount(classCount);
        this.classCount = classCount;

        // prepare min, max, range without
        double min = TCollections.getMin(population);
        double max = TCollections.getMax(population);
        double range = DescStats.getRange(min, max);
        double classInterval = Math.ceil(range / classCount);

        // create table
        table = new FrequencyClass[classCount];
        for (int i = 0; i < table.length; i++){
            double cLeft = min + i * classInterval;
            double cRight = min + (i+1) * classInterval;
            double midpoint = (cLeft + cRight) / 2d;
            // freq
            double freq = Arrays.stream(population)
                    .filter(number -> cLeft <= number.doubleValue() && number.doubleValue() < cRight) // cl <= val < cr
                    .count();
            double relFreq = freq / population.length;
            double incCumFreq = freq + (i == 0 ? 0 : table[i-1].incCumFreq);
            double incRelFreq = relFreq + (i == 0 ? 0 : table[i-1].incRelFreq);
            table[i] = new FrequencyClass(cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq);
        }
    }


    public FreqDistTable(double[] population, int classCount) {
        // error checking & references
        checkForClassCount(classCount);
        this.classCount = classCount;

        // prepare min, max, range without
        double min = TCollections.getMin(population);
        double max = TCollections.getMax(population);
        double range = DescStats.getRange(min, max);
        double classInterval = Math.ceil(range / classCount);

        // create table
        table = new FrequencyClass[classCount];
        for (int i = 0; i < table.length; i++){
            double cLeft = min + i * classInterval;
            double cRight = min + (i+1) * classInterval;
            double midpoint = (cLeft + cRight) / 2d;
            // freq
            double freq = Arrays.stream(population)
                    .filter(number -> cLeft <= number && number < cRight) // cl <= val < cr
                    .count();
            double relFreq = freq / population.length;
            double incCumFreq = freq + (i == 0 ? 0 : table[i-1].incCumFreq);
            double incRelFreq = relFreq + (i == 0 ? 0 : table[i-1].incRelFreq);
            table[i] = new FrequencyClass(cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq);
        }
    }


    public FreqDistTable(float[] population, int classCount) {
        // error checking & references
        checkForClassCount(classCount);
        this.classCount = classCount;

        // prepare min, max, range without
        double min = TCollections.getMin(population);
        double max = TCollections.getMax(population);
        double range = DescStats.getRange(min, max);
        double classInterval = Math.ceil(range / classCount);

        // create table
        table = new FrequencyClass[classCount];
        for (int i = 0; i < table.length; i++){
            double cLeft = min + i * classInterval;
            double cRight = min + (i+1) * classInterval;
            double midpoint = (cLeft + cRight) / 2d;
            // freq
            double freq = 0;
            for (int j = 0; j < population.length; j++) {
                double val = population[j];
                if(cLeft <= val && val < cRight) // [a,b) from the course
                    freq++;
            }
            double relFreq = freq / population.length;
            double incCumFreq = freq + (i == 0 ? 0 : table[i-1].incCumFreq);
            double incRelFreq = relFreq + (i == 0 ? 0 : table[i-1].incRelFreq);
            table[i] = new FrequencyClass(cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq);
        }
    }


    public FreqDistTable(int[] population, int classCount) {
        // error checking & references
        checkForClassCount(classCount);
        this.classCount = classCount;

        // prepare min, max, range without
        double min = TCollections.getMin(population);
        double max = TCollections.getMax(population);
        double range = DescStats.getRange(min, max);
        double classInterval = Math.ceil(range / classCount);

        // create table
        table = new FrequencyClass[classCount];
        for (int i = 0; i < table.length; i++){
            double cLeft = min + i * classInterval;
            double cRight = min + (i+1) * classInterval;
            double midpoint = (cLeft + cRight) / 2d;
            // freq
            double freq = Arrays.stream(population)
                    .filter(number -> cLeft <= number && number < cRight) // cl <= val < cr
                    .count();
            double relFreq = freq / population.length;
            double incCumFreq = freq + (i == 0 ? 0 : table[i-1].incCumFreq);
            double incRelFreq = relFreq + (i == 0 ? 0 : table[i-1].incRelFreq);
            table[i] = new FrequencyClass(cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq);
        }
    }


    public <T> FreqDistTable(T[] population, Class<T> clazz, String fieldName, int classCount) {
        // error checking
        checkForClassCount(classCount);

        // references
        this.classCount = classCount;

        try{
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);

            /* CALCULATIONS */
            /* ------------ */

            // prepare min, max, range, classInterval
            double count = population.length;
            double min = getValue(0, field, population);
            double max = getValue(0, field, population);
            for (int i = 1; i < population.length; i++) {
                double curValue = getValue(i, field, population);
                if(curValue > max) max = curValue;
                if(curValue < min) min = curValue;
            }
            double range = max - min;
            double classInterval = Math.ceil(range / classCount);

            // create table
            table = (FrequencyClass[]) Array.newInstance(FrequencyClass.class, classCount);
            for (int i = 0; i < table.length; i++){
                double cLeft = min + i * classInterval;
                double cRight = min + (i+1) * classInterval;
                double midpoint = (cLeft + cRight) / 2d;
                // freq
                double freq = 0;
                for (int j = 0; j < count; j++) {
                    double val = getValue(j, field, population);
                    if(cLeft <= val && val < cRight) // [a,b) from the course
                        freq++;
                }
                double relFreq = freq / count;
                double incCumFreq = freq + (i == 0 ? 0 : table[i-1].incCumFreq);
                double incRelFreq = relFreq + (i == 0 ? 0 : table[i-1].incRelFreq);

                table[i] = new FrequencyClass(cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq);
            }
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }



    ///////////////
    /*  METHODS  */
    ///////////////

    public FrequencyClass getTableRow(int row){
        return table[row];
    }


    /**
     * @param row row index
     * @return the left side of this row's interval (inclusive)
     */
    public double getIntervalLeft(int row){
        return table[row].cLeft;
    }

    /**
     * @param row row index
     * @return the right side of this row's interval (exclusive)
     */
    public double getIntervalRight(int row){
        return table[row].cRight;
    }

    /**
     * @param row row index
     * @return the midpoint of this row
     */
    public double getMidpoint(int row){
        return table[row].midpoint;
    }

    /**
     * @param row row index
     * @return the frequency of this row
     */
    public double getFrequency(int row){
        return table[row].freq;
    }

    /**
     * @param row row index
     * @return the relative frequency of this row
     */
    public double getRelativeFreq(int row){
        return table[row].relFreq;
    }

    /**
     * @param row row index
     * @return the increasing cumulative distribution's frequency of this row
     */
    public double getIncCumFreq(int row){
        return table[row].incCumFreq;
    }

    /**
     * @param row row index
     * @return the increasing relative distribution's frequency of this row
     */
    public double getIncRelFreq(int row){
        return table[row].incRelFreq;
    }


    ///////////////
    /*  HELPERS  */
    ///////////////


    private <T> double getValue(int index, Field field, T[] population) throws IllegalAccessException {
        Number num = (Number) field.get(population[index]);
        return num.doubleValue();
    }


    private void checkForClassCount(int classCount) {
        if(classCount <= 0) throw new NotGreaterThanZeroException("classCount");
    }


    //////////////////////////////////////////////////////////////////////////////


    @Override
    public String toString() {
        String startStr = "class intervals | midpoints | freq. | relative freq. | inc. cum. freq. | inc. rel. freq.\n";
        StringBuilder sb = new StringBuilder(startStr);

        String tab = "     "; // tab string
        int lastIndex = classCount-1;
        for (int i = 0; i < classCount; i++) {
            sb.append(String.format("[%.3f, %.3f)%s%.3f%s%.3f%s%.3f%s%.3f%s%.3f",
                    getIntervalLeft(i), getIntervalRight(i), tab, getMidpoint(i), tab, getFrequency(i), tab, getRelativeFreq(i), tab,
                    getIncCumFreq(i), tab, getIncRelFreq(i)));
            if(i != lastIndex) sb.append("\n");
        }

        return sb.toString();
    }




}