package com.telek.tests.matrices;

import com.telek.telekmath.core.matrices.TMatrix;
import com.telek.telekmath.utils.TelekMathException.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


public class TMatrixTest {


    @Test
    @DisplayName("matrixTest")
    void matrixTest() {
        TMatrix mat1 = new TMatrix(new double[][] {
                new double[] {1,2,4},
                new double[] {7,8,-1},
                new double[] {12,-75,36},
        }); // 3x3

        TMatrix minusMat1 = new TMatrix(new double[][] {
                new double[] {-1,-2,-4},
                new double[] {-7,-8,1},
                new double[] {-12,75,-36},
        }); // 3x3

        TMatrix mat11 = new TMatrix(new double[][] {
                new double[] {1,2,4},
                new double[] {7,8,-1},
                new double[] {12,-75,36},
        }); // 3x3

        TMatrix matFlip = new TMatrix(new double[][] {
                new double[] {4,2,1},
                new double[] {-1,8,7},
                new double[] {36,-75,12},
        }); // 3x3

        TMatrix matRotate = new TMatrix(new double[][] {
                new double[]{12.0, 7.0, 1.0},
                new double[]{-75.0, 8.0, 2.0},
                new double[]{36.0, -1.0, 4.0}
        }); // 3x3

        TMatrix mat2 = new TMatrix(new double[][] {
                new double[] {-1},
                new double[] {5},
                new double[] {7}
        }); // 3x1

        TMatrix multResult = new TMatrix(new double[][] {
                new double[] {37},
                new double[] {26},
                new double[] {-135}
        }); // 3x1

        Assertions.assertEquals(mat1, mat11);
        Assertions.assertNotEquals(mat1, mat2);
        Assertions.assertEquals(mat1.isSquareMatrix(), true);
        Assertions.assertEquals(mat1.isSquareMatrix(), true);
        Assertions.assertEquals(mat2.isSquareMatrix(), false);

        Assertions.assertEquals(mat1.copy().rotate90DegClockwise().rotate90DegClockwise().rotate90DegClockwise().rotate90DegClockwise(), mat1);
        Assertions.assertEquals(mat1.copy().rotate90DegClockwise(), matRotate);
        Assertions.assertEquals(mat1.copy().matrixMultiplication(mat2), multResult);
        Assertions.assertEquals(mat1.determinant(), -2799);
        Assertions.assertEquals(mat1.copy().flipLeftRight(), matFlip);

        Assertions.assertEquals(mat1.getRowSize(), 3);
        Assertions.assertEquals(mat1.getColSize(), 3);
        Assertions.assertEquals(mat2.getRowSize(), 3);
        Assertions.assertEquals(mat2.getColSize(), 1);

        Assertions.assertEquals(mat2.getCell(0, 0), -1);
        Assertions.assertEquals(mat2.getCell(1, 0), 5);
        Assertions.assertEquals(mat2.getCell(2, 0), 7);

        mat11.setCell(0, 0, 99);
        Assertions.assertEquals(mat11.getCell(0, 0), 99);
        Assertions.assertNotEquals(mat1, mat11);

        Assertions.assertEquals(mat1.trace(), 45);
        Assertions.assertThrows(NotASquareMatrixException.class, () -> {
            double trace = mat2.trace();
        });

        Assertions.assertArrayEquals(mat1.getDiagonal(), new double[]{1, 8, 36});

        Assertions.assertEquals(mat1.copy().add(minusMat1), new TMatrix(new double[][]{
                {0,0,0},
                {0,0,0},
                {0,0,0}
        }));

        Assertions.assertEquals(mat1.copy().subtract(minusMat1), mat1.copy().scale(2));

        Assertions.assertEquals(mat2.copy().transpose(), new TMatrix(new double[][]{
                {-1, 5, 7}
        }));


        TMatrix mat111 = new TMatrix(new double[][]{
                {1, 2, 3},
                {3, 5, 6}
        });

        TMatrix incCopy1 = mat111.copy(3, 5);

        TMatrix incCopy2 = new TMatrix(new double[][]{
                {1, 2, 3, 0, 0},
                {3, 5, 6, 0, 0},
                {0, 0, 0, 0, 0}
        });

        Assertions.assertTrue(incCopy1.equals(incCopy2));
    }


