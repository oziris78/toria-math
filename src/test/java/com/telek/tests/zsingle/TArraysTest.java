package com.telek.tests.zsingle;


import com.telek.telekutils.containers.TArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TArraysTest {

    @Test
    @DisplayName("copyOfTest")
    void copyOfTest() {
        double[][] arr21 = new double[][]{{1, 2, 3}, {10, 20, 30}};
        int[][] arr22 = new int[][]{{1, 2, 3}, {10, 20, 30}};
        float[][] arr23 = new float[][]{{1, 2, 3}, {10, 20, 30}};
        Number[][] arr24 = new Number[][]{{1, 2, 3}, {10, 20, 30}};

        double[][] arr21copy = TArrays.getCopyOf(arr21);
        int[][] arr22copy = TArrays.getCopyOf(arr22);
        float[][] arr23copy = TArrays.getCopyOf(arr23);
        Number[][] arr24copy = TArrays.getCopyOf(arr24);

        arr21copy[0][1] = 999;
        arr22copy[0][1] = 999;
        arr23copy[0][1] = 999;
        arr24copy[0][1] = 999;

        Assertions.assertTrue(arr21[0][1] != arr21copy[0][1]);
        Assertions.assertTrue(arr22[0][1] != arr22copy[0][1]);
        Assertions.assertTrue(arr23[0][1] != arr23copy[0][1]);
        Assertions.assertTrue(arr24[0][1] != arr24copy[0][1]);

//        System.out.println(TArrays.toString(arr21));
//        System.out.println(TArrays.toString(arr22));
//        System.out.println(TArrays.toString(arr23));
//        System.out.println(TArrays.toString(arr24));
//        System.out.println(TArrays.toString(arr21copy));
//        System.out.println(TArrays.toString(arr22copy));
//        System.out.println(TArrays.toString(arr23copy));
//        System.out.println(TArrays.toString(arr24copy));
    }

    @Test
    @DisplayName("castTest")
    void castTest() {
        int[] arr12 = new int[]{1, 2, 3};
        float[] arr13 = new float[]{1, 2, 3};
        Number[] arr14 = new Number[]{1, 2, 3};

        double[] cast1 = TArrays.getCastedDoubleCopyOf(arr12);
        double[] cast2 = TArrays.getCastedDoubleCopyOf(arr13);
        double[] cast3 = TArrays.getCastedDoubleCopyOf(arr14);

        cast1[1] = -999;
        cast2[1] = -999;
        cast3[1] = -999;

        Assertions.assertTrue(arr12[1] != cast1[1]);
        Assertions.assertTrue(arr13[1] != cast2[1]);
        Assertions.assertTrue(arr14[1].doubleValue() != cast3[1]);


        int[][] arr22 = new int[][]{{1, 2, 3}, {10, 20, 30}};
        float[][] arr23 = new float[][]{{1, 2, 3}, {10, 20, 30}};
        Number[][] arr24 = new Number[][]{{1, 2, 3}, {10, 20, 30}};

        double[][] cast21 = TArrays.getCastedDouble2CopyOf(arr22);
        double[][] cast22 = TArrays.getCastedDouble2CopyOf(arr23);
        double[][] cast23 = TArrays.getCastedDouble2CopyOf(arr24);

        cast21[0][1] = -999;
        cast22[0][1] = -999;
        cast23[0][1] = -999;

        Assertions.assertTrue(arr22[0][1] != cast21[0][1]);
        Assertions.assertTrue(arr23[0][1] != cast22[0][1]);
        Assertions.assertTrue(arr24[0][1].doubleValue() != cast23[0][1]);
    }

}
