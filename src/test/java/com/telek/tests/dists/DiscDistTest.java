package com.telek.tests.dists;

import com.telek.telekmath.TMath;
import com.telek.telekmath.advanced.distributions.discdist.BinomialDist;
import com.telek.telekmath.advanced.distributions.discdist.PoissonDist;
import org.apache.commons.math3.distribution.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscDistTest {

    @Test
    @DisplayName("discDistTest")
    void discDistTest() {

        {
            BinomialDist d1 = new BinomialDist(1, 1);
            BinomialDistribution d11 = new BinomialDistribution(1, 1);
            Assertions.assertTrue(TMath.areEqual(d1.probability(1), d11.probability(1)));
            Assertions.assertTrue(TMath.areEqual(d1.getE(), d11.getNumericalMean()));
            Assertions.assertTrue(TMath.areEqual(d1.getVAR(), d11.getNumericalVariance()));
        }

        {
            PoissonDist d1 = new PoissonDist(0.5d);
            PoissonDistribution d11 = new PoissonDistribution(0.5d);
            Assertions.assertTrue(TMath.areEqual(d1.probability(1), d11.probability(1)));
            Assertions.assertTrue(TMath.areEqual(d1.getE(), d11.getNumericalMean()));
            Assertions.assertTrue(TMath.areEqual(d1.getVAR(), d11.getNumericalVariance()));
        }


    }


}
