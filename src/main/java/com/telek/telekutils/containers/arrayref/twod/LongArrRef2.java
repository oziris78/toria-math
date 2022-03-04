package com.telek.telekutils.containers.arrayref.twod;


public class LongArrRef2 implements ArrayRef2 {

    private long[][] array;

    public LongArrRef2(long[][] array){
        this.array = array;
    }

    @Override
    public double getValue(int row, int col) {
        return array[row][col];
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
