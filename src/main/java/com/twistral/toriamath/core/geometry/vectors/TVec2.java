package com.twistral.toriamath.core.geometry.vectors;


import com.twistral.toriamath.utils.TMath;
import java.util.Objects;



/**
 * A fast and mutable 2D vector/point class. <br>
 * All methods either return a numeric value or this vector for method chaining purposes. <br>
 * Also take a look at {@link TVec3} class for a 3D vector/point.
 */
public class TVec2 {

    public static final TVec2 ZERO = new TVec2(0d, 0d);

    protected double x, y;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    /**
     * Creates a vector using the given coordinates.
     * @param x any double
     * @param y any double
     */
    public TVec2(double x, double y){
        this.x = x;
        this.y = y;
    }


    /**
     * Creates an origin point (0, 0).
     */
    public TVec2(){
        this.x = 0d;
        this.y = 0d;
    }


    /**
     * Copy constructor.
     * @param other a vector to copy
     */
    public TVec2(TVec2 other){
        this.x = other.x;
        this.y = other.y;
    }


    /**
     * @return a copy of this vector
     */
    public TVec2 copy(){
        return new TVec2(this);
    }



    /////////////////////////
    /*  GETTERS / SETTERS  */
    /////////////////////////


    /**
     * Sets the coordinates of this vector.
     * @param x any double
     * @param y any double
     * @return this vector for method chaining purposes
     */
    public TVec2 set(double x, double y){
        this.x = x;
        this.y = y;
        return this;
    }


    /**
     * Sets the x coordinate of this vector.
     * @param x any double
     * @return this vector for method chaining purposes
     */
    public TVec2 setX(double x){
        this.x = x;
        return this;
    }


    /**
     * Sets the y coordinate of this vector.
     * @param y any double
     * @return this vector for method chaining purposes
     */
    public TVec2 setY(double y){
        this.y = y;
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


    ////////////////////////
    /*  BASIC OPERATIONS  */
    ////////////////////////


    /**
     * Adds the given vector to this vector.
     * @param vec2 any vector
     * @return this vector for method chaining purposes
     */
    public TVec2 add(TVec2 vec2){
        this.x += vec2.x;
        this.y += vec2.y;
        return this;
    }


    /**
     * Adds the given (x, y) vector to this vector.
     * @param x any double
     * @param y any double
     * @return this vector for method chaining purposes
     */
    public TVec2 add(double x, double y){
        this.x += x;
        this.y += y;
        return this;
    }


    /**
     * Subtracts the given vector to this vector.
     * @param vec2 any vector
     * @return this vector for method chaining purposes
     */
    public TVec2 subtract(TVec2 vec2){
        this.x -= vec2.x;
        this.y -= vec2.y;
        return this;
    }


    /**
     * Subtracts the given (x, y) vector to this vector.
     * @param x any double
     * @param y any double
     * @return this vector for method chaining purposes
     */
    public TVec2 subtract(double x, double y){
        this.x -= x;
        this.y -= y;
        return this;
    }



    /**
     * Scales this vector's components by given scale
     * @param scaleXY any double
     * @return this vector for method chaining purposes
     */
    public TVec2 scale(double scaleXY){
        this.x *= scaleXY;
        this.y *= scaleXY;
        return this;
    }


    /**
     * Scales this vector's components by given scales
     * @param scaleX any double
     * @param scaleY any double
     * @return this vector for method chaining purposes
     */
    public TVec2 scale(double scaleX, double scaleY){
        this.x *= scaleX;
        this.y *= scaleY;
        return this;
    }


    /**
     * Unitizes this vector by scaling it by 1 / len(vec)
     * @return this vector for method chaining purposes
     */
    public TVec2 unit(){
        double len = this.length();
        this.x /= len;
        this.y /= len;
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
    public double dot(TVec2 other){
        return this.x * other.x + this.y * other.y;
    }


    /**
     * Returns the dot product of two vectors.
     * @param otherVecX other vector's x coordinate
     * @param otherVecY other vector's y coordinate
     * @return dot product of this vector and the given vector
     */
    public double dot(double otherVecX, double otherVecY){
        return this.x * otherVecX + this.y * otherVecY;
    }


    /**
     * @return this vector's distance from the origin
     */
    public double length() {
        return TMath.sqrt(this.x * this.x + this.y * this.y);
    }


    /**
     * @return this vector's distance from the origin but squared
     */
    public double lengthSquared() {
        return this.x * this.x + this.y * this.y;
    }



    /////////////////////////
    /*  GEOMETRIC METHODS  */
    /////////////////////////


    /**
     * Rotates this vector 90 degrees clockwise.
     * @return this vector for method chaining purposes
     */
    public TVec2 rotate90DegClockwise(){
        double temp = -this.x;
        this.x = this.y;
        this.y = temp;
        return this;
    }


    /**
     * Rotates this vector 180 degrees clockwise.
     * @return this vector for method chaining purposes
     */
    public TVec2 rotate180DegClockwise(){
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }


    /**
     * Rotates this vector 90 degrees clockwise.
     * @return this vector for method chaining purposes
     */
    public TVec2 rotate90DegAntiClockwise(){
        double temp = -this.y;
        this.y = this.x;
        this.x = temp;
        return this;
    }


    /**
     * @param xAxis a boolean specifying if the calculated vector will be symmetrical according to the x-axis or not
     * @param yAxis a boolean specifying if the calculated vector will be symmetrical according to the y-axis or not
     * @return this vector for method chaining purposes
     */
    public TVec2 symmetricalVectorToTheAxes(boolean xAxis, boolean yAxis){
        if(yAxis)
            this.x = -this.x;
        if(xAxis)
            this.y = -this.y;
        return this;
    }


    /**
     * Calculates the vector symmetrical to the given vector and assigns it to this vector.
     * @param other any vector
     * @return this vector for method chaining purposes
     */
    public TVec2 symmetricalVectorTo(TVec2 other){
        this.x = 2d * other.x - this.x;
        this.y = 2d * other.y - this.y;
        return this;
    }



    ///////////////////////
    /*  BOOLEAN METHODS  */
    ///////////////////////


    /**
     * @return true if this vector is (0, 0)
     */
    public boolean isZeroVector() {
        return TMath.areEqual(this.x, 0d) && TMath.areEqual(this.y, 0d);
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
    public static double angleBetween(TVec2 vec1, TVec2 vec2) {
        return Math.acos(vec1.dot(vec2) / ( vec1.length() * vec2.length()));
    }



    /**
     * @param vec1 any vector
     * @param vec2 any vector
     * @param vec3 any vector
     * @return true if the given vectors are orthogonal
     */
    public static boolean areOrthogonal(TVec2 vec1, TVec2 vec2, TVec2 vec3){
        return TMath.areEqual(vec1.dot(vec2), 0d) && TMath.areEqual(vec1.dot(vec3), 0d)
                && TMath.areEqual(vec2.dot(vec3), 0d);
    }


    /**
     * @param vec1 any vector
     * @param vec2 any vector
     * @param vec3 any vector
     * @return true if the given vectors are orthonormal
     */
    public static boolean areOrthonormal(TVec2 vec1, TVec2 vec2, TVec2 vec3) {
        return areOrthogonal(vec1, vec2, vec3) && vec1.isUnitVector() && vec2.isUnitVector() && vec3.isUnitVector();
    }



    //////////////////////
    /*  OBJECT METHODS  */
    //////////////////////



    @Override
    public String toString() {
        return String.format("<%f, %f>", this.x, this.y);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TVec2 other = (TVec2) o;
        return TMath.areEqual(other.x, x) && TMath.areEqual(other.y, y);
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}
