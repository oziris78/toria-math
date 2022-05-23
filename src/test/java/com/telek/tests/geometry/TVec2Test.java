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
        TVec2 v1 = new TVec2();
        TVec2 v2 = new TVec2(1, 1);

        Assertions.assertNotEquals(v1, v2);
        Assertions.assertEquals(v1, v1.copy());
        Assertions.assertEquals(v1, new TVec2(v1));
        Assertions.assertEquals(v1, v1);

        Assertions.assertTrue(TMath.areEqual(v1.getX(), 0d));
        Assertions.assertTrue(TMath.areEqual(v1.setX(10d).getX(), 10d));
        Assertions.assertTrue(TMath.areEqual(v1.setY(-10d).getY(), -10d));
        Assertions.assertTrue(TMath.areEqual(v1.set(0d, 0d).getY(), 0d));

        Assertions.assertEquals(v1.add(v2), v2);
        Assertions.assertEquals(v1.subtract(v2), TVec2.ZERO);
        Assertions.assertEquals(v1.add(1d, 1d), v2);
        Assertions.assertEquals(v1.subtract(1d, 1d), TVec2.ZERO);
        Assertions.assertEquals(v1.set(10d, 1d).scale(2d), new TVec2(20d, 2d));
        Assertions.assertEquals(v1.set(10d, 1d).scale(2d, 20d), new TVec2(20d, 20d));
        Assertions.assertEquals(v1.set(50d, 0d).unit(), new TVec2(1d, 0d));
        Assertions.assertEquals(v1.set(1d, 1d).unit(), new TVec2(1d / TMathConstants.SQRT2, 1d / TMathConstants.SQRT2));

        v1.set(10d, 20d);
        v2.set(-2d, 2d);
        Assertions.assertTrue(TMath.areEqual(v1.dot(v2), v2.dot(10d, 20d)));
        Assertions.assertTrue(TMath.areEqual(v1.dot(v2), -20d + 40d));
        Assertions.assertTrue(TMath.areEqual(v1.lengthSquared(), 500d));
        Assertions.assertTrue(TMath.areEqual(v1.length(), TMath.sqrt(500d)));

        v1.set(1d, 1d);
        Assertions.assertEquals(v1.copy().rotate90DegClockwise(), new TVec2(1d, -1d));
        Assertions.assertEquals(v1.copy().rotate180DegClockwise(), new TVec2(-1d, -1d));
        Assertions.assertEquals(v1.copy().rotate90DegAntiClockwise(), new TVec2(-1d, 1d));
        Assertions.assertEquals(v1.copy().symmetricalVectorToTheAxes(true, true), new TVec2(-1d, -1d));
        Assertions.assertEquals(v1.copy().symmetricalVectorTo(TVec2.ZERO), new TVec2(-1d, -1d));

        v1.set(0.000000001d, 0d);
        Assertions.assertTrue(v1.isZeroVector());
        v1.set(0.001d, 0d);
        Assertions.assertFalse(v1.isZeroVector());

        v1.set(1d, 0d);
        Assertions.assertTrue(v1.isUnitVector());
        v1.set(1d, 1d);
        Assertions.assertFalse(v1.isUnitVector());


        Assertions.assertTrue(TMath.areEqual(TVec2.angleBetween(new TVec2(1d, 1d), new TVec2(0d, 1d)),
                TMathConstants.PI_OVER_TWO / 2d));
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

        Assertions.assertEquals(p1.copy().add(0, -10), p3);

        Assertions.assertEquals(p1.length(), p2.length());
        Assertions.assertEquals(p2.length(), 10);
        Assertions.assertEquals(p3.length(), 0);
        Assertions.assertTrue(TMath.areEqual(p4.length(), 1d / TMathConstants.SQRT2));


        Assertions.assertEquals(p1.copy().rotate90DegClockwise().rotate90DegClockwise(), p1.copy().rotate180DegClockwise());

        Assertions.assertEquals(p1.copy().rotate90DegClockwise().rotate90DegClockwise().rotate90DegClockwise(),
                p1.copy().rotate90DegAntiClockwise());


        Assertions.assertEquals(p2.copy().symmetricalVectorTo(p1), new TVec2(0, 30));

        Assertions.assertEquals(p2.copy().symmetricalVectorToTheAxes(false, true), p2);
        Assertions.assertEquals(p2.copy().symmetricalVectorToTheAxes(true, true ), p1);

        Assertions.assertEquals(p4.copy().scale(2d), new TVec2(1, -1));
    }



}
