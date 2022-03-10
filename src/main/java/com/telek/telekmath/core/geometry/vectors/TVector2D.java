package com.telek.telekmath.core.geometry.vectors;


import com.telek.telekmath.core.geometry.TLine2D;
import com.telek.telekmath.core.geometry.points.TPoint2D;
import com.telek.telekmath.utils.TMath;
import java.util.Objects;


/**
 * An immutable 2D vector class. <br>
 * All methods either return a numeric value or <b>a new {@link TVector2D} instance</b>.
 */
public class TVector2D {

    public static final TVector2D ZERO = new TVector2D(0d, 0d);

    public double x, y;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public TVector2D(double x, double y){
        this.x = x;
        this.y = y;
    }



    ///////////////
    /*  METHODS  */
    ///////////////



    public TVector2D add(TVector2D vector2){
        return new TVector2D(this.x + vector2.x, this.y + vector2.y);
    }


    public TVector2D add(double x, double y){
        return new TVector2D(this.x + x, this.y + y);
    }

    public TVector2D subtract(TVector2D vector2){
        return new TVector2D(this.x - vector2.x, this.y - vector2.y);
    }

    public TVector2D subtract(double x, double y){
        return new TVector2D(this.x - x, this.y - y);
    }


    public TVector2D scale(double scaleAmount){
        return new TVector2D(this.x * scaleAmount, this.y * scaleAmount);
    }

    public TVector2D scale(double scaleX, double scaleY){
        return new TVector2D(this.x * scaleX, this.y * scaleY);
    }


    public TVector2D unitVector(){
        return this.scale( 1d/ this.length() );
    }


    public TVector2D projection(TVector2D vector2) {
        return vector2.scale(this.dot(vector2) / (vector2.length() * vector2.length()));
    }


    public TVector2D orthogonalProjection(TVector2D vector2){
        return this.subtract( this.projection(vector2) );
    }



    public double length() {
        return Math.sqrt( this.x * this.x + this.y * this.y );
    }

    public double dot(TVector2D vector2){
        return this.x * vector2.x + this.y * vector2.y;
    }



    public boolean isUnitVector() {
        return this.length() == 1;
    }

    public boolean isZeroVector() {
        return this.x == 0d && this.y == 0d;
    }


    public TPoint2D toTPoint2D(){
        return new TPoint2D(this.x, this.y);
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////



    public static double angleBetweenTwoVectors(TVector2D vector1, TVector2D vector2) {
        return Math.acos( vector1.dot(vector2) / ( vector1.length() * vector2.length()) );
    }

    public static double distanceBetweenTwoVectors(TVector2D vector1, TVector2D vector2) {
        return vector1.subtract(vector2).length(); // len(vec1- vec2)
    }


    public static boolean areOrthogonal(TVector2D vector1, TVector2D vector2, TVector2D vector3){
        return (vector1.dot(vector2) == 0 && vector1.dot(vector3) == 0 && vector2.dot(vector3) == 0);
    }

    public static boolean areOrthonormal(TVector2D vector1, TVector2D vector2, TVector2D vector3) {
        return areOrthogonal(vector1, vector2, vector3)
                && vector1.isUnitVector() && vector2.isUnitVector() && vector3.isUnitVector();
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
        TVector2D tVector2D = (TVector2D) o;
        return TMath.areEqual(tVector2D.x, x) && TMath.areEqual(tVector2D.y, y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}
