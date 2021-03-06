package com.twistral.toriamath.advanced.statistics.freqtable;


import com.twistral.toriamath.advanced.statistics.descriptive.DescStats;
import com.twistral.toriamath.utils.ToriaMathException.*;
import com.twistral.toriautils.containers.TArrays;

import java.util.Arrays;
import java.util.Objects;


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
        this.classCount = frequencies.length;
        checkForClassCount(classCount);

        // create table
        table = new FrequencyClass[classCount];

        double totalFreqs = 0;
        for (int i = 0; i < classCount; i++)
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



    // PRIMITIVES & NUMBER & GENERIC CLASS TO FREQTABLE

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
            double freq = 0d;
            for (int j = 0; j < population.length; j++) {
                double number = population[j];
                if(cLeft <= number && number < cRight) // cl <= val < cr
                    freq++;
            }
            double relFreq = freq / population.length;
            double incCumFreq = freq + (i == 0 ? 0 : table[i-1].incCumFreq);
            double incRelFreq = relFreq + (i == 0 ? 0 : table[i-1].incRelFreq);
            table[i] = new FrequencyClass(cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq);
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
        return classCount == that.classCount && Arrays.equals(table, that.table);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(classCount);
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }


}