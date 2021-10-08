package com.telek.telekmath.special;

import com.telek.telekmath.core.geometry.lines.*;
import com.telek.telekmath.core.geometry.points.*;


public class AnalyticGeometry {

    /**
     * @param p1 any corner of a triangle
     * @param p2 any corner of a triangle
     * @param p3 any corner of a triangle
     * @return The area of the triangle defined by p1,p2,p3
     */
    public static double areaOfTriangle(TPoint3D p1, TPoint3D p2, TPoint3D p3){
        double D = (p2.x * p1.y) + (p3.x * p2.y) + (p1.x * p3.y);
        double E = (p1.x * p2.y) + (p2.x * p3.y) + (p3.x * p1.y);
        return Math.abs(D-E) / 2;
    }


    /**
     * @param point any point
     * @param line any line
     * @return The orthogonal distance between point and line
     */
    public static double distanceBetweenPointAndLine(TPoint2D point, TLine2D line){
        return Math.abs( point.y - line.getSlope() * point.x - line.getConstant() )  / line.size();
    }


}
