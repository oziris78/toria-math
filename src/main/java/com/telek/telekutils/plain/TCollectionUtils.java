package com.telek.telekutils.plain;

import static com.telek.telekmath.exceptions.TelekMathException.*;

import java.util.*;


public final class TCollectionUtils {



    public static long getMax(long[] array){ return Arrays.stream(array).max().getAsLong(); }
    public static int getMax(int[] array){ return Arrays.stream(array).max().getAsInt(); }
    public static double getMax(double[] array){ return Arrays.stream(array).max().getAsDouble(); }


    public static long getMin(long[] array){ return Arrays.stream(array).min().getAsLong(); }
    public static int getMin(int[] array){ return Arrays.stream(array).min().getAsInt(); }
    public static double getMin(double[] array){ return Arrays.stream(array).min().getAsDouble(); }


    public static double[] doubleArr(double... nums){
        double[] arrToReturn = new double[nums.length];
        for(int i = 0; i < nums.length; i++) arrToReturn[i] = nums[i];
        return arrToReturn;
    }


    public static int[] intArr(int... nums){
        int[] arrToReturn = new int[nums.length];
        for(int i = 0; i < nums.length; i++)
            arrToReturn[i] = nums[i];
        return arrToReturn;
    }


    public static double[][] doubleArr2(double[]... nums){
        double[][] arrToReturn = new double[nums.length][nums.length];
        for(int i = 0; i < nums.length; i++)
            arrToReturn[i] = nums[i];
        return arrToReturn;
    }



    public static <T1, T2> HashMap<T1,T2> createHashMap(T1[] keys, T2[] values){
        HashMap<T1, T2> newMap = new HashMap<T1, T2>();
        try{
            if(keys.length != values.length) throw new NotEqualKeyAndValueException();
            for(int i=0; i<keys.length; i++) newMap.put( keys[i], values[i] );
        }
        catch (Exception e) { e.printStackTrace(); }
        return newMap;
    }


    public static <T1> ArrayList<T1> createArrayList(T1... entries){
        ArrayList<T1> newArrList = new ArrayList<T1>();
        for(int i=0; i<entries.length; i++) newArrList.add(entries[i]);
        return newArrList;
    }


    public static <T1> LinkedList<T1> createLinkedList(T1... entries){
        LinkedList<T1> newLinkedList = new LinkedList<T1>();
        for(int i=0; i<entries.length; i++) newLinkedList.add(entries[i]);
        return newLinkedList;
    }


    public static <T1> HashSet<T1> createHashSet(T1... entries){
        HashSet<T1> newHashSet = new HashSet<T1>();
        for(int i=0; i<entries.length; i++) newHashSet.add(entries[i]);
        return newHashSet;
    }


    public static double getSumOfElements(Collection<? extends Number> list){
        float sum = 0;
        for(Number num : list) sum += num.doubleValue();
        return sum;
    }




}
