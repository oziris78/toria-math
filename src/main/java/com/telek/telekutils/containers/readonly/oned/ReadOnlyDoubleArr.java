package com.telek.telekutils.containers.readonly.oned;


public class ReadOnlyDoubleArr implements TypelessArray {

    private double[] array;

    public ReadOnlyDoubleArr(double[] array){
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
