package com.telek.telekmath.core.geometry.vectors;

import com.telek.telekmath.core.geometry.points.TPoint3D;

public class TVector3D {

    public static final TVector3D ZERO = new TVector3D(0d, 0d, 0d);

    public double x,y,z;

    public TVector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public TVector3D add(TVector3D vector2){
        return new TVector3D(this.x + vector2.x, this.y + vector2.y, this.z + vector2.z);
    }

    public TVector3D difference(TVector3D vector2){
        return new TVector3D(this.x - vector2.x, this.y - vector2.y, this.z - vector2.z);
    }

    public TVector3D scale(double a){
        return new TVector3D( this.x * a, this.y * a, this.z * a );
    }

    public double dot(TVector3D vector2){
        return this.x * vector2.x + this.y * vector2.y + this.z * vector2.z;
    }

    public TVector3D cross(TVector3D vector2){
        return new TVector3D( (this.y * vector2.z - this.z * vector2.y), (this.z * vector2.x - this.x * vector2.z), (this.x * vector2.y - this.y * vector2.x) );
    }

    public TVector3D getUnitVector(){
        double size = this.length();
        return new TVector3D( this.x / size, this.y / size, this.z / size );
    }

    public boolean isUnitVector() {
        return this.length() == 1;
    }

    public boolean isZeroVector() {
        return this.x == 0 && this.y == 0 && this.z == 0;
    }

    public TVector3D projection(TVector3D vector2) {
        return vector2.scale( (this.dot(vector2)) / (vector2.length() * vector2.length()) );
    }

    public boolean areOrthogonal(TVector3D vector2, TVector3D vector3){
        return (this.dot(vector2) == 0 && this.dot(vector3) == 0 && vector2.dot(vector3) == 0);
    }

    public double distance(TVector3D vector2) {
        return this.difference(vector2).length();
    }

    public boolean areLinearlyIndependent(TVector3D vector2, TVector3D vector3){
        double det = (this.x * vector2.y * vector3.z ) + (vector2.x * vector3.y * this.z )
                + (vector3.x * this.y * vector2.z ) - ( this.x * vector3.y * vector2.z )
                - (vector2.x * this.y * vector3.z ) - (vector3.x * vector2.y * this.z ) ;
        return ( det != 0 );
    }

    public double angleBetween(TVector3D vector2) {
        return Math.acos( this.dot(vector2) / ( this.length() * vector2.length()) );
    }

    public boolean areOrthonormal(TVector3D vector2, TVector3D vector3) {
        return this.areOrthogonal(vector2, vector3) && this.isUnitVector() && vector2.isUnitVector() && vector3.isUnitVector() ;
    }

    public TVector3D orthogonalProjection(TVector3D vector2){
        return this.difference( this.projection(vector2) );
    }

    public double length() {
        return Math.sqrt( this.x * this.x + this.y * this.y + this.z * this.z );
    }

    public TPoint3D toTPoint3D(){
        return new TPoint3D(this.x, this.y, this.z);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return String.format("<%.5f, %.5f, %.5f>", this.x, this.y, this.z);
    }


}
