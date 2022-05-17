package com.telek.telekmath.core.geometry.vectors;


import com.telek.telekmath.utils.TMath;
import java.util.Objects;


/**
 * An immutable 2D vector/point class. <br>
 * All methods either return a numeric value or <b>a new {@link TVec2} instance</b>.
 */
public class TVec2 {

    public static final TVec2 ZERO = new TVec2(0d, 0d);

    public final double x, y;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public TVec2(double x, double y){
        this.x = x;
        this.y = y;
    }


    ///////////////
    /*  METHODS  */
    ///////////////


    public TVec2 add(TVec2 vec2){
        return new TVec2(this.x + vec2.x, this.y + vec2.y);
    }

    public TVec2 add(double x, double y){
        return new TVec2(this.x + x, this.y + y);
    }

    //////////////////////////////////////////

    /**
     * @param vec2 any vector
     * @return this - vec2
     */
    public TVec2 subtract(TVec2 vec2){
        return new TVec2(this.x - vec2.x, this.y - vec2.y);
    }

    /**
     * @param x any double
     * @param y any double
     * @return this - {x, y}
     */
    public TVec2 subtract(double x, double y){
        return new TVec2(this.x - x, this.y - y);
    }

    //////////////////////////////////////////

    public TVec2 scale(double scaleXY){
        return new TVec2(this.x * scaleXY, this.y * scaleXY);
    }

    public TVec2 scale(double scaleX, double scaleY){
        return new TVec2(this.x * scaleX, this.y * scaleY);
    }

    //////////////////////////////////////////

    public double dot(TVec2 vector2){
        return this.x * vector2.x + this.y * vector2.y;
    }

    public double dot(double vecX, double vecY){
        return this.x * vecX + this.y * vecY;
    }

    public double length() {
        return TMath.sqrt(this.x * this.x + this.y * this.y);
    }

    public double lengthSquared() {
        return this.x * this.x + this.y * this.y;
    }

    //////////////////////////////////////////

    /**  @return the unit vector of this vector by diving it by it's length  */
    public TVec2 unit(){
        return this.scale(1d / this.length());
    }

    public TVec2 projection(TVec2 vector2) {
        return vector2.scale(this.dot(vector2) / vector2.lengthSquared());
    }

    public TVec2 orthogonalProjection(TVec2 vector2){
        return this.subtract(this.projection(vector2));
    }

    //////////////////////////////////////////

    public TVec2 rotateClockwise90(){
        return new TVec2(this.y, -this.x);
    }

    public TVec2 rotateClockwise180(){
        return new TVec2(-this.x, -this.y);
    }

    public TVec2 rotateClockwise270(){
        return new TVec2(-this.y, this.x);
    }

    public TVec2 symmetricalVectorToTheAxes(boolean xAxis, boolean yAxis){
        return new TVec2(
                (yAxis ? -1d : 1d) * this.x,
                (xAxis ? -1d : 1d) * this.y
        );
    }

    public TVec2 symmetricalVectorTo(TVec2 vec2){
        return new TVec2(
                2d * vec2.x - this.x,
                2d * vec2.y - this.y
        );
    }

    //////////////////////////////////////////

    public boolean isZeroVector() {
        return TMath.areEqual(this.x, 0d) && TMath.areEqual(this.y, 0d);
    }

    public boolean isUnitVector() {
        return TMath.areEqual(this.length(), 1d);
    }

    //////////////////////////////////////////

    /**
     * @return {this.x, this.y, 0d}
     */
    public TVec3 to3D(){
        return new TVec3(this.x, this.y, 0f);
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static double angleBetween(TVec2 vec1, TVec2 vec2) {
        return Math.acos(vec1.dot(vec2) / ( vec1.length() * vec2.length()));
    }

    public static double distanceBetween(TVec2 vec1, TVec2 vec2) {
        return vec1.subtract(vec2).length(); // len(vec1- vec2)
    }

    public static TVec2 midpoint(TVec2 vec1, TVec2 vec2){
        return new TVec2( (vec1.x + vec2.x) / 2d, (vec1.y + vec2.y) / 2d );
    }

    public static boolean areOrthogonal(TVec2 vec1, TVec2 vec2, TVec2 vec3){
        return TMath.areEqual(vec1.dot(vec2), 0d) && TMath.areEqual(vec1.dot(vec3), 0d)
                && TMath.areEqual(vec2.dot(vec3), 0d);
    }

    public static boolean areOrthonormal(TVec2 vec1, TVec2 vec2, TVec2 vec3) {
        return areOrthogonal(vec1, vec2, vec3) && vec1.isUnitVector() && vec2.isUnitVector() && vec3.isUnitVector();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return String.format("<%f, %f>", this.x, this.y)
                .replaceAll("<0.000000", "<0")
                .replaceAll("0.000000>", "0>")
                .replaceAll("<1.000000", "<1")
                .replaceAll("1.000000>", "1>")
                .replaceAll("<-1.000000", "<-1")
                .replaceAll("-1.000000>", "-1>");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TVec2 tVec2 = (TVec2) o;
        return TMath.areEqual(tVec2.x, x) && TMath.areEqual(tVec2.y, y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}
