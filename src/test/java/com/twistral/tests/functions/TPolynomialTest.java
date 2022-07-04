package com.twistral.tests.functions;

import com.twistral.toriamath.core.functions.TRange;
import com.twistral.toriamath.core.functions.oned.TPolynomial;
import com.twistral.toriamath.utils.TMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TPolynomialTest {


    @Test
    @DisplayName("polyTest")
    void polyTest() {
        TPolynomial p1 = new TPolynomial(); // 0
        TPolynomial p2 = new TPolynomial(TRange.MONE_TO_ONE); // 0
        TPolynomial p3 = new TPolynomial(TRange.MONE_TO_ONE, 1); // 1
        TPolynomial p4 = new TPolynomial(TRange.MONE_TO_ONE, 0); // 0
        TPolynomial p5 = new TPolynomial(TRange.MONE_TO_ONE, -0, 1, -2, 3, -4); // x - 2x^2 + 3x^3 - 4x^4
        TPolynomial p6 = new TPolynomial(1); // 1
        TPolynomial p7 = new TPolynomial(0); // 0
        TPolynomial p8 = new TPolynomial(-70, 1, -2, -3, -4); // -70 + x - 2x^2 - 3x^3 -4x^4

        double a;
        a = 0d;
        Assertions.assertTrue(TMath.areEqual(p1.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p2.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p3.value(a), 1));
        Assertions.assertTrue(TMath.areEqual(p4.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p5.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p6.value(a), 1));
        Assertions.assertTrue(TMath.areEqual(p7.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p8.value(a), -70));

        a = 2d;
        Assertions.assertTrue(TMath.areEqual(p1.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p2.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p3.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p4.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p5.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p6.value(a), 1));
        Assertions.assertTrue(TMath.areEqual(p7.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p8.value(a), -164));

        a = -0.8d;
        Assertions.assertTrue(TMath.areEqual(p1.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p2.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p3.value(a), 1));
        Assertions.assertTrue(TMath.areEqual(p4.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p5.value(a), -5.2544));
        Assertions.assertTrue(TMath.areEqual(p6.value(a), 1));
        Assertions.assertTrue(TMath.areEqual(p7.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p8.value(a), -72.1824));

        /////////////////////////////////////////////////

        a = 0d;
        Assertions.assertTrue(TMath.areEqual(p1.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p2.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p3.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p4.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p6.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p7.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p5.derivative().value(a), 1));
        Assertions.assertTrue(TMath.areEqual(p8.derivative().value(a), 1));

        a = 2d;
        Assertions.assertTrue(TMath.areEqual(p1.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p2.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p3.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p4.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p6.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p7.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p5.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p8.derivative().value(a), -171));

        a = -0.8d;
        Assertions.assertTrue(TMath.areEqual(p1.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p2.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p3.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p4.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p6.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p7.derivative().value(a), 0));
        Assertions.assertTrue(TMath.areEqual(p5.derivative().value(a), 18.152));
        Assertions.assertTrue(TMath.areEqual(p8.derivative().value(a), 6.632));

        ///////////////////////////////////////////////////////////////

        p1 = new TPolynomial(1, 3, 0, 0, 0, 0, 4, 0, 0); // 1 + 3x + 4x^6
        p2 = new TPolynomial(7, 8, 9, 1, 0, 1, 0); // 7 + 8x + 9x^2 + x^3 + x^5
        p3 = new TPolynomial(2, 5, 7); // 2 + 5x + 7x^2

        a = 1.56d;
        Assertions.assertTrue(TMath.areEqual(p1.value(a), 63.3310977802d));
        Assertions.assertTrue(TMath.areEqual(p2.value(a), 54.4177739776d));
        Assertions.assertTrue(TMath.areEqual(p3.value(a), 26.8352d));
        Assertions.assertTrue(TMath.areEqual(p1.derivative().value(a), 224.734991462d));
        Assertions.assertTrue(TMath.areEqual(p2.derivative().value(a), 72.9928448d));
        Assertions.assertTrue(TMath.areEqual(p3.derivative().value(a), 26.84d));
        Assertions.assertTrue(TMath.areEqual(p1.add(p2).value(a), 117.748871758d));
        Assertions.assertTrue(TMath.areEqual(p1.add(p3).value(a), 90.1662977802d));
        Assertions.assertTrue(TMath.areEqual(p2.add(p3).value(a), 81.2529739776d));
        Assertions.assertTrue(TMath.areEqual(p1.subtract(p2).value(a), 8.91332380262d));
        Assertions.assertTrue(TMath.areEqual(p1.subtract(p3).value(a), 36.4958977802d));
        Assertions.assertTrue(TMath.areEqual(p2.subtract(p3).value(a), 27.5825739776d));
        Assertions.assertTrue(TMath.areEqual(p1.multiply(p2).value(a), 3446.33736476d));
        Assertions.assertTrue(TMath.areEqual(p1.multiply(p3).value(a), 1699.50267515d));
        Assertions.assertTrue(TMath.areEqual(p2.multiply(p3).value(a), 1460.31184824d));

        Assertions.assertTrue(TMath.areEqual(p1.add(7, 8, 9, 1, 0, 1, 0).value(a), 117.748871758d));
        Assertions.assertTrue(TMath.areEqual(p1.add(2, 5, 7).value(a), 90.1662977802d));
        Assertions.assertTrue(TMath.areEqual(p2.add(2, 5, 7).value(a), 81.2529739776d));
        Assertions.assertTrue(TMath.areEqual(p1.subtract(7, 8, 9, 1, 0, 1, 0).value(a), 8.91332380262d));
        Assertions.assertTrue(TMath.areEqual(p1.subtract(2, 5, 7).value(a), 36.4958977802d));
        Assertions.assertTrue(TMath.areEqual(p2.subtract(2, 5, 7).value(a), 27.5825739776d));
        Assertions.assertTrue(TMath.areEqual(p1.multiply(7, 8, 9, 1, 0, 1, 0).value(a), 3446.33736476d));
        Assertions.assertTrue(TMath.areEqual(p1.multiply(2, 5, 7).value(a), 1699.50267515d));
        Assertions.assertTrue(TMath.areEqual(p2.multiply(2, 5, 7).value(a), 1460.31184824d));

        Assertions.assertTrue(TMath.areEqual(p1.getCoefficientOfDegree(0), 1));
        Assertions.assertTrue(TMath.areEqual(p1.getCoefficientOfDegree(1), 3));
        Assertions.assertTrue(TMath.areEqual(p1.getCoefficientOfDegree(2), 0));
        Assertions.assertTrue(TMath.areEqual(p1.getCoefficientOfDegree(3), 0));
    }


}
