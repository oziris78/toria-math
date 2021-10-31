package com.telek.telekmath.core.geometry.shapes;

import com.telek.telekmath.core.geometry.points.TPoint2D;


public class TTriangle {

    private TPoint2D p1, p2, p3;


    /*  CONSTRUCTORS  */

    public TTriangle(TPoint2D p1, TPoint2D p2, TPoint2D p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public TTriangle(double x1, double y1, double x2, double y2, double x3, double y3){
        this( new TPoint2D(x1,y1), new TPoint2D(x2,y2), new TPoint2D(x3,y3) );
    }

    /*  METHODS  */


    public void moveBy(double xAmount, double yAmount){
        this.p1.moveBy(xAmount, yAmount);
        this.p2.moveBy(xAmount, yAmount);
        this.p3.moveBy(xAmount, yAmount);
    }


    public double area(){
        double D = (p2.x * p1.y) + (p3.x * p2.y) + (p1.x * p3.y);
        double E = (p1.x * p2.y) + (p2.x * p3.y) + (p3.x * p1.y);
        return Math.abs(D-E) / 2;
    }


    public double[] getAsDoubleArray(){
        return new double[]{ p1.x, p1.y, p2.x, p2.y, p3.x, p3.y };
    }

    public float[] getAsFloatArray(){
        return new float[]{ (float) p1.x, (float) p1.y, (float) p2.x, (float) p2.y, (float) p3.x, (float) p3.y };
    }


    /*  GETTERS AND SETTERS  */

    public void setPoint1(TPoint2D point) { this.p1 = point; }
    public void setPoint2(TPoint2D point) { this.p2 = point; }
    public void setPoint3(TPoint2D point) { this.p3 = point; }

    public TPoint2D getPoint1() { return this.p1; }
    public TPoint2D getPoint2() { return this.p2; }
    public TPoint2D getPoint3() { return this.p3; }



}
