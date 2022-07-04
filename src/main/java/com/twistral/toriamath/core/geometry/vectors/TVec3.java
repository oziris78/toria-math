package com.twistral.toriamath.core.geometry.vectors;


import com.twistral.toriamath.utils.TMath;

import java.util.Objects;


/**
 * A fast and mutable 3D vector/point class. <br>
 * All methods either return a numeric value or this vector for method chaining purposes. <br>
 * Also take a look at {@link TVec2} class for a 2D vector/point.
 */
public class TVec3 {

    public static final TVec3 ZERO = new TVec3(0d, 0d, 0d);

    protected double x, y, z;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    /**
     * Creates a vector using the given coordinates.
     * @param x any double
     * @param y any double
     * @param z any double
     */
    public TVec3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }


    /**
     * Creates an origin point (0, 0, 0).
     */
    public TVec3(){
        this.x = 0d;
        this.y = 0d;
        this.z = 0d;
    }


    /**
     * Copy constructor.
     * @param other a vector to copy
     */
    public TVec3(TVec3 other){
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }


    /**
     * @return a copy of this vector
     */
    public TVec3 copy(){
        return new TVec3(this);
    }



    /////////////////////////
    /*  GETTERS / SETTERS  */
    /////////////////////////


    /**
     * Sets the coordinates of this vector.
     * @param x any double
     * @param y any
     * @param z any double
     * @return this vector for method chaining purposes
     */
    public TVec3 set(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }


    /**
     * Sets the x coordinate of this vector.
     * @param x any double
     * @return this vector for method chaining purposes
     */
    public TVec3 setX(double x){
        this.x = x;
        return this;
    }


    /**
     * Sets the y coordinate of this vector.
     * @param y any double
     * @return this vector for method chaining purposes
     */
    public TVec3 setY(double y){
        this.y = y;
        return this;
    }


    /**
     * Sets the z coordinate of this vector.
     * @param z any double
     * @return this vector for method chaining purposes
     */
    public TVec3 setZ(double z){
        this.z = z;
        return this;
    }


    /**
     * @return x coordinate of this vector
     */
    public double getX() {
        return x;
    }


    /**
     * @return y coordinate of this vector
     */
    public double getY() {
        return y;
    }


    /**
     * @return z coordinate of this vector
     */
    public double getZ() {
        return z;
    }


    ////////////////////////
    /*  BASIC OPERATIONS  */
    ////////////////////////


    /**
     * Adds the given vector to this vector.
     * @param other any vector
     * @return this vector for method chaining purposes
     */
    public TVec3 add(TVec3 other){
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        return this;
    }


    /**
     * Adds the given (x, y, z) vector to this vector.
     * @param x any double
     * @param y any double
     * @param z any double
     * @return this vector for method chaining purposes
     */
    public TVec3 add(double x, double y, double z){
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }


    /**
     * Subtracts the given vector to this vector.
     * @param other any vector
     * @return this vector for method chaining purposes
     */
    public TVec3 subtract(TVec3 other){
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
        return this;
    }


    /**
     * Subtracts the given (x, y, z) vector to this vector.
     * @param x any double
     * @param y any double
     * @param z any double
     * @return this vector for method chaining purposes
     */
    public TVec3 subtract(double x, double y, double z){
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }



    /**
     * Scales this vector's components by the given scale
     * @param scaleXYZ any double
     * @return this vector for method chaining purposes
     */
    public TVec3 scale(double scaleXYZ){
        this.x *= scaleXYZ;
        this.y *= scaleXYZ;
        this.z *= scaleXYZ;
        return this;
    }


    /**
     * Scales this vector's components by given scales
     * @param scaleX any double
     * @param scaleY any double
     * @param scaleZ any double
     * @return this vector for method chaining purposes
     */
    public TVec3 scale(double scaleX, double scaleY, double scaleZ){
        this.x *= scaleX;
        this.y *= scaleY;
        this.z *= scaleZ;
        return this;
    }


    /**
     * Unitizes this vector by scaling it by 1 / len(vec)
     * @return this vector for method chaining purposes
     */
    public TVec3 unit(){
        double len = this.length();
        this.x /= len;
        this.y /= len;
        this.z /= len;
        return this;
    }


    /**
     * If v is this vector and u is the other vector this method does v = v x u
     * where the "x" operation is cross product operation.
     * @param other any vector
     * @return this vector for method chaining purposes
     */
    public TVec3 cross(TVec3 other){
        double crossX = this.y * other.z - this.z * other.y;
        double crossY = this.z * other.x - this.x * other.z;
        double crossZ = this.x * other.y - this.y * other.x;
        this.x = crossX;
        this.y = crossY;
        this.z = crossZ;
        return this;
    }



    /////////////////////
    /*  VALUE METHODS  */
    /////////////////////


    /**
     * Returns the dot product of two vectors.
     * @param other any vector
     * @return dot product of this vector and the given vector
     */
    public double dot(TVec3 other){
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }


    /**
     * Returns the dot product of two vectors.
     * @param otherVecX other vector's x coordinate
     * @param otherVecY other vector's y coordinate
     * @param otherVecZ other vector's z coordinate
     * @return dot product of this vector and the given vector
     */
    public double dot(double otherVecX, double otherVecY, double otherVecZ){
        return this.x * otherVecX + this.y * otherVecY + this.z * otherVecZ;
    }


    /**
     * @return this vector's distance from the origin
     */
    public double length() {
        return TMath.sqrt( this.x * this.x + this.y * this.y + this.z * this.z );
    }



    /**
     * @return this vector's distance from the origin but squared
     */
    public double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }



    ///////////////////////
    /*  BOOLEAN METHODS  */
    ///////////////////////


    /**
     * @return true if this vector is (0, 0)
     */
    public boolean isZeroVector() {
        return TMath.areEqual(this.x, 0d) && TMath.areEqual(this.y, 0d) && TMath.areEqual(this.z, 0d);
    }


    /**
     * @return true if this vector is unit vector
     */
    public boolean isUnitVector() {
        return TMath.areEqual(this.length(), 1d);
    }




    //////////////////////
    /*  STATIC METHODS  */
    //////////////////////


    /**
     * @param vec1 any vector
     * @param vec2 any vector
     * @return the angle between the given vectors
     */
    public static double angleBetween(TVec3 vec1, TVec3 vec2) {
        return Math.acos(vec1.dot(vec2) / ( vec1.length() * vec2.length()));
    }



    /**
     * @param vec1 any vector
     * @param vec2 any vector
     * @param vec3 any vector
     * @return true if the given vectors are orthogonal
     */
    public static boolean areOrthogonal(TVec3 vec1, TVec3 vec2, TVec3 vec3){
        return TMath.areEqual(vec1.dot(vec2), 0d) && TMath.areEqual(vec1.dot(vec3), 0d)
                && TMath.areEqual(vec2.dot(vec3), 0d);
    }


    /**
     * @param vec1 any vector
     * @param vec2 any vector
     * @param vec3 any vector
     * @return true if the given vectors are orthonormal
     */
    public static boolean areOrthonormal(TVec3 vec1, TVec3 vec2, TVec3 vec3) {
        return areOrthogonal(vec1, vec2, vec3) && vec1.isUnitVector() && vec2.isUnitVector() && vec3.isUnitVector();
    }



    //////////////////////
    /*  OBJECT METHODS  */
    //////////////////////

    @Override
    public String toString() {
        return String.format("<%f, %f, %f>", this.x, this.y, this.z);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TVec3 other = (TVec3) o;
        return TMath.areEqual(other.x, x) && TMath.areEqual(other.y, y) && TMath.areEqual(other.z, z) ;
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }



}
