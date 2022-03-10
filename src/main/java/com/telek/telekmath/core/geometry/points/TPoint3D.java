package com.telek.telekmath.core.geometry.points;

import com.telek.telekmath.core.geometry.TLine2D;
import com.telek.telekmath.core.geometry.vectors.TVector2D;
import com.telek.telekmath.core.geometry.vectors.TVector3D;
import com.telek.telekmath.utils.TMath;
import java.util.Objects;


/**
 * An immutable 3D point class. <br>
 * All methods either return a numeric value or <b>a new {@link TPoint3D} instance</b>.
 */
public class TPoint3D{

    public final double x,y,z;

    public TPoint3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public double distanceFromOrigin() {
        return Math.sqrt( this.x * this.x + this.y * this.y + this.z * this.z );
    }

    /**
     * @param point2 any point
     * @return The distance between this vector and the other specified vector
     */
    public double distance(TPoint3D point2){
        return Math.sqrt( ( (this.x - point2.x) * (this.x - point2.x) )
                + ( (this.y - point2.y) * (this.y - point2.y) )
                + ( (this.z - point2.z) * (this.z - point2.z) )
        );
    }


    public TPoint3D scale(double scale){
        return new TPoint3D(this.x * scale, this.y * scale, this.z * scale);
    }


    public TPoint3D moveBy(double xAmount, double yAmount, double zAmount){
        return new TPoint3D(this.x + xAmount, this.y + yAmount, this.z + zAmount);
    }


    public TVector3D toTVector3D(){
        return new TVector3D(this.x, this.y, this.z);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static TPoint3D midPoint(TPoint3D point1, TPoint3D point2){
        return new TPoint3D( ( point1.x + point2.x ) / 2 , ( point1.y + point2.y ) / 2 , ( point1.z + point2.z ) / 2 );
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public String toString() {
        return String.format("(%f, %f, %f)", this.x, this.y, this.z)
                .replaceAll("0.000000,", "0,")
                .replaceAll("-0.000000,", "0,")
                .replaceAll(" -0.000000", " 0")
                .replaceAll(" 0.000000", " 0")
                .replaceAll("1.000000,", "1,")
                .replaceAll("-1.000000,", "-1,")
                .replaceAll(" -1.000000", " -1")
                .replaceAll(" 1.000000", " 1")
                .replaceAll("-0", "0");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPoint3D tPoint3D = (TPoint3D) o;
        return TMath.areEqual(tPoint3D.x, x) && TMath.areEqual(tPoint3D.y, y) && TMath.areEqual(tPoint3D.z, z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}

