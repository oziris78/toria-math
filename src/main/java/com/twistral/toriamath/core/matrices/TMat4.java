package com.twistral.toriamath.core.matrices;


import com.twistral.toriamath.utils.TMath;

import java.util.Objects;


/**
 * A fast and mutable 4x4 double matrix class. <br>
 * All methods either return a numeric value or this matrix for method chaining purposes. <br>
 * Also see {@link TMat2}, {@link TMat3}, {@link TMatN}
 */
public class TMat4 {

    private double m00, m01, m02, m03;   /*    [ m00, m01, m02, m03 ]    */
    private double m10, m11, m12, m13;   /*    [ m10, m11, m12, m13 ]    */
    private double m20, m21, m22, m23;   /*    [ m20, m21, m22, m23 ]    */
    private double m30, m31, m32, m33;   /*    [ m30, m31, m32, m33 ]    */


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    /**  Returns a I<sub>4</sub> which is the 4x4 identity matrix.  */
    public TMat4(){
        this(
            1d, 0d, 0d, 0d,
            0d, 1d, 0d, 0d,
            0d, 0d, 1d, 0d,
            0d, 0d, 0d, 1d
        );
    }


    /**
     * Returns a 4x4 matrix where all cells are equal to fillValue
     * @param fillValue any double to set all cells equal to
     */
    public TMat4(double fillValue){
        this(
                fillValue, fillValue, fillValue, fillValue,
                fillValue, fillValue, fillValue, fillValue,
                fillValue, fillValue, fillValue, fillValue,
                fillValue, fillValue, fillValue, fillValue
        );
    }

    /**
     * Copy constructor. <br>
     * You can also use {@link #copy()} to create a copy of this matrix. <br>
     * @param other any matrix to copy
     */
    public TMat4(TMat4 other){
        this(
                other.m00, other.m01, other.m02, other.m03,
                other.m10, other.m11, other.m12, other.m13,
                other.m20, other.m21, other.m22, other.m23,
                other.m30, other.m31, other.m32, other.m33
        );
    }


    /**
     * Creates a matrix using the specified values
     * @param m00 any double
     * @param m01 any double
     * @param m02 any double
     * @param m03 any double
     * @param m10 any double
     * @param m11 any double
     * @param m12 any double
     * @param m13 any double
     * @param m20 any double
     * @param m21 any double
     * @param m22 any double
     * @param m23 any double
     * @param m30 any double
     * @param m31 any double
     * @param m32 any double
     * @param m33 any double
     */
    public TMat4(double m00, double m01, double m02, double m03,
                 double m10, double m11, double m12, double m13,
                 double m20, double m21, double m22, double m23,
                 double m30, double m31, double m32, double m33)
    {
        this.m00 = m00; this.m01 = m01; this.m02 = m02; this.m03 = m03;
        this.m10 = m10; this.m11 = m11; this.m12 = m12; this.m13 = m13;
        this.m20 = m20; this.m21 = m21; this.m22 = m22; this.m23 = m23;
        this.m30 = m30; this.m31 = m31; this.m32 = m32; this.m33 = m33;
    }


    /**
     * Creates a copy of this matrix and returns it. <br>
     * You can also use the copy constructor {@link #TMat4(TMat4)} to create a copy of this matrix. <br>
     * @return a copy of this matrix
     */
    public TMat4 copy(){
        return new TMat4(this);
    }


    /////////////////////////
    /*  GETTERS / SETTERS  */
    /////////////////////////


