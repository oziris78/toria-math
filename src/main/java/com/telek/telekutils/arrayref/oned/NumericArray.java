package com.telek.telekutils.arrayref.oned;


import java.lang.reflect.Array;


public class NumericArray {

    private final Object array;
    private final boolean isNumberArray;
    private final Number[] numberArray;
    private final int length;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    public NumericArray(int[] array){
        this.array = array;
        this.length = array.length;
        numberArray = null;
        isNumberArray = false;
    }

    public NumericArray(float[] array){
        this.array = array;
        this.length = array.length;
        numberArray = null;
        isNumberArray = false;
    }

    public NumericArray(double[] array){
        this.array = array;
        this.length = array.length;
        numberArray = null;
        isNumberArray = false;
    }

    public NumericArray(long[] array){
        this.array = array;
        this.length = array.length;
        numberArray = null;
        isNumberArray = false;
    }

    public NumericArray(Number[] array){
        this.array = null;
        numberArray = array;
        this.length = array.length;
        isNumberArray = true;
    }


    ///////////////
    /*  METHODS  */
    ///////////////


    public double getValue(int index){
        if(isNumberArray)
            return numberArray[index].doubleValue();

        return Array.getDouble(this.array, index);
    }


    public int getLength(){
        return length;
    }


}
