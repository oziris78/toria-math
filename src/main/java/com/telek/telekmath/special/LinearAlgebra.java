package com.telek.telekmath.special;

import com.telek.telekmath.core.matrices.TMatrix;
import com.telek.telekmath.exceptions.InvalidValueException;
import static com.telek.telekmath.exceptions.TelekMathException.*;


public class LinearAlgebra {


    public static double[] cramerMethod(int equationCount, double[][] A, double[][] B){
        if( TMatrix.getRowSize(B) != equationCount || TMatrix.getColSize(B) != 1 || TMatrix.getRowSize(A) != equationCount)
            throw new InvalidEquationCountException();

        double detA = TMatrix.determinant(A);
        if(detA == 0) throw new InvalidValueException("determinant", 0);

        double[] answers = new double[equationCount];

        for(int i = 0; i < equationCount; i++)
            answers[i] = TMatrix.determinant(getMatrixForCramer(i, A, B)) / detA;

        return answers;
    }




    /*  HELPERS  */

    private static double[][] getMatrixForCramer(int colToChange, double[][] matrixToChange, double[][] matrixToPutIn){
        int mRowSize = TMatrix.getRowSize(matrixToChange);
        int mColSize = TMatrix.getColSize(matrixToChange);
        double[][] newMatrix = new double[mRowSize][mColSize];

        for(int i = 0; i < mRowSize; i++)
            for(int j = 0; j < mColSize; j++)
                newMatrix[i][j] = (j == colToChange) ? matrixToPutIn[i][0] : matrixToChange[i][j];

        return newMatrix;
    }


}
