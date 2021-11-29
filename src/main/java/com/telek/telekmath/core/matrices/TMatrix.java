package com.telek.telekmath.core.matrices;


import com.telek.telekutils.plain.TCollections;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.IntHolder;

import static com.telek.telekmath.exceptions.TelekMathException.*;

import java.util.Arrays;


public class TMatrix {

    private double[][] matrix;
    private int rowSize, colSize;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    public TMatrix(double[][] matrix){
        this.matrix = matrix;

        this.rowSize = matrix.length;
        this.colSize = matrix[0].length;
    }



    ///////////////
    /*  METHODS  */
    ///////////////


    /**
     * Calculates A + B and assigns that to A (this matrix). <br>
     * This method only changes the first matrix.
     * @param matrix2 a second matrix
     * @return this matrix for method chaining
     */
    public TMatrix add(TMatrix matrix2){

        if( this.colSize != matrix2.colSize || this.rowSize != matrix2.rowSize )
            throw new DifferentMatrixDimensionException();

        for(int i = 0; i < this.rowSize; i++)
            for(int j = 0; j < this.colSize; j++)
                this.matrix[i][j] += matrix2.getCell(i,j);

        return this;
    }


    /**
     * Calculates A - B and assigns that to A (this matrix). <br>
     * This method only changes the first matrix.
     * @param matrix2 a second matrix
     * @return this matrix for method chaining
     */
    public TMatrix subtract(TMatrix matrix2){

        if( this.colSize != matrix2.colSize || this.rowSize != matrix2.rowSize )
            throw new DifferentMatrixDimensionException();

        for(int i = 0; i < this.rowSize; i++)
            for(int j = 0; j < this.colSize; j++)
                this.matrix[i][j] -= matrix2.getCell(i,j);

        return this;
    }



    public double getCell(int row, int col){
        return this.matrix[row][col];
    }

    public void setCell(int row, int col, double value){
        this.matrix[row][col] = value;
    }



    /**
     * This method doesn't change any of the matrices.
     * @param matrix2 a second matrix to check A == B
     * @return true if all of the elements of both of these matrices are the same.
     */
    public boolean isEqualTo(TMatrix matrix2){
        if( this.colSize != matrix2.colSize || this.rowSize != matrix2.rowSize )
            return false;

        for(int i = 0; i < this.rowSize; i++)
            for(int j = 0; j < this.colSize; j++)
                if(this.getCell(i,j) != matrix2.getCell(i,j))
                    return false;

        return true;
    }



    /**
     * Multiplies every cell (scales the matrix) with the specified amount.
     * @param scaleAmount any double to multiply every single element with
     * @return this matrix for method chaining
     */
    public TMatrix scale(double scaleAmount){
        for(int i = 0; i < this.rowSize; i++)
            for(int j = 0; j < this.colSize; j++)
                this.matrix[i][j] *= scaleAmount;

        return this;
    }


    /**  @return this matrix for method chaining  */
    public TMatrix transpose(){
        double[][] newData = new double[this.colSize][this.rowSize];
        for(int i = 0; i < this.colSize; i++){
            for(int j = 0; j < this.rowSize; j++){
                newData[i][j] = this.getCell(j,i);
            }
        }

        this.matrix = newData;
        this.rowSize = newData.length;
        this.colSize = newData[0].length;

        return this;
    }



    public double[] getDiagonal(){
        this.checkForSquareMatrix();
        double[] diagonal = new double[this.rowSize];
        for(int i = 0; i < this.rowSize; i++)
            diagonal[i] = this.getCell(i,i);

        return diagonal;
    }


    /**
     * Creates a copy of this matrix and returns it.
     * @return a copy of this matrix
     */
    public TMatrix copy(){
        double[][] newMatrix = new double[this.rowSize][this.colSize];
        for (int i = 0; i < this.rowSize; i++) {
            newMatrix[i] = TCollections.getCopyOf(this.matrix[i]);
        }

        return new TMatrix(newMatrix);
    }



    public double trace(){
        checkForSquareMatrix();
        double sum = 0;
        for (int i = 0; i < this.rowSize; i++)
            sum += this.getCell(i, i);

        return sum;
    }



    public double determinant() {
        checkForSquareMatrix();

        int result = 0, sign = 1;

        double[][] temp = new double[this.rowSize-1][this.colSize-1];

        if (this.rowSize == 1) return matrix[0][0];

        for (int f = 0; f < this.rowSize; f++) {
            int i = 0, j = 0;
            for (int row = 0; row < this.rowSize; row++) {
                for (int col = 0; col < this.rowSize; col++) {
                    if (row != 0 && col != f) {
                        temp[i][j++] = this.getCell(row,col);
                        if (j == this.rowSize - 1) {
                            j = 0;
                            i++;
                        }
                    }
                }
            }
            result += sign * this.getCell(0,f) * new TMatrix(temp).determinant();
            sign = -sign;
        }
        return result;
    }


    /**  @return this matrix for method chaining  */
    public TMatrix rotate90DegClockwise(){
        this.transpose();
        this.flipLeftRight();

        return this;
    }


    /**  @return this matrix for method chaining  */
    public TMatrix flipLeftRight(){
        int iterCount = this.colSize / 2;
        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < iterCount; j++) {
                double heldValue = this.getCell(i,j);
                this.matrix[i][j] = this.matrix[i][this.colSize - (j + 1)];
                this.matrix[i][this.colSize - (j + 1)] = heldValue;
            }
        }

        return this;
    }



    /**
     * Calculates thisMatrix * matrix2 and assigns that to this matrix. <br>
     * This method doesn't change the any of the matrices, it creates a new matrix and returns that.
     * @param matrix2 the second matrix (usually called B in A*B)
     * @return A * B matrix
     */
     public TMatrix matrixMultiplication(TMatrix matrix2){
         int m1RowSize = this.rowSize;
         int m1ColSize = this.colSize;
         int m2RowSize = matrix2.rowSize;
         int m2ColSize = matrix2.colSize;

        if(m1ColSize != m2RowSize) throw new MatricesCantBeMultipliedException(m1ColSize, m2RowSize);

        double[][] newMatrix = new double[m1RowSize][m2ColSize];
        for(int i = 0; i < m1RowSize; i++){
            for(int k = 0; k < m2ColSize; k++){
                double result = 0;
                for(int j = 0; j < m2RowSize; j++) {
                    result += this.getCell(i,j) * matrix2.getCell(j,k);
                }
                newMatrix[i][k] = result;
            }
        }

        return new TMatrix(newMatrix);
    }





    public boolean isSquareMatrix(){
        return this.rowSize == this.colSize;
    }



    ///////////////////////////
    /*  GETTERS AND SETTERS  */
    ///////////////////////////

    public double[][] getMatrix() {
        return matrix;
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }



    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.rowSize; i++)
            sb.append(Arrays.toString(matrix[i]) + "\n");
        return sb.toString();
    }


    ///////////////
    /*  HELPERS  */
    ///////////////


    private void checkForSquareMatrix(){
        if( !this.isSquareMatrix() ) throw new NotASquareMatrixException();
    }



}
