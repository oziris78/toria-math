package com.telek.telekutils.containers.readonly.oned;


public class ReadOnlyFloatArr implements TypelessArray {

    private float[] array;

    public ReadOnlyFloatArr(float[] array){
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
