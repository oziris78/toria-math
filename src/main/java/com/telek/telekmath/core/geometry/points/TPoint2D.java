package com.telek.telekmath.core.geometry.points;


import com.telek.telekmath.core.geometry.TLine2D;
import com.telek.telekmath.core.geometry.vectors.TVector2D;
import com.telek.telekmath.utils.TMath;
import java.util.Objects;


/**
 * An immutable 2D point class. <br>
 * All methods either return a numeric value or <b>a new {@link TPoint2D} instance</b>.
 */
public class TPoint2D {

    public static final TPoint2D ZERO = new TPoint2D(0d,0d);

    public final double x,y;

    
    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////
    
    public TPoint2D(double x, double y){
        this.x = x;
        this.y = y;
    }


    ///////////////
    /*  METHODS  */
    ///////////////
    
    
    public double distanceFromOrigin() {
        return Math.sqrt( this.x * this.x + this.y * this.y );
    }


    public TPoint2D moveBy(double xAmount, double yAmount){
        return new TPoint2D(this.x + xAmount, this.y + yAmount);
    }

    public TPoint2D scale(double scale){
        return new TPoint2D(this.x * scale, this.y * scale);
    }


    public TPoint2D rotateClockwise90(){ return new TPoint2D(this.y, -this.x); }

    public TPoint2D rotateClockwise180(){ return new TPoint2D(-this.x, -this.y); }

    public TPoint2D rotateClockwise270(){ return new TPoint2D(-this.y, this.x); }


    public TPoint2D getSymmetricalPointToTheAxes(boolean xAxis, boolean yAxis){
        return new TPoint2D(
                (yAxis ? -1d : 1d) * this.x,
                (xAxis ? -1d : 1d) * this.y
        );
    }


    public TPoint2D getSymmetricalPointToAnotherPoint(TPoint2D aPoint){
        return new TPoint2D( 2 * aPoint.x - this.x, 2 * aPoint.y - this.y);
    }

    public TVector2D toTVector2D(){
        return new TVector2D(this.x, this.y);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * @param point2 any point
     * @return The distance between this vector and the other specified vector
     */
    public static double distanceBetweenTwoPoints(TPoint2D point1, TPoint2D point2){
        return Math.sqrt( (point1.x - point2.x) * (point1.x - point2.x) + (point1.y - point2.y) * (point1.y - point2.y) );
    }


    public static TPoint2D midPoint(TPoint2D point1, TPoint2D point2){
        return new TPoint2D( ( point1.x + point2.x ) / 2 , ( point1.y + point2.y ) / 2 );
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return String.format("(%f, %f)", this.x, this.y)
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
        TPoint2D tPoint2D = (TPoint2D) o;
        return TMath.areEqual(tPoint2D.x, x) && TMath.areEqual(tPoint2D.y, y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
