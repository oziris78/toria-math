package com.telek.tests.matrices;

import com.twistral.toriamath.core.matrices.*;
import com.twistral.toriamath.core.matrices.TMatN;
import com.twistral.toriamath.utils.TMath;
import com.twistral.toriamath.utils.TelekMathException.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TMatNTest {


    @Test
    @DisplayName("tmanNTest")
    void tmanNTest() {
        // constructors
        Assertions.assertEquals(new TMatN(4), new TMatN(new TMat4()));
        Assertions.assertEquals(new TMatN(3), new TMatN(new TMat3()));
        Assertions.assertEquals(new TMatN(2), new TMatN(new TMat2()));
        Assertions.assertThrows(NotGreaterThanZeroException.class, () -> { new TMatN(0);});
        Assertions.assertThrows(NotGreaterThanZeroException.class, () -> { new TMatN(-1);});
        Assertions.assertThrows(NotGreaterThanZeroException.class, () -> { new TMatN(-2);});

        TMatN mat1 = new TMatN(new double[][]{
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 }
        });
        TMatN mat2 = new TMatN(mat1);
        TMatN mat3 = mat1.copy();
        Assertions.assertEquals(mat1, mat2);
        Assertions.assertEquals(mat1, mat3);
        Assertions.assertEquals(mat2, mat1);
        Assertions.assertEquals(mat2, mat3);
        Assertions.assertEquals(mat3, mat1);
        Assertions.assertEquals(mat3, mat2);

        // getters and setters
        mat1.set(new double[][]{
                { 1, 2, 3, 4 },
                { 5, 6, 7, 80 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 16 }
        });
        Assertions.assertTrue(TMath.areEqual(mat1.getCell(0, 0), 1));
        Assertions.assertTrue(TMath.areEqual(mat1.getCell(0, 1), 2));
        Assertions.assertTrue(TMath.areEqual(mat1.getCell(2, 2), 11));
        Assertions.assertTrue(TMath.areEqual(mat1.getCell(0, 200), Double.NaN));
        Assertions.assertTrue(TMath.areEqual(mat1.setCell(0, 0, -20).getCell(0, 0), -20));
        Assertions.assertTrue(TMath.areEqual(mat1.getN(), 4));
        Assertions.assertTrue(TMath.areEqual(mat1.set(new double[][]{ {100, 20}, {3, 4} }).getN(), 2));
        Assertions.assertTrue(TMath.areEqual(mat1.getCell(0, 0), 100));

        // special value methods
        mat1.set(new double[][]{
                {10, 2, 5, 7, 8, 9},
                {0, 7, 9, -9, 9, 9},
                {0, 0, 1, 4, 7, 1},
                {10, -10, 9, 7, 2, 4},
                {1, 7, 0, 9, 0, 1},
                {1, 1, 1, 7, 8.5d, 9.5d}
        });
        mat2 = mat1.copy();
        Assertions.assertTrue(TMath.areEqual(mat1.determinant(), 929005.5d));
        Assertions.assertTrue(TMath.areEqual(mat1.trace(), 34.5d));
        Assertions.assertEquals(mat1, mat2);
        mat1.set(new double[][]{
                {0,2,5,7,8,4,1},
                {0,7,1,-2,2,3,2},
                {0,0,1,4,7,1,3},
                {0,0,9,7,2,4,4},
                {1,7,0,7,0,1,5},
                {1,1,1,7,0,3,6},
                {7,0,0,0,0,0,2}
        });
        mat2 = mat1.copy();
        Assertions.assertTrue(TMath.areEqual(mat1.determinant(), 493174));
        Assertions.assertTrue(TMath.areEqual(mat1.trace(), 20d));
        Assertions.assertEquals(mat1, mat2);

        // basic methods
        mat1 = new TMatN(new double[][]{
                {10, 11, 12, 13, 14},
                {15, 16, 17, 18, 19},
                {20, 21, 22, 23, 24},
                {25, 26, 27, 28, 29},
                {30, 31, 32, 33, 34}
        });
        mat2.set(new double[][]{
                {1, 0, -1, 1, -2},
                {1, 0, -1, 1, -2},
                {1, 0, -1, 1, -2},
                {1, 0, -1, 1, -2},
                {1, 0, -1, 1, -2}
        });
        mat3 = mat1.copy();
        mat1.add(new double[][]{
                {0, 0, -3, 0, 2},
                {0, 0, -3, 0, 2},
                {0, 0, -3, 0, 2},
                {0, 0, -3, 0, 2},
                {0, 0, -3, 0, 2}
        });
        mat1.add(mat2);
        Assertions.assertEquals(mat1, new TMatN(new double[][]{
                {11, 11, 8, 14, 14},
                {16, 16, 13, 19, 19},
                {21, 21, 18, 24, 24},
                {26, 26, 23, 29, 29},
                {31, 31, 28, 34, 34}
        }));
        mat1.subtract(new double[][]{
                {0, 0, -3, 0, 2},
                {0, 0, -3, 0, 2},
                {0, 0, -3, 0, 2},
                {0, 0, -3, 0, 2},
                {0, 0, -3, 0, 2}
        });
        mat1.subtract(mat2);
        Assertions.assertEquals(mat3, mat1);
        mat1.scale(0d);
        mat1.setCell(0, 0, 1);
        mat1.setCell(1, 1, 1);
        mat1.setCell(2, 2, 1);
        mat1.setCell(3, 3, 1);
        mat1.setCell(4, 4, 1);
        Assertions.assertEquals(mat1, new TMatN(5));


        mat1 = new TMatN(new double[][]{
                {1, 2, 1, 1, 1},
                {0, 1, -1, 1, 2},
                {2, 2, 2, 1, 1},
                {1, 0, 1, 1, 1},
                {0, 2, 1, 1, 2}
        });
        Assertions.assertEquals(mat1.copy().invert(), new TMatN(new double[][]{
                        {-3d/4d, 1d/2d, 1d, -1d/4d, -1d/2d},
                        {1d/2d, 0d, 0d, -1d/2d, 0d},
                        {-1d/4d, -1d/2d, 0d, 1d/4d, 1d/2d},
                        {11d/4d, -1d/2d, -2d, 5d/4d, -1d/2d},
                        {-7d/4d, 1d/2d, 1d, -1d/4d, 1d/2d}
                })
        );
        mat1.multiply(mat1); // A =  A * A = A^2
        mat1.multiply(mat1); // A = A^2 * A^2 = A^4
        // mat1 = mat1^4
        Assertions.assertEquals(mat1, new TMatN(new double[][]{
                        {80, 200, 87, 150, 237},
                        {23, 67, 30, 51, 84},
                        {123, 300, 130, 224, 351},
                        {76, 174, 77, 128, 197},
                        {79, 200, 87, 150, 238}
                })
        );
        mat1.set(new double[][]{
                {1, 2, 1, 1, 1},
                {0, 1, -1, 1, 2},
                {2, 2, 2, 1, 1},
                {1, 0, 1, 1, 1},
                {0, 2, 1, 1, 2}
        });
        mat2.set(new double[][]{
                {1, 2, 1, 1, 1},
                {0, 1, -1, 1, 2},
                {10, 10, 10, 1, 1},
                {1, 0, 1, 1, 1},
                {0, 2, 1, 1, 2}
        });
        Assertions.assertEquals(mat1.multiply(mat2), new TMatN(new double[][]{
                    {12, 16, 11, 6, 9},
                    {-9, -5, -8, 3, 6},
                    {23, 28, 22, 8, 11},
                    {12, 14, 13, 4, 5},
                    {11, 16, 11, 6, 10}
            })
        );

        // geometric methods
        mat1 = new TMatN(new double[][]{
                {10, 11, 12, 13, 14},
                {15, 16, 17, 18, 19},
                {20, 21, 22, 23, 24},
                {25, 26, 27, 28, 29},
                {30, 31, 32, 33, 34}
        });
        TMatN rotate90DegClockwise1 = new TMatN(new double[][]{
                {30, 25, 20, 15, 10},
                {31, 26, 21, 16, 11},
                {32, 27, 22, 17, 12},
                {33, 28, 23, 18, 13},
                {34, 29, 24, 19, 14}
        });
        TMatN rotate90DegAntiClockwise1 = new TMatN(new double[][]{
                {14, 19, 24, 29, 34},
                {13, 18, 23, 28, 33},
                {12, 17, 22, 27, 32},
                {11, 16, 21, 26, 31},
                {10, 15, 20, 25, 30}
        });
        TMatN rotate180Deg1 = new TMatN(new double[][]{
                {34, 33, 32, 31, 30},
                {29, 28, 27, 26, 25},
                {24, 23, 22, 21, 20},
                {19, 18, 17, 16, 15},
                {14, 13, 12, 11, 10}
        });
        TMatN flipVertically1 = new TMatN(new double[][]{
                {30, 31, 32, 33, 34},
                {25, 26, 27, 28, 29},
                {20, 21, 22, 23, 24},
                {15, 16, 17, 18, 19},
                {10, 11, 12, 13, 14}
        });
        TMatN flipHorizontally1 = new TMatN(new double[][]{
                {14, 13, 12, 11, 10},
                {19, 18, 17, 16, 15},
                {24, 23, 22, 21, 20},
                {29, 28, 27, 26, 25},
                {34, 33, 32, 31, 30}
        });
        TMatN transpose1 = new TMatN(new double[][]{
                {10, 15, 20, 25, 30},
                {11, 16, 21, 26, 31},
                {12, 17, 22, 27, 32},
                {13, 18, 23, 28, 33},
                {14, 19, 24, 29, 34}
        });
        Assertions.assertEquals(mat1.copy().transpose(), transpose1);
        Assertions.assertEquals(mat1.copy().flipHorizontally(), flipHorizontally1);
        Assertions.assertEquals(mat1.copy().flipVertically(), flipVertically1);
        Assertions.assertEquals(mat1.copy().rotate90DegAntiClockwise(), rotate90DegAntiClockwise1);
        Assertions.assertEquals(mat1.copy().rotate90DegClockwise(), rotate90DegClockwise1);
        Assertions.assertEquals(mat1.copy().rotate180Deg(), rotate180Deg1);

        mat1 = new TMatN(new double[][]{
                {10, 11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20, 21},
                {22, 23, 24, 25, 26, 27},
                {28, 29, 30, 31, 32, 33},
                {34, 35, 36, 37, 38, 39},
                {40, 41, 42, 43, 44, 45}
        });
        rotate90DegClockwise1 = new TMatN(new double[][]{
                {40, 34, 28, 22, 16, 10},
                {41, 35, 29, 23, 17, 11},
                {42, 36, 30, 24, 18, 12},
                {43, 37, 31, 25, 19, 13},
                {44, 38, 32, 26, 20, 14},
                {45, 39, 33, 27, 21, 15}
        });
        rotate90DegAntiClockwise1 = new TMatN(new double[][]{
                {15, 21, 27, 33, 39, 45},
                {14, 20, 26, 32, 38, 44},
                {13, 19, 25, 31, 37, 43},
                {12, 18, 24, 30, 36, 42},
                {11, 17, 23, 29, 35, 41},
                {10, 16, 22, 28, 34, 40}
        });
        rotate180Deg1 = new TMatN(new double[][]{
                {45, 44, 43, 42, 41, 40},
                {39, 38, 37, 36, 35, 34},
                {33, 32, 31, 30, 29, 28},
                {27, 26, 25, 24, 23, 22},
                {21, 20, 19, 18, 17, 16},
                {15, 14, 13, 12, 11, 10}
        });
        flipVertically1 = new TMatN(new double[][]{
                {40, 41, 42, 43, 44, 45},
                {34, 35, 36, 37, 38, 39},
                {28, 29, 30, 31, 32, 33},
                {22, 23, 24, 25, 26, 27},
                {16, 17, 18, 19, 20, 21},
                {10, 11, 12, 13, 14, 15}
        });
        flipHorizontally1 = new TMatN(new double[][]{
                {15, 14, 13, 12, 11, 10},
                {21, 20, 19, 18, 17, 16},
                {27, 26, 25, 24, 23, 22},
                {33, 32, 31, 30, 29, 28},
                {39, 38, 37, 36, 35, 34},
                {45, 44, 43, 42, 41, 40}
        });
        transpose1 = new TMatN(new double[][]{
                {10, 16, 22, 28, 34, 40},
                {11, 17, 23, 29, 35, 41},
                {12, 18, 24, 30, 36, 42},
                {13, 19, 25, 31, 37, 43},
                {14, 20, 26, 32, 38, 44},
                {15, 21, 27, 33, 39, 45}
        });
        Assertions.assertEquals(mat1.copy().transpose(), transpose1);
        Assertions.assertEquals(mat1.copy().flipHorizontally(), flipHorizontally1);
        Assertions.assertEquals(mat1.copy().flipVertically(), flipVertically1);
        Assertions.assertEquals(mat1.copy().rotate90DegAntiClockwise(), rotate90DegAntiClockwise1);
        Assertions.assertEquals(mat1.copy().rotate90DegClockwise(), rotate90DegClockwise1);
        Assertions.assertEquals(mat1.copy().rotate180Deg(), rotate180Deg1);


        // special matrix methods
        mat1.set(new double[][]{
                {10, 2, 2, 2},
                {2, 94, 2, 2},
                {2, 2, 165651, 2},
                {2, 2, 2, 515050}
        });
        Assertions.assertTrue(mat1.isSymmetrical());
        mat1.setCell(0, 1, 50);
        Assertions.assertFalse(mat1.isSymmetrical());

        for (int i = 1; i < 200; i++)
            Assertions.assertTrue(new TMatN(i).isIdentityMatrix());
        Assertions.assertTrue(new TMatN(new TMat2()).isIdentityMatrix());
        Assertions.assertTrue(new TMatN(new TMat3()).isIdentityMatrix());
        Assertions.assertTrue(new TMatN(new TMat4()).isIdentityMatrix());
        Assertions.assertFalse(new TMatN(new TMat2(1, 0, 0, 2)).isIdentityMatrix());

        mat1.set(new double[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        });
        Assertions.assertTrue(mat1.isSingular());
        mat1.set(new double[][]{
                {100, 2, 56413, 4},
                {5, 600, 7, 68},
                {9, 100, 11, 12},
                {13, 14, 20015, 16}
        });
        Assertions.assertFalse(mat1.isSingular());


    }



}


