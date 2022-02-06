package com.telek.telekutils.plain;


import com.telek.telekmath.TMath;
import com.telek.telekmath.exceptions.TelekMathException.*;
import java.lang.reflect.Array;
import java.util.*;


public final class TCollections {


    ////////////////////////////////
    /////// COPYING METHODS ////////
    ////////////////////////////////

    public static int[] getCopyOf(int[] arrToCopy){
        int size = arrToCopy.length;
        int[] newArray = new int[size];
        System.arraycopy(arrToCopy, 0, newArray, 0, size);
        return newArray;
    }

    public static float[] getCopyOf(float[] arrToCopy){
        int size = arrToCopy.length;
        float[] newArray = new float[size];
        System.arraycopy(arrToCopy, 0, newArray, 0, size);
        return newArray;
    }

    public static long[] getCopyOf(long[] arrToCopy){
        int size = arrToCopy.length;
        long[] newArray = new long[size];
        System.arraycopy(arrToCopy, 0, newArray, 0, size);
        return newArray;
    }

    public static short[] getCopyOf(short[] arrToCopy){
        int size = arrToCopy.length;
        short[] newArray = new short[size];
        System.arraycopy(arrToCopy, 0, newArray, 0, size);
        return newArray;
    }

    public static byte[] getCopyOf(byte[] arrToCopy){
        int size = arrToCopy.length;
        byte[] newArray = new byte[size];
        System.arraycopy(arrToCopy, 0, newArray, 0, size);
        return newArray;
    }

    public static double[] getCopyOf(double[] arrToCopy){
        int size = arrToCopy.length;
        double[] newArray = new double[size];
        System.arraycopy(arrToCopy, 0, newArray, 0, size);
        return newArray;
    }

    public static Number[] getCopyOf(Number[] arrToCopy){
        int size = arrToCopy.length;
        Number[] newArray = new Number[size];
        System.arraycopy(arrToCopy, 0, newArray, 0, size);
        return newArray;
    }

    public static <T> T[] getCopyOf(T[] arrToCopy, Class<T> clazz){
        int size = arrToCopy.length;
        T[] newArray = (T[]) Array.newInstance(clazz, size);
        System.arraycopy(arrToCopy, 0, newArray, 0, size);
        return newArray;
    }

    public static <T> ArrayList<T> getCopyOf(ArrayList<T> arrayListToCopy){
        return new ArrayList<T>(arrayListToCopy);
    }

    public static <T> LinkedList<T> getCopyOf(LinkedList<T> linkedListToCopy){
        return new LinkedList<T>(linkedListToCopy);
    }

    public static <T> HashSet<T> getCopyOf(HashSet<T> hashSetToCopy){
        return new HashSet<T>(hashSetToCopy);
    }

    public static <T,K> HashMap<T,K> getCopyOf(HashMap<T,K> hashMapToCopy){
        return new HashMap<T,K>(hashMapToCopy);
    }


    ////////////////////////////////////
    /////// SORT & COPY METHODS ////////
    ////////////////////////////////////


    public static <T> T[] getSortedCopy(T[] arrToCopyAndSort, Class<T> clazz, Comparator<T> comparator){
        T[] newArray = getCopyOf(arrToCopyAndSort, clazz);
        Arrays.sort(newArray, comparator);
        return newArray;
    }

    public static int[] getSortedCopy(int[] arrToCopyAndSort){
        int[] newArray = getCopyOf(arrToCopyAndSort);
        Arrays.sort(newArray);
        return newArray;
    }

    public static float[] getSortedCopy(float[] arrToCopyAndSort){
        float[] newArray = getCopyOf(arrToCopyAndSort);
        Arrays.sort(newArray);
        return newArray;
    }

    public static double[] getSortedCopy(double[] arrToCopyAndSort){
        double[] newArray = getCopyOf(arrToCopyAndSort);
        Arrays.sort(newArray);
        return newArray;
    }

    public static long[] getSortedCopy(long[] arrToCopyAndSort){
        long[] newArray = getCopyOf(arrToCopyAndSort);
        Arrays.sort(newArray);
        return newArray;
    }

    public static byte[] getSortedCopy(byte[] arrToCopyAndSort){
        byte[] newArray = getCopyOf(arrToCopyAndSort);
        Arrays.sort(newArray);
        return newArray;
    }

    public static short[] getSortedCopy(short[] arrToCopyAndSort){
        short[] newArray = getCopyOf(arrToCopyAndSort);
        Arrays.sort(newArray);
        return newArray;
    }


    ////////////////////////////////////
    ///// ARRAY CREATION METHODS //////
    ////////////////////////////////////


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


    public static double[][] doubleArr2(double[]... numArrays){
        double[][] arrToReturn = new double[numArrays.length][numArrays.length];
        for(int i = 0; i < numArrays.length; i++)
            arrToReturn[i] = numArrays[i];
        return arrToReturn;
    }

    public static int[][] intArr2(int[]... numArrays){
        int[][] arrToReturn = new int[numArrays.length][numArrays.length];
        for(int i = 0; i < numArrays.length; i++)
            arrToReturn[i] = numArrays[i];
        return arrToReturn;
    }




    /**
     * @param start first value (inclusive)
     * @param end last value (inclusive)
     * @param elemCountIncludingBothEnds element count (size of the array)
     * @return an array in form start, start+delta, start+2delta, ..., end
     */
    public static double[] linearlySpacedArr(double start, double end, int elemCountIncludingBothEnds){
        double min = Math.min(start, end);
        double max = Math.max(start, end);
        int elemCount = Math.abs(elemCountIncludingBothEnds - 2);

        int len = 2 + elemCount;
        double[] arr = new double[len];

        final double range = max - min;
        final double delta = range / (double) (len-1);

        arr[0] = min; // first elem
        arr[len-1] = max; // last elem
        for (int i = 1; i < len-1; i++) { // elems in the middle
            arr[i] = min + i * delta;
        }

        return arr;
    }




