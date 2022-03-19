package com.telek.tests.numbers;

import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.core.numbers.ComplexNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class ComplexNumberTest {


    @Test
    @DisplayName("complexNumTest")
    void complexNumTest() {
        ComplexNumber c1 = new ComplexNumber(2, 2); // 2 + 2i
        ComplexNumber c2 = new ComplexNumber(2,-2); // 2 - 2i
        ComplexNumber c3 = new ComplexNumber(0, 8); // 8i
        ComplexNumber c4 = new ComplexNumber(8, 0); // 8
        ComplexNumber c5 = new ComplexNumber(8, 0); // 8

        Assertions.assertNotEquals(c1, c5);
        Assertions.assertNotEquals(c2, c5);
        Assertions.assertNotEquals(c3, c5);
        Assertions.assertEquals(c4, c5);

        Assertions.assertEquals(c1.equals(c2.add(new ComplexNumber(0, 4))), true);
        Assertions.assertEquals(c1.equals(c2.subtract(new ComplexNumber(0, -4))), true);
        Assertions.assertEquals(c1.conjugate().equals(c2), true);

        Assertions.assertEquals(c1.getImaginary(), 2);
        Assertions.assertEquals(c2.getImaginary(), -2);
        Assertions.assertEquals(c3.getImaginary(), 8);

        Assertions.assertEquals(c1.getReal(), 2);
        Assertions.assertEquals(c2.getReal(), 2);
        Assertions.assertEquals(c3.getReal(), 0);


        Assertions.assertEquals(c1.hasImaginaryPart(), true);
        Assertions.assertEquals(c2.hasImaginaryPart(), true);
        Assertions.assertEquals(c3.hasImaginaryPart(), true);
        Assertions.assertEquals(c4.hasImaginaryPart(), false);

        Assertions.assertEquals(c1.hasRealPart(), true);
        Assertions.assertEquals(c2.hasRealPart(), true);
        Assertions.assertEquals(c3.hasRealPart(), false);

        Assertions.assertEquals(TMath.areEqual(c1.distanceFromOrigin(), Math.sqrt(8)), true);
        Assertions.assertEquals(TMath.areEqual(c2.distanceFromOrigin(), Math.sqrt(8)), true);
        Assertions.assertEquals(TMath.areEqual(c3.distanceFromOrigin(), 8), true);

        Assertions.assertEquals(c1.multiply(c2), c2.multiply(c1));
        Assertions.assertEquals(c3.multiply(c2), c2.multiply(c3));
        Assertions.assertEquals(c4.multiply(c2), c2.multiply(c4));

        Assertions.assertEquals(c1.multiply(c2), new ComplexNumber(8, 0));
        Assertions.assertEquals(c1.reciprocal(), new ComplexNumber(1d/4d, -1d/4d));
        Assertions.assertEquals(c2.reciprocal(), new ComplexNumber(1d/4d, 1d/4d));
        Assertions.assertEquals(c4.reciprocal(), new ComplexNumber(1d/8d, 0));



    }


}
