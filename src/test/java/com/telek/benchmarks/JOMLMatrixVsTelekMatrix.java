package com.telek.benchmarks;

import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekmath.core.matrices.TMat2;
import com.telek.telekmath.core.matrices.TMat3;
import com.telek.telekmath.core.matrices.TMat4;
import com.telek.telekmath.utils.TMath;
import org.joml.Matrix2d;
import org.joml.Matrix3d;
import org.joml.Matrix4d;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Random;


public class JOMLMatrixVsTelekMatrix {


    static final int TIMES = 10_000_000;
    static Random ran = new Random();




    public static void main(String[] args) {
        mat2Benchmark();
        mat3Benchmark();
        mat4Benchmark();
    }

    static void mat4Benchmark() {
        double[] jomlTimes = new double[TIMES];
        int jomlIndex = 0;
        double[] telekTimes = new double[TIMES];
        int telekIndex = 0;
        for (int unused = 0; unused < TIMES; unused++) {
            double m00 = ran.nextDouble(), m01 = ran.nextDouble(), m02 = ran.nextDouble(), m03 = ran.nextDouble();
            double m10 = ran.nextDouble(), m11 = ran.nextDouble(), m12 = ran.nextDouble(), m13 = ran.nextDouble();
            double m20 = ran.nextDouble(), m21 = ran.nextDouble(), m22 = ran.nextDouble(), m23 = ran.nextDouble();
            double m30 = ran.nextDouble(), m31 = ran.nextDouble(), m32 = ran.nextDouble(), m33 = ran.nextDouble();
            double jDet;
            double tDet;
            // JOML
            long jomlStart = System.nanoTime();
            Matrix4d jMat = new Matrix4d(m00, m10, m20,m30,m01,m11,m21,m31,m02,m12,m22,m32,m03,m13,m23,m33);
            jMat.scale(5d);
            jMat.add(jMat);
            jMat.sub(new Matrix4d(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16));
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
            TMat4 tMat = new TMat4(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33);
            tMat.scale(5d);
            tMat.add(tMat);
            tMat.subtract(new TMat4(1 ,5, 9 ,13, 2 ,6, 10 ,14, 3 ,7, 11 ,15, 4 ,8 ,12 ,16));
            tMat.multiply(tMat);
            tDet = tMat.determinant();
            if(!TMath.areEqual(tDet, 0d))
                tMat.invert();
            long telekEnd = System.nanoTime();
            double tTotalTime = (telekEnd - telekStart) / 1000000d;
            telekTimes[telekIndex++] = tTotalTime;
        }
        Arrays.sort(jomlTimes);
        Arrays.sort(telekTimes);
        System.out.println("Telek:");
        System.out.println(DescStats.getDataDesc(telekTimes));
        System.out.println();
        System.out.println("JOML:");
        System.out.println(DescStats.getDataDesc(jomlTimes));
    }


    static void mat3Benchmark() {
        double[] jomlTimes = new double[TIMES];
        int jomlIndex = 0;
        double[] telekTimes = new double[TIMES];
        int telekIndex = 0;
        for (int unused = 0; unused < TIMES; unused++) {
            double m00 = ran.nextDouble(), m01 = ran.nextDouble(), m02 = ran.nextDouble();
            double m10 = ran.nextDouble(), m11 = ran.nextDouble(), m12 = ran.nextDouble();
            double m20 = ran.nextDouble(), m21 = ran.nextDouble(), m22 = ran.nextDouble();
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


    static void mat2Benchmark() {
        double[] jomlTimes = new double[TIMES];
        int jomlIndex = 0;
        double[] telekTimes = new double[TIMES];
        int telekIndex = 0;
        for (int i = 0; i < TIMES; i++) {
            double m00 = ran.nextDouble();
            double m01 = ran.nextDouble();
            double m10 = ran.nextDouble();
            double m11 = ran.nextDouble();
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
