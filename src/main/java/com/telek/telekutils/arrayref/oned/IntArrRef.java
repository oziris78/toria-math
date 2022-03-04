package com.telek.telekutils.arrayref.oned;


public class IntArrRef implements ArrayRef {

    private int[] array;

    public IntArrRef(int[] array){
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
