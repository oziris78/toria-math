package com.telek.tests.unittests.functions;

import com.telek.telekmath.core.functions.TRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;

public class TRangeTest {


    @Test
    @DisplayName("size")
    @ValueSource(classes = Point.class)
    void size() {

        Point[] testData = {
            new Point(0,0),
            new Point(-1,1),
            new Point(-9,9),
            new Point(-9,90),
            new Point(-1, 0),
            new Point(10000,500000)
        };

        for(Point p : testData){
            Assertions.assertEquals(p.y - p.x, new TRange(p.x, p.y).size());
        }
    }


    @Test
    @DisplayName("_toString")
    void _toString() {
        Assertions.assertEquals("[1.000, 2.000]", new TRange(1,2).toString());
        Assertions.assertEquals("[1.000, 2.000]", new TRange(2,1).toString());
        Assertions.assertEquals("[-2.000, 20.000]", new TRange(-2,20).toString());
        Assertions.assertEquals("[-2.000, 20.000]", new TRange(20,-2).toString());
        Assertions.assertEquals("[0.000, 0.000]", new TRange(0,0).toString());
    }


}
