package com.telek.tests.dists;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChiSqDistTest {

    @Test
    @DisplayName("cdfTest")
    void cdfTest() {
        ChiSquaredDistribution csd = new ChiSquaredDistribution(2);
        System.out.println(csd.inverseCumulativeProbability(0.75d));
    }


}
