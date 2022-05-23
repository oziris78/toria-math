package com.telek.tests.matrices;

import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekmath.core.matrices.TMat2;
import com.telek.telekmath.core.matrices.TMat3;
import com.telek.telekmath.utils.TMath;
import org.joml.Matrix2d;
import org.joml.Matrix3d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;


public class JOMLvsTelek {


    static final int TIMES = 20_000_000;
    static Random random = new Random();



    @Test
    @DisplayName("mat3Test")
    void mat3Test() {
        double[] jomlTimes = new double[TIMES];
        int jomlIndex = 0;
        double[] telekTimes = new double[TIMES];
        int telekIndex = 0;
        for (int unused = 0; unused < TIMES; unused++) {
            double m00 = random.nextDouble(), m01 = random.nextDouble(), m02 = random.nextDouble();
            double m10 = random.nextDouble(), m11 = random.nextDouble(), m12 = random.nextDouble();
            double m20 = random.nextDouble(), m21 = random.nextDouble(), m22 = random.nextDouble();
            double jDet;
            double tDet;
            // JOML
            long jomlStart = System.nanoTime();
            Matrix3d jMat = new Matrix3d(m00, m10, m20, m01, m11, m21, m02, m12, m22);
            jMat.scale(5d);
            jMat.add(jMat);
            jMat.sub(new Matrix3d(1, 2, 3, 4, 5, 6, 7, 8, 9));
            jMat.mul(jMat);
            jDet = jMat.determinant();
            if(!TMath.areEqual(jDet, 0d))
                jMat.invert();
            long jomlEnd = System.nanoTime();
            double jTotalTime = (jomlEnd - jomlStart) / 1000000d;
            jomlTimes[jomlIndex++] = jTotalTime;
            // JOML
            // TELEK
            long telekStart = System.nanoTime();
            TMat3 tMat = new TMat3(m00, m01, m02, m10, m11, m12, m20, m21, m22);
            tMat.scale(5d);
            tMat.add(tMat);
            tMat.subtract(new TMat3(1, 4, 7, 2, 5, 8, 3, 6, 9));
            tMat.multiply(tMat);
            tDet = tMat.determinant();
            if(!TMath.areEqual(tDet, 0d))
                tMat.invert();
            long telekEnd = System.nanoTime();
            double tTotalTime = (telekEnd - telekStart) / 1000000d;
            telekTimes[telekIndex++] = tTotalTime;
            // TELEK
            if(!TMath.areEqual(jDet, tDet)){
                System.out.println("Failed for these values:");
                System.out.printf("jDet: %f\n tDet: %f\n tMat: %s\n jMat: %s\n", jDet, tDet, tMat, jMat);
                Assertions.fail();
            }
        }
        Arrays.sort(jomlTimes);
        Arrays.sort(telekTimes);
        System.out.println("Telek:");
        System.out.println(DescStats.getDataDesc(telekTimes));
        System.out.println();
        System.out.println("JOML:");
        System.out.println(DescStats.getDataDesc(jomlTimes));
    }

    @Test
    @DisplayName("mat2Test")
    void mat2Test() {
        double[] jomlTimes = new double[TIMES];
        int jomlIndex = 0;
        double[] telekTimes = new double[TIMES];
        int telekIndex = 0;
        for (int i = 0; i < TIMES; i++) {
            double m00 = random.nextDouble();
            double m01 = random.nextDouble();
            double m10 = random.nextDouble();
            double m11 = random.nextDouble();
            double jDet;
            double tDet;
            // JOML
            long jomlStart = System.nanoTime();
            Matrix2d jMat = new Matrix2d(m00, m10, m01, m11);
            jMat.scale(5d);
            jMat.add(jMat);
            jMat.sub(new Matrix2d(m11, m10, m01, m00));
            jMat.mul(jMat);
            jDet = jMat.determinant();
            if(!TMath.areEqual(jDet, 0d))
                jMat.invert();
            long jomlEnd = System.nanoTime();
            double jTotalTime = (jomlEnd - jomlStart) / 1000000d;
            jomlTimes[jomlIndex++] = jTotalTime;
            // JOML
            // TELEK
            long telekStart = System.nanoTime();
            TMat2 tMat = new TMat2(m00, m01, m10, m11);
            tMat.scale(5d);
            tMat.add(tMat);
            tMat.subtract(new TMat2(m11, m01, m10, m00));
            tMat.multiply(tMat);
            tDet = tMat.determinant();
            if(!TMath.areEqual(tDet, 0d))
                tMat.invert();
            long telekEnd = System.nanoTime();
            double tTotalTime = (telekEnd - telekStart) / 1000000d;
            telekTimes[telekIndex++] = tTotalTime;
            // TELEK
            if(!TMath.areEqual(jDet, tDet)){
                System.out.println("Failed for these values:");
                System.out.printf("jDet: %f\n tDet: %f\n tMat: %s\n jMat: %s\n", jDet, tDet, tMat, jMat);
                Assertions.fail();
            }
        }
        Arrays.sort(jomlTimes);
        Arrays.sort(telekTimes);
        System.out.println("Telek:");
        System.out.println(DescStats.getDataDesc(telekTimes));
        System.out.println();
        System.out.println("JOML:");
        System.out.println(DescStats.getDataDesc(jomlTimes));
    }



}