    /**
     * Sets all of the values of this matrix.
     * @param m00 any double
     * @param m01 any double
     * @param m02 any double
     * @param m03 any double
     * @param m10 any double
     * @param m11 any double
     * @param m12 any double
     * @param m13 any double
     * @param m20 any double
     * @param m21 any double
     * @param m22 any double
     * @param m23 any double
     * @param m30 any double
     * @param m31 any double
     * @param m32 any double
     * @param m33 any double
     * @return this matrix for method chaining
     */
    public TMat4 set(double m00, double m01, double m02, double m03,
                     double m10, double m11, double m12, double m13,
                     double m20, double m21, double m22, double m23,
                     double m30, double m31, double m32, double m33)
    {
        this.m00 = m00; this.m01 = m01; this.m02 = m02; this.m03 = m03;
        this.m10 = m10; this.m11 = m11; this.m12 = m12; this.m13 = m13;
        this.m20 = m20; this.m21 = m21; this.m22 = m22; this.m23 = m23;
        this.m30 = m30; this.m31 = m31; this.m32 = m32; this.m33 = m33;
        return this;
    }


    /**
     * Sets the value of mat[row][col].
     * @param row 0-based row index
     * @param col 0-based column index
     * @param value any double
     * @return this matrix for method chaining
     */
    public TMat4 setCell(int row, int col, double value){
        if(row == 0 && col == 0) m00 = value;
        else if(row == 0 && col == 1) m01 = value;
        else if(row == 0 && col == 2) m02 = value;
        else if(row == 0 && col == 3) m03 = value;
        else if(row == 1 && col == 0) m10 = value;
        else if(row == 1 && col == 1) m11 = value;
        else if(row == 1 && col == 2) m12 = value;
        else if(row == 1 && col == 3) m13 = value;
        else if(row == 2 && col == 0) m20 = value;
        else if(row == 2 && col == 1) m21 = value;
        else if(row == 2 && col == 2) m22 = value;
        else if(row == 2 && col == 3) m23 = value;
        else if(row == 3 && col == 0) m30 = value;
        else if(row == 3 && col == 1) m31 = value;
        else if(row == 3 && col == 2) m32 = value;
        else if(row == 3 && col == 3) m33 = value;
        return this;
    }


    /**
     * @param row 0-based row index
     * @param col 0-based column index
     * @return the value of mat[row][col]
     */
    public double getCell(int row, int col){
        if(row == 0 && col == 0) return this.m00;
        else if(row == 0 && col == 1) return this.m01;
        else if(row == 0 && col == 2) return this.m02;
        else if(row == 0 && col == 3) return this.m03;
        else if(row == 1 && col == 0) return this.m10;
        else if(row == 1 && col == 1) return this.m11;
        else if(row == 1 && col == 2) return this.m12;
        else if(row == 1 && col == 3) return this.m13;
        else if(row == 2 && col == 0) return this.m20;
        else if(row == 2 && col == 1) return this.m21;
        else if(row == 2 && col == 2) return this.m22;
        else if(row == 2 && col == 3) return this.m23;
        else if(row == 3 && col == 0) return this.m30;
        else if(row == 3 && col == 1) return this.m31;
        else if(row == 3 && col == 2) return this.m32;
        else if(row == 3 && col == 3) return this.m33;
        else return Double.NaN;
    }



    /////////////////////////////
    /*  SPECIAL VALUE METHODS  */
    /////////////////////////////


    /**  @return the determinant of this matrix  */
    public double determinant(){
        return m00 * m11 * m22 * m33 + m00 * m12 * m23 * m31 + m00 * m13 * m21 * m32 +
               m03 * m10 * m22 * m31 + m02 * m10 * m21 * m33 + m01 * m10 * m23 * m32 +
               m01 * m12 * m20 * m33 + m02 * m13 * m20 * m31 + m03 * m11 * m20 * m32 +
               m03 * m12 * m21 * m30 + m02 * m11 * m23 * m30 + m01 * m13 * m22 * m30 -
               m00 * m13 * m22 * m31 - m00 * m12 * m21 * m33 - m00 * m11 * m23 * m32 -
               m01 * m10 * m22 * m33 - m02 * m10 * m23 * m31 - m03 * m10 * m21 * m32 -
               m03 * m12 * m20 * m31 - m02 * m11 * m20 * m33 - m01 * m13 * m20 * m32 -
               m01 * m12 * m23 * m30 - m02 * m13 * m21 * m30 - m03 * m11 * m22 * m30;
    }


