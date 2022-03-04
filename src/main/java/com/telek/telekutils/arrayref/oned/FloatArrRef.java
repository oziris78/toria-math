package com.telek.telekutils.arrayref.oned;


public class FloatArrRef implements ArrayRef {

    private float[] array;

    public FloatArrRef(float[] array){
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
