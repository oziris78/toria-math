package com.twistral.tests.zsingle;

import com.twistral.toriamath.core.geometry.vectors.TVec2;
import com.twistral.toriamath.utils.ToriaMath;
import com.twistral.toriamath.core.functions.oned.TPolynomial;
import com.twistral.toriamath.special.NumericalAnalysis;
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

        Assertions.assertEquals(ToriaMath.areEqual(NumericalAnalysis.regulaFalse(p1, -1, 2, 500), sol1), true);
        Assertions.assertEquals(ToriaMath.areEqual(NumericalAnalysis.regulaFalse(p1, -1, -2, 500), sol2), true);
        Assertions.assertEquals(ToriaMath.areEqual(NumericalAnalysis.bisectionMethod(p1, -1, 2, 500), sol1), true);
        Assertions.assertEquals(ToriaMath.areEqual(NumericalAnalysis.bisectionMethod(p1, -1, -2, 500), sol2), true);
        Assertions.assertEquals(ToriaMath.areEqual(NumericalAnalysis.newtonsMethod(p1, 0, 2, 500), sol1), true);
        Assertions.assertEquals(ToriaMath.areEqual(NumericalAnalysis.newtonsMethod(p1, -1, -2, 500), sol2), true);

        TPolynomial poly1, poly2;
        TVec2[] arr = new TVec2[]{
                new TVec2(1, 1),
                new TVec2(2, 8),
                new TVec2(3, 27),
                new TVec2(4, 64)
        };
        // x^3
        Assertions.assertTrue(NumericalAnalysis.getLagrangePolynomial(arr).equals(new TPolynomial(0, 0, 0, 1)));
        Assertions.assertTrue(NumericalAnalysis.getLagrangePolynomial(1,1,2,8,3,27,4,64).equals(new TPolynomial(0, 0, 0, 1)));

        arr = new TVec2[]{
                new TVec2(0, 1),
                new TVec2(2, 5),
                new TVec2(4, 17),
                new TVec2(15, 2500)
        };
        /*  1 + (6064/715)x - (3833/715)x^2 + (758/715)x^3  */
        Assertions.assertTrue(NumericalAnalysis.getLagrangePolynomial(arr).equals(new TPolynomial(1, 6064d/715d, -3833d/715d, 758d/715d)));
        Assertions.assertTrue(NumericalAnalysis.getLagrangePolynomial(0,1,2,5,4,17,15,2500).equals(new TPolynomial(1, 6064d/715d, -3833d/715d, 758d/715d)));

    }





}
