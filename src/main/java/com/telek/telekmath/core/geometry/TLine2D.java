package com.telek.telekmath.core.geometry;

import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.geometry.points.TPoint2D;
import com.telek.telekmath.utils.TelekMathException.*;
import com.telek.telekutils.colors.TColor;

import java.util.Objects;



/**
 * An immutable 2D line class. <br>
 * Defines a mathematical line in form  ax + by + c = 0 <br>
 * All methods either return a numeric value or <b>a new {@link TLine2D} instance</b>.
 */
public class TLine2D {

    /*   ax + by + c = 0   <=>   y = mx + n
    *       =>  m = -a/b,  n = -c/b
    *
     *    if b = 0 then it's in form x = k  (parallel to y axis)
     *    ax + by + c = 0, b = 0   <=>   x = k
     *       =>  k = -c/a
     * */

    private final double a, b, c;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    public TLine2D(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }



    /**
     * Defines a line in form  y = mx + n
     * @param m specifies m
     * @param n specifies n
     */
    public TLine2D(double m, double n){
        this(-m, 1d, -n);
    }



    public TLine2D(TPoint2D pointOneOnLine, TPoint2D pointTwoOnLine){
        double x1 = pointOneOnLine.x;
        double x2 = pointTwoOnLine.x;
        double y1 = pointOneOnLine.y;
        double y2 = pointTwoOnLine.y;

        if( x1 == x2 ){ // x = k
            this.a = 1d;
            this.b = 0d;
            this.c = -x1;
        }
        else{ // ax+by+c=0
            this.a = -(y1-y2)/(x1-x2);
            this.b = 1d;
            this.c = -this.a * x1 - y1;
        }
    }



    ///////////////
    /*  METHODS  */
    ///////////////


    /**
     * @param line2 any line
     * @return true if this line and line2 are parallel to each other if and only if  m1 = m2
     */
    public boolean isParallelTo(TLine2D line2){
        boolean firstParallel = this.isParallelToYAxis();
        boolean secondParallel = line2.isParallelToYAxis();

        if( firstParallel && secondParallel ) // x = k, x = k
            return true;
        else if( (!firstParallel && secondParallel) || (firstParallel && !secondParallel) ) // ax+by+c=0, x = k  or otherway around
            return false;
        else // ax+by+c=0, ax+by+c=0
            return ( -a/b ) == ( -line2.a / line2.b ); // m1 == m2
    }





    /**
     * @param line2 any line
     * @return true if this line and line2 are parallel to each other if and only if m1 = m2
     */
    public boolean isOrthogonalTo(TLine2D line2){
        return TMath.areEqual(angleBetweenTwoLines(this, line2), TMathConstants.PI_OVER_TWO);
    }



    public TLine2D getSymmetricalLineTo(boolean isSymmetricalToXAxis, boolean isSymmetricalToYAxis){
        return new TLine2D(
                isSymmetricalToYAxis ? -this.a : this.a,
                isSymmetricalToXAxis ? -this.b : this.b,
                this.c
        );
    }





    /**
     * sqrt(1 + m^2) = sqrt( (a^2 + b^2) / b^2 )
     * @return The size of this line.
     */
    public double size(){
        double a2 = a * a;
        double b2 = b * b;
        double inside = (a2 + b2) / b2;
        return Math.sqrt( inside );
    }




