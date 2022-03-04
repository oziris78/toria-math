package com.telek.telekutils.arrayref.oned;


public class NumberArrRef implements ArrayRef {

    private Number[] array;

    public NumberArrRef(Number[] array){
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
