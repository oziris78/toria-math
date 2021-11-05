package com.telek.tests.old;

import com.telek.telekmath.core.geometry.points.TPoint2D;

public class TPoint2DTest {

    public static void main(String[] args) {

        TPoint2D p1 = new TPoint2D(0,0);
        TPoint2D p2 = new TPoint2D(-1,1);

        System.out.println(TPoint2D.midPoint(p1,p2));
        System.out.println(p1.distance(p2));
        System.out.println(p1.distanceFromOrigin());
        System.out.println(p2.distanceFromOrigin());

    }

}
