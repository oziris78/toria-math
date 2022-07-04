package com.twistral.tests.functions;

import com.twistral.toriamath.utils.TMathConsts;
import com.twistral.toriamath.core.functions.oned.AbstractSingleVarFunc;
import com.twistral.toriamath.core.functions.oned.TPolynomial;
import com.twistral.toriamath.core.functions.oned.CompositeFunc;
import com.twistral.toriamath.utils.TMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class CompositeFuncTest {



    @Test
    @DisplayName("tCompositeFuncTest")
    void tCompositeFuncTest() {
        CompositeFunc f1 = new CompositeFunc();
        CompositeFunc f2 = new CompositeFunc(new TPolynomial(1, 2, 3, 4));
        CompositeFunc f3 = new CompositeFunc(new TPolynomial(1, 2, 3, 4), new TPolynomial(1, 2, 3, 4));
        ArrayList<AbstractSingleVarFunc[]> funcs = new ArrayList<>();
        funcs.add(new AbstractSingleVarFunc[]{new TPolynomial(1, 2, 3, 7) ,new TPolynomial(1, 2, 3, 8), new TPolynomial(1, 2, 3, 9)});
        CompositeFunc f4 = new CompositeFunc(funcs);
        CompositeFunc f5 = new CompositeFunc(f1);
        CompositeFunc f6 = new CompositeFunc(f2);
        CompositeFunc f7 = new CompositeFunc(f3);
        CompositeFunc f8 = new CompositeFunc(f4);

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

        CompositeFunc df1 = f1.derivative();
        CompositeFunc df2 = f2.derivative();
        CompositeFunc df3 = f3.derivative();
        CompositeFunc df4 = f4.derivative();

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

        a = TMathConsts.PI_OVER_TWO;
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