    /**  @return the trace (sum of all values on diagonals) of this matrix  */
    public double trace(){
        return m00 + m11 + m22 + m33;
    }


    ////////////////////////
    /*  BASIC OPERATIONS  */
    ////////////////////////


    /**
     * Adds the parameter matrix to this matrix.
     * @param mat the matrix to add to this matrix
     * @return this matrix for method chaining
     */
    public TMat4 add(TMat4 mat){
        this.m00 += mat.m00;
        this.m01 += mat.m01;
        this.m02 += mat.m02;
        this.m03 += mat.m03;
        this.m10 += mat.m10;
        this.m11 += mat.m11;
        this.m12 += mat.m12;
        this.m13 += mat.m13;
        this.m20 += mat.m20;
        this.m21 += mat.m21;
        this.m22 += mat.m22;
        this.m23 += mat.m23;
        this.m30 += mat.m30;
        this.m31 += mat.m31;
        this.m32 += mat.m32;
        this.m33 += mat.m33;
        return this;
    }


    /**
     * Adds the parameters to this matrix.
     * @param m00 any double
     * @param m01 any double
     * @param m02 any double
     * @param m03 any double
     * @param m10 any double
     * @param m11 any double
     * @param m12 any double
     * @param m13 any double
     * @param m20 any double
     * @param m21 any double
     * @param m22 any double
     * @param m23 any double
     * @param m30 any double
     * @param m31 any double
     * @param m32 any double
     * @param m33 any double
     * @return this matrix for method chaining
     */
    public TMat4 add(double m00, double m01, double m02, double m03,
                     double m10, double m11, double m12, double m13,
                     double m20, double m21, double m22, double m23,
                     double m30, double m31, double m32, double m33)
    {
        this.m00 += m00;
        this.m01 += m01;
        this.m02 += m02;
        this.m03 += m03;
        this.m10 += m10;
        this.m11 += m11;
        this.m12 += m12;
        this.m13 += m13;
        this.m20 += m20;
        this.m21 += m21;
        this.m22 += m22;
        this.m23 += m23;
        this.m30 += m30;
        this.m31 += m31;
        this.m32 += m32;
        this.m33 += m33;
        return this;
    }


    /**
     * Subtracts the parameter matrix to this matrix.
     * @param mat the matrix to add to this matrix
     * @return this matrix for method chaining
     */
    public TMat4 subtract(TMat4 mat){
        this.m00 -= mat.m00;
        this.m01 -= mat.m01;
        this.m02 -= mat.m02;
        this.m03 -= mat.m03;
        this.m10 -= mat.m10;
        this.m11 -= mat.m11;
        this.m12 -= mat.m12;
        this.m13 -= mat.m13;
        this.m20 -= mat.m20;
        this.m21 -= mat.m21;
        this.m22 -= mat.m22;
        this.m23 -= mat.m23;
        this.m30 -= mat.m30;
        this.m31 -= mat.m31;
        this.m32 -= mat.m32;
        this.m33 -= mat.m33;
        return this;
    }


    /**
     * Subtracts the parameters to this matrix.
     * @param m00 any double
     * @param m01 any double
     * @param m02 any double
     * @param m03 any double
     * @param m10 any double
     * @param m11 any double
     * @param m12 any double
     * @param m13 any double
     * @param m20 any double
     * @param m21 any double
     * @param m22 any double
     * @param m23 any double
     * @param m30 any double
     * @param m31 any double
     * @param m32 any double
     * @param m33 any double
     * @return this matrix for method chaining
     */
    public TMat4 subtract(double m00, double m01, double m02, double m03,
                     double m10, double m11, double m12, double m13,
                     double m20, double m21, double m22, double m23,
                     double m30, double m31, double m32, double m33)
    {
        this.m00 -= m00;
        this.m01 -= m01;
        this.m02 -= m02;
        this.m03 -= m03;
        this.m10 -= m10;
        this.m11 -= m11;
        this.m12 -= m12;
        this.m13 -= m13;
        this.m20 -= m20;
        this.m21 -= m21;
        this.m22 -= m22;
        this.m23 -= m23;
        this.m30 -= m30;
        this.m31 -= m31;
        this.m32 -= m32;
        this.m33 -= m33;
        return this;
    }




