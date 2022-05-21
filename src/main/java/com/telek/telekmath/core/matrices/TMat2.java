package com.telek.telekmath.core.matrices;


import com.telek.telekmath.core.geometry.vectors.TVec2;
import com.telek.telekmath.utils.TMath;
import java.util.Objects;


/**
 * A fast and mutable 2x2 double matrix class. <br>
 * All methods either return a numeric value or this matrix for method chaining purposes. <br>
 * Also see {@link TMat3}, {@link TMat4},{@link TMat}
 */
public class TMat2 {

    private double m00, m01;   /*    [ m00, m01 ]    */
    private double m10, m11;   /*    [ m10, m11 ]    */

    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    /**
     * Creates a matrix using the specified values
     * @param m00 any double
     * @param m01 any double
     * @param m10 any double
     * @param m11 any double
     */
    public TMat2(double m00, double m01, double m10, double m11){
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
    }


    /**  Returns a I<sub>2</sub> which is the 2x2 identity matrix.  */
    public TMat2(){
        this(
                1d, 0d,
                0d, 1d
        );
    }


    /**
     * Returns a 2x2 matrix where all cells are equal to fillValue
     * @param fillValue any double to set all cells equal to
     */
    public TMat2(double fillValue){
        this(fillValue, fillValue, fillValue, fillValue);
    }


    /**
     * Copy constructor. <br>
     * You can also use {@link #copy()} to create a copy of this matrix. <br>
     * @param mat any matrix to copy
     */
    public TMat2(TMat2 mat){
        this(mat.m00, mat.m01, mat.m10, mat.m11);
    }


    /**
     * Creates a matrix using the column vectors.
     * @param col1 {m00, m10}
     * @param col2 {m01, m11}
     */
    public TMat2(TVec2 col1, TVec2 col2){
        this(col1.x, col2.x, col1.y, col2.y);
    }


    /**
     * Creates a copy of this matrix and returns it. <br>
     * You can also use the copy constructor {@link #TMat2(TMat2)} to create a copy of this matrix. <br>
     * @return a copy of this matrix
     */
    public TMat2 copy(){
        return new TMat2(this);
    }


    /////////////////////////
    /*  GETTERS / SETTERS  */
    /////////////////////////


    /**
     * Sets the value of mat[row][col]
     * @param row 0-based row index
     * @param col 0-based column index
     * @param value any double
     */
    public void setCell(int row, int col, double value){
        if(row == 0 && col == 0) m00 = value;
        else if(row == 0 && col == 1) m01 = value;
        else if(row == 1 && col == 0) m10 = value;
        else if(row == 1 && col == 1) m11 = value;
    }


    /**
     * @param row 0-based row index
     * @param col 0-based column index
     * @return the value of mat[row][col]
     */
    public double getCell(int row, int col){
        if(row == 0 && col == 0) return this.m00;
        else if(row == 0 && col == 1) return this.m01;
        else if(row == 1 && col == 0) return this.m10;
        else if(row == 1 && col == 1) return this.m11;
        else return Double.NaN;
    }


    /////////////////////////////
    /*  SPECIAL VALUE METHODS  */
    /////////////////////////////

    /**  @return the determinant of this matrix  */
    public double determinant(){
        return m00 * m11 - m01 * m10;
    }

    /**  @return the trace (sum of all values on diagonals) of this matrix  */
    public double trace(){
        return m00 + m11;
    }


    ////////////////////////
    /*  BASIC OPERATIONS  */
    ////////////////////////


    /**
     * Adds the parameter matrix to this matrix.
     * @param mat the matrix to add to this matrix
     * @return this matrix for method chaining
     */
    public TMat2 add(TMat2 mat){
        this.m00 += mat.m00;
        this.m01 += mat.m01;
        this.m10 += mat.m10;
        this.m11 += mat.m11;
        return this;
    }


