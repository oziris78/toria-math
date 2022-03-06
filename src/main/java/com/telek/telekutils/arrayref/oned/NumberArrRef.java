package com.telek.telekutils.arrayref.oned;


import java.util.Arrays;

public class NumberArrRef implements ArrayRef {

    private Number[] array;

    public NumberArrRef(Number[] array){
        this.array = array;
    }

    public Number[] getArray() {
        return array;
    }

    @Override
    public double getValue(int index) {
        return array[index].doubleValue();
    }

    @Override
    public int getSize() {
        return array.length;
    }

    ////////////////////////////////////////////////


    @Override
    public String toString() {
        return "NumberArrRef{" +
                Arrays.toString(array) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberArrRef that = (NumberArrRef) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
