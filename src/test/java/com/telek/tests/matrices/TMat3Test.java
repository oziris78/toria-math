package com.telek.tests.matrices;

import com.telek.telekmath.core.matrices.TMat;
import com.telek.telekmath.core.matrices.TMat3;
import com.telek.telekmath.utils.TMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TMat3Test {


    @Test
    @DisplayName("allOperationsTest")
    void allOperationsTest() {
        TMat3 mat1 = new TMat3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        TMat3 mat2 = new TMat3(-1, 2, -3, 5, 4, 7, 8, -9, 10);
        TMat3 mat3 = new TMat3(33, -17, 41, 69, -26, 83, 105, -35, 125);
        TMat3 mat4 = new TMat3(33, -17, 41, 69, -26, 83, 99, -35, 125);
        TMat3 mat5 = new TMat3(
                -1d / 6d, 1d / 3d, -1d / 6d,
                -68d / 345d, 11d / 345d, 1d / 23d,
                53d / 690d, -88d / 345d, 7d / 46d
        );

        Assertions.assertEquals(mat1.copy().multiply(mat2), mat3);
        Assertions.assertEquals(mat1.copy().multiply(-1, 2, -3, 5, 4, 7, 8, -9, 10), mat3);

        Assertions.assertTrue(TMath.areEqual(mat3.determinant(), 0d));
        Assertions.assertTrue(TMath.areEqual(mat4.determinant(), 2070));

        Assertions.assertEquals(mat4.copy().invert(), mat5);


    }


}
