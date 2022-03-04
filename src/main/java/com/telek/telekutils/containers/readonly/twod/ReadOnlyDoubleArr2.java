package com.telek.telekutils.containers.readonly.twod;


public class ReadOnlyDoubleArr2 implements TypelessArray2 {

    private double[][] array;

    public ReadOnlyDoubleArr2(double[][] array){
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
