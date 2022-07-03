package com.twistral.tests.numbers;

import com.twistral.toriamath.core.functions.TRange;
import com.twistral.toriamath.utils.ToriaMathException;
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

        Assertions.assertThrows(ToriaMathException.EqualBoundsException.class, () -> {
            new TRange(0, 0);
        });
        Assertions.assertThrows(ToriaMathException.EqualBoundsException.class, () -> {
            new TRange(10, 0);
        });
        Assertions.assertThrows(ToriaMathException.EqualBoundsException.class, () -> {
            new TRange(-1, -1);
        });
        Assertions.assertThrows(ToriaMathException.EqualBoundsException.class, () -> {
            new TRange(1, 1);
        });
        Assertions.assertDoesNotThrow(() -> {
            new TRange(0, 0.000000000000001);
        });

        Assertions.assertEquals(r1, r2);
        Assertions.assertNotEquals(r1, r3);


    }
}
