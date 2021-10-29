package com.telek.telekmath.core.matrices;


import com.telek.telekmath.exceptions.InvalidValueException;
import com.telek.telekmath.exceptions.TelekMathException.InvalidEquationCountException;



public class TMatrixUtils {


    public static double[] cramerMethod(int equationCount, double[][] A, double[][] B){
        if( TMatrix.getRowSize(B) != equationCount || TMatrix.getColSize(B) != 1 || TMatrix.getRowSize(A) != equationCount)
            throw new InvalidEquationCountException();

        double detA = TMatrix.determinant(A);
        if(detA == 0) throw new InvalidValueException("determinant", 0);

        double[] answers = new double[equationCount];

        for(int i = 0; i < equationCount; i++){
            // GET THE MATRIX
            int mRowSize = TMatrix.getRowSize(A), mColSize = TMatrix.getColSize(A);
            double[][] matrix = new double[mRowSize][mColSize];

            for(int row = 0; row < mRowSize; row++)
                for(int col = 0; col < mColSize; col++)
                    matrix[row][col] = (col == i) ? B[row][0] : A[row][col];

            // CALCULATE THE ANSWER
            answers[i] = TMatrix.determinant(matrix) / detA;
        }

        return answers;
    }




}
