package com.telek.telekmath.core.geometry.points;

import com.telek.telekmath.core.geometry.lines.TLine2D;
import com.telek.telekmath.core.geometry.vectors.TVector2D;
import static com.telek.telekmath.exceptions.TelekMathException.*;

public class TPoint2D {

    public static final TPoint2D ZERO = new TPoint2D(0d,0d);

    public double x,y;

    public TPoint2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public TVector2D toTVector2D(){
        return new TVector2D(this.x, this.y);
    }

    public double distanceFromOrigin() {
        return Math.sqrt( this.x * this.x + this.y * this.y );
    }

    public void moveBy(double xAmount, double yAmount){
        this.x += xAmount;
        this.y += yAmount;
    }

    /**
     * @param point2 any point
     * @return The distance between this vector and the other specified vector
     */
    public double distance(TPoint2D point2){
        return Math.sqrt( ((this.x - point2.x) * (this.x - point2.x)) + ((this.y - point2.y) * (this.y - point2.y)) );
    }

    public TPoint2D rotateClockwise90(){ return new TPoint2D(this.y, -this.x); }

    public TPoint2D rotateClockwise180(){ return new TPoint2D(-this.x, -this.y); }

    public TPoint2D rotateClockwise270(){ return new TPoint2D(-this.y, this.x); }

    public TPoint2D getSymmetricalPointToXAxis(){ return new TPoint2D(this.x, -this.y); }

    public TPoint2D getSymmetricalPointToYAxis(){ return new TPoint2D(-this.x, this.y); }

    public TPoint2D getSymmetricalPointToOrigin(){ return new TPoint2D(-this.x, -this.y); }

    /** @return the symmetrical point according to y = x */
    public TPoint2D getSymmetricalPointToYEqualsX(){
        return new TPoint2D(this.y, this.x);
    }

    /** @return the symmetrical point according to y = -x */
    public TPoint2D getSymmetricalPointToYEqualsMinusX(){
        return new TPoint2D(-this.y, -this.x);
    }

    public TPoint2D getSymmetricalPointToAnotherPoint(TPoint2D aPoint){
        return new TPoint2D( 2 * aPoint.x - this.x, 2 * aPoint.y - this.y);
    }

    public TPoint2D getSymmetricalPointToVerticalLine(TLine2D verticalLine){
        if(!verticalLine.isParallelToYAxis()) throw new NotAVerticalLineException(verticalLine);
        else return new TPoint2D(2 * verticalLine.getX0() - this.x, this.y);
    }

    public TPoint2D getSymmetricalPointToHorizontalLine(TLine2D horizontalLine){
        if(horizontalLine.getSlope() != 0) throw new NotAHorizontalLineException(horizontalLine);
        else return new TPoint2D(this.x, 2 * horizontalLine.getConstant() - this.y);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static TPoint2D midPoint(TPoint2D point1, TPoint2D point2){
        return new TPoint2D( ( point1.x + point2.x ) / 2 , ( point1.y + point2.y ) / 2 );
    }


    /**  @see TLine2D#distanceBetweenPointAndLine(TPoint2D, TLine2D)  */
    public static double distanceBetweenPointAndLine(TPoint2D point, TLine2D line){
        return TLine2D.distanceBetweenPointAndLine(point, line);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return String.format("( %.5f , %.5f )", this.x, this.y);
    }



}
