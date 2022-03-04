package com.telek.telekutils.containers.readonly.oned;


public class ReadOnlyLongArr implements TypelessArray {

    private long[] array;

    public ReadOnlyLongArr(long[] array){
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
