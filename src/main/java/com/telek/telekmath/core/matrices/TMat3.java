package com.telek.telekmath.core.matrices;


import com.telek.telekmath.core.geometry.vectors.TVec3;
import com.telek.telekmath.utils.TMath;
import java.util.Objects;



/**
 * A fast and mutable 3x3 double matrix class. <br>
 * All methods either return a numeric value or this matrix for method chaining purposes. <br>
 * Also see {@link TMat2}, {@link TMat4}, {@link TMatN}
 */
public class TMat3 {
    private double m00, m01, m02;   /*    [ m00, m01, m02 ]    */
    private double m10, m11, m12;   /*    [ m10, m11, m12 ]    */
    private double m20, m21, m22;   /*    [ m20, m21, m22 ]    */


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    /**  Returns a I<sub>3</sub> which is the 3x3 identity matrix.  */
    public TMat3(){
        this(
                1d, 0d, 0d,
                0d, 1d, 0d,
                0d, 0d, 1d
        );
    }


    /**
     * Returns a 3x3 matrix where all cells are equal to fillValue
     * @param fillValue any double to set all cells equal to
     */
    public TMat3(double fillValue){
        this(
                fillValue, fillValue, fillValue,
                fillValue, fillValue, fillValue,
                fillValue, fillValue, fillValue
        );
    }


    /**
     * Copy constructor. <br>
     * You can also use {@link #copy()} to create a copy of this matrix. <br>
     * @param mat any matrix to copy
     */
    public TMat3(TMat3 mat){
        this(
            mat.m00, mat.m01, mat.m02,
            mat.m10, mat.m11, mat.m12,
            mat.m20, mat.m21, mat.m22
        );
    }


    /**
     * Creates a matrix using the column vectors.
     * @param col1 {m00, m10, m20}
     * @param col2 {m01, m11, m21}
     * @param col3 {m02, m12, m22}
     */
    public TMat3(TVec3 col1, TVec3 col2, TVec3 col3){
        this(
            col1.getX(), col2.getX(), col3.getX(),
            col1.getY(), col2.getY(), col3.getY(),
            col1.getZ(), col2.getZ(), col3.getZ()
        );
    }


    /**
     * Creates a matrix using the specified values
     * @param m00 any double
     * @param m01 any double
     * @param m02 any double
     * @param m10 any double
     * @param m11 any double
     * @param m12 any double
     * @param m20 any double
     * @param m21 any double
     * @param m22 any double
     */
    public TMat3(double m00, double m01, double m02,
                 double m10, double m11, double m12,
                 double m20, double m21, double m22)
    {
        this.m00 = m00; this.m01 = m01; this.m02 = m02;
        this.m10 = m10; this.m11 = m11; this.m12 = m12;
        this.m20 = m20; this.m21 = m21; this.m22 = m22;
    }


    /**
     * Creates a copy of this matrix and returns it. <br>
     * You can also use the copy constructor {@link #TMat3(TMat3)} to create a copy of this matrix. <br>
     * @return a copy of this matrix
     */
    public TMat3 copy(){
        return new TMat3(this);
    }


    /////////////////////////
    /*  GETTERS / SETTERS  */
    /////////////////////////


    /**
     * Sets all of the values of this matrix.
     * @param m00 any double
     * @param m01 any double
     * @param m02 any double
     * @param m10 any double
     * @param m11 any double
     * @param m12 any double
     * @param m20 any double
     * @param m21 any double
     * @param m22 any doubleq
     */
    public TMat3 set(double m00, double m01, double m02,
                     double m10, double m11, double m12,
                     double m20, double m21, double m22)
    {
        this.m00 = m00; this.m01 = m01; this.m02 = m02;
        this.m10 = m10; this.m11 = m11; this.m12 = m12;
        this.m20 = m20; this.m21 = m21; this.m22 = m22;
        return this;
    }


