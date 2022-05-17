package com.telek.tests.geometry;

import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.geometry.vectors.TVec2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TVec2Test {

    @Test
    @DisplayName("tvec2dTest")
    void tvec2DTest() {

        TVec2 v1 = new TVec2(0, 0);
        TVec2 v2 = new TVec2(1, 1);
        TVec2 v3 = new TVec2(-1, -1);
        TVec2 v4 = new TVec2(0, -1);
        TVec2 v5 = new TVec2(0, 1);
        TVec2 v6 = new TVec2(0, 1);

        Assertions.assertNotEquals(v1, v2);
        Assertions.assertNotEquals(v1, v3);
        Assertions.assertNotEquals(v1, v4);
        Assertions.assertNotEquals(v1, v5);
        Assertions.assertNotEquals(v1, v6);
        Assertions.assertEquals(v5, v6);



        Assertions.assertEquals(v1.add(1, 1), v2);

        Assertions.assertEquals(v5.scale(2), new TVec2(0,2));

        Assertions.assertTrue(TMath.areEqual(TVec2.angleBetween(v2, v5), TMathConstants.PI_OVER_TWO / 2d));

        Assertions.assertEquals(v3.unit(), new TVec2(-1d / TMathConstants.SQRT2,
                -1d / TMathConstants.SQRT2));

        Assertions.assertEquals(v1.dot(v2), 0);
        Assertions.assertEquals(v3.dot(v2), -2);

        Assertions.assertEquals(TVec2.distanceBetween(v1, v2), TMathConstants.SQRT2);
        Assertions.assertEquals(TVec2.distanceBetween(v3, v2), 2*TMathConstants.SQRT2);

        Assertions.assertEquals(v1.length(), 0);
        Assertions.assertEquals(v2.length(), TMathConstants.SQRT2);
        Assertions.assertEquals(v3.length(), TMathConstants.SQRT2);
        Assertions.assertEquals(v4.length(), 1);

        Assertions.assertEquals(v4.isUnitVector(), true);
        Assertions.assertEquals(v4.isZeroVector(), false);

        Assertions.assertEquals(v1.isZeroVector(), true);
    }



    @Test
    @DisplayName("oldPoint2dTest")
    void oldPoint2dTest() {

        TVec2 p1 = new TVec2(0, 10);
        TVec2 p2 = new TVec2(0, -10);
        TVec2 p3 = new TVec2(0, 0);
        TVec2 p4 = new TVec2(0.5, -0.5);
        TVec2 p5 = new TVec2(0.5, -0.5);

        Assertions.assertNotEquals(p1, p2);
        Assertions.assertNotEquals(p1, p3);
        Assertions.assertNotEquals(p1, p4);
        Assertions.assertNotEquals(p1, p5);
        Assertions.assertEquals(p4, p5);

        Assertions.assertEquals(p1.add(0, -10), p3);

        Assertions.assertEquals(p1.length(), p2.length());
        Assertions.assertEquals(p2.length(), 10);
        Assertions.assertEquals(p3.length(), 0);
        Assertions.assertTrue(TMath.areEqual(p4.length(), 1d / TMathConstants.SQRT2));


        Assertions.assertEquals(p1.rotateClockwise90().rotateClockwise90(), p1.rotateClockwise180());

        Assertions.assertEquals(p1.rotateClockwise90().rotateClockwise90().rotateClockwise90(),
                p1.rotateClockwise270());


        Assertions.assertEquals(TVec2.distanceBetween(p1, p2), 20);

        Assertions.assertEquals(TVec2.midpoint(p1, p2), p3);
        Assertions.assertEquals(p2.symmetricalVectorTo(p1), new TVec2(0, 30));

        Assertions.assertEquals(p2.symmetricalVectorToTheAxes(false, true), p2);
        Assertions.assertEquals(p2.symmetricalVectorToTheAxes(true, true ), p1);

        Assertions.assertEquals(p4.scale(2), new TVec2(1, -1));

    }



}