    /**
     * Scales this matrix by the specified amount.
     * @param scale any double
     * @return this matrix for method chaining
     */
    public TMat4 scale(double scale){
        this.m00 *= scale;
        this.m01 *= scale;
        this.m02 *= scale;
        this.m03 *= scale;
        this.m10 *= scale;
        this.m11 *= scale;
        this.m12 *= scale;
        this.m13 *= scale;
        this.m20 *= scale;
        this.m21 *= scale;
        this.m22 *= scale;
        this.m23 *= scale;
        this.m30 *= scale;
        this.m31 *= scale;
        this.m32 *= scale;
        this.m33 *= scale;
        return this;
    }




    /**
     * Does matrix multiplication for A, B and assigns the result to this matrix. <br>
     * Simply does A = A x B where A is this matrix and B is the parameter matrix.
     * @param mat any matrix
     * @return this matrix for method chaining
     */

    public TMat4 multiply(TMat4 mat){
        double newM00 = this.m00 * mat.m00 + this.m01 * mat.m10 + this.m02 * mat.m20 + this.m03 * mat.m30;
        double newM01 = this.m00 * mat.m01 + this.m01 * mat.m11 + this.m02 * mat.m21 + this.m03 * mat.m31;
        double newM02 = this.m00 * mat.m02 + this.m01 * mat.m12 + this.m02 * mat.m22 + this.m03 * mat.m32;
        double newM03 = this.m00 * mat.m03 + this.m01 * mat.m13 + this.m02 * mat.m23 + this.m03 * mat.m33;

        double newM10 = this.m10 * mat.m00 + this.m11 * mat.m10 + this.m12 * mat.m20 + this.m13 * mat.m30;
        double newM11 = this.m10 * mat.m01 + this.m11 * mat.m11 + this.m12 * mat.m21 + this.m13 * mat.m31;
        double newM12 = this.m10 * mat.m02 + this.m11 * mat.m12 + this.m12 * mat.m22 + this.m13 * mat.m32;
        double newM13 = this.m10 * mat.m03 + this.m11 * mat.m13 + this.m12 * mat.m23 + this.m13 * mat.m33;

        double newM20 = this.m20 * mat.m00 + this.m21 * mat.m10 + this.m22 * mat.m20 + this.m23 * mat.m30;
        double newM21 = this.m20 * mat.m01 + this.m21 * mat.m11 + this.m22 * mat.m21 + this.m23 * mat.m31;
        double newM22 = this.m20 * mat.m02 + this.m21 * mat.m12 + this.m22 * mat.m22 + this.m23 * mat.m32;
        double newM23 = this.m20 * mat.m03 + this.m21 * mat.m13 + this.m22 * mat.m23 + this.m23 * mat.m33;

        double newM30 = this.m30 * mat.m00 + this.m31 * mat.m10 + this.m32 * mat.m20 + this.m33 * mat.m30;
        double newM31 = this.m30 * mat.m01 + this.m31 * mat.m11 + this.m32 * mat.m21 + this.m33 * mat.m31;
        double newM32 = this.m30 * mat.m02 + this.m31 * mat.m12 + this.m32 * mat.m22 + this.m33 * mat.m32;
        double newM33 = this.m30 * mat.m03 + this.m31 * mat.m13 + this.m32 * mat.m23 + this.m33 * mat.m33;

        this.m00 = newM00;
        this.m01 = newM01;
        this.m02 = newM02;
        this.m03 = newM03;
        this.m10 = newM10;
        this.m11 = newM11;
        this.m12 = newM12;
        this.m13 = newM13;
        this.m20 = newM20;
        this.m21 = newM21;
        this.m22 = newM22;
        this.m23 = newM23;
        this.m30 = newM30;
        this.m31 = newM31;
        this.m32 = newM32;
        this.m33 = newM33;

        return this;
    }



