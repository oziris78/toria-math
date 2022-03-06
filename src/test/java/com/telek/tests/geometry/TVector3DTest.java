package com.telek.tests.geometry;

import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.geometry.vectors.TVector3D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TVector3DTest {

    @Test
    @DisplayName("tvec3dTest")
    void tvec3DTest() {

        TVector3D v1 = new TVector3D(0, 1, -1);
        TVector3D v2 = new TVector3D(0, 0, 0);
        TVector3D v3 = new TVector3D(0, 2, 7);
        TVector3D v4 = new TVector3D(0, 2, 7);

        Assertions.assertNotEquals(v1, v2);
        Assertions.assertNotEquals(v1, v3);
        Assertions.assertNotEquals(v1, v4);
        Assertions.assertEquals(v3, v4);

        Assertions.assertEquals(v1.cross(v3), new TVector3D(9,0,0));
        Assertions.assertEquals(v3.projection(v1), new TVector3D(0,-2.5d,2.5d));
        Assertions.assertEquals(TVector3D.distanceBetweenTwoVectors(v1,v3), Math.sqrt(1 + 64));
        Assertions.assertEquals(v1.dot(v3), -5);
        Assertions.assertEquals(v3.unitVector(), new TVector3D(0, 2d / Math.sqrt(53), 7d / Math.sqrt(53)));

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

        Assertions.assertEquals(v1.scale(2,3,4), new TVector3D(0,3,-4));
        Assertions.assertEquals(v3.scale(1d/2d), new TVector3D(0, 1, 3.5d));

        Assertions.assertEquals(v1.add(0, -1, 1), v2);
        Assertions.assertEquals(v1.add(new TVector3D(0,-1,1)), v2);

        Assertions.assertEquals(v1.subtract(0, 1, -1), v2);
        Assertions.assertEquals(v1.subtract(new TVector3D(0,1,-1)), v2);



    }



}
