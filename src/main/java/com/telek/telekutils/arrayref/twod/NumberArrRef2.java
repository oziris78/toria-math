package com.telek.telekutils.arrayref.twod;


public class NumberArrRef2 implements ArrayRef2 {

    private Number[][] array;

    public NumberArrRef2(Number[][] array){
        this.array = array;
    }

    @Override
    public double getValue(int row, int col) {
        return array[row][col].doubleValue();
    }

    @Override
    public int getRowSize() {
        return array.length;
    }

    @Override
    public int getColSize() {
        return array[0].length;
    }
}