    /**
     * Sets the value of mat[row][col]
     * @param row 0-based row index
     * @param col 0-based column index
     * @param value any double
     * @return this matrix for method chaining
     */
    public TMat3 setCell(int row, int col, double value){
        if(row == 0 && col == 0) m00 = value;
        else if(row == 0 && col == 1) m01 = value;
        else if(row == 0 && col == 2) m02 = value;
        else if(row == 1 && col == 0) m10 = value;
        else if(row == 1 && col == 1) m11 = value;
        else if(row == 1 && col == 2) m12 = value;
        else if(row == 2 && col == 0) m20 = value;
        else if(row == 2 && col == 1) m21 = value;
        else if(row == 2 && col == 2) m22 = value;
        return this;
    }


    /**
     * @param row 0-based row index
     * @param col 0-based column index
     * @return the value of mat[row][col]
     */
    public double getCell(int row, int col){
        if(row == 0){
            if(col == 0) return this.m00;
            else if(col == 1) return this.m01;
            else if(col == 2) return this.m02;
        }
        else if(row == 1){
            if(col == 0) return this.m10;
            else if(col == 1) return this.m11;
            else if(col == 2) return this.m12;
        }
        else if(row == 2){
            if(col == 0) return this.m20;
            else if(col == 1) return this.m21;
            else if(col == 2) return this.m22;
        }
        return Double.NaN;
    }


    /////////////////////////////
    /*  SPECIAL VALUE METHODS  */
    /////////////////////////////


    /**  @return the determinant of this matrix  */
    public double determinant(){
        return ( (m00 * m11 * m22) + (m10 * m21 * m02) + (m20 * m01 * m12) ) -
               ( (m20 * m11 * m02) + (m12 * m21 * m00) + (m10 * m01 * m22) );
    }


    /**  @return the trace (sum of all values on diagonals) of this matrix  */
    public double trace(){
        return m00 + m11 + m22;
    }


    ////////////////////////
    /*  BASIC OPERATIONS  */
    ////////////////////////


    /**
     * Adds the parameter matrix to this matrix.
     * @param mat the matrix to add to this matrix
     * @return this matrix for method chaining
     */
    public TMat3 add(TMat3 mat){
        this.m00 += mat.m00;
        this.m01 += mat.m01;
        this.m02 += mat.m02;
        this.m10 += mat.m10;
        this.m11 += mat.m11;
        this.m12 += mat.m12;
        this.m20 += mat.m20;
        this.m21 += mat.m21;
        this.m22 += mat.m22;
        return this;
    }


    /**
     * Adds the parameters to this matrix.
     * @param m00 any double
     * @param m01 any double
     * @param m02 any double
     * @param m10 any double
     * @param m11 any double
     * @param m12 any double
     * @param m20 any double
     * @param m21 any double
     * @param m22 any double
     * @return this matrix for method chaining
     */
    public TMat3 add(double m00, double m01, double m02,
                     double m10, double m11, double m12,
                     double m20, double m21, double m22)
    {
        this.m00 += m00;
        this.m01 += m01;
        this.m02 += m02;
        this.m10 += m10;
        this.m11 += m11;
        this.m12 += m12;
        this.m20 += m20;
        this.m21 += m21;
        this.m22 += m22;
        return this;
    }


    /**
     * Subtracts the parameter matrix to this matrix.
     * @param mat the matrix to add to this matrix
     * @return this matrix for method chaining
     */
    public TMat3 subtract(TMat3 mat){
        this.m00 -= mat.m00;
        this.m01 -= mat.m01;
        this.m02 -= mat.m02;
        this.m10 -= mat.m10;
        this.m11 -= mat.m11;
        this.m12 -= mat.m12;
        this.m20 -= mat.m20;
        this.m21 -= mat.m21;
        this.m22 -= mat.m22;
        return this;
    }


