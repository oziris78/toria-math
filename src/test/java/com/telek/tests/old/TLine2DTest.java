package com.telek.tests.old;

import com.telek.telekmath.core.geometry.lines.TLine2D;
import com.telek.telekmath.core.geometry.points.TPoint2D;


public class TLine2DTest {

    public static void main(String[] args) {

        {
            TLine2D line1 = new TLine2D(new TPoint2D(2, 3), new TPoint2D(2, 4));  // x = 2
            TLine2D line2 = new TLine2D(new TPoint2D(0, 0), new TPoint2D(1, 1));  // y = x
            TLine2D line3 = new TLine2D(new TPoint2D(0, 0), new TPoint2D(-1, 1));  // y = -x
            TLine2D line4 = new TLine2D(new TPoint2D(20, 3), new TPoint2D(20, 4));  // x = 20

            System.out.println(line1);
            System.out.println(line2);
            System.out.println(line3);
            System.out.println(line4);

            System.out.println(line1.isOrthogonalTo(line4));
            System.out.println(line2.isOrthogonalTo(line3));
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        {
            TLine2D line1 = new TLine2D(1d, 0d); // y = x, m=1, n=0
            TLine2D line2 = new TLine2D(-1d, 0d); // y = -x, m=-1, n=0

            System.out.println(TLine2D.angleBetweenTwoLines(line1, line2)); // pi / 2 = 1.570796...
            System.out.println(TLine2D.angleBetweenTwoLines(line1, line1)); // 0
        }

    }


}
