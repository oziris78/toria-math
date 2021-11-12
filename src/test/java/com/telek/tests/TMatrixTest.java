package com.telek.tests;

import com.telek.telekmath.core.matrices.TMatrix;
import com.telek.telekmath.core.matrices.TSpecialMatrix;


public class TMatrixTest {


    public static void main(String[] args) {

        double[][] matrix1 = {
                {1,2,3},
                {7,8,9},
                {-1,0,16},
        };

        double[][] matrix2 = {
                {4,2,4},
                {1,1,91},
                {78,20,64},
        };

        double[][] matrix3 = TMatrix.createIdentityMatrix(6);
        double[][] matrix4 = TMatrix.add(matrix1, matrix2);
        double[][] matrix5 = TMatrix.matrixMultiplication(matrix1, matrix2);
        double[][] matrix6 = TMatrix.scale(matrix3,10);
        double[][] matrix7 = TMatrix.subtract(matrix4, matrix2);
        double[][] matrix8 = TMatrix.powerMatrix(matrix3, 10);
        double[][] matrix9 = TMatrix.powerMatrix(matrix1, 3);

        TMatrix.printlnMatrix(matrix1);
        TMatrix.printlnMatrix(matrix2);
        TMatrix.printlnMatrix(matrix3);
        TMatrix.printlnMatrix(matrix4);
        TMatrix.printlnMatrix(matrix5);
        TMatrix.printlnMatrix(matrix6);
        TMatrix.printlnMatrix(matrix7);
        TMatrix.printlnMatrix(matrix8);
        TMatrix.printlnMatrix(matrix9);

        System.out.println("det(matrix1): " + TMatrix.determinant(matrix1));

        System.out.println("this should be true: " + TSpecialMatrix.isIdentityMatrix(matrix3));
        System.out.println("this should be false: " + TSpecialMatrix.isIdentityMatrix(matrix1));

        System.out.println("this should be true: " + TMatrix.areEqual(matrix3, matrix8));

        // lehmer matrices

        TMatrix.printlnMatrix(TSpecialMatrix.createLehmerMatrix(1));
        TMatrix.printlnMatrix(TSpecialMatrix.createLehmerMatrix(2));
        TMatrix.printlnMatrix(TSpecialMatrix.createLehmerMatrix(3));
        TMatrix.printlnMatrix(TSpecialMatrix.createLehmerMatrix(4));

        // hilbert matrices

        TMatrix.printlnMatrix(TSpecialMatrix.createHilbertMatrix(1));
        TMatrix.printlnMatrix(TSpecialMatrix.createHilbertMatrix(2));
        TMatrix.printlnMatrix(TSpecialMatrix.createHilbertMatrix(3));
        TMatrix.printlnMatrix(TSpecialMatrix.createHilbertMatrix(4));

    }


}
