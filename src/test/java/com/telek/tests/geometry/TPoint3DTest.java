package com.telek.tests.geometry;

import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.geometry.points.TPoint3D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TPoint3DTest {

    @Test
    @DisplayName("point3dTest")
    void point3DTest() {

        TPoint3D p1 = new TPoint3D(0, 10, 1);
        TPoint3D p2 = new TPoint3D(0, -10, -1);
        TPoint3D p3 = new TPoint3D(0, 0, 0);
        TPoint3D p4 = new TPoint3D(1,1,1);
        TPoint3D p5 = new TPoint3D(2,2,1);

        Assertions.assertEquals(p1, new TPoint3D(0, 10, 1));
        Assertions.assertEquals(p2, new TPoint3D(0, -10, -1));
        Assertions.assertEquals(p3, new TPoint3D(0, 0, 0));

        Assertions.assertEquals(TPoint3D.midPoint(p1,p2), p3);

        Assertions.assertEquals(p4.distanceFromOrigin(), TMathConstants.SQRT3);
        Assertions.assertEquals(p5.distanceFromOrigin(), 3);

        Assertions.assertEquals(TMath.areEqual(p2.distance(p4),11.224972), true);

    }

}
