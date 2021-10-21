package com.telek.telekmath.core.geometry.lines;

import com.telek.telekmath.core.constants.LineConstants;
import com.telek.telekmath.core.geometry.points.TPoint2D;

import static com.telek.telekmath.exceptions.TelekMathException.*;


public class TLine2D {

    private boolean isParallelToYAxis; // for distinguishing lines

    // isParallelToYAxis = false, y = mx + n
    private double slope; // m
    private double constant; // n

    // isParallelToYAxis = true, x = x0
    private double x0; // x0


    /*  CONSTRUCTORS  */

    public TLine2D(double m, double n){
        // means it is written as y = mx + n
        this.slope = m;
        this.constant = n;
        isParallelToYAxis = false;
    }


    public TLine2D(TPoint2D pointOneOnLine, TPoint2D pointTwoOnLine){
        double x1 = pointOneOnLine.x; double x2 = pointTwoOnLine.x;
        double y1 = pointOneOnLine.y; double y2 = pointTwoOnLine.y;
        if( x2 - x1 == 0){
            this.isParallelToYAxis = true; // means it is x = x0
            this.x0 = x1; // x = x1 or x = x2 is the same thing and the line
        }
        else{
            this.isParallelToYAxis = false; // means it is y = mx + n
            double m = (y2 - y1) / (x2 - x1);
            double n = y1 - m * x1;
            this.slope = m;
            this.constant = n;
        }
    }


    public TLine2D(double m, TPoint2D point){
        this.slope = m;
        this.constant = point.y - m * point.x;
        this.isParallelToYAxis = false;
    }


    /**
     * @param line2 any line
     * @return true if this line and line2 are parallel to each other if and only if  m1 = m2
     */
    public boolean isParallelTo(TLine2D line2){
        boolean isParallel = false;
        if( !this.isParallelToYAxis && !line2.isParallelToYAxis )
            isParallel = this.getSlope() == line2.getSlope();
        else if( this.isParallelToYAxis && !line2.isParallelToYAxis)
            isParallel = false;
        else if( !this.isParallelToYAxis && line2.isParallelToYAxis)
            isParallel = false;
        else if( this.isParallelToYAxis && line2.isParallelToYAxis)
            isParallel = true;
        return isParallel;
    }


    /**
     * @param line2 any line
     * @return true if this line and line2 are parallel to each other if and only if m1 = m2
     */
    public boolean isOrthogonalTo(TLine2D line2){
        /*  if the lines are both NOT parallel to the y axis, then we must chech if m1 * m2 is -1
        otherwise if one or both of them are parallel to the y axis then;
        if the line that is NOT parallel to the y axis has a slope of m=0, they will be orthogonal because
        one is x = x0 and other one is y = y0 which means they are always orthogonal in an orthogonal coordinate system  */
        boolean isOrthogonal = false;
        if( !this.isParallelToYAxis && !line2.isParallelToYAxis ){
            double multipliedSlope = this.getSlope() * line2.getSlope();
            isOrthogonal = ( multipliedSlope <= -1d + LineConstants.threshhold && multipliedSlope >= -1d - LineConstants.threshhold );  // -0.9 >= multipliedSlope >= -1.1
        }
        else if( this.isParallelToYAxis && !line2.isParallelToYAxis)
            isOrthogonal = (line2.getSlope() == 0) ;
        else if( !this.isParallelToYAxis && line2.isParallelToYAxis)
            isOrthogonal = (this.getSlope() == 0) ;
        else if( this.isParallelToYAxis && line2.isParallelToYAxis)
            isOrthogonal = false;
        return isOrthogonal;

    }




    /** @return The symmetrical line according to the origin (0,0) */
    public TLine2D getSymmetricalLineToOrigin(){
        return new TLine2D(this.getSlope(), -this.getConstant());  /*  y = mx-n  */
    }



    /** @return The symmetrical line according to the x-axis (y=0)  */
    public TLine2D getSymmetricalLineToXAxis(){
        return new TLine2D(-this.getSlope(), -this.getConstant());  /*  y = -mx-n  */
    }


    /** @return The symmetrical line according to the y-axis (x=0) */
    public TLine2D getSymmetricalLineToYAxis(){
        return new TLine2D(-this.getSlope(), this.getConstant());  /*  y= -mx + n  */
    }


    /** @return The symmetrical line according to y = x */
    public TLine2D getSymmetricalLineToYEqualsX(){
        /* -my+x-n=0,  if y=mx+n type then y=(1/m)x-(n/m) else if x=x0 type then y=x0 */
        if(!this.isParallelToYAxis()) return new TLine2D( 1 / this.getSlope(), - this.getConstant() / this.getSlope());
        else return new TLine2D(0, this.getX0());
    }


