package com.telek.tests.dists;

import org.apache.commons.math3.distribution.TDistribution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TDistTest {

    @Test
    @DisplayName("tDistTest")
    void tDistTest() {
        TDistribution td = new TDistribution(8);
        System.out.println(td.inverseCumulativeProbability(0.025));

    }

}
