package com.telek.telekmath.advanced.statistics.descriptive;


import java.util.ArrayList;

public class FreqDistTable {

    private class FrequencyClass {

        double cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq;

        FrequencyClass(int i) {
            cLeft = min + i * classInterval;
            cRight = min + (i+1) * classInterval;
            midpoint = (cLeft + cRight) / 2d;
            // freq
            for (int j = 0; j < count; j++) {
                double val = getValue(j);
                if(cLeft <= val && val < cRight) // [a,b) from the course
                    freq++;
            }
            // freq
            relFreq = freq / count;
            incCumFreq = freq + (i == 0 ? 0 : table[i-1].incCumFreq);
            incRelFreq = relFreq + (i == 0 ? 0 : table[i-1].incRelFreq);
        }

    }


    // supported types
    private int[] ipopulation = null;
    private float[] fpopulation = null;
    private double[] dpopulation = null;
    private long[] lpopulation = null;

    private final int classCount;

    private FrequencyClass[] table;
    private double classInterval;
    private double count, min, max, range;



    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    public FreqDistTable(int[] population, int classCount) {
        this.ipopulation = population;
        this.classCount = classCount;
    }

    public FreqDistTable(float[] population, int classCount) {
        this.fpopulation = population;
        this.classCount = classCount;
    }

    public FreqDistTable(double[] population, int classCount) {
        this.dpopulation = population;
        this.classCount = classCount;
    }

    public FreqDistTable(long[] population, int classCount) {
        this.lpopulation = population;
        this.classCount = classCount;
    }


    ///////////////
    /*  METHODS  */
    ///////////////


    public void fillTheTable(){
        if(ipopulation != null) calcInt();
        else if(dpopulation != null) calcDouble();
        else if(fpopulation != null) calcFloat();
        else if(lpopulation != null) calcLong();
    }


    public String getTableAsString() {
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


    private void calcInt() {
        int[] arr = ipopulation;
        // prepare min, max, range, classInterval
        count = arr.length;
        min = arr[0];
        max = arr[0];
        for (int i = 1; i < count; i++) {
            double curValue = arr[i];
            if(curValue > max) max = curValue;
            if(curValue < min) min = curValue;
        }
        range = max - min;
        classInterval = Math.ceil(range / classCount);

        // create table
        table = new FrequencyClass[classCount];
        for (int i = 0; i < table.length; i++)
            table[i] = new FrequencyClass(i);
    }

    private void calcFloat() {
        float[] arr = fpopulation;
        // prepare min, max, range, classInterval
        count = arr.length;
        min = arr[0];
        max = arr[0];
        for (int i = 1; i < count; i++) {
            double curValue = arr[i];
            if(curValue > max) max = curValue;
            if(curValue < min) min = curValue;
        }
        range = max - min;
        classInterval = Math.ceil(range / classCount);

        // create table
        table = new FrequencyClass[classCount];
        for (int i = 0; i < table.length; i++)
            table[i] = new FrequencyClass(i);
    }

    private void calcDouble() {
        double[] arr = dpopulation;
        // prepare min, max, range, classInterval
        count = arr.length;
        min = arr[0];
        max = arr[0];
        for (int i = 1; i < count; i++) {
            double curValue = arr[i];
            if(curValue > max) max = curValue;
            if(curValue < min) min = curValue;
        }
        range = max - min;
        classInterval = Math.ceil(range / classCount);

        // create table
        table = new FrequencyClass[classCount];
        for (int i = 0; i < table.length; i++)
            table[i] = new FrequencyClass(i);
    }

    private void calcLong() {
        long[] arr = lpopulation;
        // prepare min, max, range, classInterval
        count = arr.length;
        min = arr[0];
        max = arr[0];
        for (int i = 1; i < count; i++) {
            double curValue = arr[i];
            if(curValue > max) max = curValue;
            if(curValue < min) min = curValue;
        }
        range = max - min;
        classInterval = Math.ceil(range / classCount);

        // create table
        table = new FrequencyClass[classCount];
        for (int i = 0; i < table.length; i++)
            table[i] = new FrequencyClass(i);
    }

    private double getValue(int j){
        if(ipopulation != null) return ipopulation[j];
        else if(dpopulation != null) return dpopulation[j];
        else if(fpopulation != null) return fpopulation[j];
        else return lpopulation[j];
    }



}
