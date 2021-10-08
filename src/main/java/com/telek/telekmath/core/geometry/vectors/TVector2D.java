package com.telek.telekmath.core.geometry.vectors;


import com.telek.telekmath.core.geometry.points.TPoint2D;

public class TVector2D {

    public static final TVector2D ZERO = new TVector2D(0d, 0d);

    public double x, y;

    public TVector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public TVector2D add(TVector2D vector2){
        return new TVector2D(this.x + vector2.x, this.y + vector2.y);
    }

    public TVector2D difference(TVector2D vector2){
        return new TVector2D(this.x - vector2.x, this.y - vector2.y);
    }

    public double length() {
        return Math.sqrt( this.x * this.x + this.y * this.y );
    }

    public double dot(TVector2D vector2){
        return this.x * vector2.x  + this.y * vector2.y ;
    }

    public TVector2D scale(double scaleAmount){
        return new TVector2D( this.x * scaleAmount, this.y * scaleAmount);
    }

    public TPoint2D toTPoint2D(){
        return new TPoint2D(this.x, this.y);
    }

    public TVector2D getUnitVector(){
        double size = this.length();
        return new TVector2D( this.x / size, this.y / size );
    }

    public boolean isUnitVector() {
        return this.length() == 1;
    }

    public boolean isZeroVector() {
        return this.x == 0d && this.y == 0d;
    }

    public double distance(TVector2D vector2) {
        return this.difference(vector2).length();
    }

    public double angleBetween(TVector2D vector2) {
        return Math.acos( this.dot(vector2) / ( this.length() * vector2.length()) );
    }

    public TVector2D projection(TVector2D vector2) {
        return vector2.scale( (this.dot(vector2)) / (vector2.length() * vector2.length()) );
    }

    public TVector2D orthogonalProjection(TVector2D vector2){
        return this.difference( this.projection(vector2) );
    }

    public boolean areOrthogonal(TVector2D vector2, TVector2D vector3){
        return (this.dot(vector2) == 0 && this.dot(vector3) == 0 && vector2.dot(vector3) == 0);
    }

    public boolean areOrthonormal(TVector2D vector2, TVector2D vector3) {
        return this.areOrthogonal(vector2, vector3) && this.isUnitVector() && vector2.isUnitVector() && vector3.isUnitVector() ;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return String.format("<%.5f, %.5f>", this.x, this.y);
    }


}
