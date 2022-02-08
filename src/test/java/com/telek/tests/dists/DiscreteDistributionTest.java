package com.telek.tests.dists;

import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.advanced.distributions.discrete.BinomialDist;
import com.telek.telekmath.advanced.distributions.discrete.PoissonDist;
import org.apache.commons.math3.distribution.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscreteDistributionTest {

    @Test
    @DisplayName("discDistTest")
    void discDistTest() {

        {
            BinomialDistribution d11 = new BinomialDistribution(1, 1);
            Assertions.assertTrue(TMath.areEqual(BinomialDist.probability(1, 1, 1), d11.probability(1)));
            Assertions.assertTrue(TMath.areEqual(BinomialDist.expectedValue(1, 1), d11.getNumericalMean()));
            Assertions.assertTrue(TMath.areEqual(BinomialDist.variance(1, 1), d11.getNumericalVariance()));
        }

        {
            org.apache.commons.math3.distribution.PoissonDistribution d11 = new org.apache.commons.math3.distribution.PoissonDistribution(0.5d);
            Assertions.assertTrue(TMath.areEqual(PoissonDist.probability(0.5d, 1), d11.probability(1)));
            Assertions.assertTrue(TMath.areEqual(PoissonDist.expectedValue(0.5d), d11.getNumericalMean()));
            Assertions.assertTrue(TMath.areEqual(PoissonDist.variance(0.5d), d11.getNumericalVariance()));
        }


    }


}
