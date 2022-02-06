package com.telek.tests.matrices;

import com.telek.telekmath.core.matrices.TMatrix;
import com.telek.telekmath.core.matrices.TMatrixUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TMatrixUtilsTest {


    @Test
    @DisplayName("matrixUtilsTest")
    void matrixUtilsTest() {

        TMatrix I2 = new TMatrix(new double[][]{
                {1,0},
                {0,1}
        });

        TMatrix I3 = new TMatrix(new double[][]{
                {1,0,0},
                {0,1,0},
                {0,0,1}
        });

        Assertions.assertEquals(TMatrixUtils.createIdentityMatrix(2), I2);
        Assertions.assertEquals(TMatrixUtils.createIdentityMatrix(3), I3);
        Assertions.assertEquals(TMatrixUtils.createLehmerMatrix(3), new TMatrix(new double[][]{
                {1d, 1d/2d, 1d/3d},
                {1d/2d, 1d, 2d/3d},
                {1d/3d, 2d/3d, 1d}
        }));
        Assertions.assertEquals(TMatrixUtils.createHilbertMatrix(5), new TMatrix(new double[][]{
                {1, 1d/2d, 1d/3d, 1d/4d, 1d/5d},
                {1d/2d, 1d/3d, 1d/4d, 1d/5d, 1d/6d},
                {1d/3d, 1d/4d, 1d/5d, 1d/6d, 1d/7d},
                {1d/4d, 1d/5d, 1d/6d, 1d/7d, 1d/8d},
                {1d/5d, 1d/6d, 1d/7d, 1d/8d, 1d/9d}
        }));
        Assertions.assertEquals(TMatrixUtils.allValues(2,7,35), new TMatrix(new double[][]{
                {35,35,35,35,35,35,35},
                {35,35,35,35,35,35,35}
        }));

        Assertions.assertEquals(TMatrixUtils.isIdentityMatrix(I2), true);
        Assertions.assertEquals(TMatrixUtils.isIdentityMatrix(I3), true);
        I3.setCell(0,0,99);
        Assertions.assertNotEquals(TMatrixUtils.isIdentityMatrix(I3), true);
        I3.setCell(0,0,1);

        Assertions.assertEquals(TMatrixUtils.isSymmetrical(I2), true);
        Assertions.assertEquals(TMatrixUtils.isSymmetrical(I3), true);
        I3.setCell(1,2,99);
        Assertions.assertNotEquals(TMatrixUtils.isSymmetrical(I3), true);
        I3.setCell(1,2,1);


        TMatrix A = new TMatrix(new double[][]{
                {2, 1, 1},
                {1, 1, 1},
                {1, -1, 0}
        });

        TMatrix B = new TMatrix(new double[][]{
                {2},
                {5},
                {-1}
        });

        TMatrix X = new TMatrix(new double[][]{
                {-3},
                {-2},
                {10}
        });

        Assertions.assertEquals(TMatrixUtils.cramerMethod(A, B), X);



    }


}