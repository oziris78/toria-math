package com.telek.tests.numbers;

import com.telek.telekmath.core.numbers.Fraction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FractionTest {


    @Test
    @DisplayName("fracTest")
    void fracTest() {
        Fraction f1 = new Fraction(-5, 7);
        Fraction f2 = new Fraction(10, 14);
        Fraction f3 = new Fraction(5, 14);
        Fraction f4 = new Fraction(19, 10);

        Assertions.assertFalse(f1.equals(f2));
        Assertions.assertFalse(f1.equals(f3));
        Assertions.assertEquals(f1.multiply(new Fraction(-1, 1)), f2);

        Assertions.assertEquals(f4.getAsDouble(), 1.9d);
        Assertions.assertEquals(f1.add(f2), new Fraction(0, 7));
        Assertions.assertEquals(f1.subtract(f2), new Fraction(-10, 7));
        Assertions.assertEquals(f3.subtract(f2), new Fraction(-5, 14));
        Assertions.assertEquals(f3.multiply(f2), new Fraction(5*10, 14*14));
        Assertions.assertEquals(f3.divide(f2), new Fraction(5*14, 14*10));

        for (int i = 0; i < 8; i++) {
            Assertions.assertEquals(f3.pow(i), new Fraction((int) Math.pow(5, i), (int) Math.pow(14, i)));
        }

        for (int i = 1; i < 100; i++) {
            Fraction f = new Fraction(i, 80);
            Assertions.assertEquals(f.getAsDouble(), ((double) i) / 80d);

            Assertions.assertEquals(new Fraction(2 * i, i).isInteger(), true);
        }

        for (int i = 1; i < 100; i++) {
            // 7727 is a prime so it won't simplify, so that we can test it!
            // if it was something like 80, it would simplify 40/80 to 1/2 and f.getNumerator() would return 1 instead of 40
            Fraction f = new Fraction(i, 7727);
            Assertions.assertEquals(f.getNumerator(), i);
            Assertions.assertEquals(f.getDenominator(), 7727);
        }


    }


}