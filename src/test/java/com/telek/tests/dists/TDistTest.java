package com.telek.tests.dists;

import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.advanced.distributions.cont.TDist;
import org.apache.commons.math3.distribution.TDistribution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;


public class TDistTest {

    static final int TIMES = 100_000;


    @Test
    @DisplayName("densityTest")
    void densityTest() {
        Random random = new Random();
        final int TIMES = 10_000;
        for (int i = 0; i < TIMES; i++) {
            double v = 1 + (int) (random.nextDouble() * random.nextInt(1000000));
            double x = random.nextDouble() * random.nextInt(1000000);
            TDistribution td = new TDistribution(v);
            double val1 = td.density(x);
            double val2 = TDist.density(v, x);
            boolean b = TMath.areEqual(val1, val2);
            if(!b){
                System.out.println("v: " + v);
                System.out.println("x: " + x);
                System.out.println("val1: " + val1);
                System.out.println("val2: " + val2);
            }
            Assertions.assertTrue(b);
        }
    }


    @Test
    @DisplayName("cumProbTest")
    void cumProbTest() {
        Random random = new Random();

        for (int i = 0; i < TIMES; i++) {
            double x = (random.nextInt(80) * random.nextDouble()) + 1;
            double v = (int) (random.nextInt(10000) * random.nextDouble()) + 10001;

            double val1 = new TDistribution(v).cumulativeProbability(x);
            double val2 = TDist.cumulativeProbability(v, x);
            boolean res = TMath.areEqual(val1, val2);
            if(!res){
                System.out.println("x: " + x);
                System.out.println("v: " + v);
                System.out.println("val1: " + val1);
                System.out.println("val2: " + val2);
            }
            Assertions.assertTrue(res);
        }


        for (int i = 0; i < TIMES; i++) {
            double x = (random.nextInt(10000) * random.nextDouble());
            double v = 1 + (int) (random.nextInt(10000) * random.nextDouble());

            double val1 = new TDistribution(v).cumulativeProbability(x);
            double val2 = TDist.cumulativeProbability(v, x);
            boolean res = TMath.areEqual(val1, val2);
            if(!res){
                System.out.println("x: " + x);
                System.out.println("v: " + v);
                System.out.println("val1: " + val1);
                System.out.println("val2: " + val2);
            }
            Assertions.assertTrue(res);
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
