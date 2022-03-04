package com.telek.telekutils.containers.readonly.oned;


public class ReadOnlyIntArr implements TypelessArray {

    private int[] array;

    public ReadOnlyIntArr(int[] array){
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