    /**
     * @param start first value (inclusive)
     * @param end last value (inclusive)
     * @param step increment value
     * @return an array containing start, start+step, start+2step, ...
     */
    public static double[] doubleArr(double start, double end, double step){
        double min = Math.min(start, end);
        double max = Math.max(start, end);
        double inc = Math.abs(step);

        int len = 1 + (int) ((max - min) / inc);
        if(TMath.areEqual((min + len * inc), max))
            len++; // make end inclusive for doubles

        double[] arr = new double[len];

        for (int i = 0; i < len; i++)
            arr[i] = min + i * inc;

        return arr;
    }

    /**
     * @param start first value (inclusive)
     * @param end last value (inclusive)
     * @param step increment value
     * @return an array containing start, start+step, start+2step, ...
     */
    public static int[] intArr(int start, int end, int step){
        int min = Math.min(start, end);
        int max = Math.max(start, end);
        int inc = Math.abs(step);

        int len = 1 + ((max - min) / inc);
        int[] arr = new int[len];

        for (int i = 0; i < len; i++)
            arr[i] = min + i * inc;

        return arr;
    }




    public static double[] doubleFilledArr(int size, double fillValue){
        double[] arrToReturn = new double[size];
        for(int i = 0; i < size; i++)
            arrToReturn[i] = fillValue;
        return arrToReturn;
    }

    public static int[] intFilledArr(int size, int fillValue){
        int[] arrToReturn = new int[size];
        for(int i = 0; i < size; i++)
            arrToReturn[i] = fillValue;
        return arrToReturn;
    }


    ////////////////////////////////////////
    ///// COLLECTION CREATION METHODS //////
    ////////////////////////////////////////


    public static <T1, T2> HashMap<T1,T2> createHashMap(T1[] keys, T2[] values){
        HashMap<T1, T2> newMap = new HashMap<T1, T2>();
        try{
            if(keys.length != values.length) throw new NotEqualKeyAndValueException();
            for(int i=0; i<keys.length; i++) newMap.put( keys[i], values[i] );
        }
        catch (Exception e) { e.printStackTrace(); }
        return newMap;
    }


    public static <T> ArrayList<T> createArrayList(T... entries){
        ArrayList<T> newArrList = new ArrayList<T>();
        for(int i=0; i<entries.length; i++) newArrList.add(entries[i]);
        return newArrList;
    }


    public static <T> LinkedList<T> createLinkedList(T... entries){
        LinkedList<T> newLinkedList = new LinkedList<T>();
        for(int i=0; i<entries.length; i++) newLinkedList.add(entries[i]);
        return newLinkedList;
    }


    public static <T> HashSet<T> createHashSet(T... entries){
        HashSet<T> newHashSet = new HashSet<T>();
        for(int i=0; i<entries.length; i++) newHashSet.add(entries[i]);
        return newHashSet;
    }



    /////////////////////////////////
    ///// MATHEMATICAL METHODS //////
    /////////////////////////////////


    public static boolean areEqual(double[] arr1, double[] arr2){
        return Arrays.equals(arr1, arr2);
    }

    public static boolean areEqual(int[] arr1, int[] arr2){
        return Arrays.equals(arr1, arr2);
    }

    public static boolean areEqual(float[] arr1, float[] arr2){
        return Arrays.equals(arr1, arr2);
    }

    public static boolean areEqual(long[] arr1, long[] arr2){
        return Arrays.equals(arr1, arr2);
    }



    ////////////////////////////////
    /////// UTILITY METHODS ////////
    ////////////////////////////////


    public static long getMax(long[] array){ return Arrays.stream(array).max().getAsLong(); }
    public static int getMax(int[] array){ return Arrays.stream(array).max().getAsInt(); }
    public static double getMax(double[] array){ return Arrays.stream(array).max().getAsDouble(); }

    public static long getMin(long[] array){ return Arrays.stream(array).min().getAsLong(); }
    public static int getMin(int[] array){ return Arrays.stream(array).min().getAsInt(); }
    public static double getMin(double[] array){ return Arrays.stream(array).min().getAsDouble(); }


    public static double getSumOfElements(Collection<? extends Number> list){
        float sum = 0;
        for(Number num : list) sum += num.doubleValue();
        return sum;
    }

    public static <T> void shuffleArray(T[] arr){
        List<T> list = Arrays.asList(arr);
        Collections.shuffle(list);
        list.toArray(arr);
    }




    /**
     * Changing anything in the Integer[] array WILL change stuff in the int[]
     * @param arr any array
     * @return that array in class form
     */
    public static Integer[] getAsClassArray(int[] arr){
        return Arrays.stream(arr).boxed().toArray(Integer[]::new);
    }



    /**
     * Changing anything in the Long[] array WILL change stuff in the long[]
     * @param arr any array
     * @return that array in class form
     */
    public static Long[] getAsClassArray(long[] arr){
        return Arrays.stream(arr).boxed().toArray(Long[]::new);
    }


    /**
     * Changing anything in the Double[] array WILL change stuff in the double[]
     * @param arr any array
     * @return that array in class form
     */
    public static Double[] getAsClassArray(double[] arr){
        return Arrays.stream(arr).boxed().toArray(Double[]::new);
    }


}
