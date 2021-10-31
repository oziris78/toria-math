package com.telek.telekmath.core.matrices;

public class TSpecialMatrix {


    private TSpecialMatrix(){}

    /*  CONSTRUCTORS  */


    /**
     * Please look here for more information: <a>https://en.wikipedia.org/wiki/Hilbert_matrix</a>
     * @param dimension the dimension of this Hilbert (also a square) matrix
     * @return a Hilbert matrix
     */
    public static double[][] createHilbertMatrix(int dimension){
        double[][] newMatrix = new double[dimension][dimension];

        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                newMatrix[i][j] = 1d / (i+j+1);

        return newMatrix;
    }


    /**
     * Please look here for more information: <a>https://en.wikipedia.org/wiki/Lehmer_matrix</a>
     * @param dimension the dimension of this Lehmer (also a square) matrix
     * @return a Lehmer matrix
     */
    public static double[][] createLehmerMatrix(int dimension){
        double[][] newMatrix = new double[dimension][dimension];

        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                newMatrix[i][j] = (double) Math.min(i+1, j+1) / (double) Math.max(i+1, j+1);

        return newMatrix;
    }



    /**
     * @param rowSize the row size of this matrix
     * @param colSize the column size of this matrix
     * @param value any double value
     * @return A matrix filled with the specified value
     */
    public static double[][] createMatrixFilledWith(int rowSize, int colSize, double value){
        double[][] newMatrix = new double[rowSize][colSize];

        for (int i = 0; i < rowSize; i++)
            for (int j = 0; j < colSize; j++)
                newMatrix[i][j] = 1;

        return newMatrix;
    }



    /*  METHODS  */

    public static boolean isSquareMatrix(double[][] matrix){
        return (TMatrix.getRowSize(matrix) == TMatrix.getColSize(matrix));
    }

    public static boolean isOrthogonal(double[][] matrix){ return TMatrix.determinant(matrix) == 1; }

    public static boolean isSingular(double[][] matrix){ return TMatrix.determinant(matrix) == 0; }

    public static boolean isRegular(double[][] matrix){ return TMatrix.determinant(matrix) != 0; }


    public static boolean isIdentityMatrix(double[][] matrix){
        int mColSize = TMatrix.getColSize(matrix);
        int mRowSize = TMatrix.getRowSize(matrix);

        // check for square matrices
        if( mRowSize != mColSize ) return false;

        // Check for zeros
        for(int i = 0; i < mRowSize; i++){
            for(int j = 0; j < mColSize; j++){
                if(i == j) continue;
                else if( matrix[i][j] != 0) return false;
            }
        }
        // Check for ones
        double[] diagonal = TMatrix.getDiagonal(matrix);
        for( double d : diagonal ){
            if(d != 1) return false;
        }

        return true;
    }


    /**
     * @param matrix any matrix
     * @return true if A * A^T = A^T * A
     */
    public static boolean isNormal(double[][] matrix){
        if( TMatrix.getRowSize(matrix) != TMatrix.getColSize(matrix) ) return false;
        return TMatrix.areEqual(
                TMatrix.matrixMultiplication(matrix, TMatrix.transpose(matrix) ),
                TMatrix.matrixMultiplication(TMatrix.transpose(matrix), matrix)
        );
    }


    public static boolean isSymmetrical(double[][] matrix){
        if( TMatrix.getRowSize(matrix) != TMatrix.getColSize(matrix) ) return false;
        return TMatrix.areEqual(matrix, TMatrix.transpose(matrix));
    }


    /**
     * @param matrix any matrix
     * @return true if A * A = I_n
     */
    public static boolean isInvolutory(double[][] matrix){
        if( TMatrix.getRowSize(matrix) != TMatrix.getColSize(matrix) ) return false;
        return TMatrix.areEqual( TMatrix.matrixMultiplication(matrix,matrix), TMatrix.createIdentityMatrix(TMatrix.getRowSize(matrix)) );
    }


    /**
     * @param matrix any matrix
     * @return true if A * A = A
     */
    public static boolean isIdempotent(double[][] matrix){
        if( TMatrix.getRowSize(matrix) != TMatrix.getColSize(matrix) ) return false;
        return TMatrix.areEqual( TMatrix.matrixMultiplication(matrix,matrix), matrix );
    }


    /*  HELPERS  */


}
