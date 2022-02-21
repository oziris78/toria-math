package com.telek.telekmath.advanced.statistics.freqtable;


import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.utils.TelekMathException.*;
import com.telek.telekutils.plain.TArrays;
import com.telek.telekutils.plain.TClassUtils;

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

    // KNOWN FREQUENCIES TO FREQTABLE
    public FreqDistTable(double[] frequencies, double min, double classInterval){
        // error checking & references
        checkForClassCount(frequencies.length);
        this.classCount = frequencies.length;

        // create table
        table = new FrequencyClass[classCount];

        double totalFreqs = 0;
        for (int i = 0; i < frequencies.length; i++)
            totalFreqs += frequencies[i];

        for (int i = 0; i < table.length; i++){
            double cLeft = min + i * classInterval;
            double cRight = min + (i+1) * classInterval;
            double midpoint = (cLeft + cRight) / 2d;
            // freq
            double freq = frequencies[i];
            double relFreq = freq / totalFreqs;
            double incCumFreq = freq + (i == 0 ? 0 : table[i-1].incCumFreq);
            double incRelFreq = relFreq + (i == 0 ? 0 : table[i-1].incRelFreq);
            table[i] = new FrequencyClass(cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq);
        }
    }


    // copied from that method (nothing was changed)
    /**  @see #FreqDistTable(double[], double, double)  */
    public FreqDistTable(float[] frequencies, double min, double classInterval){
        // error checking & references
        checkForClassCount(frequencies.length);
        this.classCount = frequencies.length;

        // create table
        table = new FrequencyClass[classCount];

        double totalFreqs = 0;
        for (int i = 0; i < frequencies.length; i++)
            totalFreqs += frequencies[i];

        for (int i = 0; i < table.length; i++){
            double cLeft = min + i * classInterval;
            double cRight = min + (i+1) * classInterval;
            double midpoint = (cLeft + cRight) / 2d;
            // freq
            double freq = frequencies[i];
            double relFreq = freq / totalFreqs;
            double incCumFreq = freq + (i == 0 ? 0 : table[i-1].incCumFreq);
            double incRelFreq = relFreq + (i == 0 ? 0 : table[i-1].incRelFreq);
            table[i] = new FrequencyClass(cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq);
        }
    }


    // copied from that method (nothing was changed)
    /**  @see #FreqDistTable(double[], double, double)  */
    public FreqDistTable(int[] frequencies, double min, double classInterval){
        // error checking & references
        checkForClassCount(frequencies.length);
        this.classCount = frequencies.length;

        // create table
        table = new FrequencyClass[classCount];

        double totalFreqs = 0;
        for (int i = 0; i < frequencies.length; i++)
            totalFreqs += frequencies[i];

        for (int i = 0; i < table.length; i++){
            double cLeft = min + i * classInterval;
            double cRight = min + (i+1) * classInterval;
            double midpoint = (cLeft + cRight) / 2d;
            // freq
            double freq = frequencies[i];
            double relFreq = freq / totalFreqs;
            double incCumFreq = freq + (i == 0 ? 0 : table[i-1].incCumFreq);
            double incRelFreq = relFreq + (i == 0 ? 0 : table[i-1].incRelFreq);
            table[i] = new FrequencyClass(cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq);
        }
    }


    // copied from that method (the changed lines have comments - 2 lines)
    /**  @see #FreqDistTable(double[], double, double)  */
    public FreqDistTable(Number[] frequencies, double min, double classInterval){
        // error checking & references
        checkForClassCount(frequencies.length);
        this.classCount = frequencies.length;

        // create table
        table = new FrequencyClass[classCount];

        double totalFreqs = 0;
        for (int i = 0; i < frequencies.length; i++)
            totalFreqs += frequencies[i].doubleValue(); // this line was changed

        for (int i = 0; i < table.length; i++){
            double cLeft = min + i * classInterval;
            double cRight = min + (i+1) * classInterval;
            double midpoint = (cLeft + cRight) / 2d;
            // freq
            double freq = frequencies[i].doubleValue(); // this line was changed
            double relFreq = freq / totalFreqs;
            double incCumFreq = freq + (i == 0 ? 0 : table[i-1].incCumFreq);
            double incRelFreq = relFreq + (i == 0 ? 0 : table[i-1].incRelFreq);
            table[i] = new FrequencyClass(cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq);
        }
    }


    // PRIMITIVES & NUMBER CLASS TO FREQTABLE

    /**
     * Creates a frequency distribution table.
     * @param population any population (can be unsorted)
     * @param classCount an integer specifying how many rows this frequency distribution table will have
     */
    public FreqDistTable(double[] population, int classCount) {
        // error checking & references
        checkForClassCount(classCount);
        this.classCount = classCount;

        // prepare min, max, range without
        double min = TArrays.getMin(population);
        double max = TArrays.getMax(population);
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


    // copied from that method (the changed lines have comments - 1 line 2 changes)
    /**  @see #FreqDistTable(double[], int)  */
    public FreqDistTable(Number[] population, int classCount){
        // error checking & references
        checkForClassCount(classCount);
        this.classCount = classCount;

        // prepare min, max, range without
        double min = TArrays.getMin(population);
        double max = TArrays.getMax(population);
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
                    // the line below was changed - 2 changes: doubleValue()
                    .filter(number -> cLeft <= number.doubleValue() && number.doubleValue() < cRight) // cl <= val < cr
                    .count();
            double relFreq = freq / population.length;
            double incCumFreq = freq + (i == 0 ? 0 : table[i-1].incCumFreq);
            double incRelFreq = relFreq + (i == 0 ? 0 : table[i-1].incRelFreq);
            table[i] = new FrequencyClass(cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq);
        }
    }


    // copied from that method (changed part is written down below, changed because we have no float streams in java)
    /**  @see #FreqDistTable(double[], int)  */
    public FreqDistTable(float[] population, int classCount){
        // error checking & references
        checkForClassCount(classCount);
        this.classCount = classCount;

        // prepare min, max, range without
        double min = TArrays.getMin(population);
        double max = TArrays.getMax(population);
        double range = DescStats.getRange(min, max);
        double classInterval = Math.ceil(range / classCount);

        // create table
        table = new FrequencyClass[classCount];
        for (int i = 0; i < table.length; i++){
            double cLeft = min + i * classInterval;
            double cRight = min + (i+1) * classInterval;
            double midpoint = (cLeft + cRight) / 2d;
            // freq
            // this part is different down below
            double freq = 0;
            for (int j = 0; j < population.length; j++) {
                double val = population[j];
                if(cLeft <= val && val < cRight) // [a,b) from the course
                    freq++;
            }
            // this part is different ^^
            double relFreq = freq / population.length;
            double incCumFreq = freq + (i == 0 ? 0 : table[i-1].incCumFreq);
            double incRelFreq = relFreq + (i == 0 ? 0 : table[i-1].incRelFreq);
            table[i] = new FrequencyClass(cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq);
        }
    }


    // copied from that method (nothing was changed)
    /**  @see #FreqDistTable(double[], int)  */
    public FreqDistTable(int[] population, int classCount) {
        // error checking & references
        checkForClassCount(classCount);
        this.classCount = classCount;

        // prepare min, max, range without
        double min = TArrays.getMin(population);
        double max = TArrays.getMax(population);
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



    // GENERIC CLASS TO FREQTABLE

    public <T> FreqDistTable(T[] population, Class<T> clazz, String fieldName, int classCount) {
        // error checking
        checkForClassCount(classCount);

        // references
        this.classCount = classCount;

        try{
            Field field = TClassUtils.getField(clazz, fieldName);

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


    /**
     * Returns the sum of all frequencies (from every row)
     * @return the sum of all frequencies (n value in stats)
     */
    public double getTotalFrequency(){
        double totalFreqs = 0;
        final int size = this.getRowCount();
        for (int i = 0; i < size; i++)
            totalFreqs += this.getFrequency(i);
        return totalFreqs;
    }


    /**
     * Returns all frequencies as an array starting from the first class to
     * the last class like this: {f1, f2, f3, ...}
     * @return all frequencies as an array
     */
    public double[] getFrequenciesAsArray(){
        double[] arr = new double[this.getRowCount()];
        for (int i = 0; i < this.getRowCount(); i++) {
            arr[i] = this.getFrequency(i);
        }
        return arr;
    }


    public int getRowCount(){
        return table.length;
    }





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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreqDistTable that = (FreqDistTable) o;
        if(this.classCount != that.classCount)
            return false;
        if(this.getRowCount() != that.getRowCount())
            return false;
        for (int i = 0; i < this.getRowCount(); i++) {
            FrequencyClass cl1 = this.getTableRow(i);
            FrequencyClass cl2 = that.getTableRow(i);
            if(!TMath.areEqual(cl1.cLeft, cl2.cLeft)) return false;
            if(!TMath.areEqual(cl1.cRight, cl2.cRight)) return false;
            if(!TMath.areEqual(cl1.freq, cl2.freq)) return false;
            if(!TMath.areEqual(cl1.relFreq, cl2.relFreq)) return false;
            if(!TMath.areEqual(cl1.incCumFreq, cl2.incCumFreq)) return false;
            if(!TMath.areEqual(cl1.incRelFreq, cl2.incRelFreq)) return false;
            if(!TMath.areEqual(cl1.midpoint, cl2.midpoint)) return false;
        }
        return true;
    }


}