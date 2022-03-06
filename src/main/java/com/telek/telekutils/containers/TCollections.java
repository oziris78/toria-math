package com.telek.telekutils.containers;


import com.telek.telekmath.utils.TelekMathException.*;

import java.util.*;



/**
 * Utility methods revolving around collections such as ArrayLists, HashSets, HashMaps, LinkedLists etc.
 */
public final class TCollections {


    /* No Constructor */
    private TCollections(){}


    ////////////////////////////////////////
    ///// COLLECTION CREATION METHODS //////
    ////////////////////////////////////////


    public static <T1, T2> HashMap<T1,T2> createHashMap(T1[] keys, T2[] values) {
        HashMap<T1, T2> newMap = new HashMap<T1, T2>();
        try{
            if(keys.length != values.length) throw new NotEqualKeyAndValueException();
            for(int i=0; i<keys.length; i++) newMap.put( keys[i], values[i] );
        }
        catch (Exception e) { e.printStackTrace(); }
        return newMap;
    }


    public static <T> ArrayList<T> createArrayList(T... entries) {
        ArrayList<T> newArrList = new ArrayList<T>();
        for(int i=0; i<entries.length; i++) newArrList.add(entries[i]);
        return newArrList;
    }


    public static <T> LinkedList<T> createLinkedList(T... entries) {
        LinkedList<T> newLinkedList = new LinkedList<T>();
        for(int i=0; i<entries.length; i++) newLinkedList.add(entries[i]);
        return newLinkedList;
    }


    public static <T> HashSet<T> createHashSet(T... entries) {
        HashSet<T> newHashSet = new HashSet<T>();
        for(int i=0; i<entries.length; i++) newHashSet.add(entries[i]);
        return newHashSet;
    }



    ///////////////////////////////////////////////
    /////// COLLECTION CONVERSION METHODS ////////
    ///////////////////////////////////////////////

    public static int[] collectionToArray(List<Integer> steps) {
        return steps.stream().mapToInt(i -> i.intValue()).toArray();
    }





    ////////////////////////////////
    /////// COPYING METHODS ////////
    ////////////////////////////////

    public static <T> ArrayList<T> getCopyOf(ArrayList<T> arrayListToCopy) {
        return new ArrayList<T>(arrayListToCopy);
    }

    public static <T> LinkedList<T> getCopyOf(LinkedList<T> linkedListToCopy) {
        return new LinkedList<T>(linkedListToCopy);
    }

    public static <T> HashSet<T> getCopyOf(HashSet<T> hashSetToCopy) {
        return new HashSet<T>(hashSetToCopy);
    }

    public static <T,K> HashMap<T,K> getCopyOf(HashMap<T,K> hashMapToCopy) {
        return new HashMap<T,K>(hashMapToCopy);
    }


}