    /**
     * If b = 0 then this line is in form x = k, otherwise it's in the form y = mx + n
     * @return True if this line is in form x = k.
     */
    public boolean isParallelToYAxis() {
        return b == 0;
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////



    /**
     * Finds the intersection point of two lines, returns null if lines do not intersect. (Parallel lines)
     * @param line1 any line
     * @param line2 any line
     * @return Returns the intersection point or null if it doesn't exists
     */
    public static TPoint2D intersectionPointBetweenTwoLines(TLine2D line1, TLine2D line2){
        boolean firstParallel = line1.isParallelToYAxis();
        boolean secondParallel = line2.isParallelToYAxis();
        if(firstParallel && secondParallel){ // x = k, x = k
            double k1 = -line1.c / line1.a;
            double k2 = -line2.c / line2.a;
            // wont intersect here if they have different k values
            if(k1 != k2) return null;

            // infinitely many points here, just return one of them
            return new TPoint2D(k1, 0d);
        }
        else if(!firstParallel && secondParallel){ // ax+by+c=0, x = k
            // always intersects here
            double a = line1.a, b = line1.b, c = line1.c, k = -line2.c / line2.a;
            return new TPoint2D(k, (-a*k-c) / b );
        }
        else if(firstParallel && !secondParallel){ // x = k, ax+by+c=0
            // always intersects here
            double a = line2.a, b = line2.b, c = line2.c, k = -line1.c / line1.a;
            return new TPoint2D(k, (-a*k-c) / b );
        }
        else{ // ax+by+c=0, ax+by+c=0
            double m1 = -line1.a / line1.b;
            double m2 = -line2.a / line2.b;
            double n1 = -line1.c / line1.b;
            double n2 = -line2.c / line2.b;
            // wont intersect here if they have the same m and different n
            if(m1 == m2 && n1 != n2) return null;

            // always intersects here
            double x = - (n1-n2) / (m1-m2);
            double y = m1 * x + n1;

            return new TPoint2D(x, y);
        }
    }



    /** Returns the distance between two parallel lines
     * @param line1 any line
     * @param line2 any line that's parallel to line1
     * @return The orthogonal distance between two parallel lines
     */
    public static double distanceBetweenTwoParallelLines(TLine2D line1, TLine2D line2){
        if(!line1.isParallelTo(line2)) throw new LinesAreNotParallelException(line1, line2);
        return Math.abs(line2.c - line1.c) / Math.sqrt( line1.a * line1.a + line1.b * line1.b ); // abs(c2-c1) / sqrt(a^2+b^2)
    }



    /**
     * @param point any point
     * @param line any line
     * @return The orthogonal distance between point and line
     */
    public static double distanceBetweenPointAndLine(TPoint2D point, TLine2D line){
        return Math.abs( line.a * point.x + line.b * point.y + line.c )  / Math.sqrt( line.a * line.a + line.b * line.b );
    }



    /**
     * This method will return the angle between the lines in range [0, PI/2]. <br>
     * Use (180deg - angle) to find the obtuse angle.
     * @param line1 any line
     * @param line2 any line
     * @return The angle between line1 and line2
     */
    public static double angleBetweenTwoLines(TLine2D line1, TLine2D line2){
        // https://www.cuemath.com/geometry/angle-between-two-lines/
        boolean firstParallel = line1.isParallelToYAxis();
        boolean secondParallel = line2.isParallelToYAxis();

        if(firstParallel && secondParallel){ // x = k, x = k
            return 0d;
        }
        else{
            double a1 = line1.a, a2 = line2.a;
            double b1 = line1.b, b2 = line2.b;
            return Math.abs(Math.atan( (a2 * b1 - a1 * b2) / (a1 * a2 + b1 * b2) ));
        }
    }






    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public String toString() {
        if(this.isParallelToYAxis()) return String.format("x = %f", -c/a).replaceAll("-0.000000", "0");


        // ax+by+c = 0
        // a/b x + y + c/b = 0
        // y = -a/b x -c/b

        return String.format(" y = %fx + %f", -a/b, -c/b)
                .replaceAll(" 0.000000x \\+ ", "")
                .replaceAll("\\+ \\-", "- ")
                .replaceAll("\\+ 0.000000 =", "=")
                .replaceAll("\\- 0.000000 =", "=")
                .replaceAll("1.000000x", "x")
                .replaceAll("-1.000000x", "x")
                .trim();
        /* was like this:
        return String.format(" %fx + %fy + %f = 0", a, b, c)
                .replaceAll(" 0.000000x \\+ ", "")
                .replaceAll("0.000000y \\+ ", "")
                .replaceAll("\\+ \\-", "- ")
                .replaceAll("\\+ 0.000000 =", "=")
                .replaceAll("\\- 0.000000 =", "=")
                .replaceAll("1.000000y", "y")
                .replaceAll("1.000000x", "x")
                .replaceAll("-1.000000x", "x")
                .replaceAll("-1.000000y", "y")
                .trim();
        */
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TLine2D line2 = (TLine2D) o;
        // -x + y = 0 and x - y = 0 are the same thing so check them both here:
        boolean b1 = this.a == line2.a && this.b == line2.b && this.c == line2.c;
        boolean b2 = this.a == -line2.a && this.b == -line2.b && this.c == -line2.c;
        return b1 || b2;
    }


    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }



}
