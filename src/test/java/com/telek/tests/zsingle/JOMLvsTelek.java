package com.telek.tests.zsingle;

import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekmath.core.matrices.TMatrix;
import com.telek.telekmath.core.matrices.TMatrixUtils;
import org.joml.*;
import java.util.Arrays;
import java.util.Random;


public class JOMLvsTelek {

    static class MyMat2{
        double m00, m01;
        double m10, m11;

        public MyMat2(){
            this.m00 = 1; this.m01 = 0;
            this.m10 = 0; this.m11 = 1;
        }

        public void setCell(int row, int col, double val){
            if(row == 0 && col == 0) m00 = val;
            else if(row == 0 && col == 1) m01 = val;
            else if(row == 1 && col == 0) m10 = val;
            else if(row == 1 && col == 1) m11 = val;
        }

        public double determinant(){
            return m00 * m11 - m01 * m10;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        final int TIMES = 1_000_000;

        {
            double[] takenTimes = new double[TIMES];
            int index = 0;
            for (int unused = 0; unused < TIMES; unused++) {
                long start = System.nanoTime();
                Matrix2d mat = new Matrix2d();
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        mat.set(j, i, random.nextDouble());
                    }
                }
                mat.determinant();
                long end = System.nanoTime();
                double totalTime = (end - start) / 1000000d;
                takenTimes[index++] = totalTime;
            }
            Arrays.sort(takenTimes);
            DataDescription jomlDesc = DescStats.getDataDesc(takenTimes);
            System.out.println("JOML:");
            System.out.println(jomlDesc.sum);
        }


        {
            double[] takenTimes = new double[TIMES];
            int index = 0;
            for (int unused = 0; unused < TIMES; unused++) {
                long start = System.nanoTime();
                TMatrix mat = TMatrixUtils.createIdentityMatrix(2);
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        mat.setCell(i, j, random.nextDouble());
                    }
                }
                mat.determinant();
                long end = System.nanoTime();
                double totalTime = (end - start) / 1000000d;
                takenTimes[index++] = totalTime;
            }
            Arrays.sort(takenTimes);
            DataDescription telekDesc = DescStats.getDataDesc(takenTimes);
            System.out.println("TMatrix:");
            System.out.println(telekDesc.sum);
        }


        {
            double[] takenTimes = new double[TIMES];
            int index = 0;
            for (int unused = 0; unused < TIMES; unused++) {
                long start = System.nanoTime();
                MyMat2 mat = new MyMat2();
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        mat.setCell(i, j, random.nextDouble());
                    }
                }
                mat.determinant();
                long end = System.nanoTime();
                double totalTime = (end - start) / 1000000d;
                takenTimes[index++] = totalTime;
            }
            Arrays.sort(takenTimes);
            DataDescription myMat2Desc = DescStats.getDataDesc(takenTimes);
            System.out.println("MyMat2:");
            System.out.println(myMat2Desc.sum);
        }

    }


}
