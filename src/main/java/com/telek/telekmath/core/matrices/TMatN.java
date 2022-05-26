package com.telek.telekmath.core.matrices;


import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.utils.TelekMathException.*;
import com.telek.telekutils.containers.TArrays;

import java.util.Arrays;
import java.util.Objects;

/**
 * A very slow and mutable NxN double matrix class. <br>
 * If your matrix size is 2, 3 or 4 then do not use this class. <br>
 * This class only exists to be a generic and functional matrix class and is nowhere close to being
 * as fast as {@link TMat2}, {@link TMat3}, {@link TMat4} classes. <br>
 * All methods either return a numeric value or this matrix for method chaining purposes. <br>
 */
public class TMatN {

    private int N;
    private double[][] mat;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    /**  Returns I<sub>N</sub> which is the NxN identity matrix.  */
    public TMatN(int N){
        if(N <= 0)
            throw new NotGreaterThanZeroException("matrix size");

        this.N = N;
        this.mat = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.mat[i][j] = (i == j) ? 1d : 0d;
            }
        }
    }


    /**  Converts a TMat2 object into a TMatN object and returns it.  */
    public TMatN(TMat2 mat){
        this(new double[][]{
                { mat.getCell(0, 0), mat.getCell(0, 1) },
                { mat.getCell(1, 0), mat.getCell(1, 1) }
        });
    }


    /**  Converts a TMat3 object into a TMatN object and returns it.  */
    public TMatN(TMat3 mat){
        this(new double[][]{
                { mat.getCell(0, 0), mat.getCell(0, 1), mat.getCell(0, 2) },
                { mat.getCell(1, 0), mat.getCell(1, 1), mat.getCell(1, 2) },
                { mat.getCell(2, 0), mat.getCell(2, 1), mat.getCell(2, 2) }
        });
    }


    /**  Converts a TMat4 object into a TMatN object and returns it.  */
    public TMatN(TMat4 mat){
        this(new double[][]{
                { mat.getCell(0, 0), mat.getCell(0, 1), mat.getCell(0, 2), mat.getCell(0, 3) },
                { mat.getCell(1, 0), mat.getCell(1, 1), mat.getCell(1, 2), mat.getCell(1, 3) },
                { mat.getCell(2, 0), mat.getCell(2, 1), mat.getCell(2, 2), mat.getCell(2, 3) },
                { mat.getCell(3, 0), mat.getCell(3, 1), mat.getCell(3, 2), mat.getCell(3, 3) }
        });
    }


    /**
     * Copy constructor. <br>
     * You can also use {@link #copy()} to create a copy of this matrix. <br>
     * @param mat any matrix to copy
     */
    public TMatN(TMatN mat){
        this.N = mat.N;
        this.mat = TArrays.getCopyOf(mat.mat);
    }


    /**
     * Creates a TMatN object using the given two dimensional array. <br>
     * A reference to the array will be kept so any methods of this class will modify the array. <br>
     * If you have a two-dimensional array with a different type (float, int, ...) use the
     * {@link TArrays#getCastedDouble2CopyOf(int[][])},
     * {@link TArrays#getCastedDouble2CopyOf(float[][])},
     * {@link TArrays#getCastedDouble2CopyOf(Number[][])}
     * methods to convert your array into a double[][] array.
     * @param matrixData any two dimensional array
     */
    public TMatN(double[][] matrixData){
        for (int i = 0; i < matrixData.length; i++)
            if(matrixData.length != matrixData[i].length)
                throw new NotASquareMatrixException();

        if(matrixData.length == 0)
            throw new InvalidValueException("matrix size", 0);

        this.N = matrixData.length;
        this.mat = matrixData;
    }


    /**
     * Creates a copy of this matrix and returns it. <br>
     * You can also use the copy constructor {@link #TMatN(TMatN)} to create a copy of this matrix. <br>
     * @return a copy of this matrix
     */
    public TMatN copy(){
        return new TMatN(this);
    }


    /////////////////////////
    /*  GETTERS / SETTERS  */
    /////////////////////////


    /**
     * Replaces this matrix's array reference.
     * @param matrixData any square double matrix as a double[][]
     * @return this matrix for method chaining
     */
    public TMatN set(double[][] matrixData){
        for (int i = 0; i < matrixData.length; i++)
            if(matrixData.length != matrixData[i].length)
                throw new NotASquareMatrixException();

        if(matrixData.length == 0)
            throw new InvalidValueException("matrix size", 0);

        this.N = matrixData.length;
        this.mat = matrixData;
        return this;
    }


    /**
     * Sets the value of mat[row][col] if the indexes are valid, if not it just returns the matrix.
     * @param row row index
     * @param col column index
     * @param value value to set
     * @return this matrix for method chaining
     */
    public TMatN setCell(int row, int col, double value){
        if( !(row < 0 || row > N-1 || col < 0 || col > N-1) ) // if it's a valid [row][col]
            this.mat[row][col] = value;
        return this;
    }


    /**
     * @return the dimension of this matrix (NxN)
     */
    public int getN(){
        return this.N;
    }


    /**
     * Returns the value of mat[row][col]
     * @param row row index
     * @param col column index
     * @return the value of mat[row][col] or {@link Double#NaN} if any index is not valid
     */
    public double getCell(int row, int col){
        if( !(row < 0 || row > N-1 || col < 0 || col > N-1) ) // if it's a valid [row][col]
            return this.mat[row][col];
        return Double.NaN;
    }


    /////////////////////////////
    /*  SPECIAL VALUE METHODS  */
    /////////////////////////////


    /**  @return the determinant of this matrix  */
    /*public double determinant(){
        double[][] mat = TArrays.getCopyOf(this.mat);

        double num1, num2, det = 1d, total = 1d;
        int index;

        double[] temp = new double[N + 1];
        for (int i = 0; i < N; i++) {
            index = i;

            while (mat[index][i] == 0 && index < N) {
                index++;
            }
            if (index == N)
                continue;
            if (index != i) {
                for (int j = 0; j < N; j++) {
                    double t = mat[index][j];
                    mat[index][j] = mat[i][j];
                    mat[i][j] = t;
                }
                det *= Math.pow(-1, index - i);
            }

            for (int j = 0; j < N; j++)
                temp[j] = mat[i][j];


            for (int j = i + 1; j < N; j++) {
                num1 = temp[i];
                num2 = mat[j][i];

                for (int k = 0; k < N; k++) {
                    mat[j][k] = (num1 * mat[j][k]) - (num2 * temp[k]);
                }
                total *= num1; // Det(kA)=kDet(A);
            }
        }

        for (int i = 0; i < N; i++) {
            det *= mat[i][i];
        }
        return (det / total);
    }*/
    public double determinant(){
        return determinantOfMatrix(this.mat, N);
    }



    /**  @return the trace (sum of all values on diagonals) of this matrix  */
    public double trace(){
        double sum = 0d;
        for (int i = 0; i < N; i++)
            sum += this.mat[i][i];

        return sum;
    }


    ////////////////////////
    /*  BASIC OPERATIONS  */
    ////////////////////////


    /**
     * If A is this matrix and B is the other matrix then this method does A = A + B. <br>
     * @param other any matrix
     * @return this matrix for method chaining
     */
    public TMatN add(TMatN other){
        this.add(other.mat);
        return this;
    }


    /**
     * If A is this matrix and B is the other matrix then this method does A = A + B. <br>
     * @param other any matrix
     * @return this matrix for method chaining
     */
    public TMatN add(double[][] other){
        for (int i = 0; i < other.length; i++)
            if(other.length != other[i].length)
                throw new NotASquareMatrixException();

        if(other.length == 0)
            throw new InvalidValueException("matrix size", 0);

        if(this.N != other.length)
            throw new DifferentMatrixSizeException();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.mat[i][j] += other[i][j];
            }
        }

        return this;
    }



    /**
     * If A is this matrix and B is the other matrix then this method does A = A - B. <br>
     * @param other any matrix
     * @return this matrix for method chaining
     */
    public TMatN subtract(TMatN other){
        this.subtract(other.mat);
        return this;
    }



    /**
     * If A is this matrix and B is the other matrix then this method does A = A - B. <br>
     * @param other any matrix
     * @return this matrix for method chaining
     */
    public TMatN subtract(double[][] other){
        for (int i = 0; i < other.length; i++)
            if(other.length != other[i].length)
                throw new NotASquareMatrixException();

        if(other.length == 0)
            throw new InvalidValueException("matrix size", 0);

        if(this.N != other.length)
            throw new DifferentMatrixSizeException();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.mat[i][j] -= other[i][j];
            }
        }

        return this;
    }


    /**
     * If A is this matrix and k is the scale value then this method does A = k * A. <br>
     * @param scale any double
     * @return this matrix for method chaining
     */
    public TMatN scale(double scale){
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                this.mat[i][j] *= scale;

        return this;
    }


    /**
     * If A is this matrix and B is the other matrix then this method does A = A * B. <br>
     * @param other any matrix
     * @return this matrix for method chaining
     */
    public TMatN multiply(TMatN other){
        double[][] newMatrix = new double[N][N];
        for(int i = 0; i < N; i++){
            for(int k = 0; k < N; k++){
                double result = 0;
                for(int j = 0; j < N; j++) {
                    result += this.mat[i][j] * other.mat[j][k];
                }
                newMatrix[i][k] = result;
            }
        }

        // slow asf lmao
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                this.mat[i][j] = newMatrix[i][j];

        return this;
    }


    /**
     * Calculates the inverse of this matrix and assigns it to this matrix. <br>
     * Simply does A = A<sup>-1</sup>. <br>
     * This method will return null if the matrix is not invertable (determinant is zero). <br>
     * @return this matrix for method chaining
     */
    public TMatN invert(){
        double det = this.determinant();
        if(TMath.areEqual(det, 0d))
            return null;

        double newMatrix[][] = new double[N][N];
        TMatN b = new TMatN(N);

        int[] index;
        {
            double c[] = new double[N];
            index = new int[N];

            for (int i=0; i<N; ++i)
                index[i] = i;

            for (int i=0; i<N; ++i) {
                double c1 = 0;
                for (int j=0; j<N; ++j) {
                    double c0 = TMath.abs(this.mat[i][j]);
                    if (c0 > c1)
                        c1 = c0;
                }
                c[i] = c1;
            }

            int k = 0;
            for (int j=0; j<N-1; ++j) {
                double pi1 = 0;
                for (int i=j; i<N; ++i) {
                    double pi0 = TMath.abs(this.mat[index[i]][j]);
                    pi0 /= c[index[i]];
                    if (pi0 > pi1) {
                        pi1 = pi0;
                        k = i;
                    }
                }

                int itmp = index[j];
                index[j] = index[k];
                index[k] = itmp;
                for (int i=j+1; i<N; ++i) {
                    double pj = this.mat[index[i]][j]/this.mat[index[j]][j];

                    this.mat[index[i]][j] = pj;

                    for (int l=j+1; l<N; ++l)
                        this.mat[index[i]][l] -= pj*this.mat[index[j]][l];
                }
            }
        }

        for (int i=0; i<N-1; ++i)
            for (int j=i+1; j<N; ++j)
                for (int k=0; k<N; ++k)
                    b.mat[index[j]][k] -= this.mat[index[j]][i]*b.mat[index[i]][k];

        for (int i=0; i<N; ++i) {
            newMatrix[N-1][i] = b.mat[index[N-1]][i]/this.mat[index[N-1]][N-1];
            for (int j=N-2; j>=0; --j) {
                newMatrix[j][i] = b.mat[index[j]][i];
                for (int k=j+1; k<N; ++k)
                    newMatrix[j][i] -= this.mat[index[j]][k]*newMatrix[k][i];
                newMatrix[j][i] /= this.mat[index[j]][j];
            }
        }

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                this.mat[i][j] = newMatrix[i][j];

        return this;
    }



    ////////////////////////////
    /*  GEOMETRIC OPERATIONS  */
    ////////////////////////////



    /**
     * Flips this matrix horizontally. <br>
     * @return this matrix for method chaining
     */
    public TMatN flipHorizontally(){
        int iterCount = N / 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < iterCount; j++) {
                double heldValue = this.getCell(i,j);
                this.mat[i][j] = this.mat[i][N - (j + 1)];
                this.mat[i][N - (j + 1)] = heldValue;
            }
        }

        return this;
    }


    /**
     * Flips this matrix vertically. <br>
     * @return this matrix for method chaining
     */
    public TMatN flipVertically(){
        int iterCount = N / 2;
        for (int i = 0; i < iterCount; i++) {
            for (int j = 0; j < N; j++) {
                double heldValue = this.mat[i][j];
                this.mat[i][j] = this.mat[N - (i + 1)][j];
                this.mat[N - (i + 1)][j] = heldValue;
            }
        }

        return this;
    }


    /**
     * Rotates this matrix 90 degrees clockwise. <br>
     * @return this matrix for method chaining
     */
    public TMatN rotate90DegClockwise(){
        this.transpose();
        this.flipHorizontally();
        return this;
    }


    /**
     * Rotates this matrix 90 degrees anti-clockwise. <br>
     * @return this matrix for method chaining
     */
    public TMatN rotate90DegAntiClockwise(){
        this.transpose();
        this.flipVertically();
        return this;
    }


    /**
     * Rotates this matrix 180 degrees clockwise / anti-clockwise. <br>
     * @return this matrix for method chaining
     */
    public TMatN rotate180Deg(){
        this.rotate90DegClockwise();
        this.rotate90DegClockwise();
        return this;
    }


    /**
     * Calculates the transpose of this matrix and assigns it to this matrix. <br>
     * @return this matrix for method chaining
     */
    public TMatN transpose(){
        double[][] newMatrix = new double[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                newMatrix[i][j] = this.mat[j][i];
            }
        }

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                this.mat[i][j] = newMatrix[i][j];

        return this;
    }




    ////////////////////////////////
    /*   SPECIAL MATRIX METHODS   */
    ////////////////////////////////


    /**  @return true if this matrix's transpose is equal to itself  */
    public boolean isSymmetrical(){
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (mat[i][j] != mat[j][i])
                    return false;
        return true;
    }


    /**  @return true if this matrix's determinant is zero  */
    public boolean isSingular(){
        return TMath.areEqual(this.determinant(), 0d);
    }


    /**  @return true if this matrix is equal to I<sub>N</sub>  */
    public boolean isIdentityMatrix(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if( i == j ){
                    if(!TMath.areEqual(this.mat[i][j], 1d))
                        return false;
                }
                else{
                    if(!TMath.areEqual(this.mat[i][j], 0d))
                        return false;
                }
            }
        }
        return true;
    }



    ////////////////////////
    /*   HELPER METHODS   */
    ////////////////////////


    private double determinantOfMatrix(double matrix[][], int n) {
        double determinant = 0;
        if (n == 1)
            return matrix[0][0];
        if (n == 2)
            return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        double temp[][] = new double[N][N];
        int sign = 1;
        for (int i = 0; i < n; i++) {
            subMatrix(matrix, temp, 0, i, n);
            determinant += sign * matrix[0][i] * determinantOfMatrix(temp, n - 1);
            sign = -sign;
        }
        return determinant;
    }


    private void subMatrix(double mat[][], double temp[][], int p, int q, int n) {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    temp[i][j++] = mat[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }



    ////////////////////////
    /*   OBJECT METHODS   */
    ////////////////////////


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++)
            sb.append(Arrays.toString(mat[i]) + "\n");
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TMatN other = (TMatN) o;
        if (N != other.N)
            return false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!TMath.areEqual(other.mat[i][j], this.mat[i][j]))
                    return false;
            }
        }
        return true;
    }



    @Override
    public int hashCode() {
        int result = Objects.hash(N);
        result = 31 * result + Arrays.hashCode(mat);
        return result;
    }


}
