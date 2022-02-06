package com.telek.tests.dists;

import com.telek.telekmath.TMath;
import com.telek.telekmath.advanced.distributions.cont.TDist;
import org.apache.commons.math3.distribution.TDistribution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class TDistTest {


    @Test
    @DisplayName("densityTest")
    void densityTest() {
        Random random = new Random();
        final int TIMES = 1000;
        for (int i = 0; i < TIMES; i++) {
            double v = random.nextDouble();
            double x = random.nextDouble();
            TDistribution td = new TDistribution(v);
            Assertions.assertTrue(TMath.areEqual(TDist.density(v, x), td.density(x)));
        }
    }


    @Test
    @DisplayName("cumProbTest")
    void cumProbTest() {
        Random random = new Random();

        double val11 = new TDistribution(106.0).cumulativeProbability(5.170182555744066);
        double val21 = TDist.cumulativeProbability(106.0, 5.170182555744066);
        System.out.println(val11);
        System.out.println(val21);
        Assertions.assertTrue(TMath.areEqual(val11, val21));

        for (int i = 0; i < 1000; i++) {
            double x = random.nextInt(10) * random.nextDouble();
            double v = (int) (100 + random.nextInt(10) * random.nextDouble());
            // for hypergeometric func |z| < 1  <=>  |-x^2/v| < 1   <=>  x^2/v < 1  <=>   x^2 < v
            if(x * x >= v)
                continue;
            double val1 = new TDistribution(v).cumulativeProbability(x);
            double val2 = TDist.cumulativeProbability(v, x);
            System.out.println("x: " + x);
            System.out.println("v: " + v);
            System.out.println("val1: " + val1);
            System.out.println("val2: " + val2);
            Assertions.assertTrue(TMath.areEqual(val1, val2));
        }
    }


    @Test
    @DisplayName("invCumProbTest")
    void invCumProbTest() {
        double v = 10;
        double p = 0.4d;
        double val1 = new TDistribution(v).inverseCumulativeProbability(p);
        double val2 = TDist.inverseCumulativeProbability(v, p);
        System.out.println("apache: " + val1);
        System.out.println("my val: " + val2);
    }

}
