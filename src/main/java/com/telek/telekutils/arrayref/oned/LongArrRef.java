package com.telek.telekutils.arrayref.oned;


import java.util.Arrays;

public class LongArrRef implements ArrayRef {

    private long[] array;

    public LongArrRef(long[] array){
        this.array = array;
    }

    public long[] getArray() {
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
        return "LongArrRef{" +
                Arrays.toString(array) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongArrRef that = (LongArrRef) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

}

