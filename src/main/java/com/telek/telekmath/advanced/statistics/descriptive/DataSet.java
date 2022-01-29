package com.telek.telekmath.advanced.statistics.descriptive;


import com.telek.telekutils.plain.TCollections;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;


public class DataSet<T> {

    HashMap<String, DataDescription> dataDescriptions;

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


    public static DataDescription getDataDescription(int[] population){
        int[] sortedData = TCollections.getCopyOf(population);
        Arrays.sort(sortedData);
        return new DataDescription(sortedData);
    }

    public static DataDescription getDataDescription(double[] population){
        double[] sortedData = TCollections.getCopyOf(population);
        Arrays.sort(sortedData);
        return new DataDescription(sortedData);
    }

    public static DataDescription getDataDescription(float[] population){
        float[] sortedData = TCollections.getCopyOf(population);
        Arrays.sort(sortedData);
        return new DataDescription(sortedData);
    }

    public static DataDescription getDataDescription(long[] population){
        long[] sortedData = TCollections.getCopyOf(population);
        Arrays.sort(sortedData);
        return new DataDescription(sortedData);
    }



    public DataDescription getDataDescription(String fieldName){
        if(!this.dataDescriptions.containsKey(fieldName)){
            try{
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                DataDescription dataDescription = new DataDescription(this, field);
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
