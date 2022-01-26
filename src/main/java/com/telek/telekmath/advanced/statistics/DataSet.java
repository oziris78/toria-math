package com.telek.telekmath.advanced.statistics;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;


public class DataSet<T> {

    HashMap<String, DataDescription<T, ?>> dataDescriptions;

    private Class<T> clazz;
    private T[] rawData, data;


    public DataSet(T[] rawData, Class<T> clazz){
        this.dataDescriptions = new HashMap<>();
        this.rawData = rawData;
        this.clazz = clazz;
        this.data = (T[]) Array.newInstance(this.clazz, rawData.length);
        System.arraycopy(this.rawData, 0, this.data, 0, rawData.length);
    }

    public void sort(Comparator<T> comparator){
        Arrays.sort(this.data, comparator);
    }

    ///////////////
    /*  METHODS  */
    ///////////////

    public DataDescription<T, ?> getDataDescription(String fieldName, Class<?> fieldClass){
        if(!this.dataDescriptions.containsKey(fieldName)){
            try{
                Field field = clazz.getField(fieldName);
                DataDescription<T, ?> dataDescription = new DataDescription(this, fieldClass, field);
                dataDescriptions.put(fieldName, dataDescription);
            }
            catch (NoSuchFieldException | IllegalAccessException e) { e.printStackTrace(); }
        }

        return this.dataDescriptions.get(fieldName);
    }

    public T[] getSortedData() {
        return data;
    }


}
