package com.telek.tests.functions;

import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.core.functions.twod.TwoVarFunc;
import com.telek.telekmath.utils.TMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TwoVarFuncTest {

    @Test
    @DisplayName("twoVarFuncTest")
    void twoVarFuncTest() {
        TwoVarFunc z1 = new TwoVarFunc((x,y) -> x * x + y * y);  // z = f(x,y) = x^2 + y^2
        Assertions.assertTrue(TMath.areEqual(z1.value(1, 2), 1d + 4d));
        Assertions.assertTrue(TMath.areEqual(z1.value(-1, 2.5), 1d + 2.5d * 2.5d));

        TwoVarFunc z2 = new TwoVarFunc(new TRange(1, 2), new TRange(1.5d, 1.75d), (x,y) -> x + y);
        Assertions.assertTrue(TMath.areEqual(z2.value(1, 1.5d), 2.5d));
        Assertions.assertTrue(TMath.areEqual(z2.value(0.99d, 1.5d), 0));
        Assertions.assertTrue(TMath.areEqual(z2.value(2.01d, 1.5d), 0));
        Assertions.assertTrue(TMath.areEqual(z2.value(1d, 1.76d), 0));

    }

}
