package com.telek.tests.zsingle;

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


    }

}
