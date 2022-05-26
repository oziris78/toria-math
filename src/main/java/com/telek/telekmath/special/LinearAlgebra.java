package com.telek.telekmath.special;

import com.telek.telekmath.core.matrices.TMatN;

public class LinearAlgebra {



    /**
     * Solves a linear system of equations and returns the solution. <br>
     * This method returns null if there's no solution to this system of linear equations. <br>
     * @param A coefficients matrix, must be a square matrix (NxN)
     * @param B right side matrix, must be Nx1 (column vector)
     * @return the solutions as a row vector {x1, x2, x3, ..., xn}
     */
    public static double[] cramerMethod(double[][] A, double[][] B){
        int equationCount = A.length;
        if( B.length != A.length || B[0].length != 1)
            return null;

        double detA = new TMatN(A).determinant();
        if(detA == 0)
            return null;

        double[] answers = new double[equationCount];

        for(int i = 0; i < equationCount; i++){
            // GET THE MATRIX
            int mRowSize = A.length, mColSize = A.length;
            double[][] newMatrix = new double[mRowSize][mColSize];

            for(int row = 0; row < mRowSize; row++)
                for(int col = 0; col < mColSize; col++)
                    newMatrix[row][col] = (col == i) ? B[row][0] : A[row][col];

            // CALCULATE THE ANSWER
            answers[i] = new TMatN(newMatrix).determinant() / detA;
        }

        return answers;
    }



}
