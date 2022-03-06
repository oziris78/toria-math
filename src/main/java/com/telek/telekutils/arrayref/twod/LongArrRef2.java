package com.telek.telekutils.arrayref.twod;


import java.util.Arrays;

public class LongArrRef2 implements ArrayRef2 {

    private long[][] array;

    public LongArrRef2(long[][] array){
        this.array = array;
    }

    public long[][] getArray() {
        return array;
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

    ////////////////////////////////////////////////


    @Override
    public String toString() {
        return "LongArrRef2{" +
                Arrays.toString(array) +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongArrRef2 that = (LongArrRef2) o;
        if(this.getRowSize() != that.getRowSize())
            return false;
        if(this.getColSize() != that.getColSize())
            return false;

        for (int i = 0; i < getRowSize(); i++) {
            for (int j = 0; j < getColSize(); j++) {
                if(this.getValue(i, j) != that.getValue(i, j))
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
