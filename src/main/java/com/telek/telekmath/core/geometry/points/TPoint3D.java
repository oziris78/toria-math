package com.telek.telekmath.core.geometry.points;

import com.telek.telekmath.core.geometry.vectors.TVector3D;

public class TPoint3D{

    public double x,y,z;

    public TPoint3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distanceFromCenter() {
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
        return String.format("( %.5f, %.5f, %.5f )", this.x, this.y, this.z);
    }



}

