package com.telek.telekutils.containers;


import com.telek.telekmath.utils.TMath;
import com.telek.telekutils.arrayref.oned.FloatArrRef;
import com.telek.telekutils.arrayref.oned.ArrayRef;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;


/**
 * Utility methods revolving around arrays.
 */
public final class TArrays {

    /* No Constructor */
    private TArrays(){}


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


    public static int[][] getCopyOf(int[][] arrToCopy){
        return Arrays.stream(arrToCopy).map(int[]::clone).toArray(int[][]::new);
    }

    public static float[][] getCopyOf(float[][] arrToCopy){
        return Arrays.stream(arrToCopy).map(float[]::clone).toArray(float[][]::new);
    }

    public static Number[][] getCopyOf(Number[][] arrToCopy){
        return Arrays.stream(arrToCopy).map(Number[]::clone).toArray(Number[][]::new);
    }

    public static double[][] getCopyOf(double[][] arrToCopy){
        return Arrays.stream(arrToCopy).map(double[]::clone).toArray(double[][]::new);
    }


    ///////////////////////////////////////
    /////// CASTING ARRAYS METHODS ////////
    ///////////////////////////////////////

    public static double[] getCastedDoubleCopyOf(float[] arrToCast){
        return IntStream.range(0, arrToCast.length).mapToDouble(i -> arrToCast[i]).toArray();
    }

    public static double[] getCastedDoubleCopyOf(int[] arrToCast){
        return IntStream.range(0, arrToCast.length).mapToDouble(i -> arrToCast[i]).toArray();
    }

    public static double[] getCastedDoubleCopyOf(Number[] arrToCast){
        return IntStream.range(0, arrToCast.length).mapToDouble(i -> arrToCast[i].doubleValue()).toArray();
    }

    public static double[][] getCastedDouble2CopyOf(float[][] arrToCast){
        double[][] dArray = new double[arrToCast.length][arrToCast[0].length];
        for (int row = 0; row < dArray.length; row++) {
            for (int column = 0; column < dArray[0].length; column++){
                dArray[row][column] = arrToCast[row][column];
            }
        }
        return dArray;
    }

    public static double[][] getCastedDouble2CopyOf(int[][] arrToCast){
        double[][] dArray = new double[arrToCast.length][arrToCast[0].length];
        for (int row = 0; row < dArray.length; row++) {
            for (int column = 0; column < dArray[0].length; column++){
                dArray[row][column] = arrToCast[row][column];
            }
        }
        return dArray;
    }

    public static double[][] getCastedDouble2CopyOf(Number[][] arrToCast){
        double[][] dArray = new double[arrToCast.length][arrToCast[0].length];
        for (int row = 0; row < dArray.length; row++) {
            for (int column = 0; column < dArray[0].length; column++){
                dArray[row][column] = arrToCast[row][column].doubleValue();
            }
        }
        return dArray;
    }


    ///////////////////////////////////////
    /////// PRINTING ARRAY METHODS ////////
    ///////////////////////////////////////

    public static String toString(double[] arr){return Arrays.toString(arr);}
    public static String toString(float[] arr){return Arrays.toString(arr);}
    public static String toString(int[] arr){return Arrays.toString(arr);}
    public static String toString(long[] arr){return Arrays.toString(arr);}
    public static String toString(byte[] arr){return Arrays.toString(arr);}
    public static String toString(short[] arr){return Arrays.toString(arr);}
    public static String toString(Number[] arr){return Arrays.toString(arr);}

    public static String toString(double[][] arr){return Arrays.deepToString(arr);}
    public static String toString(float[][] arr){return Arrays.deepToString(arr);}
    public static String toString(int[][] arr){return Arrays.deepToString(arr);}
    public static String toString(long[][] arr){return Arrays.deepToString(arr);}
    public static String toString(byte[][] arr){return Arrays.deepToString(arr);}
    public static String toString(short[][] arr){return Arrays.deepToString(arr);}
    public static String toString(Number[][] arr){return Arrays.deepToString(arr);}



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

    public static float[] floatArr(float... nums){
        float[] arrToReturn = new float[nums.length];
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


    public static long getMax(long[] array){
        return Arrays.stream(array).max().getAsLong();
    }

    public static int getMax(int[] array){
        return Arrays.stream(array).max().getAsInt();
    }

    public static double getMax(double[] array){
        return Arrays.stream(array).max().getAsDouble();
    }

    public static double getMax(Number[] array){
        return Arrays.stream(array).max((o1, o2) -> (int) (o1.doubleValue() - o2.doubleValue())).get().doubleValue();
    }

    public static double getMax(ArrayRef array){
        double max = array.getValue(0);
        for (int i = 1; i < array.getSize(); i++) {
            double curValue = array.getValue(i);
            if(curValue > max) max = curValue;
        }
        return max;
    }


    public static float getMax(float[] array){
        return (float) getMax(new FloatArrRef(array));
    }


    // O(n)
    public static double getMin(ArrayRef array){
        double min = array.getValue(0);
        for (int i = 1; i < array.getSize(); i++) {
            double curValue = array.getValue(i);
            if(curValue < min)
                min = curValue;
        }
        return min;
    }

    public static float getMin(float[] array){
        return (float) getMin(new FloatArrRef(array));
    }

    public static long getMin(long[] array){
        return Arrays.stream(array).min().getAsLong();
    }

    public static int getMin(int[] array){
        return Arrays.stream(array).min().getAsInt();
    }

    public static double getMin(double[] array){
        return Arrays.stream(array).min().getAsDouble();
    }

    public static double getMin(Number[] array){
        return Arrays.stream(array).min((o1, o2) -> (int) (o1.doubleValue() - o2.doubleValue())).get().doubleValue();
    }



    /////////////////////////////////////////
    /////// ARRAY CONVERSION METHODS ////////
    /////////////////////////////////////////


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
