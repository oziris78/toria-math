package com.telek.telekutils.arrayref.oned;


import java.util.Arrays;

public class FloatArrRef implements ArrayRef {

    private float[] array;

    public FloatArrRef(float[] array){
        this.array = array;
    }

    public float[] getArray() {
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
        return "FloatArrRef{" +
                Arrays.toString(array) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloatArrRef that = (FloatArrRef) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
