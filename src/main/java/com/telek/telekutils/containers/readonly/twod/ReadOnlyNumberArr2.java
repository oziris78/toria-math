package com.telek.telekutils.containers.readonly.twod;


public class ReadOnlyNumberArr2 implements TypelessArray2 {

    private Number[][] array;

    public ReadOnlyNumberArr2(Number[][] array){
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
