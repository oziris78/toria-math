package com.telek.telekmath.special;

import com.telek.telekmath.core.geometry.lines.*;
import com.telek.telekmath.core.geometry.points.*;


public class AnalyticGeometry {


    /**
     * @param point any point
     * @param line any line
     * @return The orthogonal distance between point and line
     */
    public static double distanceBetweenPointAndLine(TPoint2D point, TLine2D line){
        return Math.abs( point.y - line.getSlope() * point.x - line.getConstant() )  / line.size();
    }



}
