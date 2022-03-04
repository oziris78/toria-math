package com.telek.telekutils.containers.readonly.twod;


public class ReadOnlyLongArr2 implements TypelessArray2 {

    private long[][] array;

    public ReadOnlyLongArr2(long[][] array){
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
