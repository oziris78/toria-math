package com.telek.tests.geometry;

import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.geometry.vectors.TVector2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TVector2DTest {

    @Test
    @DisplayName("tvec2dTest")
    void tvec2DTest() {

        TVector2D v1 = new TVector2D(0, 0);
        TVector2D v2 = new TVector2D(1, 1);
        TVector2D v3 = new TVector2D(-1, -1);
        TVector2D v4 = new TVector2D(0, -1);
        TVector2D v5 = new TVector2D(0, 1);
        TVector2D v6 = new TVector2D(0, 1);

        Assertions.assertNotEquals(v1, v2);
        Assertions.assertNotEquals(v1, v3);
        Assertions.assertNotEquals(v1, v4);
        Assertions.assertNotEquals(v1, v5);
        Assertions.assertNotEquals(v1, v6);
        Assertions.assertEquals(v5, v6);



        Assertions.assertEquals(v1.add(1, 1), v2);

        Assertions.assertEquals(v5.scale(2), new TVector2D(0,2));

        Assertions.assertTrue(TMath.areEqual(TVector2D.angleBetweenTwoVectors(v2, v5), TMathConstants.PI_OVER_TWO / 2d));

        Assertions.assertEquals(v3.unitVector(), new TVector2D(-1d / TMathConstants.SQRT2,
                -1d / TMathConstants.SQRT2));

        Assertions.assertEquals(v1.dot(v2), 0);
        Assertions.assertEquals(v3.dot(v2), -2);

        Assertions.assertEquals(TVector2D.distanceBetweenTwoVectors(v1, v2), TMathConstants.SQRT2);
        Assertions.assertEquals(TVector2D.distanceBetweenTwoVectors(v3, v2), 2*TMathConstants.SQRT2);

        Assertions.assertEquals(v1.length(), 0);
        Assertions.assertEquals(v2.length(), TMathConstants.SQRT2);
        Assertions.assertEquals(v3.length(), TMathConstants.SQRT2);
        Assertions.assertEquals(v4.length(), 1);

        Assertions.assertEquals(v4.isUnitVector(), true);
        Assertions.assertEquals(v4.isZeroVector(), false);

        Assertions.assertEquals(v1.isZeroVector(), true);



    }


}