    /**
     * Subtracts the parameters to this matrix.
     * @param m00 any double
     * @param m01 any double
     * @param m02 any double
     * @param m10 any double
     * @param m11 any double
     * @param m12 any double
     * @param m20 any double
     * @param m21 any double
     * @param m22 any double
     * @return this matrix for method chaining
     */
    public TMat3 subtract(double m00, double m01, double m02,
                          double m10, double m11, double m12,
                          double m20, double m21, double m22)
    {
        this.m00 -= m00;
        this.m01 -= m01;
        this.m02 -= m02;
        this.m10 -= m10;
        this.m11 -= m11;
        this.m12 -= m12;
        this.m20 -= m20;
        this.m21 -= m21;
        this.m22 -= m22;
        return this;
    }


    /**
     * Scales this matrix by the specified amount.
     * @param scale any double
     * @return this matrix for method chaining
     */
    public TMat3 scale(double scale){
        this.m00 *= scale;
        this.m01 *= scale;
        this.m02 *= scale;
        this.m10 *= scale;
        this.m11 *= scale;
        this.m12 *= scale;
        this.m20 *= scale;
        this.m21 *= scale;
        this.m22 *= scale;
        return this;
    }


    /**
     * Does matrix multiplication for A, B and assigns the result to this matrix. <br>
     * Simply does A = A x B where A is this matrix and B is the parameter matrix.
     * @param mat any matrix
     * @return this matrix for method chaining
     */
    public TMat3 multiply(TMat3 mat){
        double newM00 = this.m00 * mat.m00 + this.m01 * mat.m10 + this.m02 * mat.m20;
        double newM01 = this.m00 * mat.m01 + this.m01 * mat.m11 + this.m02 * mat.m21;
        double newM02 = this.m00 * mat.m02 + this.m01 * mat.m12 + this.m02 * mat.m22;
        double newM10 = this.m10 * mat.m00 + this.m11 * mat.m10 + this.m12 * mat.m20;
        double newM11 = this.m10 * mat.m01 + this.m11 * mat.m11 + this.m12 * mat.m21;
        double newM12 = this.m10 * mat.m02 + this.m11 * mat.m12 + this.m12 * mat.m22;
        double newM20 = this.m20 * mat.m00 + this.m21 * mat.m10 + this.m22 * mat.m20;
        double newM21 = this.m20 * mat.m01 + this.m21 * mat.m11 + this.m22 * mat.m21;
        double newM22 = this.m20 * mat.m02 + this.m21 * mat.m12 + this.m22 * mat.m22;

        this.m00 = newM00;
        this.m01 = newM01;
        this.m02 = newM02;
        this.m10 = newM10;
        this.m11 = newM11;
        this.m12 = newM12;
        this.m20 = newM20;
        this.m21 = newM21;
        this.m22 = newM22;

        return this;
    }


    /**
     * Does matrix multiplication for A, B and assigns the result to this matrix. <br>
     * Simply does A = A x B where A is this matrix and B is the parameters.
     * @param m00 any double
     * @param m01 any double
     * @param m02 any double
     * @param m10 any double
     * @param m11 any double
     * @param m12 any double
     * @param m20 any double
     * @param m21 any double
     * @param m22 any double
     * @return this matrix for method chaining
     */
    public TMat3 multiply(double m00, double m01, double m02,
                          double m10, double m11, double m12,
                          double m20, double m21, double m22)
    {
        double newM00 = this.m00 * m00 + this.m01 * m10 + this.m02 * m20;
        double newM01 = this.m00 * m01 + this.m01 * m11 + this.m02 * m21;
        double newM02 = this.m00 * m02 + this.m01 * m12 + this.m02 * m22;
        double newM10 = this.m10 * m00 + this.m11 * m10 + this.m12 * m20;
        double newM11 = this.m10 * m01 + this.m11 * m11 + this.m12 * m21;
        double newM12 = this.m10 * m02 + this.m11 * m12 + this.m12 * m22;
        double newM20 = this.m20 * m00 + this.m21 * m10 + this.m22 * m20;
        double newM21 = this.m20 * m01 + this.m21 * m11 + this.m22 * m21;
        double newM22 = this.m20 * m02 + this.m21 * m12 + this.m22 * m22;

        this.m00 = newM00;
        this.m01 = newM01;
        this.m02 = newM02;
        this.m10 = newM10;
        this.m11 = newM11;
        this.m12 = newM12;
        this.m20 = newM20;
        this.m21 = newM21;
        this.m22 = newM22;

        return this;
    }


