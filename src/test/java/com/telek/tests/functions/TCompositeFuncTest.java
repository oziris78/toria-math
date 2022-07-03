package com.telek.tests.functions;

import com.twistral.toriamath.core.constants.TMathConstants;
import com.twistral.toriamath.core.functions.oned.AbstractSingleVarFunc;
import com.twistral.toriamath.core.functions.oned.TPolynomial;
import com.twistral.toriamath.core.functions.oned.TCompositeFunc;
import com.twistral.toriamath.utils.TMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class TCompositeFuncTest {



    @Test
    @DisplayName("tCompositeFuncTest")
    void tCompositeFuncTest() {
        TCompositeFunc f1 = new TCompositeFunc();
        TCompositeFunc f2 = new TCompositeFunc(new TPolynomial(1, 2, 3, 4));
        TCompositeFunc f3 = new TCompositeFunc(new TPolynomial(1, 2, 3, 4), new TPolynomial(1, 2, 3, 4));
        ArrayList<AbstractSingleVarFunc[]> funcs = new ArrayList<>();
        funcs.add(new AbstractSingleVarFunc[]{new TPolynomial(1, 2, 3, 7) ,new TPolynomial(1, 2, 3, 8), new TPolynomial(1, 2, 3, 9)});
        TCompositeFunc f4 = new TCompositeFunc(funcs);
        TCompositeFunc f5 = new TCompositeFunc(f1);
        TCompositeFunc f6 = new TCompositeFunc(f2);
        TCompositeFunc f7 = new TCompositeFunc(f3);
        TCompositeFunc f8 = new TCompositeFunc(f4);

        double a = 0d;
        Assertions.assertTrue(TMath.areEqual(f1.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(f2.value(a), 1));
        Assertions.assertTrue(TMath.areEqual(f3.value(a), 1));
        Assertions.assertTrue(TMath.areEqual(f4.value(a), 1));
        Assertions.assertTrue(TMath.areEqual(f1.value(a), f5.value(a)));
        Assertions.assertTrue(TMath.areEqual(f2.value(a), f6.value(a)));
        Assertions.assertTrue(TMath.areEqual(f3.value(a), f7.value(a)));
        Assertions.assertTrue(TMath.areEqual(f4.value(a), f8.value(a)));

        a = -1.25d;
        Assertions.assertTrue(TMath.areEqual(f1.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(f2.value(a), -4.625));
        Assertions.assertTrue(TMath.areEqual(f3.value(a), 21.390625));
        Assertions.assertTrue(TMath.areEqual(f4.value(a), -1876.52906799d));
        Assertions.assertTrue(TMath.areEqual(f1.value(a), f5.value(a)));
        Assertions.assertTrue(TMath.areEqual(f2.value(a), f6.value(a)));
        Assertions.assertTrue(TMath.areEqual(f3.value(a), f7.value(a)));
        Assertions.assertTrue(TMath.areEqual(f4.value(a), f8.value(a)));

        TCompositeFunc df1 = f1.derivative();
        TCompositeFunc df2 = f2.derivative();
        TCompositeFunc df3 = f3.derivative();
        TCompositeFunc df4 = f4.derivative();

        a = -1.25d;
        Assertions.assertTrue(TMath.areEqual(df1.value(a), 0d));
        Assertions.assertTrue(TMath.areEqual(df2.value(a), 13.25d));
        Assertions.assertTrue(TMath.areEqual(df3.value(a), -122.5625d));
        Assertions.assertTrue(TMath.areEqual(df4.value(a), 14500.5672607d));

        a = 0d;
        Assertions.assertTrue(TMath.areEqual(df1.value(a), 0));
        Assertions.assertTrue(TMath.areEqual(df2.value(a), 2));
        Assertions.assertTrue(TMath.areEqual(df3.value(a), 4));
        Assertions.assertTrue(TMath.areEqual(df4.value(a), 6));

        a = TMathConstants.PI_OVER_TWO;
        Assertions.assertTrue(TMath.areEqual(df1.value(a), 0d));
        Assertions.assertTrue(TMath.areEqual(df2.value(a), 41.033591164d));
        Assertions.assertTrue(TMath.areEqual(df3.value(a), 2219.66568817d));
        Assertions.assertTrue(TMath.areEqual(df4.value(a), 380193.422202d));

        // f(x) + (7+8x^4)^2 + (7+8x^4)^2
        df1.addProduct(new TPolynomial(7, 0, 0, 0, 8), new TPolynomial(7, 0, 0, 0, 8));
        df1.addProduct(new TPolynomial(7, 0, 0, 0, 8), new TPolynomial(7, 0, 0, 0, 8));
        df2.addProduct(new TPolynomial(7, 0, 0, 0, 8), new TPolynomial(7, 0, 0, 0, 8));
        df2.addProduct(new TPolynomial(7, 0, 0, 0, 8), new TPolynomial(7, 0, 0, 0, 8));
        df3.addProduct(new TPolynomial(7, 0, 0, 0, 8), new TPolynomial(7, 0, 0, 0, 8));
        df3.addProduct(new TPolynomial(7, 0, 0, 0, 8), new TPolynomial(7, 0, 0, 0, 8));
        df4.addProduct(new TPolynomial(7, 0, 0, 0, 8), new TPolynomial(7, 0, 0, 0, 8));
        df4.addProduct(new TPolynomial(7, 0, 0, 0, 8), new TPolynomial(7, 0, 0, 0, 8));

        Assertions.assertTrue(TMath.areEqual(df1.value(a), 6205.99278251d));
        Assertions.assertTrue(TMath.areEqual(df2.value(a), 6247.02637368d));
        Assertions.assertTrue(TMath.areEqual(df3.value(a), 8425.65847068d));
        Assertions.assertTrue(TMath.areEqual(df4.value(a), 386399.414985d));

        Assertions.assertTrue(TMath.areEqual(df1.derivative().value(a), 27635.0488104d));
        Assertions.assertTrue(TMath.areEqual(df2.derivative().value(a), 27678.7479223d));
        Assertions.assertTrue(TMath.areEqual(df3.derivative().value(a), 33366.4140315d));
        Assertions.assertTrue(TMath.areEqual(df4.derivative().value(a), 1726689.06059d));
    }



}

