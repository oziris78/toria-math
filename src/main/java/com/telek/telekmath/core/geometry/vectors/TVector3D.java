package com.telek.telekmath.core.geometry.vectors;


import com.telek.telekmath.core.geometry.TLine2D;
import com.telek.telekmath.core.geometry.points.TPoint3D;
import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.matrices.TMatrix;

import java.util.Objects;


/**
 * An immutable 3D vector class. <br>
 * All methods either return a numeric value or <b>a new {@link TVector3D} instance</b>.
 */
public class TVector3D {

    public static final TVector3D ZERO = new TVector3D(0d, 0d, 0d);

    public final double x,y,z;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public TVector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }



    ///////////////
    /*  METHODS  */
    ///////////////

    public TVector3D add(TVector3D vector2){
        return new TVector3D(this.x + vector2.x, this.y + vector2.y, this.z + vector2.z);
    }

    public TVector3D add(double x, double y, double z){
        return new TVector3D(this.x + x, this.y + y, this.z + z);
    }

    public TVector3D subtract(TVector3D vector2){
        return new TVector3D(this.x - vector2.x, this.y - vector2.y, this.z - vector2.z);
    }

    public TVector3D subtract(double x, double y, double z){
        return new TVector3D(this.x - x, this.y - y, this.z - z);
    }

    public TVector3D scale(double scale){
        return new TVector3D( this.x * scale, this.y * scale, this.z * scale );
    }

    public TVector3D scale(double scaleX, double scaleY, double scaleZ){
        return new TVector3D( this.x * scaleX, this.y * scaleY, this.z * scaleZ );
    }

    public double dot(TVector3D vector2){
        return this.x * vector2.x + this.y * vector2.y + this.z * vector2.z;
    }

    public TVector3D cross(TVector3D vector2){
        return new TVector3D(
                (this.y * vector2.z - this.z * vector2.y),
                (this.z * vector2.x - this.x * vector2.z),
                (this.x * vector2.y - this.y * vector2.x)
        );
    }

    public TVector3D unitVector(){
        double size = this.length();
        return new TVector3D( this.x / size, this.y / size, this.z / size );
    }

    public boolean isUnitVector() {
        return this.length() == 1;
    }

    public boolean isZeroVector() {
        return this.x == 0 && this.y == 0 && this.z == 0;
    }


    /**
     * vec1.projection(vec2) computes the projection of vec2 onto vec1.
     * @param vector2 any vector
     * @return projection of vec2 onto vec1
     */
    public TVector3D projection(TVector3D vector2) {
        return vector2.scale( (this.dot(vector2)) / (vector2.length() * vector2.length()) );
    }


    public double length() {
        return Math.sqrt( this.x * this.x + this.y * this.y + this.z * this.z );
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static boolean areOrthogonal(TVector3D vector1, TVector3D vector2, TVector3D vector3){
        return (vector1.dot(vector2) == 0 && vector1.dot(vector3) == 0 && vector2.dot(vector3) == 0);
    }

    public static boolean areLinearlyIndependent(TVector3D vector1, TVector3D vector2, TVector3D vector3){
        double det = new TMatrix(new double[][]{
                {vector1.x, vector2.x, vector2.x},
                {vector1.y, vector2.y, vector2.y},
                {vector1.z, vector2.z, vector2.z}
        }).determinant();

        return ( det != 0 );
    }

    public static double distanceBetweenTwoVectors(TVector3D vector1, TVector3D vector2) {
        return vector1.subtract(vector2).length();
    }

    public static double angleBetweenTwoVectors(TVector3D vector1, TVector3D vector2) {
        return Math.acos( vector1.dot(vector2) / ( vector1.length() * vector2.length()) );
    }

    public static boolean areOrthonormal(TVector3D vector1, TVector3D vector2, TVector3D vector3) {
        return areOrthogonal(vector1, vector2, vector3) &&
                vector1.isUnitVector() && vector2.isUnitVector() && vector3.isUnitVector() ;
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {

        return String.format("<%f, %f, %f>", this.x, this.y, this.z)
                .replaceAll("<0.000000", "<0")
                .replaceAll("0.000000>", "0>")
                .replaceAll("<1.000000", "<1")
                .replaceAll("1.000000>", "1>")
                .replaceAll("<-1.000000", "<-1")
                .replaceAll("-1.000000>", "-1>")
                .replaceAll(", 0.000000", ", 0")
                .replaceAll(", 1.000000", ", 1")
                .replaceAll(", -1.000000",", -1")
                .replaceAll( "0.000000,",  "0,")
                .replaceAll( "1.000000,",  "1,")
                .replaceAll("-1.000000,", "-1,");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TVector3D tVector3D = (TVector3D) o;
        return TMath.areEqual(tVector3D.x, x) && TMath.areEqual(tVector3D.y, y) && TMath.areEqual(tVector3D.z, z) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }





}

