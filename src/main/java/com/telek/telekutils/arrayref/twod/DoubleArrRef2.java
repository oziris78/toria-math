package com.telek.telekutils.arrayref.twod;


public class DoubleArrRef2 implements ArrayRef2 {

    private double[][] array;

    public DoubleArrRef2(double[][] array){
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
