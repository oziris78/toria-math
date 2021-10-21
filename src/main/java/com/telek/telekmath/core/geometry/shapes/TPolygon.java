package com.telek.telekmath.core.geometry.shapes;

import com.telek.telekmath.core.geometry.points.TPoint2D;

import java.util.LinkedList;

public class TPolygon {


    /*  FIELDS  */

    private LinkedList<TPoint2D> points;


    /*  CONSTRUCTORS  */

    public TPolygon(LinkedList<TPoint2D> points){
        this.points = points;
    }

    public TPolygon(TPoint2D... points){
        this(new LinkedList<>());
        for(TPoint2D p : points) this.points.add(p);
    }


    /*  METHODS  */





    /*  GETTERS AND SETTERS  */


}