    /**
     * Does matrix multiplication for A, B and assigns the result to this matrix. <br>
     * Simply does A = A x B where A is this matrix and B is the parameters.
     * @param m00 any double
     * @param m01 any double
     * @param m02 any double
     * @param m03 any double
     * @param m10 any double
     * @param m11 any double
     * @param m12 any double
     * @param m13 any double
     * @param m20 any double
     * @param m21 any double
     * @param m22 any double
     * @param m23 any double
     * @param m30 any double
     * @param m31 any double
     * @param m32 any double
     * @param m33 any double
     * @return this matrix for method chaining
     */
    public TMat4 multiply(double m00, double m01, double m02, double m03,
                          double m10, double m11, double m12, double m13,
                          double m20, double m21, double m22, double m23,
                          double m30, double m31, double m32, double m33)
    {
        double newM00 = this.m00 * m00 + this.m01 * m10 + this.m02 * m20 + this.m03 * m30;
        double newM01 = this.m00 * m01 + this.m01 * m11 + this.m02 * m21 + this.m03 * m31;
        double newM02 = this.m00 * m02 + this.m01 * m12 + this.m02 * m22 + this.m03 * m32;
        double newM03 = this.m00 * m03 + this.m01 * m13 + this.m02 * m23 + this.m03 * m33;

        double newM10 = this.m10 * m00 + this.m11 * m10 + this.m12 * m20 + this.m13 * m30;
        double newM11 = this.m10 * m01 + this.m11 * m11 + this.m12 * m21 + this.m13 * m31;
        double newM12 = this.m10 * m02 + this.m11 * m12 + this.m12 * m22 + this.m13 * m32;
        double newM13 = this.m10 * m03 + this.m11 * m13 + this.m12 * m23 + this.m13 * m33;

        double newM20 = this.m20 * m00 + this.m21 * m10 + this.m22 * m20 + this.m23 * m30;
        double newM21 = this.m20 * m01 + this.m21 * m11 + this.m22 * m21 + this.m23 * m31;
        double newM22 = this.m20 * m02 + this.m21 * m12 + this.m22 * m22 + this.m23 * m32;
        double newM23 = this.m20 * m03 + this.m21 * m13 + this.m22 * m23 + this.m23 * m33;

        double newM30 = this.m30 * m00 + this.m31 * m10 + this.m32 * m20 + this.m33 * m30;
        double newM31 = this.m30 * m01 + this.m31 * m11 + this.m32 * m21 + this.m33 * m31;
        double newM32 = this.m30 * m02 + this.m31 * m12 + this.m32 * m22 + this.m33 * m32;
        double newM33 = this.m30 * m03 + this.m31 * m13 + this.m32 * m23 + this.m33 * m33;

        this.m00 = newM00;
        this.m01 = newM01;
        this.m02 = newM02;
        this.m03 = newM03;
        this.m10 = newM10;
        this.m11 = newM11;
        this.m12 = newM12;
        this.m13 = newM13;
        this.m20 = newM20;
        this.m21 = newM21;
        this.m22 = newM22;
        this.m23 = newM23;
        this.m30 = newM30;
        this.m31 = newM31;
        this.m32 = newM32;
        this.m33 = newM33;

        return this;
    }