    /**
     * Adds the parameters to this matrix.
     * @param m00 any double
     * @param m01 any double
     * @param m10 any double
     * @param m11 any double
     * @return this matrix for method chaining
     */
    public TMat2 add(double m00, double m01, double m10, double m11){
        this.m00 += m00;
        this.m01 += m01;
        this.m10 += m10;
        this.m11 += m11;
        return this;
    }


    /**
     * Subtracts the parameter matrix to this matrix.
     * @param mat the matrix to add to this matrix
     * @return this matrix for method chaining
     */
    public TMat2 subtract(TMat2 mat){
        this.m00 -= mat.m00;
        this.m01 -= mat.m01;
        this.m10 -= mat.m10;
        this.m11 -= mat.m11;
        return this;
    }


    /**
     * Subtracts the parameters to this matrix.
     * @param m00 any double
     * @param m01 any double
     * @param m10 any double
     * @param m11 any double
     * @return this matrix for method chaining
     */
    public TMat2 subtract(double m00, double m01, double m10, double m11){
        this.m00 -= m00;
        this.m01 -= m01;
        this.m10 -= m10;
        this.m11 -= m11;
        return this;
    }


    /**
     * Scales this matrix by the specified amount.
     * @param scale any double
     * @return this matrix for method chaining
     */
    public TMat2 scale(double scale){
        this.m00 *= scale;
        this.m01 *= scale;
        this.m10 *= scale;
        this.m11 *= scale;
        return this;
    }


    /**
     * Does matrix multiplication for A, B and assigns the result to this matrix. <br>
     * Simply does A = A x B where A is this matrix and B is the parameter matrix.
     * @param mat any matrix
     * @return this matrix for method chaining
     */
    public TMat2 multiply(TMat2 mat){
        double newM00 = this.m00 * mat.m00 + this.m01 * mat.m10;
        double newM01 = this.m00 * mat.m01 + this.m01 * mat.m11;
        double newM10 = this.m10 * mat.m00 + this.m11 * mat.m10;
        double newM11 = this.m10 * mat.m01 + this.m11 * mat.m11;
        this.m00 = newM00;
        this.m01 = newM01;
        this.m10 = newM10;
        this.m11 = newM11;
        return this;
    }


    /**
     * Does matrix multiplication for A, B and assigns the result to this matrix. <br>
     * Simply does A = A x B where A is this matrix and B is the parameters.
     * @param m00 any double
     * @param m01 any double
     * @param m10 any double
     * @param m11 any double
     * @return this matrix for method chaining
     */
    public TMat2 multiply(double m00, double m01, double m10, double m11){
        double newM00 = this.m00 * m00 + this.m01 * m10;
        double newM01 = this.m00 * m01 + this.m01 * m11;
        double newM10 = this.m10 * m00 + this.m11 * m10;
        double newM11 = this.m10 * m01 + this.m11 * m11;
        this.m00 = newM00;
        this.m01 = newM01;
        this.m10 = newM10;
        this.m11 = newM11;
        return this;
    }


    /**
     * Calculates the inverse of this matrix and assigns it to this matrix. <br>
     * Simply does A = A<sup>-1</sup>. <br>
     * This method will return null if the matrix is not invertable (determinant is zero). <br>
     * @return this matrix for method chaining
     */
    public TMat2 invert(){
        double det = this.determinant();
        if(TMath.areEqual(det, 0d))
            return null;

        // make the changes
        double temp = this.m00;
        this.m00 = this.m11;
        this.m11 = temp;
        this.m01 *= -1d;
        this.m10 *= -1d;

        // scale and return
        return this.scale(1d / det);
    }



    ////////////////////////////
    /*  GEOMETRIC OPERATIONS  */
    ////////////////////////////


    /**
     * Flips this matrix horizontally. <br>
     * Example: <br>
     * [1 5]  =>  [5 1]  <br>
     * [7 8]  =>  [8 7]  <br>
     * @return this matrix for method chaining
     */
    public TMat2 flipHorizontally(){
        double temp;
        temp = this.m00;
        this.m00 = this.m01;
        this.m01 = temp;
        temp = this.m10;
        this.m10 = this.m11;
        this.m11 = temp;
        return this;
    }


