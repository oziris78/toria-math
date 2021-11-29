package com.telek.telekmath.core.geometry.shapes;

import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.geometry.lines.TLine2D;
import com.telek.telekmath.core.geometry.points.TPoint2D;

import java.util.ArrayList;
import java.util.Arrays;


public class TPolygon {


    /*  FIELDS  */

    private TPoint2D[] points;



    /*  CONSTRUCTORS  */


    /**
     * The order you insert these points will determine the edges, so be careful!
     * @param points all of the points IN ORDER
     */
    public TPolygon(TPoint2D... points){
        TPoint2D firstPoint = points[0];
        TPoint2D lastPoint = points[points.length-1];
        if(firstPoint.x == lastPoint.x && firstPoint.x == lastPoint.y){
            this.points = Arrays.copyOfRange(points, 0, points.length);
        }
        else{
            this.points = points;
        }
    }


    /*  METHODS  */

    public void scale(double scale){
        for (int i = 0; i < points.length; i++)
            points[i].scale(scale);
    }

    public void moveBy(double xAmount, double yAmount){
        for (int i = 0; i < points.length; i++)
            points[i].moveBy(xAmount, yAmount);
    }



    /*  GETTERS AND SETTERS  */

    public TPoint2D[] getPoints() {return points;}


}
