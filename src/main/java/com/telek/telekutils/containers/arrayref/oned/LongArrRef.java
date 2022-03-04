package com.telek.telekutils.containers.arrayref.oned;


public class LongArrRef implements ArrayRef {

    private long[] array;

    public LongArrRef(long[] array){
        this.array = array;
    }

    @Override
    public double getValue(int index) {
        return array[index];
    }

    @Override
    public int getSize() {
        return array.length;
    }

}