    /**
     * Calculates the inverse of this matrix and assigns it to this matrix. <br>
     * Simply does A = A<sup>-1</sup>. <br>
     * This method will return null if the matrix is not invertable (determinant is zero). <br>
     * @return this matrix for method chaining
     */
    public TMat4 invert(){
        double det = this.determinant();
        if(TMath.areEqual(det, 0d))
            return null;

        this.set( // definitely first try ;)
            (m11 * m22 * m33 + m12 * m23 * m31 + m13 * m21 * m32 - m13 * m22 * m31 - m12 * m21 * m33 - m11 * m23 * m32) / det,
            (m03 * m22 * m31 + m02 * m21 * m33 + m01 * m23 * m32 - m01 * m22 * m33 - m02 * m23 * m31 - m03 * m21 * m32) / det,
            (m01 * m12 * m33 + m02 * m13 * m31 + m03 * m11 * m32 - m03 * m12 * m31 - m02 * m11 * m33 - m01 * m13 * m32) / det,
            (m03 * m12 * m21 + m02 * m11 * m23 + m01 * m13 * m22 - m01 * m12 * m23 - m02 * m13 * m21 - m03 * m11 * m22) / det,
            (m13 * m22 * m30 + m12 * m20 * m33 + m10 * m23 * m32 - m10 * m22 * m33 - m12 * m23 * m30 - m13 * m20 * m32) / det,
            (m00 * m22 * m33 + m02 * m23 * m30 + m03 * m20 * m32 - m03 * m22 * m30 - m02 * m20 * m33 - m00 * m23 * m32) / det,
            (m03 * m12 * m30 + m02 * m10 * m33 + m00 * m13 * m32 - m00 * m12 * m33 - m02 * m13 * m30 - m03 * m10 * m32) / det,
            (m00 * m12 * m23 + m02 * m13 * m20 + m03 * m10 * m22 - m03 * m12 * m20 - m02 * m10 * m23 - m00 * m13 * m22) / det,
            (m10 * m21 * m33 + m11 * m23 * m30 + m13 * m20 * m31 - m13 * m21 * m30 - m11 * m20 * m33 - m10 * m23 * m31) / det,
            (m03 * m21 * m30 + m01 * m20 * m33 + m00 * m23 * m31 - m00 * m21 * m33 - m01 * m23 * m30 - m03 * m20 * m31) / det,
            (m00 * m11 * m33 + m01 * m13 * m30 + m03 * m10 * m31 - m03 * m11 * m30 - m01 * m10 * m33 - m00 * m13 * m31) / det,
            (m03 * m11 * m20 + m01 * m10 * m23 + m00 * m13 * m21 - m00 * m11 * m23 - m01 * m13 * m20 - m03 * m10 * m21) / det,
            (m12 * m21 * m30 + m11 * m20 * m32 + m10 * m22 * m31 - m10 * m21 * m32 - m11 * m22 * m30 - m12 * m20 * m31) / det,
            (m00 * m21 * m32 + m01 * m22 * m30 + m02 * m20 * m31 - m02 * m21 * m30 - m01 * m20 * m32 - m00 * m22 * m31) / det,
            (m02 * m11 * m30 + m01 * m10 * m32 + m00 * m12 * m31 - m00 * m11 * m32 - m01 * m12 * m30 - m02 * m10 * m31) / det,
            (m00 * m11 * m22 + m01 * m12 * m20 + m02 * m10 * m21 - m02 * m11 * m20 - m01 * m10 * m22 - m00 * m12 * m21) / det
        );

        return this;
    }



    ////////////////////////////
    /*  GEOMETRIC OPERATIONS  */
    ////////////////////////////



    /**
     * Flips this matrix horizontally. <br>
     * Example: <br>
     * [10 11 12 13]   =>   [13 12 11 10]  <br>
     * [14 15 16 17]   =>   [17 16 15 14]  <br>
     * [18 19 20 21]   =>   [21 20 19 18]  <br>
     * [22 23 24 25]   =>   [25 24 23 22]  <br>
     * @return this matrix for method chaining
     */
    public TMat4 flipHorizontally(){
        double temp;

        temp = m00;
        m00 = m03;
        m03 = temp;

        temp = m10;
        m10 = m13;
        m13 = temp;

        temp = m20;
        m20 = m23;
        m23 = temp;

        temp = m30;
        m30 = m33;
        m33 = temp;

        temp = m01;
        m01 = m02;
        m02= temp;

        temp = m21;
        m21 = m22;
        m22 = temp;

        temp = m11;
        m11 = m12;
        m12 = temp;

        temp = m31;
        m31 = m32;
        m32 = temp;

        return this;
    }



