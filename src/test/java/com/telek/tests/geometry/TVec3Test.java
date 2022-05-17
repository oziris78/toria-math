package com.telek.tests.geometry;

import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.geometry.vectors.TVec3;
import com.telek.telekmath.utils.TMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TVec3Test {

    @Test
    @DisplayName("tvec3dTest")
    void tvec3DTest() {

        TVec3 v1 = new TVec3(0, 1, -1);
        TVec3 v2 = new TVec3(0, 0, 0);
        TVec3 v3 = new TVec3(0, 2, 7);
        TVec3 v4 = new TVec3(0, 2, 7);

        Assertions.assertNotEquals(v1, v2);
        Assertions.assertNotEquals(v1, v3);
        Assertions.assertNotEquals(v1, v4);
        Assertions.assertEquals(v3, v4);

        Assertions.assertEquals(v1.cross(v3), new TVec3(9,0,0));
        Assertions.assertEquals(v3.projection(v1), new TVec3(0,-2.5d,2.5d));
        Assertions.assertEquals(TVec3.distanceBetween(v1,v3), Math.sqrt(1 + 64));
        Assertions.assertEquals(v1.dot(v3), -5);
        Assertions.assertEquals(v3.unit(), new TVec3(0, 2d / Math.sqrt(53), 7d / Math.sqrt(53)));

        Assertions.assertEquals(v1.toString(), "<0, 1, -1>");

        Assertions.assertEquals(v1.length(), TMathConstants.SQRT2);
        Assertions.assertEquals(v2.length(), 0);
        Assertions.assertEquals(v3.length(), Math.sqrt(53));

        Assertions.assertFalse(v1.isUnitVector());
        Assertions.assertFalse(v2.isUnitVector());
        Assertions.assertFalse(v3.isUnitVector());

        Assertions.assertFalse(v1.isZeroVector());
        Assertions.assertTrue(v2.isZeroVector());
        Assertions.assertFalse(v3.isZeroVector());

        Assertions.assertEquals(v1.scale(2,3,4), new TVec3(0,3,-4));
        Assertions.assertEquals(v3.scale(1d/2d), new TVec3(0, 1, 3.5d));

        Assertions.assertEquals(v1.add(0, -1, 1), v2);
        Assertions.assertEquals(v1.add(new TVec3(0,-1,1)), v2);

        Assertions.assertEquals(v1.subtract(0, 1, -1), v2);
        Assertions.assertEquals(v1.subtract(new TVec3(0,1,-1)), v2);

    }


    @Test
    @DisplayName("oldPoint3dTest")
    void oldPoint3dTest() {

        TVec3 p1 = new TVec3(0, 10, 1);
        TVec3 p2 = new TVec3(0, -10, -1);
        TVec3 p3 = new TVec3(0, 0, 0);
        TVec3 p4 = new TVec3(1,1,1);
        TVec3 p5 = new TVec3(2,2,1);

        Assertions.assertEquals(p1, new TVec3(0, 10, 1));
        Assertions.assertEquals(p2, new TVec3(0, -10, -1));
        Assertions.assertEquals(p3, new TVec3(0, 0, 0));

        Assertions.assertEquals(TVec3.midpoint(p1, p2), p3);

        Assertions.assertEquals(p4.length(), TMathConstants.SQRT3);
        Assertions.assertEquals(p5.length(), 3);

        Assertions.assertTrue(TMath.areEqual(TVec3.distanceBetween(p2, p4), 11.224972));

    }



}
