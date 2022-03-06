package com.telek.telekutils.arrayref.twod;


import java.util.Arrays;

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

    ////////////////////////////////////////////////


    @Override
    public String toString() {
        return "IntArrRef2{" +
                Arrays.toString(array) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntArrRef2 that = (IntArrRef2) o;
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
