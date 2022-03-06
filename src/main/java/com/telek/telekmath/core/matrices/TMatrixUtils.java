package com.telek.telekmath.core.matrices;




public class TMatrixUtils {


    /* No constructor */
    private TMatrixUtils(){}



    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    public static TMatrix createIdentityMatrix(int dimension){
        double[][] newMatrix = new double[dimension][dimension];
        for(int i = 0; i < dimension ; i++){
            for(int j = 0 ; j < dimension ; j++){
                if(i == j) newMatrix[i][j] = 1;
                else newMatrix[i][j] = 0;
            }
        }
        return new TMatrix(newMatrix);
    }



    /**
     * Please look here for more information: <a>https://en.wikipedia.org/wiki/Hilbert_matrix</a>
     * @param dimension the dimension of this Hilbert (also a square) matrix
     * @return a Hilbert matrix
     */
    public static TMatrix createHilbertMatrix(int dimension){
        double[][] newMatrix = new double[dimension][dimension];

        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                newMatrix[i][j] = 1d / (i+j+1);

        return new TMatrix(newMatrix);
    }


    /**
     * Please look here for more information: <a>https://en.wikipedia.org/wiki/Lehmer_matrix</a>
     * @param dimension the dimension of this Lehmer (also a square) matrix
     * @return a Lehmer matrix
     */
    public static TMatrix createLehmerMatrix(int dimension){
        double[][] newMatrix = new double[dimension][dimension];

        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                newMatrix[i][j] = (double) Math.min(i+1, j+1) / (double) Math.max(i+1, j+1);

        return new TMatrix(newMatrix);
    }



    /**
     * @param rowSize the row size of this matrix
     * @param colSize the column size of this matrix
     * @param value any double value
     * @return A matrix filled with the specified value
     */
    public static TMatrix allValues(int rowSize, int colSize, double value){
        double[][] newMatrix = new double[rowSize][colSize];

        for (int i = 0; i < rowSize; i++)
            for (int j = 0; j < colSize; j++)
                newMatrix[i][j] = value;

        return new TMatrix(newMatrix);
    }


    public static TMatrix allValues(int dimension, double value){
        return allValues(dimension, dimension, value);
    }


    // same as ones() in MATLAB
    public static TMatrix allOnes(int rowSize, int colSize){
        return allValues(rowSize, colSize, 1d);
    }

    // same as ones() in MATLAB
    public static TMatrix allOnes(int dimension){
        return allValues(dimension, dimension, 1d);
    }


    // same as zeros() in MATLAB
    public static TMatrix allZeros(int rowSize, int colSize){
        return allValues(rowSize, colSize, 0d);
    }

    // same as zeros() in MATLAB
    public static TMatrix allZeros(int dimension){
        return allValues(dimension, dimension, 0d);
    }




    //////////////////////////////
    /*  SPECIAL MATRIX METHODS  */
    //////////////////////////////



    public static boolean isOrthogonal(TMatrix matrix){
        return matrix.determinant() == 1;
    }

    public static boolean isSingular(TMatrix matrix){
        return matrix.determinant() == 0;
    }

    public static boolean isRegular(TMatrix matrix){
        return matrix.determinant() != 0;
    }


    public static boolean isIdentityMatrix(TMatrix matrix){
        int mColSize = matrix.getColSize();
        int mRowSize = matrix.getRowSize();

        // check for square matrices
        if( mRowSize != mColSize ) return false;

        // Check for zeros
        for(int i = 0; i < mRowSize; i++){
            for(int j = 0; j < mColSize; j++){
                if(i == j) continue;
                else if( matrix.getCell(i,j) != 0d ) return false;
            }
        }

        // Check for ones
        double[] diagonal = matrix.getDiagonal();
        for( double d : diagonal ){
            if(d != 1) return false;
        }

        return true;
    }



    public static boolean isSymmetrical(TMatrix matrix){
        if( matrix.getRowSize() != matrix.getColSize() ) return false;

        return matrix.equals(matrix.copy().transpose());
   }




    ////////////////////////////////
    /*  IMPLEMENTATION FUNCTIONS  */
    ////////////////////////////////


    /**
     * Solves AX = B and returns X matrix. <br>
     * This method will return null if a solution doesn't exist.
     * @param A any square matrix
     * @param B any matrix
     * @return The solution matrix X
     */
    public static TMatrix cramerMethod(TMatrix A, TMatrix B){
        int equationCount = A.getRowSize();
        if( B.getRowSize() != A.getRowSize() || B.getColSize() != 1)
            return null;

        double detA = A.determinant();
        if(detA == 0) return null;

        double[] answers = new double[equationCount];

        for(int i = 0; i < equationCount; i++){
            // GET THE MATRIX
            int mRowSize = A.getRowSize(), mColSize = A.getColSize();
            double[][] newMatrix = new double[mRowSize][mColSize];

            for(int row = 0; row < mRowSize; row++)
                for(int col = 0; col < mColSize; col++)
                    newMatrix[row][col] = (col == i) ? B.getCell(row, 0) : A.getCell(row, col);

            // CALCULATE THE ANSWER
            answers[i] = new TMatrix(newMatrix).determinant() / detA;
        }

        return new TMatrix(new double[][]{ answers }).transpose();
    }




}
