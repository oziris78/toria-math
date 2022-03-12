package com.telek.tests.zsingle;

import com.telek.telekutils.arrayref.oned.*;
import com.telek.telekutils.arrayref.twod.*;
import com.telek.telekutils.plain.TClassUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class ArrayRefTest {

    class MyClass{
        private final int num;
        private final String name;
        public MyClass(int num, String name){
            this.num = num;
            this.name = name;
        }
    }

    @Test
    @DisplayName("arrayRefOnedTest")
    void arrayRefOnedTest() {
        double[] d1 = new double[]{1, 2, 3};
        double[] d2 = new double[]{1, 2, 3};
        float[] f1 = new float[]{1, 2, 3};
        float[] f2 = new float[]{1, 2, 3};
        int[] i1 = new int[]{1, 2, 3};
        int[] i2 = new int[]{1, 2, 3};
        long[] l1 = new long[]{1, 2, 3};
        long[] l2 = new long[]{1, 2, 3};
        Integer[] ii1 = new Integer[]{1, 2, 3};
        Integer[] ii2 = new Integer[]{1, 2, 3};
        Number[] n1 = new Number[]{1, 2, 3};
        Number[] n2 = new Number[]{1, 2, 3};

        MyClass[] g1 = new MyClass[]{new MyClass(6, "a"), new MyClass(-2, "c"), new MyClass(0, "f")};
        MyClass[] g2 = new MyClass[]{new MyClass(6, "b"), new MyClass(-2, "e"), new MyClass(0, "f")};
        Field numField = TClassUtils.getField(MyClass.class, "num");
        numField.setAccessible(true);

        Assertions.assertTrue(new DoubleArrRef(d1).equals(new DoubleArrRef(d2)));
        Assertions.assertTrue(new FloatArrRef(f1).equals(new FloatArrRef(f2)));
        Assertions.assertTrue(new IntArrRef(i1).equals(new IntArrRef(i2)));
        Assertions.assertTrue(new LongArrRef(l1).equals(new LongArrRef(l2)));
        Assertions.assertTrue(new NumberArrRef(n1).equals(new NumberArrRef(n2)));
        Assertions.assertTrue(new NumberArrRef(ii1).equals(new NumberArrRef(ii2)));
        Assertions.assertTrue(new GenericArrRef<>(g1, numField).equals(new GenericArrRef<>(g2, numField)));


        d1 = new double[]{1, 2, 3};
        d2 = new double[]{16, 2, 3};
        f1 = new float[]{1, 28, 3};
        f2 = new float[]{1, 2, 3};
        i1 = new int[]{1, 2, 37};
        i2 = new int[]{1, 2, 3};
        l1 = new long[]{1, 25, 3};
        l2 = new long[]{1, 2, 3};
        ii1 = new Integer[]{1, 2, 3};
        ii2 = new Integer[]{1, 25, 3};
        n1 = new Number[]{15, 2, 3};
        n2 = new Number[]{1, 2, 3};

        g1 = new MyClass[]{new MyClass(65, "a"), new MyClass(-2, "b"), new MyClass(0, "c")};
        g2 = new MyClass[]{new MyClass(6, "a"), new MyClass(-2, "b"), new MyClass(0, "c")};

        Assertions.assertFalse(new DoubleArrRef(d1).equals(new DoubleArrRef(d2)));
        Assertions.assertFalse(new FloatArrRef(f1).equals(new FloatArrRef(f2)));
        Assertions.assertFalse(new IntArrRef(i1).equals(new IntArrRef(i2)));
        Assertions.assertFalse(new LongArrRef(l1).equals(new LongArrRef(l2)));
        Assertions.assertFalse(new NumberArrRef(n1).equals(new NumberArrRef(n2)));
        Assertions.assertFalse(new NumberArrRef(ii1).equals(new NumberArrRef(ii2)));
        Assertions.assertFalse(new GenericArrRef<>(g1, numField).equals(new GenericArrRef<>(g2, numField)));
    }

    @Test
    @DisplayName("arrayRefTwodTest")
    void arrayRefTwodTest() {
        int[][] i1 = new int[][]{ {0,1}, {-1,5} };
        int[][] i2 = new int[][]{ {0,1}, {-1,5} };
        float[][] f1 = new float[][]{ {0,1}, {-1,5} };
        float[][] f2 = new float[][]{ {0,1}, {-1,5} };
        double[][] d1 = new double[][]{ {0,1}, {-1,5} };
        double[][] d2 = new double[][]{ {0,1}, {-1,5} };
        long[][] l1 = new long[][]{ {0,1}, {-1,5} };
        long[][] l2 = new long[][]{ {0,1}, {-1,5} };
        Integer[][] ii1 = new Integer[][]{ {0,1}, {-1,5} };
        Integer[][] ii2 = new Integer[][]{ {0,1}, {-1,5} };
        Number[][] n1 = new Number[][]{ {0,1}, {-1,5} };
        Number[][] n2 = new Number[][]{ {0,1}, {-1,5} };

        Assertions.assertTrue(new IntArrRef2(i1).equals(new IntArrRef2(i2)));
        Assertions.assertTrue(new FloatArrRef2(f1).equals(new FloatArrRef2(f2)));
        Assertions.assertTrue(new DoubleArrRef2(d1).equals(new DoubleArrRef2(d2)));
        Assertions.assertTrue(new LongArrRef2(l1).equals(new LongArrRef2(l2)));
        Assertions.assertTrue(new NumberArrRef2(ii1).equals(new NumberArrRef2(ii2)));
        Assertions.assertTrue(new NumberArrRef2(n1).equals(new NumberArrRef2(n2)));

        i1 = new int[][]{ {0,12}, {-1,5} };
        i2 = new int[][]{ {0,1}, {-1,5} };
        f1 = new float[][]{ {6,1}, {-1,5} };
        f2 = new float[][]{ {0,1}, {-1,5} };
        d1 = new double[][]{ {0,1}, {-1,5} };
        d2 = new double[][]{ {0,71}, {-1,5} };
        l1 = new long[][]{ {0,1}, {-1,5} };
        l2 = new long[][]{ {0,-1}, {-1,5} };
        ii1 = new Integer[][]{ {0,1}, {-1,5} };
        ii2 = new Integer[][]{ {0,1}, {1,5} };
        n1 = new Number[][]{ {0,1}, {-1,5} };
        n2 = new Number[][]{ {0,15}, {-1,5} };

        Assertions.assertFalse(new IntArrRef2(i1).equals(new IntArrRef2(i2)));
        Assertions.assertFalse(new FloatArrRef2(f1).equals(new FloatArrRef2(f2)));
        Assertions.assertFalse(new DoubleArrRef2(d1).equals(new DoubleArrRef2(d2)));
        Assertions.assertFalse(new LongArrRef2(l1).equals(new LongArrRef2(l2)));
        Assertions.assertFalse(new NumberArrRef2(ii1).equals(new NumberArrRef2(ii2)));
        Assertions.assertFalse(new NumberArrRef2(n1).equals(new NumberArrRef2(n2)));
    }


}
