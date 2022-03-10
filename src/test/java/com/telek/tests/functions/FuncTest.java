package com.telek.tests.functions;

import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.functions.TFunction;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.core.functions.amnfuncs.*;
import com.telek.telekmath.core.functions.other.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class FuncTest {

    @Test
    @DisplayName("tfunctionTest")
    void tfunctionTest() {
        TFunction f1 = new TFunction();
        TFunction f2 = new TFunction(new TLog());
        TFunction f3 = new TFunction(new TSin());
        f3.addProduct(new TCos());

        TFunction cf1 = new TFunction(f1);
        TFunction cf2 = new TFunction(f2);
        TFunction cf3 = new TFunction(f3);

        Assertions.assertEquals(f1, cf1);
        Assertions.assertEquals(f2, cf2);
        Assertions.assertEquals(f3, cf3);

        cf1.addProduct(new TConstantFunc(50d));
        cf2.addProduct(new TConstantFunc(50d));
        cf3.addProduct(new TConstantFunc(50d));

        double val1 = TMath.abs(cf1.value(10) - f1.value(10d));
        double val2 = TMath.abs(cf2.value(10) - f2.value(10d));
        double val3 = TMath.abs(cf3.value(10) - f3.value(10d));

        Assertions.assertTrue(TMath.areEqual(val1, 50d));
        Assertions.assertTrue(TMath.areEqual(val2, 50d));
        Assertions.assertTrue(TMath.areEqual(val3, 50d));

    }



    @Test
    @DisplayName("amnTest")
    void amnTest() {

        TFunction f = new TFunction(
            new TCos(5), new TCosh(5), new TSin(5), new TSinh(5)
        );
        f.addProduct(new TTan(-5));
        f.addProduct(new TSign(), new TDiracDeltaFunc(), new TFloor(), new TCeil(), new THeavisideFunc(), new TRectFunc());

        Assertions.assertEquals(TMath.areEqual(f.value(1), -1494.477280d), true);
        Assertions.assertEquals(TMath.areEqual(f.value(2), 55366156.9726d), true);
        Assertions.assertEquals(TMath.areEqual(f.value(-1), -1501.23831075d), true);

        Assertions.assertEquals(TMath.areEqual(f.derivative().value(0.5d), -133.0729), true);
        Assertions.assertEquals(TMath.areEqual(f.derivative().value(-0.5d), 117.49247889d), true);


        Assertions.assertEquals(new TFloor().value(2d), 2d);
        Assertions.assertEquals(new TFloor().value(2.1d), 2d);
        Assertions.assertEquals(new TFloor().value(2.9999999d), 2d);
        Assertions.assertEquals(new TFloor().value(3d), 3d);

        Assertions.assertEquals(new TCeil().value(2d), 2d);
        Assertions.assertEquals(new TCeil().value(2.1d), 3);
        Assertions.assertEquals(new TCeil().value(2.9999999d), 3);
        Assertions.assertEquals(new TCeil().value(3d), 3);

        Assertions.assertEquals(new TSign().value(100), 1);
        Assertions.assertEquals(new TSign().value(1), 1);
        Assertions.assertEquals(new TSign().value(0), 0);
        Assertions.assertEquals(new TSign().value(-1), -1);
        Assertions.assertEquals(new TSign().value(-1000), -1);


        for (int i = -10; i < 10; i++) {
            if(i <= 0)
                Assertions.assertEquals(new THeavisideFunc().value(i), 0);
            else
                Assertions.assertEquals(new THeavisideFunc().value(i), 1);
        }

        Assertions.assertEquals(new TDiracDeltaFunc().value(0.0000001), 1);
        Assertions.assertEquals(new TDiracDeltaFunc().value(0), 1);
        Assertions.assertEquals(new TDiracDeltaFunc().value(1), 0);


        Assertions.assertEquals(new TRectFunc().value(0.5f), 0.5f);
        Assertions.assertEquals(new TRectFunc().value(-0.6f), 0);
        Assertions.assertEquals(new TRectFunc().value(-0.7f), 0);
        Assertions.assertEquals(new TRectFunc().value(0.8f), 0);
        Assertions.assertEquals(new TRectFunc().value(-0.9f), 0);
        Assertions.assertEquals(new TRectFunc().value(1f), 0);
        Assertions.assertEquals(new TRectFunc().value(1.1f), 0);
        Assertions.assertEquals(new TRectFunc().value(0.1), 1);
        Assertions.assertEquals(new TRectFunc().value(-0.1), 1);
        Assertions.assertEquals(new TRectFunc().value(0.48999999d), 1);
        Assertions.assertEquals(new TRectFunc().value(-0.48999999d), 1);
        Assertions.assertEquals(new TRectFunc().value(0.25), 1);
        Assertions.assertEquals(new TRectFunc().value(0.31), 1);

        TFunction f2 = new TFunction(
                new TConstantFunc(100)
        );
        f2.addProduct(
                new TPowerFunc(2, 5, 2), // 2 * 5^2x
                new TLog(), // ln(x)
                new TLog(2), // ln(2x)
                new TPolynomial(new double[]{1, 0, 0, 7, 6, 8}), // 1 + 7x^3 + 6x^4 + 8x^5
                new TInverseFunc() // 1 / x
        );

        Assertions.assertEquals(new TRange(-1, 1).isInRange(0), true);
        Assertions.assertEquals(new TRange(-1, 1).isInRange(1), true);
        Assertions.assertEquals(new TRange(-1, 1).isInRange(-1), true);
        Assertions.assertEquals(new TRange(-1, 1).isInRange(2), false);

        // i used (int) here because telek-math isn't extremely precise when it comes to complex functions
        Assertions.assertEquals((int) f2.value(1d), 100);
        Assertions.assertEquals((int) f2.value(0.5d), 100);
        Assertions.assertEquals((int) f2.value(0.3333d), 110);
        Assertions.assertEquals((int) f2.value(2d), 245731);

        Assertions.assertEquals((int) f2.derivative().value(0.3333d), -76);
        Assertions.assertEquals((int) f2.derivative().value(1d), 762);
    }

}