    /**
     * Calculates the inverse of this matrix and assigns it to this matrix. <br>
     * Simply does A = A<sup>-1</sup>. <br>
     * This method will return null if the matrix is not invertable (determinant is zero). <br>
     * @return this matrix for method chaining
     */
    public TMat3 invert(){
        double det = this.determinant();
        if(TMath.areEqual(det, 0d))
            return null;

        this.set(
            (this.m11 * this.m22 - this.m12 * this.m21) / det,
            (this.m02 * this.m21 - this.m01 * this.m22) / det,
            (this.m01 * this.m12 - this.m02 * this.m11) / det,
            (this.m12 * this.m20 - this.m10 * this.m22) / det,
            (this.m00 * this.m22 - this.m02 * this.m20) / det,
            (this.m02 * this.m10 - this.m00 * this.m12) / det,
            (this.m10 * this.m21 - this.m11 * this.m20) / det,
            (this.m01 * this.m20 - this.m00 * this.m21) / det,
            (this.m00 * this.m11 - this.m01 * this.m10) / det
        );

        return this;
    }



    ////////////////////////////
    /*  GEOMETRIC OPERATIONS  */
    ////////////////////////////



    /**
     * Flips this matrix horizontally. <br>
     * Example: <br>
     * [1 2 3]  =>  [3 2 1]  <br>
     * [4 5 6]  =>  [6 5 4]  <br>
     * [7 8 9]  =>  [9 8 7]  <br>
     * @return this matrix for method chaining
     */
    public TMat3 flipHorizontally(){
        double temp;
        temp = this.m00;
        this.m00 = this.m02;
        this.m02 = temp;
        temp = this.m10;
        this.m10 = this.m12;
        this.m12 = temp;
        temp = this.m20;
        this.m20 = this.m22;
        this.m22 = temp;
        return this;
    }


    /**
     * Flips this matrix vertically. <br>
     * Example: <br>
     * [1 2 3]  =>  [7 8 9]  <br>
     * [4 5 6]  =>  [4 5 6]  <br>
     * [7 8 9]  =>  [1 2 3]  <br>
     * @return this matrix for method chaining
     */
    public TMat3 flipVertically(){
        double temp;
        temp = this.m00;
        this.m00 = this.m20;
        this.m20 = temp;
        temp = this.m01;
        this.m01 = this.m21;
        this.m21 = temp;
        temp = this.m02;
        this.m02 = this.m22;
        this.m22 = temp;
        return this;
    }


    /**
     * Rotates this matrix 90 degrees clockwise. <br>
     * Example: <br>
     * [1 2 3]  =>  [7 4 1]  <br>
     * [4 5 6]  =>  [8 5 2]  <br>
     * [7 8 9]  =>  [9 6 3]  <br>
     * @return this matrix for method chaining
     */
    public TMat3 rotate90DegClockwise(){
        double temp;
        temp = this.m20;
        this.m20 = this.m22;
        this.m22 = this.m02;
        this.m02 = this.m00;
        this.m00 = temp;
        temp = this.m01;
        this.m01 = this.m10;
        this.m10 = this.m21;
        this.m21 = this.m12;
        this.m12 = temp;
        return this;
    }


    /**
     * Rotates this matrix 90 degrees anti-clockwise. <br>
     * Example: <br>
     * [1 2 3]  =>  [3 6 9]  <br>
     * [4 5 6]  =>  [2 5 8]  <br>
     * [7 8 9]  =>  [1 4 7]  <br>
     * @return this matrix for method chaining
     */
    public TMat3 rotate90DegAntiClockwise(){
        double temp;
        temp = this.m20;
        this.m20 = this.m00;
        this.m00 = this.m02;
        this.m02 = this.m22;
        this.m22 = temp;
        temp = this.m10;
        this.m10 = this.m01;
        this.m01 = this.m12;
        this.m12 = this.m21;
        this.m21 = temp;
        return this;
    }


