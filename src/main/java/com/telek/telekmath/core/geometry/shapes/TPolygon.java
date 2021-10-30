package com.telek.telekmath.core.geometry.shapes;

import com.telek.telekmath.core.geometry.points.TPoint2D;



public class TPolygon {


    /*  FIELDS  */

    private TPoint2D[] points;
    // edges???  default access modifier???


    /*  CONSTRUCTORS  */

    public TPolygon(TPoint2D... points){
        this.points = points;
    }


    /*  METHODS  */

    public TTriangle[] earClippingTriangulation(){
        return null;
    }



    /*  GETTERS AND SETTERS  */

    public TPoint2D[] getPoints() {return points;}


}
