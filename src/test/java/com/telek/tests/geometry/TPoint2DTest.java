package com.telek.tests.geometry;

import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.geometry.points.TPoint2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TPoint2DTest {


    @Test
    @DisplayName("point2dTest")
    void point2DTest() {

        TPoint2D p1 = new TPoint2D(0, 10);
        TPoint2D p2 = new TPoint2D(0, -10);
        TPoint2D p3 = new TPoint2D(0, 0);
        TPoint2D p4 = new TPoint2D(0.5, -0.5);
        TPoint2D p5 = new TPoint2D(0.5, -0.5);

        Assertions.assertNotEquals(p1, p2);
        Assertions.assertNotEquals(p1, p3);
        Assertions.assertNotEquals(p1, p4);
        Assertions.assertNotEquals(p1, p5);
        Assertions.assertEquals(p4, p5);

        Assertions.assertEquals(p1.moveBy(0, -10), p3);

        Assertions.assertEquals(p1.distanceFromOrigin(), p2.distanceFromOrigin());
        Assertions.assertEquals(p2.distanceFromOrigin(), 10);
        Assertions.assertEquals(p3.distanceFromOrigin(), 0);
        Assertions.assertEquals(TMath.areEqual(p4.distanceFromOrigin(),1d / TMathConstants.SQRT2), true);


        Assertions.assertEquals(p1.rotateClockwise90().rotateClockwise90(), p1.rotateClockwise180());

        Assertions.assertEquals(p1.rotateClockwise90().rotateClockwise90().rotateClockwise90(),
                p1.rotateClockwise270());


        Assertions.assertEquals(TPoint2D.distanceBetweenTwoPoints(p1, p2), 20);

        Assertions.assertEquals(TPoint2D.midPoint(p1, p2), p3);
        Assertions.assertEquals(p2.getSymmetricalPointToAnotherPoint(p1), new TPoint2D(0, 30));

        Assertions.assertEquals(p2.getSymmetricalPointToTheAxes(false, true), p2);
        Assertions.assertEquals(p2.getSymmetricalPointToTheAxes(true, true ), p1);

        Assertions.assertEquals(p4.scale(2), new TPoint2D(1, -1));

    }


}
