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
    @DisplayName("tDistTest")
    void tDistTest() {
        TDistribution td = new TDistribution(8);
        System.out.println(td.inverseCumulativeProbability(0.025));

    }

}