    /**
     * Flips this matrix vertically. <br>
     * Example: <br>
     * [10 11 12 13]   =>   [22 23 24 25]  <br>
     * [14 15 16 17]   =>   [18 19 20 21]  <br>
     * [18 19 20 21]   =>   [14 15 16 17]  <br>
     * [22 23 24 25]   =>   [10 11 12 13]  <br>
     * @return this matrix for method chaining
     */
    public TMat4 flipVertically(){
        double temp;

        temp = m20;
        m20 = m10;
        m10 = temp;

        temp = m11;
        m11 = m21;
        m21 = temp;

        temp = m12;
        m12 = m22;
        m22 = temp;

        temp = m23;
        m23 = m13;
        m13 = temp;

        temp = m00;
        m00 = m30;
        m30 = temp;

        temp = m01;
        m01 = m31;
        m31 = temp;

        temp = m02;
        m02 = m32;
        m32 = temp;

        temp = m03;
        m03 = m33;
        m33 = temp;

        return this;
    }



    /**
     * Rotates this matrix 90 degrees clockwise. <br>
     * Example: <br>
     * [10 11 12 13]   =>   [22 18 14 10]  <br>
     * [14 15 16 17]   =>   [23 19 15 11]  <br>
     * [18 19 20 21]   =>   [24 20 16 12]  <br>
     * [22 23 24 25]   =>   [25 21 17 13]  <br>
     * @return this matrix for method chaining
     */
    public TMat4 rotate90DegClockwise(){
        double temp;

        temp = m12;
        m12 = m11;
        m11 = m21;
        m21 = m22;
        m22 = temp;

        temp = m02;
        m02 = m10;
        m10 = m31;
        m31 = m23;
        m23 = temp;

        temp = m01;
        m01 = m20;
        m20 = m32;
        m32 = m13;
        m13 = temp;

        temp = m03;
        m03 = m00;
        m00 = m30;
        m30 = m33;
        m33 = temp;

        return this;
    }



    /**
     * Rotates this matrix 90 degrees anti-clockwise. <br>
     * Example: <br>
     * [10 11 12 13]   =>   [13 17 21 25]  <br>
     * [14 15 16 17]   =>   [12 16 20 24]  <br>
     * [18 19 20 21]   =>   [11 15 19 23]  <br>
     * [22 23 24 25]   =>   [10 14 18 22]  <br>
     * @return this matrix for method chaining
     */
    public TMat4 rotate90DegAntiClockwise(){
        double temp;

        temp = m11;
        m11 = m12;
        m12 = m22;
        m22 = m21;
        m21 = temp;

        temp = m10;
        m10 = m02;
        m02 = m23;
        m23 = m31;
        m31 = temp;

        temp = m00;
        m00 = m03;
        m03 = m33;
        m33 = m30;
        m30 = temp;

        temp = m01;
        m01 = m13;
        m13 = m32;
        m32 = m20;
        m20 = temp;

        return this;
    }



    /**
     * Rotates this matrix 180 degrees clockwise / anti-clockwise. <br>
     * Example: <br>
     * [10 11 12 13]   =>   [25 24 23 22]  <br>
     * [14 15 16 17]   =>   [21 20 19 18]  <br>
     * [18 19 20 21]   =>   [17 16 15 14]  <br>
     * [22 23 24 25]   =>   [13 12 11 10]  <br>
     * @return this matrix for method chaining
     */
    public TMat4 rotate180Deg(){
        double temp;

        temp = m00;
        m00 = m33;
        m33 = temp;

        temp = m10;
        m10 = m23;
        m23 = temp;

        temp = m30;
        m30 = m03;
        m03 = temp;

        temp = m31;
        m31 = m02;
        m02 = temp;

        temp = m32;
        m32 = m01;
        m01 = temp;

        temp = m20;
        m20 = m13;
        m13 = temp;

        temp = m21;
        m21 = m12;
        m12 = temp;

        temp = m22;
        m22 = m11;
        m11 = temp;

        return this;
    }



