package com.telek.tests.zsingle;

import com.telek.telekmath.core.geometry.points.TPoint2D;
import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.functions.other.TPolynomial;
import com.telek.telekmath.special.NumericalAnalysis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumericalAnalysisTest {

    @Test
    @DisplayName("numAnalTest")
    void numAnalTest() {

        TPolynomial p1 = new TPolynomial(new double[] {
            -2, 2, 1, 0, 1
        }); // -2.0 + 2.0 x + 1.0 x^2 + 1.0 x^4
        // zeros are 0.6721, -1.3057

        double sol1 = 0.67210d;
        double sol2 = -1.30570d;

        Assertions.assertEquals(TMath.areEqual(NumericalAnalysis.regulaFalse(p1, -1, 2, 500), sol1), true);
        Assertions.assertEquals(TMath.areEqual(NumericalAnalysis.regulaFalse(p1, -1, -2, 500), sol2), true);
        Assertions.assertEquals(TMath.areEqual(NumericalAnalysis.bisectionMethod(p1, -1, 2, 500), sol1), true);
        Assertions.assertEquals(TMath.areEqual(NumericalAnalysis.bisectionMethod(p1, -1, -2, 500), sol2), true);
        Assertions.assertEquals(TMath.areEqual(NumericalAnalysis.newtonsMethod(p1, 0, 2, 500), sol1), true);
        Assertions.assertEquals(TMath.areEqual(NumericalAnalysis.newtonsMethod(p1, -1, -2, 500), sol2), true);

        TPolynomial poly1, poly2;
        TPoint2D[] arr = new TPoint2D[]{
                new TPoint2D(1, 1),
                new TPoint2D(2, 8),
                new TPoint2D(3, 27),
                new TPoint2D(4, 64)
        };
        // x^3
        Assertions.assertTrue(NumericalAnalysis.getLagrangePolynomial(arr).equals(new TPolynomial(0, 0, 0, 1)));
        Assertions.assertTrue(NumericalAnalysis.getLagrangePolynomial(1,1,2,8,3,27,4,64).equals(new TPolynomial(0, 0, 0, 1)));

        arr = new TPoint2D[]{
                new TPoint2D(0, 1),
                new TPoint2D(2, 5),
                new TPoint2D(4, 17),
                new TPoint2D(15, 2500)
        };
        /*  1 + (6064/715)x - (3833/715)x^2 + (758/715)x^3  */
        Assertions.assertTrue(NumericalAnalysis.getLagrangePolynomial(arr).equals(new TPolynomial(1, 6064d/715d, -3833d/715d, 758d/715d)));
        Assertions.assertTrue(NumericalAnalysis.getLagrangePolynomial(0,1,2,5,4,17,15,2500).equals(new TPolynomial(1, 6064d/715d, -3833d/715d, 758d/715d)));

    }





}
