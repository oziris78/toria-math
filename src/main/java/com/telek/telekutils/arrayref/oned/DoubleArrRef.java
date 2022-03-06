package com.telek.telekutils.arrayref.oned;


import java.util.Arrays;

public class DoubleArrRef implements ArrayRef {

    private double[] array;

    public DoubleArrRef(double[] array){
        this.array = array;
    }

    public double[] getArray() {
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
        return "DoubleArrRef{" + Arrays.toString(array) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleArrRef that = (DoubleArrRef) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

}
