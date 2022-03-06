package com.telek.telekutils.arrayref.oned;


import java.util.Arrays;

public class IntArrRef implements ArrayRef {

    private int[] array;

    public IntArrRef(int[] array){
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    @Override
    public double getValue(int index) {
        return array[index];
    }

    @Override
    public int getSize() {
        return array.length;
    }

    ////////////////////////////////////////////////


    @Override
    public String toString() {
        return "IntArrRef{" +
                Arrays.toString(array) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntArrRef intArrRef = (IntArrRef) o;
        return Arrays.equals(array, intArrRef.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