    /**  @return The symmetrical line according to y = -x  */
    public TLine2D getSymmetricalLineToYEqualsMinusX(){
        /*  my-x-n=0,  if y=mx+n type then y=(1/m)x+(n/m) else if x=x0 type then y=-x0  */
        if(!this.isParallelToYAxis()) return new TLine2D( 1 / this.getSlope(), this.getConstant() / this.getSlope());
        else return new TLine2D(0, -this.getX0());
    }


    /**
     * @param verticalLine any vertical line such as x = k
     * @return The symmetrical line according to x = k
     */
    public TLine2D getSymmetricalLineToAVerticalLine(TLine2D verticalLine){
        if(!verticalLine.isParallelToYAxis()) throw new NotAVerticalLineException(verticalLine);
        else return getSymmetricalLineToAVerticalLine( verticalLine.getX0() );  /*  y = -mx + (2km + n)  */
    }

    /**
     * @param k any double specifying any vertical line such as x = k
     * @return The symmetrical line according to x = k
     */
    public TLine2D getSymmetricalLineToAVerticalLine(double k){
        return new TLine2D(-this.getSlope(), 2 * k * this.getSlope() + this.getConstant() );
    }


    /**
     * @param horizontalLine any horizontal line y = k
     * @return The symmetrical line according to y = k
     */
    public TLine2D getSymmetricalLineToAHorizontalLine(TLine2D horizontalLine){
        if(horizontalLine.getSlope() != 0) throw new NotAHorizontalLineException(horizontalLine);
        else return getSymmetricalLineToAHorizontalLine(horizontalLine.getConstant());  /*  y = -mx + (2k-n)  */
    }


    /**
     * @param k the constant specifying the horizontal line y = k
     * @return The symmetrical line according to y = k
     */
    public TLine2D getSymmetricalLineToAHorizontalLine(double k){
        return new TLine2D(-this.getSlope(), 2 * k - this.getConstant() );
    }


    public double size(){
        return Math.sqrt( 1 + this.getSlope() * this.getSlope() ); // sqrt(1 + m^2)
    }


    /*  GETTERS  */
    public boolean isParallelToYAxis() { return isParallelToYAxis; }
    public double getSlope() { return slope; }
    public double getConstant() { return constant; }
    public double getX0() { return x0; }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /** Returns the distance between two parallel lines
     * @param line1 any line
     * @param line2 any line that's parallel to line1
     * @return The orthogonal distance between two parallel lines
     */
    public static double distanceBetweenTwoParallelLines(TLine2D line1, TLine2D line2){
        if(!line1.isParallelTo(line2)) throw new LinesAreNotParallelException(line1, line2);
        return Math.abs(line1.getConstant() - line2.getConstant()) / line1.size();
    }


    /**
     * @param line1 any line
     * @param line2 any line
     * @return The angle between line1 and line2
     */
    public static double angleBetweenTwoLines(TLine2D line1, TLine2D line2){
        /* if both lines are NOT parallel to y axis, use (m1-m2)/(1+m1*m2) to find the tangent of angle
        if both lines are parallel to y axis, the angle will be 0 because they're parallel to each other
        if one of them are parallel to y axis, the angle will be equal to the angle between the y axis and
        the other line so it will be (pi/2 - theta) where tan(theta) = m  */
        double result = 0;
        if( !line1.isParallelToYAxis() && !line2.isParallelToYAxis() )
            result = Math.atan( (line1.getSlope() - line2.getSlope()) / ( 1 + (line1.getSlope() * line2.getSlope()) )  );
        else if( line1.isParallelToYAxis() && !line2.isParallelToYAxis() )
            result = (Math.PI / 2) - Math.atan( line2.getSlope() );
        else if( !line1.isParallelToYAxis() && line2.isParallelToYAxis() )
            result = (Math.PI / 2) - Math.atan( line1.getSlope() );
        else if( line1.isParallelToYAxis() && line2.isParallelToYAxis() )
            result = 0;
        return result;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        if(this.isParallelToYAxis()) return String.format("x = %.5f", this.x0);

        if(this.slope == 0 && this.constant == 0) return "y = 0";
        else if(this.slope == 0 && this.constant != 0) return String.format("y = %.5f", this.constant);
        else if(this.slope != 0 && this.constant == 0) return String.format("y = %.5f x", this.slope);
        else return String.format("y = %.5f x + %.5f", this.slope, this.constant);
    }



}
