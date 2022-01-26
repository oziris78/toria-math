package com.telek.telekmath.advanced.statistics;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;


public class DataSet<T> {

    HashMap<String, DataDescription<T>> dataDescriptions;

    private Class<T> clazz;
    private T[] rawData, sortedData;


    public DataSet(T[] population, Class<T> clazz){
        this.dataDescriptions = new HashMap<>();
        this.rawData = population;
        this.clazz = clazz;
        this.sortedData = (T[]) Array.newInstance(this.clazz, population.length);
        System.arraycopy(this.rawData, 0, this.sortedData, 0, population.length);
    }


    public void sort(Comparator<T> comparator){
        Arrays.sort(this.sortedData, comparator);
    }


    ///////////////
    /*  METHODS  */
    ///////////////

    public DataDescription<T> getDataDescription(String fieldName){
        if(!this.dataDescriptions.containsKey(fieldName)){
            try{
                Field field = clazz.getField(fieldName);
                DataDescription<T> dataDescription = new DataDescription(this, field);
                dataDescriptions.put(fieldName, dataDescription);
            }
            catch (NoSuchFieldException | IllegalAccessException e) { e.printStackTrace(); }
        }

        return this.dataDescriptions.get(fieldName);
    }

    public T[] getSortedData() {
        return sortedData;
    }


}
