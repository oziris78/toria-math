package com.telek.telekmath.advanced.statistics;

import jdk.jfr.Frequency;

import java.lang.reflect.Field;


public class FrequencyDistTable<T> {

    private final T[] population;
    private final Class<T> clazz;
    private Field field;
    private final int classCount;

    private double classInterval;
    private double count, min, max, range;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


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


    ///////////////
    /*  METHODS  */
    ///////////////


    public void fillTheTable(){
        try{
            doCalculations();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
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

        // Frequency sınıfını oluşturduktan Frequnecy[] yapıp
    }


    private double getValue(int index) throws IllegalAccessException {
        T currentTerm = this.population[index];
        Number value = (Number) this.field.get(currentTerm);
        return value.doubleValue();
    }



}
