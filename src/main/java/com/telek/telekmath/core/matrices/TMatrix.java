package com.telek.telekmath.core.matrices;


import com.telek.telekmath.exceptions.TelekMathException;

import static com.telek.telekmath.exceptions.TelekMathException.*;

import java.util.Arrays;


public class TMatrix {


    private TMatrix(){}

    /*  CONSTRUCTORS  */

    public static double[][] createIdentityMatrix(int dimension){
        double[][] newMatrix = new double[dimension][dimension];
        for(int i = 0; i < dimension ; i++){
            for(int j = 0 ; j < dimension ; j++){
                if(i == j) newMatrix[i][j] = 1;
                else newMatrix[i][j] = 0;
            }
        }
        return newMatrix;
    }



    /*  METHODS  */

    public static double[][] add(double[][] matrix1, double[][] matrix2){
        int m1ColSize = getColSize(matrix1);
        int m1RowSize = getRowSize(matrix1);

        if( m1ColSize != getColSize(matrix2) || m1RowSize != getRowSize(matrix2) )
            throw new DifferentMatrixDimensionException();

        double[][] newMatrix = new double[m1RowSize][m1ColSize];
        for(int i = 0; i < m1RowSize; i++)
            for(int j = 0; j < m1ColSize; j++)
                newMatrix[i][j] = matrix1[i][j] + matrix2[i][j];

        return newMatrix;
    }


    public static double[][] subtract(double[][] matrix1, double[][] matrix2){
        int m1ColSize = getColSize(matrix1);
        int m1RowSize = getRowSize(matrix1);

        if( m1ColSize != getColSize(matrix2) || m1RowSize != getRowSize(matrix2) )
            throw new DifferentMatrixDimensionException();

        double[][] newMatrix = new double[m1RowSize][m1ColSize];
        for(int i = 0; i < m1RowSize; i++)
            for(int j = 0; j < m1ColSize; j++)
                newMatrix[i][j] = matrix1[i][j] - matrix2[i][j];

        return newMatrix;
    }


    public static boolean areEqual(double[][] matrix1, double[][] matrix2){
        int m1ColSize = getColSize(matrix1);
        int m1RowSize = getRowSize(matrix1);

        if( m1ColSize != getColSize(matrix2) || m1RowSize != getRowSize(matrix2) )
            return false;

        for(int i = 0; i < m1RowSize; i++)
            for(int j = 0; j < m1ColSize; j++)
                if(matrix1[i][j] != matrix2[i][j])
                    return false;

        return true;
    }


    public static double[][] scale(double[][] matrix, double scaleAmount){
        int m1ColSize = getColSize(matrix);
        int m1RowSize = getRowSize(matrix);

        double[][] newMatrix = new double[m1RowSize][m1ColSize];
        for(int i = 0; i < m1RowSize; i++)
            for(int j = 0; j < m1ColSize; j++)
                newMatrix[i][j] = matrix[i][j] * scaleAmount;

        return newMatrix;
    }


    public static double[][] matrixMultiplication(double[][] matrix1, double[][] matrix2){
        int m1ColSize = getColSize(matrix1);
        int m2ColSize = getColSize(matrix2);
        int m1RowSize = getRowSize(matrix1);
        int m2RowSize = getRowSize(matrix2);

        if(m1ColSize != m2RowSize) throw new MatricesCantBeMultipliedException(m1ColSize, m2RowSize);

        double[][] newMatrix = new double[m1RowSize][m2ColSize];
        for(int i = 0; i < m1RowSize; i++){
            for(int k = 0; k < m2ColSize; k++){
                double result = 0;
                for(int j = 0; j < m2RowSize; j++) {
                    result += matrix1[i][j] * matrix2[j][k];
                }
                newMatrix[i][k] = result;
            }
        }
        return newMatrix;
    }



    public static double[][] transpose(double[][] matrix){
        int mColSize = getColSize(matrix);
        int mRowSize = getRowSize(matrix);

        double[][] newData = new double[mColSize][mRowSize];
        for(int i = 0; i < mColSize; i++){
            for(int j = 0; j < mRowSize; j++){
                newData[i][j] = matrix[j][i];
            }
        }

        return newData;
    }



    public static double[] getDiagonal(double[][] matrix){
        checkForSquareMatrix(matrix);
        int mRowSize = getRowSize(matrix);
        double[] diagonal = new double[mRowSize];
        for(int i = 0; i < mRowSize; i++) {
            diagonal[i] = matrix[i][i];
        }
        return diagonal;
    }



    public static double determinant(double[][] matrix) {
        checkForSquareMatrix(matrix);

        int mColSize = getColSize(matrix);
        int mRowSize = getRowSize(matrix);
        int result = 0, sign = 1;

        double[][] temp = new double[mRowSize-1][mColSize-1];

        if (mRowSize == 1) return matrix[0][0];

        for (int f = 0; f < mRowSize; f++) {
            int i = 0, j = 0;
            for (int row = 0; row < mRowSize; row++) {
                for (int col = 0; col < mRowSize; col++) {
                    if (row != 0 && col != f) {
                        temp[i][j++] = matrix[row][col];
                        if (j == mRowSize - 1) {
                            j = 0;
                            i++;
                        }
                    }
                }
            }
            result += sign * matrix[0][f] * determinant(temp);
            sign = -sign;
        }
        return result;
    }



    public static double trace(double[][] matrix){
        double[] diagonal = getDiagonal(matrix);
        double sum = 0;
        for(double d : diagonal) sum += d;
        return sum;
    }


    public static double[][] powerMatrix(double[][] matrix, int exponential){
        checkForSquareMatrix(matrix);
        if(exponential <= 0) return createIdentityMatrix(getRowSize(matrix));
        else if(exponential == 1) return matrix.clone();
        else return matrixMultiplication(matrix, powerMatrix(matrix, exponential-1));
    }






    /**
     * @param matrix any matrix
     * @return The matrix in a 2D string form
     */
    public static String stringifyMatrix(double[][] matrix) {
        StringBuilder sb = new StringBuilder();
        int matLen = matrix.length;
        for (int i = 0; i < matLen; i++) {
            sb.append(Arrays.toString(matrix[i]));
            if(i+1 != matLen) sb.append("\n");
        }
        return sb.toString();
    }


    public static void printMatrix(double[][] matrix){
        System.out.println(stringifyMatrix(matrix));
    }

    public static void printlnMatrix(double[][] matrix){
        System.out.println(stringifyMatrix(matrix) + "\n");
    }

    public static int getRowSize(double[][] matrix){
        return matrix.length;
    }

    public static int getColSize(double[][] matrix){
        return matrix[0].length;
    }


    /*  HELPERS  */

    private static void checkForSquareMatrix(double[][] matrix){
        if( !TSpecialMatrix.isSquareMatrix(matrix) ) throw new NotASquareMatrixException();
    }



}
