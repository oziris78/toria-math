package com.telek.telekutils.arrayref.oned;


public class DoubleArrRef implements ArrayRef {

    private double[] array;

    public DoubleArrRef(double[] array){
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
