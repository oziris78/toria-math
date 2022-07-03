package com.telek.tests.zsingle;

import com.twistral.toriamath.special.LinearAlgebra;
import com.twistral.toriautils.containers.TArrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class LinearAlgebraTest {


    @Test
    @DisplayName("tmatNTest")
    void tmatNTest() {

        // x = 1, y = 2, z = -3

        // 2x + 2y - z = 9
        // x - y = -1
        // y - z = 5
        double[][] A = new double[][]{
                {2d, 2d, -1d},
                {1d, -1d, 0d},
                {0d, 1d, -1d}
        };
        double[][] B = new double[][]{
                {9},
                {-1},
                {5}
        };
        System.out.println(TArrays.toString(LinearAlgebra.cramerMethod(A, B)));

    }


}