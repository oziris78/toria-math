package com.telek.tests.matrices;

import com.telek.telekmath.core.geometry.vectors.TVec3;
import com.telek.telekmath.core.matrices.TMat3;
import com.telek.telekmath.core.matrices.TMat4;
import com.telek.telekmath.utils.TMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.util.resources.cldr.mt.TimeZoneNames_mt;

public class TMat4Test {


    @Test
    @DisplayName("allOperationsTest")
    void allOperationsTest() {
        TMat4 mat = new TMat4();
        TMat4 mat2 = new TMat4(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        TMat4 mat3 = new TMat4(50d);

        Assertions.assertEquals(mat, mat.copy());
        Assertions.assertEquals(mat, mat);
        Assertions.assertEquals(mat, new TMat4(mat));
        Assertions.assertEquals(mat, mat3.set(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1));
        Assertions.assertEquals(mat,
                mat3.set(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1)
                .setCell(0, 0, 20)
                .subtract(19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        Assertions.assertTrue(TMath.areEqual(mat2.getCell(0, 0), 1d));
        Assertions.assertTrue(TMath.areEqual(mat2.getCell(3, 3), 16d));
        Assertions.assertTrue(TMath.areEqual(mat2.getCell(2, 30), Double.NaN));
        Assertions.assertTrue(TMath.areEqual(mat2.getCell(2, 3), 12d));


        mat2 = new TMat4();
        mat2 = new TMat4(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Assertions.assertTrue(TMath.areEqual(mat2.getCell(i, j), i * 4 + j + 1));
            }
        }
        mat2 = new TMat4();
        mat2.set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                Assertions.assertTrue(TMath.areEqual(mat2.getCell(i, j), i * 4 + j + 1));

        mat2 = new TMat4();
        mat2.set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                Assertions.assertTrue(TMath.areEqual(mat2.getCell(i, j), i * 4 + j + 1));

        mat2 = new TMat4();
        mat2.setCell(0, 0, 1);
        mat2.setCell(0, 1, 2);
        mat2.setCell(0, 2, 3);
        mat2.setCell(0, 3, 4);
        mat2.setCell(1, 0, 5);
        mat2.setCell(1, 1, 6);
        mat2.setCell(1, 2, 7);
        mat2.setCell(1, 3, 8);
        mat2.setCell(2, 0, 9);
        mat2.setCell(2, 1, 10);
        mat2.setCell(2, 2, 11);
        mat2.setCell(2, 3, 12);
        mat2.setCell(3, 0, 13);
        mat2.setCell(3, 1, 14);
        mat2.setCell(3, 2, 15);
        mat2.setCell(3, 3, 16);
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                Assertions.assertTrue(TMath.areEqual(mat2.getCell(i, j), i * 4 + j + 1));

        mat2.set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        Assertions.assertTrue(TMath.areEqual(mat2.determinant(), 0d));
        Assertions.assertTrue(TMath.areEqual(mat2.trace(), 1d + 6d + 11d + 16d));

        mat2.set(5, 7, -8, 10, 6, 1, 2, 2, 3, 7, 8, 9, 0, 0, -90, 12);
        Assertions.assertTrue(TMath.areEqual(mat2.determinant(), -5022d));
        Assertions.assertTrue(TMath.areEqual(mat2.trace(), 26d));


        mat2.set(5, 7, -8, 10,
                6, 1, 2, 2,
                3, 7, 8, 9,
                0, 0, -90, 12).scale(2d);
        Assertions.assertTrue(TMath.areEqual(mat2.trace(), 26d * 2d));
        Assertions.assertTrue(TMath.areEqual(mat2.getCell(0, 0), 10d));
        Assertions.assertTrue(TMath.areEqual(mat2.getCell(0, 2), -16d));
        Assertions.assertTrue(TMath.areEqual(mat2.getCell(2, 2), 16d));

        mat2.set(5, 7, -8, 10, 6, 1, 2, 2, 3, 7, 8, 9, 0, 0, -90, 12).subtract(
                new TMat4(
                        1, 2, 7, -9,
                        4, 7, 89, 0
                        , 0, 0, 0, 0,
                        6, 7, 8, 9
                )
        );
        Assertions.assertEquals(mat2, new TMat4(4,5,-15,19,2,-6,-87,2,3,7,8,9,-6,-7,-98,3));

        mat2 = new TMat4();
        mat2.add(new TMat4());
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(i == j)
                    Assertions.assertEquals(mat2.getCell(i, j), 2d);
                else
                    Assertions.assertEquals(mat2.getCell(i, j), 0d);
            }
        }

        mat2 = new TMat4();
        mat2.add(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(i == j)
                    Assertions.assertEquals(mat2.getCell(i, j), 2d);
                else
                    Assertions.assertEquals(mat2.getCell(i, j), 0d);
            }
        }

        TMat4 res;

        mat.set(1, 4, 7, -9, 2, 0, 4, 7, 0, 0, 15, 61, 84, 10, 20, -30);
        mat2.set(7, 8, 1, 4, 6, 0, 7, 9, 2, 1, 7, 2, 0, 41, 91, -16);
        res = new TMat4(45,-354,-741,198,22,307,667,-96,30,2516,5656,-946,688,-538,-2436,946);
        Assertions.assertEquals(mat.multiply(mat2), res);

        mat.set(1, 4, 7, -9, 2, 0, 4, 7, 0, 0, 15, 61, 84, 10, 20, -30);
        res = new TMat4(45,-354,-741,198,22,307,667,-96,30,2516,5656,-946,688,-538,-2436,946);
        Assertions.assertEquals(mat.multiply(7, 8, 1, 4, 6, 0, 7, 9, 2, 1, 7, 2, 0, 41, 91, -16), res);



    }


}
