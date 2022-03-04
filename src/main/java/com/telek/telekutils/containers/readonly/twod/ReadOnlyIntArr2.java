package com.telek.telekutils.containers.readonly.twod;


public class ReadOnlyIntArr2 implements TypelessArray2 {

    private int[][] array;

    public ReadOnlyIntArr2(int[][] array){
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
