package com.twistral.tests.geometry;

import com.twistral.toriamath.core.geometry.vectors.TVec2;
import com.twistral.toriamath.utils.TMath;
import com.twistral.toriamath.utils.TMathConsts;
import com.twistral.toriamath.core.geometry.TLine2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TLine2DTest {

    @Test
    @DisplayName("line2dTest")
    void line2DTest() {
        TLine2D l1 = new TLine2D(1, 0);       // y = x
        TLine2D l2 = new TLine2D(-1, 0);      // y = -x
        TLine2D l3 = new TLine2D(-1, 1, 0);   // y = x
        TLine2D l4 = new TLine2D(1, 1, 0);    // y = -x
        TLine2D l5 = new TLine2D(1, 5);       // y = x +5
        TLine2D yAxis = new TLine2D(new TVec2(0,0), new TVec2(0, 10)); // x = 0

        Assertions.assertEquals(l1, l3);
        Assertions.assertEquals(l2, l4);
        Assertions.assertNotEquals(l1, l2);
        Assertions.assertNotEquals(l1, l4);
        Assertions.assertNotEquals(l1, l5);
        Assertions.assertNotEquals(l2, l5);
        Assertions.assertNotEquals(l3, l5);
        Assertions.assertNotEquals(l4, l5);

        Assertions.assertEquals(TLine2D.angleBetweenTwoLines(l1, l2), TMathConsts.PI_OVER_TWO);
        Assertions.assertEquals(TLine2D.angleBetweenTwoLines(l2, l1), TMathConsts.PI_OVER_TWO);

        Assertions.assertTrue(TMath.areEqual(
                TLine2D.distanceBetweenPointAndLine(new TVec2(10, 20), new TLine2D(123, 234, 345)),
                23.66112169729583d
        ));

        Assertions.assertEquals(TLine2D.intersectionPointBetweenTwoLines(l1, l2), TVec2.ZERO);
        Assertions.assertEquals(TLine2D.intersectionPointBetweenTwoLines(l1, l5), null);

        Assertions.assertEquals(l1.getSymmetricalLineTo(false, true), l2);
        Assertions.assertEquals(l1.getSymmetricalLineTo(true, true), l1);

        Assertions.assertFalse(l1.isParallelToYAxis());
        Assertions.assertFalse(l2.isParallelToYAxis());
        Assertions.assertFalse(l4.isParallelToYAxis());
        Assertions.assertTrue(yAxis.isParallelToYAxis());

        Assertions.assertTrue(l1.isParallelTo(l3));
        Assertions.assertTrue(l2.isParallelTo(l4));

        Assertions.assertTrue(l2.isOrthogonalTo(l3));


        Assertions.assertTrue(
                TMath.areEqual(TLine2D.distanceBetweenTwoParallelLines(new TLine2D(2.65d, -13), new TLine2D(2.65d, 4)),
                6.002d
        ));

        Assertions.assertEquals(l1.size(), TMathConsts.SQRT2);
        Assertions.assertEquals(l3.size(), l1.size());

        Assertions.assertEquals(l1, l3);
        Assertions.assertEquals(l2, l4);



    }

}
