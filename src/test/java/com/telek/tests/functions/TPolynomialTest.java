package com.telek.tests.functions;

import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.core.functions.other.TPolynomial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TPolynomialTest {


    @Test
    @DisplayName("polyTest")
    void polyTest() {
        TPolynomial p1 = new TPolynomial(new double[]{
            1,0,1,2,0,0,0,9
        }); // 1 + x^2 + 2x^3 + 9x^7

        TPolynomial p2 = new TPolynomial(new double[]{
                1, 0, -1, 0, 1
        }); // 1 - x^2 + x^4

        Assertions.assertEquals(p2, new TPolynomial(new double[]{1, 0, -1, 0, 1}));
        Assertions.assertEquals(p1.add(p2).value(4), 147842);
        Assertions.assertEquals(p1.subtract(p2).value(4), 147360);
        Assertions.assertEquals(p1.multiply(p2).value(4), 35571841);

        Assertions.assertEquals(p1.hasTerm(0), true);
        Assertions.assertEquals(p1.hasTerm(1), false);

        Assertions.assertEquals(p1.getMaxDegree(), 7);
        Assertions.assertEquals(p2.getMaxDegree(), 4);

        Assertions.assertEquals(p1.getCoefficientOfDegree(0), 1);
        Assertions.assertEquals(p1.getCoefficientOfDegree(1), 0);
        Assertions.assertEquals(p1.getCoefficientOfDegree(2), 1);
        Assertions.assertEquals(p1.getCoefficientOfDegree(7), 9);

        Assertions.assertEquals(TPolynomial.getTPolynomial(1, -1, 2, -2, 3).value(4), 180);
        Assertions.assertEquals(TPolynomial.getTPolynomial(TRange.ZERO_TO_ONE, 1, -1, 2, -2, 3).value(4), 0); // out of range

        Assertions.assertEquals(TPolynomial.getCoefOnePoly(10).value(2), 2047);
    }


}