    /**
     * Rotates this matrix 180 degrees clockwise / anti-clockwise. <br>
     * Example: <br>
     * [1 2 3]  =>  [9 8 7]  <br>
     * [4 5 6]  =>  [6 5 4]  <br>
     * [7 8 9]  =>  [3 2 1]  <br>
     * @return this matrix for method chaining
     */
    public TMat3 rotate180Deg(){
        double temp;
        temp = this.m10;
        this.m10 = this.m12;
        this.m12 = temp;
        temp = this.m22;
        this.m22 = this.m00;
        this.m00 = temp;
        temp = this.m20;
        this.m20 = this.m02;
        this.m02 = temp;
        temp = this.m21;
        this.m21 = this.m01;
        this.m01 = temp;
        return this;
    }


    /**
     * Calculates the transpose of this matrix and assigns it to this matrix. <br>
     * @return this matrix for method chaining
     */
    public TMat3 transpose(){
        double temp;
        temp = this.m01;
        this.m01 = this.m10;
        this.m10 = temp;
        temp = this.m02;
        this.m02 = this.m20;
        this.m20 = temp;
        temp = this.m12;
        this.m12 = this.m21;
        this.m21 = temp;
        return this;
    }



    ////////////////////////////////
    /*   SPECIAL MATRIX METHODS   */
    ////////////////////////////////


    /**  @return true if this matrix's transpose is equal to itself  */
    public boolean isSymmetrical(){
        return TMath.areEqual(this.m01, this.m10) &&
               TMath.areEqual(this.m02, this.m20) &&
               TMath.areEqual(this.m21, this.m12);
    }


    /**  @return true if this matrix's determinant is zero  */
    public boolean isSingular(){
        return TMath.areEqual(this.determinant(), 0d);
    }


    /**  @return true if this matrix is equal to I<sub>3</sub>  */
    public boolean isIdentityMatrix(){
        return TMath.areEqual(this.m00, 1d) && TMath.areEqual(this.m01, 0d) && TMath.areEqual(this.m02, 0d) &&
               TMath.areEqual(this.m10, 0d) && TMath.areEqual(this.m11, 1d) && TMath.areEqual(this.m12, 0d) &&
               TMath.areEqual(this.m20, 0d) && TMath.areEqual(this.m21, 0d) && TMath.areEqual(this.m22, 1d);
    }



    ////////////////////////
    /*   OBJECT METHODS   */
    ////////////////////////


    /**  @return the string representation of this matrix with high precision (%.12f)  */
    public String toStringPrecise() {
        return String.format("[%.12f, %.12f, %.12f]\n[%.12f, %.12f, %.12f]\n[%.12f, %.12f, %.12f]\n",
                this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22);
    }


    @Override
    public String toString() {
        return String.format("[%f, %f, %f]\n[%f, %f, %f]\n[%f, %f, %f]\n",
                this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TMat3 tMat3 = (TMat3) o;
        return  TMath.areEqual(tMat3.m00, m00) &&
                TMath.areEqual(tMat3.m01, m01) &&
                TMath.areEqual(tMat3.m02, m02) &&
                TMath.areEqual(tMat3.m10, m10) &&
                TMath.areEqual(tMat3.m11, m11) &&
                TMath.areEqual(tMat3.m12, m12) &&
                TMath.areEqual(tMat3.m20, m20) &&
                TMath.areEqual(tMat3.m21, m21) &&
                TMath.areEqual(tMat3.m22, m22);
    }


    @Override
    public int hashCode() {
        return Objects.hash(m00, m01, m02, m10, m11, m12, m20, m21, m22);
    }


}
