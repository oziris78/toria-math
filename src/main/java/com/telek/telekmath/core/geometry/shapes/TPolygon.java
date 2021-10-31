package com.telek.telekmath.core.geometry.shapes;

import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.geometry.lines.TLine2D;
import com.telek.telekmath.core.geometry.points.TPoint2D;

import java.util.ArrayList;
import java.util.Arrays;


public class TPolygon {


    /*  FIELDS  */

    private TPoint2D[] points;
    // edges???  default access modifier???


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

    public TTriangle[] triangulate(){
        int len = this.points.length;
        ArrayList<TTriangle> triangulatedTriangles = new ArrayList<>(); // returns n-2 triangles

        for (int i = 0; i < len-1; i++) {

            TPoint2D prePoint = this.points[ (i - 1 + len) % len ];
            TPoint2D curPoint = this.points[ i ];
            TPoint2D nextPoint = this.points[ (i+1) % len ];

            TLine2D edge1 = new TLine2D(prePoint, curPoint);
            TLine2D edge2 = new TLine2D(curPoint, nextPoint);

            double angle = TLine2D.angleBetweenTwoLines(edge1, edge2);
            boolean ear = angle < TMathConstants.PI;

            if(ear){
                triangulatedTriangles.add(new TTriangle(prePoint, curPoint, nextPoint));
            }

        }

        TTriangle[] triangles = new TTriangle[len-2];
        for (int i = 0; i < triangles.length; i++) {
            triangles[i] = triangulatedTriangles.get(i);
        }

        return triangles;
    }



    /*  GETTERS AND SETTERS  */

    public TPoint2D[] getPoints() {return points;}


}
