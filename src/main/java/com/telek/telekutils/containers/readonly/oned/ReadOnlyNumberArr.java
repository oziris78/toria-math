package com.telek.telekutils.containers.readonly.oned;


public class ReadOnlyNumberArr implements TypelessArray {

    private Number[] array;

    public ReadOnlyNumberArr(Number[] array){
        this.array = array;
    }

    @Override
    public double getValue(int index) {
        return array[index].doubleValue();
    }

    @Override
    public int getSize() {
        return array.length;
    }

}
