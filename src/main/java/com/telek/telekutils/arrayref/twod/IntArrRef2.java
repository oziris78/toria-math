package com.telek.telekutils.arrayref.twod;


public class IntArrRef2 implements ArrayRef2 {

    private int[][] array;

    public IntArrRef2(int[][] array){
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
