package com.telek.tests.numbers;

import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.utils.TelekMathException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TRangeTest {

    @Test
    @DisplayName("rangeTest")
    void rangeTest() {
        TRange r1 = new TRange(0, 10);
        TRange r2 = new TRange(0, 10);
        TRange r3 = new TRange(-1, 10);

        Assertions.assertThrows(TelekMathException.EqualBoundsException.class, () -> {
            new TRange(0, 0);
        });
        Assertions.assertThrows(TelekMathException.EqualBoundsException.class, () -> {
            new TRange(10, 0);
        });
        Assertions.assertThrows(TelekMathException.EqualBoundsException.class, () -> {
            new TRange(-1, -1);
        });
        Assertions.assertThrows(TelekMathException.EqualBoundsException.class, () -> {
            new TRange(1, 1);
        });
        Assertions.assertDoesNotThrow(() -> {
            new TRange(0, 0.000000000000001);
        });

        Assertions.assertEquals(r1, r2);
        Assertions.assertNotEquals(r1, r3);


    }
}
