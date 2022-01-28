package com.telek.telekmath.advanced.statistics;

import java.lang.reflect.Field;


public class FrequencyDistTable<T> {

    private class FrequencyClass<T> {

        double cLeft, cRight, midpoint, freq, relFreq, incCumFreq, incRelFreq;

        FrequencyClass(int i) throws IllegalAccessException {
            cLeft = min + i * classInterval;
            cRight = min + (i+1) * classInterval;
            midpoint = (cLeft + cRight) / 2d;
            freq = getFrequency();
            relFreq = freq / count;
            incCumFreq = freq + (i == 0 ? 0 : table[i-1].incCumFreq);
            incRelFreq = relFreq + (i == 0 ? 0 : table[i-1].incRelFreq);
        }


        private double getFrequency() throws IllegalAccessException {
            int freq = 0;
            for (int i = 0; i < count; i++) {
                double val = getValue(i);
                if(cLeft <= val && val < cRight)
                    freq++;
            }
            return freq;
        }

    }


    private final T[] population;
    private final Class<T> clazz;
    private Field field;
    private final int classCount;

    private FrequencyClass<?>[] table;
    private double classInterval;
    private double count, min, max, range;


    ///////////////////////////////
    /*  CONSTRUCTORS AND FILLERS */
    ///////////////////////////////

    /*

     */


    public FrequencyDistTable(T[] population, Class<T> clazz, String fieldName, int classCount) {
        // references
        this.population = population;
        this.clazz = clazz;
        this.classCount = classCount;
        try{
            this.field = this.clazz.getField(fieldName);
        }
        catch (NoSuchFieldException e) {e.printStackTrace();}
    }


    public void fillTheTable(){
        try{
            doCalculations();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    ///////////////
    /*  METHODS  */
    ///////////////


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


    private void doCalculations() throws IllegalAccessException {
        // prepare min, max, range, classInterval
        count = population.length;
        min = getValue(0);
        max = getValue(0);
        for (int i = 1; i < population.length; i++) {
            double curValue = getValue(i);
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


    private double getValue(int index) throws IllegalAccessException {
        T currentTerm = this.population[index];
        Number value = (Number) this.field.get(currentTerm);
        return value.doubleValue();
    }



}
