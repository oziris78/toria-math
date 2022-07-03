package com.twistral.tests.matrices;

import com.twistral.toriamath.core.geometry.vectors.TVec2;
import com.twistral.toriamath.core.matrices.TMat2;
import com.twistral.toriamath.utils.ToriaMath;
import org.apache.commons.math3.linear.FieldLUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.BigReal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class TMat2Test {


    @Test
    @DisplayName("allOperationsTest")
    void allOperationsTest() {
        TMat2 mat1 = new TMat2();
        TMat2 mat2 = new TMat2(1, -1, -5, 1);
        TMat2 mat3 = new TMat2(5.25d);
        TMat2 mat4 = new TMat2(new TVec2(5, 6), new TVec2(7, 8));
        TMat2 mat5 = mat1.copy();

        Assertions.assertEquals(mat1, mat5);

        mat1.add(0d, -1d, -5d, 0d);
        Assertions.assertEquals(mat1, mat2);
        Assertions.assertEquals(mat1.subtract(-4d, -8d, -11d, -7d), mat4);
        Assertions.assertEquals(mat3.scale(1d / 5.25d).subtract(0d, 1d, 1d, 0d), mat5);

        Assertions.assertEquals(mat1.copy().add(new TMat2(13, 14, 15, 16)), mat1.copy().add(13, 14, 15, 16));
        Assertions.assertEquals(mat1.copy().subtract(new TMat2(13, 14, 15, 16)), mat1.copy().subtract(13, 14, 15, 16));

        TMat2 t1 = new TMat2(15, 6, 7, 8);
        TMat2 t2 = new TMat2(6, 9, -4, 1);
        Assertions.assertEquals(new TMat2(66, 141, 10, 71), t1.multiply(t2));

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            double m00 = random.nextDouble();
            double m01 = random.nextDouble();
            double m10 = random.nextDouble();
            double m11 = random.nextDouble();
            RealMatrix am1 = MatrixUtils.createRealMatrix(new double[][]{
                    {m00, m01},
                    {m10, m11}
            });
            TMat2 tm1 = new TMat2(m00, m01, m10, m11);
            m00 = random.nextDouble();
            m01 = random.nextDouble();
            m10 = random.nextDouble();
            m11 = random.nextDouble();
            RealMatrix am2 = MatrixUtils.createRealMatrix(new double[][]{
                    {m00, m01},
                    {m10, m11}
            });
            TMat2 tm2 = new TMat2(m00, m01, m10, m11);

            am1 = am1.multiply(am2);
            tm1.multiply(tm2);

            TMat2 res = new TMat2(am1.getRow(0)[0],am1.getRow(0)[1], am1.getRow(1)[0], am1.getRow(1)[1]);
            Assertions.assertEquals(res, tm1);
        }

        mat1 = new TMat2(
                45540, 45605,
                5445, -1651
        );
        Assertions.assertEquals(mat1.invert(), new TMat2(
                1651d / 323505765d, 9121d / 64701153d,
                11d / 653547d, -92d / 653547d
        ));

        for (int i = 0; i < 100000; i++) {
            double m00 = random.nextDouble();
            double m01 = random.nextDouble();
            double m10 = random.nextDouble();
            double m11 = random.nextDouble();
            BigReal[][] data = new BigReal[][]{
                    { new BigReal(m00), new BigReal(m01) },
                    { new BigReal(m10), new BigReal(m11) }
            };
            TMat2 t = new TMat2(m00, m01, m10, m11);
            double res = new FieldLUDecomposition<>(MatrixUtils.createFieldMatrix(data)).getDeterminant().doubleValue();
            Assertions.assertTrue(ToriaMath.areEqual(t.determinant(), res));
        }


        mat1 = new TMat2();
        mat1.setCell(0, 0, 1);
        mat1.setCell(0, 1, -1);
        mat1.setCell(1, 0, 5);
        mat1.setCell(1, 1, 8);
        mat1.setCell(1, 2, 51561561561d);
        /*
        1 -1
        5 8
        */
        Assertions.assertEquals(mat1.getCell(0, 0), 1);
        Assertions.assertEquals(mat1.getCell(0, 1), -1);
        Assertions.assertEquals(mat1.getCell(1, 0), 5);
        Assertions.assertEquals(mat1.getCell(1, 1), 8);
        Assertions.assertEquals(mat1.trace(), 9);


        mat1 = new TMat2(10, 20, 30, 40);
        /*
        10 20
        30 40
        */
        TMat2 mflipHorizontally = new TMat2(
                20, 10,
                40, 30
        );
        Assertions.assertEquals(mflipHorizontally, mat1.copy().flipHorizontally());
        TMat2 mflipVertically = new TMat2(
                30, 40,
                10, 20
        );
        Assertions.assertEquals(mflipVertically, mat1.copy().flipVertically());
        TMat2 mrotate90DegAntiClockwise = new TMat2(
                20, 40,
                10, 30
        );
        Assertions.assertEquals(mrotate90DegAntiClockwise, mat1.copy().rotate90DegAntiClockwise());
        TMat2 mrotate90DegClockwise = new TMat2(
                30, 10,
                40, 20
        );
        Assertions.assertEquals(mrotate90DegClockwise, mat1.copy().rotate90DegClockwise());
        TMat2 mrotate180Deg = new TMat2(
                40, 30,
                20, 10
        );
        Assertions.assertEquals(mrotate180Deg, mat1.copy().rotate180Deg());
        TMat2 mtranspose = new TMat2(
                10, 30,
                20, 40
        );
        Assertions.assertEquals(mtranspose, mat1.copy().transpose());


        Assertions.assertTrue(new TMat2().isIdentityMatrix());
        Assertions.assertTrue(new TMat2(9, 5, 5, 999).isSymmetrical());
        Assertions.assertFalse(new TMat2(9, 5, 50, 999).isSymmetrical());
        Assertions.assertFalse(new TMat2(9, 5, 50, 999).isSingular());
        Assertions.assertTrue(new TMat2(5, 7,
                                        10, 14).isSingular());





    }

}