    /**
     * Flips this matrix vertically. <br>
     * Example: <br>
     * [1 5]  =>  [7 8]  <br>
     * [7 8]  =>  [1 5]  <br>
     * @return this matrix for method chaining
     */
    public TMat2 flipVertically(){
        double temp;
        temp = this.m00;
        this.m00 = this.m10;
        this.m10 = temp;
        temp = this.m01;
        this.m01 = this.m11;
        this.m11 = temp;
        return this;
    }


    /**
     * Rotates this matrix 90 degrees clockwise. <br>
     * Example: <br>
     * [1 5]  =>  [7 1]  <br>
     * [7 8]  =>  [8 5]  <br>
     * @return this matrix for method chaining
     */
    public TMat2 rotate90DegClockwise(){
        double temp;
        temp = this.m10;
        this.m10 = this.m11;
        this.m11 = this.m01;
        this.m01 = this.m00;
        this.m00 = temp;
        return this;
    }


    /**
     * Rotates this matrix 90 degrees anti-clockwise. <br>
     * Example: <br>
     * [1 5]  =>  [5 8]  <br>
     * [7 8]  =>  [1 7]  <br>
     * @return this matrix for method chaining
     */
    public TMat2 rotate90DegAntiClockwise(){
        double temp;
        temp = this.m10;
        this.m10 = this.m00;
        this.m00 = this.m01;
        this.m01 = this.m11;
        this.m11 = temp;
        return this;
    }


    /**
     * Rotates this matrix 180 degrees clockwise / anti-clockwise. <br>
     * Example: <br>
     * [1 5]  =>  [8 7]  <br>
     * [7 8]  =>  [5 1]  <br>
     * @return this matrix for method chaining
     */
    public TMat2 rotate180Deg(){
        double temp = this.m01;
        this.m01 = this.m10;
        this.m10 = temp;
        temp = this.m00;
        this.m00 = this.m11;
        this.m11 = temp;
        return this;
    }


    /**
     * Calculates the transpose of this matrix and assigns it to this matrix. <br>
     * @return this matrix for method chaining
     */
    public TMat2 transpose(){
        double temp = this.m01;
        this.m01 = this.m10;
        this.m10 = temp;
        return this;
    }


    ////////////////////////////////
    /*   SPECIAL MATRIX METHODS   */
    ////////////////////////////////

    /**  @return true if this matrix's transpose is equal to itself  */
    public boolean isSymmetrical(){
        return TMath.areEqual(this.m01, this.m10);
    }


    /**  @return true if this matrix's determinant is zero  */
    public boolean isSingular(){
        return TMath.areEqual(this.determinant(), 0d);
    }


    /**  @return true if this matrix is equal to I<sub>2</sub>  */
    public boolean isIdentityMatrix(){
        return TMath.areEqual(this.m00, 1d) && TMath.areEqual(this.m01, 0d) &&
               TMath.areEqual(this.m10, 0d) && TMath.areEqual(this.m11, 1d);
    }


    ////////////////////////
    /*   OBJECT METHODS   */
    ////////////////////////

    /**  @return the string representation of this matrix with high precision (%.12f)  */
    public String toStringPrecise() {
        return String.format("[%.12f, %.12f]\n[%.12f, %.12f]\n",
                this.m00, this.m01,
                this.m10, this.m11);
    }

    @Override
    public String toString() {
        return String.format("[%f, %f]\n[%f, %f]\n",
                this.m00, this.m01,
                this.m10, this.m11);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TMat2 tMat2 = (TMat2) o;
        return TMath.areEqual(tMat2.m00, m00) &&
                TMath.areEqual(tMat2.m01, m01) &&
                TMath.areEqual(tMat2.m10, m10) &&
                TMath.areEqual(tMat2.m11, m11);
    }


    @Override
    public int hashCode() {
        return Objects.hash(m00, m01, m10, m11);
    }


}