    /**
     * Calculates the transpose of this matrix and assigns it to this matrix. <br>
     * @return this matrix for method chaining
     */
    public TMat4 transpose(){
        double temp;

        temp = this.m01;
        this.m01 = this.m10;
        this.m10 = temp;

        temp = this.m02;
        this.m02 = this.m20;
        this.m20 = temp;

        temp = this.m21;
        this.m21 = this.m12;
        this.m12 = temp;

        temp = this.m03;
        this.m03 = this.m30;
        this.m30 = temp;

        temp = this.m23;
        this.m23 = this.m32;
        this.m32 = temp;

        temp = this.m13;
        this.m13 = this.m31;
        this.m31 = temp;

        return this;
    }




    ////////////////////////////////
    /*   SPECIAL MATRIX METHODS   */
    ////////////////////////////////


    /**  @return true if this matrix's transpose is equal to itself  */
    public boolean isSymmetrical(){
        return TMath.areEqual(this.m01, this.m10) &&
                TMath.areEqual(this.m02, this.m20) &&
                TMath.areEqual(this.m21, this.m12) &&
                TMath.areEqual(this.m30, this.m03) &&
                TMath.areEqual(this.m23, this.m32) &&
                TMath.areEqual(this.m13, this.m31);
    }


    /**  @return true if this matrix's determinant is zero  */
    public boolean isSingular(){
        return TMath.areEqual(this.determinant(), 0d);
    }


    /**  @return true if this matrix is equal to I<sub>4</sub>  */
    public boolean isIdentityMatrix(){
        return  TMath.areEqual(m00, 1d) && TMath.areEqual(m01, 0d) && TMath.areEqual(m02, 0d) && TMath.areEqual(m03, 0d) &&
                TMath.areEqual(m10, 0d) && TMath.areEqual(m11, 1d) && TMath.areEqual(m12, 0d) && TMath.areEqual(m13, 0d) &&
                TMath.areEqual(m20, 0d) && TMath.areEqual(m21, 0d) && TMath.areEqual(m22, 1d) && TMath.areEqual(m23, 0d) &&
                TMath.areEqual(m30, 0d) && TMath.areEqual(m31, 0d) && TMath.areEqual(m32, 0d) && TMath.areEqual(m33, 1d);
    }



    ////////////////////////
    /*   OBJECT METHODS   */
    ////////////////////////


    /**  @return the string representation of this matrix with high precision (%.12f)  */
    public String toStringPrecise() {
        return String.format("[%.12f, %.12f, %.12f, %.12f]\n" +
                        "[%.12f, %.12f, %.12f, %.12f]\n[%.12f, %.12f, %.12f, %.12f]\n[%.12f, %.12f, %.12f, %.12f]\n",
                m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
    }


    @Override
    public String toString() {
        return String.format("[%f, %f, %f, %f]\n[%f, %f, %f, %f]\n[%f, %f, %f, %f]\n[%f, %f, %f, %f]\n",
                m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TMat4 tMat4 = (TMat4) o;
        return  TMath.areEqual(tMat4.m00, m00) && TMath.areEqual(tMat4.m01, m01) && TMath.areEqual(tMat4.m02, m02) &&
                TMath.areEqual(tMat4.m03, m03) && TMath.areEqual(tMat4.m10, m10) && TMath.areEqual(tMat4.m11, m11) &&
                TMath.areEqual(tMat4.m12, m12) && TMath.areEqual(tMat4.m13, m13) && TMath.areEqual(tMat4.m20, m20) &&
                TMath.areEqual(tMat4.m21, m21) && TMath.areEqual(tMat4.m22, m22) && TMath.areEqual(tMat4.m23, m23) &&
                TMath.areEqual(tMat4.m30, m30) && TMath.areEqual(tMat4.m31, m31) && TMath.areEqual(tMat4.m32, m32) &&
                TMath.areEqual(tMat4.m33, m33);
    }


    @Override
    public int hashCode() {
        return Objects.hash(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
    }



}
