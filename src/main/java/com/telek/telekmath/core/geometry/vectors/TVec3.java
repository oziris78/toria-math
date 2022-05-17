package com.telek.telekmath.core.geometry.vectors;


import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.matrices.TMatrix;
import java.util.Objects;


/**
 * An immutable 3D vector/point class. <br>
 * All methods either return a numeric value or <b>a new {@link TVec3} instance</b>.
 */
public class TVec3 {

    public static final TVec3 ZERO = new TVec3(0d, 0d, 0d);

    public final double x, y, z;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public TVec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }



    ///////////////
    /*  METHODS  */
    ///////////////

    public TVec3 add(TVec3 vec2){
        return new TVec3(this.x + vec2.x, this.y + vec2.y, this.z + vec2.z);
    }

    public TVec3 add(double x, double y, double z){
        return new TVec3(this.x + x, this.y + y, this.z + z);
    }

    //////////////////////////////////////////

    /**
     * @param vec2 any vector
     * @return this - vec2
     */
    public TVec3 subtract(TVec3 vec2){
        return new TVec3(this.x - vec2.x, this.y - vec2.y, this.z - vec2.z);
    }

    /**
     * @param x any double
     * @param y any double
     * @param z any double
     * @return this - {x, y, z}
     */
    public TVec3 subtract(double x, double y, double z){
        return new TVec3(this.x - x, this.y - y, this.z - z);
    }

    //////////////////////////////////////////

    public TVec3 scale(double scaleXYZ){
        return new TVec3( this.x * scaleXYZ, this.y * scaleXYZ, this.z * scaleXYZ );
    }

    public TVec3 scale(double scaleX, double scaleY, double scaleZ){
        return new TVec3( this.x * scaleX, this.y * scaleY, this.z * scaleZ );
    }

    //////////////////////////////////////////

    public double dot(TVec3 vec2){
        return this.x * vec2.x + this.y * vec2.y + this.z * vec2.z;
    }

    public TVec3 cross(TVec3 vector2){
        return new TVec3(
                (this.y * vector2.z - this.z * vector2.y),
                (this.z * vector2.x - this.x * vector2.z),
                (this.x * vector2.y - this.y * vector2.x)
        );
    }

    public double length() {
        return TMath.sqrt( this.x * this.x + this.y * this.y + this.z * this.z );
    }

    public double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    //////////////////////////////////////////

    public TVec3 unit(){
        return this.scale(1d / this.length());
    }


    /**
     * vec1.projection(vec2) computes the projection of vec2 onto vec1.
     * @param vec2 any vector
     * @return projection of vec2 onto vec1
     */
    public TVec3 projection(TVec3 vec2) {
        return vec2.scale( (this.dot(vec2)) / vec2.lengthSquared() );
    }

    //////////////////////////////////////////


    public boolean isZeroVector() {
        return TMath.areEqual(this.x, 0d) && TMath.areEqual(this.y, 0d) && TMath.areEqual(this.z, 0d);
    }

    public boolean isUnitVector() {
        return TMath.areEqual(this.length(), 1d);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static boolean areOrthogonal(TVec3 vec1, TVec3 vec2, TVec3 vec3){
        return TMath.areEqual(vec1.dot(vec2), 0d) && TMath.areEqual(vec1.dot(vec3), 0d)
                && TMath.areEqual(vec2.dot(vec3), 0d);
    }

    public static double distanceBetween(TVec3 vec1, TVec3 vec2) {
        return vec1.subtract(vec2).length();
    }

    public static double angleBetween(TVec3 vec1, TVec3 vec2) {
        return Math.acos( vec1.dot(vec2) / ( vec1.length() * vec2.length()) );
    }

    public static boolean areOrthonormal(TVec3 vec1, TVec3 vec2, TVec3 vec3) {
        return areOrthogonal(vec1, vec2, vec3) &&
                vec1.isUnitVector() && vec2.isUnitVector() && vec3.isUnitVector() ;
    }

    public static TVec3 midpoint(TVec3 vec1, TVec3 vec2){
        return new TVec3((vec1.x + vec2.x) / 2d, (vec1.y + vec2.y) / 2d, (vec1.z + vec2.z) / 2d);
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
        TVec3 tVec3 = (TVec3) o;
        return TMath.areEqual(tVec3.x, x) && TMath.areEqual(tVec3.y, y) && TMath.areEqual(tVec3.z, z) ;
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }


}