    @Test
    @DisplayName("copyConstructorTest")
    void copyConstructorTest() {
        TMatrix mat1 = new TMatrix(new double[][] {
                new double[]{12.0, 7.0, 1.0},
                new double[]{-75.0, 8.0, 2.0},
                new double[]{36.0, -1.0, 4.0}
        }); // 3x3

        TMatrix mat2 = new TMatrix(mat1);

        mat1.setCell(0, 0, 25d);
        mat2.setCell(0, 1, 100);

        Assertions.assertFalse(mat1.getCell(0, 1) == 100);
        Assertions.assertFalse(mat2.getCell(0, 0) == 25);
    }


    @Test
    @DisplayName("differentTypeArray2Tests")
    void differentTypeArray2Tests() {
        // these tests are the same as matrixTest()
        // i recommend collapsing these scopes to get a better view
        // int test
        {
            TMatrix mat1 = new TMatrix(new int[][] {
                    {1,2,4},
                    {7,8,-1},
                    {12,-75,36},
            }); // 3x3

            TMatrix minusMat1 = new TMatrix(new int[][] {
                    {-1,-2,-4},
                    {-7,-8,1},
                    {-12,75,-36},
            }); // 3x3

            TMatrix mat11 = new TMatrix(new int[][] {
                    {1,2,4},
                    {7,8,-1},
                    {12,-75,36},
            }); // 3x3

            TMatrix matFlip = new TMatrix(new int[][] {
                    {4,2,1},
                    {-1,8,7},
                    {36,-75,12},
            }); // 3x3

            TMatrix matRotate = new TMatrix(new int[][] {
                    {12, 7, 1},
                    {-75, 8, 2},
                    {36, -1, 4}
            }); // 3x3

            TMatrix mat2 = new TMatrix(new int[][] {
                    {-1},
                    {5},
                    {7}
            }); // 3x1

            TMatrix multResult = new TMatrix(new int[][] {
                    {37},
                    {26},
                    {-135}
            }); // 3x1

            Assertions.assertEquals(mat1, mat11);
            Assertions.assertNotEquals(mat1, mat2);
            Assertions.assertEquals(mat1.isSquareMatrix(), true);
            Assertions.assertEquals(mat1.isSquareMatrix(), true);
            Assertions.assertEquals(mat2.isSquareMatrix(), false);

            Assertions.assertEquals(mat1.copy().rotate90DegClockwise().rotate90DegClockwise().rotate90DegClockwise().rotate90DegClockwise(), mat1);
            Assertions.assertEquals(mat1.copy().rotate90DegClockwise(), matRotate);
            Assertions.assertEquals(mat1.copy().matrixMultiplication(mat2), multResult);
            Assertions.assertEquals(mat1.determinant(), -2799);
            Assertions.assertEquals(mat1.copy().flipLeftRight(), matFlip);

            Assertions.assertEquals(mat1.getRowSize(), 3);
            Assertions.assertEquals(mat1.getColSize(), 3);
            Assertions.assertEquals(mat2.getRowSize(), 3);
            Assertions.assertEquals(mat2.getColSize(), 1);

            Assertions.assertEquals(mat2.getCell(0, 0), -1);
            Assertions.assertEquals(mat2.getCell(1, 0), 5);
            Assertions.assertEquals(mat2.getCell(2, 0), 7);

            mat11.setCell(0, 0, 99);
            Assertions.assertEquals(mat11.getCell(0, 0), 99);
            Assertions.assertNotEquals(mat1, mat11);

            Assertions.assertEquals(mat1.trace(), 45);
            Assertions.assertThrows(NotASquareMatrixException.class, () -> {
                double trace = mat2.trace();
            });

            Assertions.assertArrayEquals(mat1.getDiagonal(), new double[]{1, 8, 36});

            Assertions.assertEquals(mat1.copy().add(minusMat1), new TMatrix(new int[][]{
                    {0,0,0},
                    {0,0,0},
                    {0,0,0}
            }));

            Assertions.assertEquals(mat1.copy().subtract(minusMat1), mat1.copy().scale(2));

            Assertions.assertEquals(mat2.copy().transpose(), new TMatrix(new int[][]{
                    {-1, 5, 7}
            }));


            TMatrix mat111 = new TMatrix(new int[][]{
                    {1, 2, 3},
                    {3, 5, 6}
            });

            TMatrix incCopy1 = mat111.copy(3, 5);

            TMatrix incCopy2 = new TMatrix(new int[][]{
                    {1, 2, 3, 0, 0},
                    {3, 5, 6, 0, 0},
                    {0, 0, 0, 0, 0}
            });

            Assertions.assertTrue(incCopy1.equals(incCopy2));
        }
        // float test
        {
            TMatrix mat1 = new TMatrix(new float[][] {
                    {1,2,4},
                    {7,8,-1},
                    {12,-75,36},
            }); // 3x3

            TMatrix minusMat1 = new TMatrix(new float[][] {
                    {-1,-2,-4},
                    {-7,-8,1},
                    {-12,75,-36},
            }); // 3x3

            TMatrix mat11 = new TMatrix(new float[][] {
                    {1,2,4},
                    {7,8,-1},
                    {12,-75,36},
            }); // 3x3

            TMatrix matFlip = new TMatrix(new float[][] {
                    {4,2,1},
                    {-1,8,7},
                    {36,-75,12},
            }); // 3x3

            TMatrix matRotate = new TMatrix(new float[][] {
                    {12, 7, 1},
                    {-75, 8, 2},
                    {36, -1, 4}
            }); // 3x3

            TMatrix mat2 = new TMatrix(new float[][] {
                    {-1},
                    {5},
                    {7}
            }); // 3x1

            TMatrix multResult = new TMatrix(new float[][] {
                    {37},
                    {26},
                    {-135}
            }); // 3x1

            Assertions.assertEquals(mat1, mat11);
            Assertions.assertNotEquals(mat1, mat2);
            Assertions.assertEquals(mat1.isSquareMatrix(), true);
            Assertions.assertEquals(mat1.isSquareMatrix(), true);
            Assertions.assertEquals(mat2.isSquareMatrix(), false);

            Assertions.assertEquals(mat1.copy().rotate90DegClockwise().rotate90DegClockwise().rotate90DegClockwise().rotate90DegClockwise(), mat1);
            Assertions.assertEquals(mat1.copy().rotate90DegClockwise(), matRotate);
            Assertions.assertEquals(mat1.copy().matrixMultiplication(mat2), multResult);
            Assertions.assertEquals(mat1.determinant(), -2799);
            Assertions.assertEquals(mat1.copy().flipLeftRight(), matFlip);

            Assertions.assertEquals(mat1.getRowSize(), 3);
            Assertions.assertEquals(mat1.getColSize(), 3);
            Assertions.assertEquals(mat2.getRowSize(), 3);
            Assertions.assertEquals(mat2.getColSize(), 1);

            Assertions.assertEquals(mat2.getCell(0, 0), -1);
            Assertions.assertEquals(mat2.getCell(1, 0), 5);
            Assertions.assertEquals(mat2.getCell(2, 0), 7);

            mat11.setCell(0, 0, 99);
            Assertions.assertEquals(mat11.getCell(0, 0), 99);
            Assertions.assertNotEquals(mat1, mat11);

            Assertions.assertEquals(mat1.trace(), 45);
            Assertions.assertThrows(NotASquareMatrixException.class, () -> {
                double trace = mat2.trace();
            });

            Assertions.assertArrayEquals(mat1.getDiagonal(), new double[]{1, 8, 36});

            Assertions.assertEquals(mat1.copy().add(minusMat1), new TMatrix(new float[][]{
                    {0,0,0},
                    {0,0,0},
                    {0,0,0}
            }));

            Assertions.assertEquals(mat1.copy().subtract(minusMat1), mat1.copy().scale(2));

            Assertions.assertEquals(mat2.copy().transpose(), new TMatrix(new float[][]{
                    {-1, 5, 7}
            }));


            TMatrix mat111 = new TMatrix(new float[][]{
                    {1, 2, 3},
                    {3, 5, 6}
            });

            TMatrix incCopy1 = mat111.copy(3, 5);

            TMatrix incCopy2 = new TMatrix(new float[][]{
                    {1, 2, 3, 0, 0},
                    {3, 5, 6, 0, 0},
                    {0, 0, 0, 0, 0}
            });

            Assertions.assertTrue(incCopy1.equals(incCopy2));
        }
        // Number test
        {
            TMatrix mat1 = new TMatrix(new Number[][] {
                    {1,2,4},
                    {7,8,-1},
                    {12,-75,36},
            }); // 3x3

            TMatrix minusMat1 = new TMatrix(new Number[][] {
                    {-1,-2,-4},
                    {-7,-8,1},
                    {-12,75,-36},
            }); // 3x3

            TMatrix mat11 = new TMatrix(new Number[][] {
                    {1,2,4},
                    {7,8,-1},
                    {12,-75,36},
            }); // 3x3

            TMatrix matFlip = new TMatrix(new Number[][] {
                    {4,2,1},
                    {-1,8,7},
                    {36,-75,12},
            }); // 3x3

            TMatrix matRotate = new TMatrix(new Number[][] {
                    {12, 7, 1},
                    {-75, 8, 2},
                    {36, -1, 4}
            }); // 3x3

            TMatrix mat2 = new TMatrix(new Number[][] {
                    {-1},
                    {5},
                    {7}
            }); // 3x1

            TMatrix multResult = new TMatrix(new Number[][] {
                    {37},
                    {26},
                    {-135}
            }); // 3x1

            Assertions.assertEquals(mat1, mat11);
            Assertions.assertNotEquals(mat1, mat2);
            Assertions.assertEquals(mat1.isSquareMatrix(), true);
            Assertions.assertEquals(mat1.isSquareMatrix(), true);
            Assertions.assertEquals(mat2.isSquareMatrix(), false);

            Assertions.assertEquals(mat1.copy().rotate90DegClockwise().rotate90DegClockwise().rotate90DegClockwise().rotate90DegClockwise(), mat1);
            Assertions.assertEquals(mat1.copy().rotate90DegClockwise(), matRotate);
            Assertions.assertEquals(mat1.copy().matrixMultiplication(mat2), multResult);
            Assertions.assertEquals(mat1.determinant(), -2799);
            Assertions.assertEquals(mat1.copy().flipLeftRight(), matFlip);

            Assertions.assertEquals(mat1.getRowSize(), 3);
            Assertions.assertEquals(mat1.getColSize(), 3);
            Assertions.assertEquals(mat2.getRowSize(), 3);
            Assertions.assertEquals(mat2.getColSize(), 1);

            Assertions.assertEquals(mat2.getCell(0, 0), -1);
            Assertions.assertEquals(mat2.getCell(1, 0), 5);
            Assertions.assertEquals(mat2.getCell(2, 0), 7);

            mat11.setCell(0, 0, 99);
            Assertions.assertEquals(mat11.getCell(0, 0), 99);
            Assertions.assertNotEquals(mat1, mat11);

            Assertions.assertEquals(mat1.trace(), 45);
            Assertions.assertThrows(NotASquareMatrixException.class, () -> {
                double trace = mat2.trace();
            });

            Assertions.assertArrayEquals(mat1.getDiagonal(), new double[]{1, 8, 36});

            Assertions.assertEquals(mat1.copy().add(minusMat1), new TMatrix(new Number[][]{
                    {0,0,0},
                    {0,0,0},
                    {0,0,0}
            }));

            Assertions.assertEquals(mat1.copy().subtract(minusMat1), mat1.copy().scale(2));

            Assertions.assertEquals(mat2.copy().transpose(), new TMatrix(new Number[][]{
                    {-1, 5, 7}
            }));


            TMatrix mat111 = new TMatrix(new Number[][]{
                    {1, 2, 3},
                    {3, 5, 6}
            });

            TMatrix incCopy1 = mat111.copy(3, 5);

            TMatrix incCopy2 = new TMatrix(new Number[][]{
                    {1, 2, 3, 0, 0},
                    {3, 5, 6, 0, 0},
                    {0, 0, 0, 0, 0}
            });

            Assertions.assertTrue(incCopy1.equals(incCopy2));
        }
    }

}