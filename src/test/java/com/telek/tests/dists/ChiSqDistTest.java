package com.telek.tests.dists;

import com.telek.telekmath.TMath;
import com.telek.telekmath.advanced.distributions.cont.ChiSquaredDist;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ChiSqDistTest {

    static final int TIMES = 10_000_000;

    @Test
    @DisplayName("densityTest")
    void densityTest() {
        Random random = new Random();
        for (int unused = 0; unused < TIMES; unused++) {
            int v = 1 + random.nextInt(1000);
            ChiSquaredDistribution csd = new ChiSquaredDistribution(v);
            double x = random.nextDouble() * random.nextInt(150);
            double val1 = csd.density(x);
            double val2 = ChiSquaredDist.density(v, x);
            boolean b = TMath.areEqual(val1, val2);
            if(!b){
                System.out.println("v : " + v);
                System.out.println("x : " + x);
                System.out.println("val1 : " + val1);
                System.out.println("val2 : " + val2);
            }
            Assertions.assertTrue(b);
        }
    }


}
