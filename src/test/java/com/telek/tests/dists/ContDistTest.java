package com.telek.tests.dists;

import com.telek.telekmath.TMath;
import com.telek.telekmath.special.distributions.contdist.*;
import org.apache.commons.math3.distribution.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContDistTest {

    @Test
    @DisplayName("ContDistTest")
    void contDistTest() {

        {
            BetaDist d1 = new BetaDist(1, 1);
            BetaDistribution d11 = new BetaDistribution(1, 1);
            Assertions.assertTrue(TMath.areEqual(d1.probability(0.5d), 0));
            Assertions.assertTrue(TMath.areEqual(d1.getE(), d11.getNumericalMean()));
            Assertions.assertTrue(TMath.areEqual(d1.getVAR(), d11.getNumericalVariance()));
        }

        {
            ChiSquaredDist d1 = new ChiSquaredDist(0.5d);
            ChiSquaredDistribution d11 = new ChiSquaredDistribution(0.5d * 2d);
            Assertions.assertTrue(TMath.areEqual(d1.probability(0.5d), 0));
            Assertions.assertTrue(TMath.areEqual(d1.getE(), d11.getNumericalMean()));
            Assertions.assertTrue(TMath.areEqual(d1.getVAR(), d11.getNumericalVariance()));
        }

        {
            ExponentialDist d1 = new ExponentialDist(1d);
            ExponentialDistribution d11 = new ExponentialDistribution(1d);
            Assertions.assertTrue(TMath.areEqual(d1.probability(0.5d), 0));
            Assertions.assertTrue(TMath.areEqual(d1.getE(), d11.getNumericalMean()));
            Assertions.assertTrue(TMath.areEqual(d1.getVAR(), d11.getNumericalVariance()));
        }

        {
            GammaDist d1 = new GammaDist(0.5d, 0.5d);
            GammaDistribution d11 = new GammaDistribution(0.5d, 0.5d);
            Assertions.assertTrue(TMath.areEqual(d1.probability(0.5d), 0));
            Assertions.assertTrue(TMath.areEqual(d1.getE(), d11.getNumericalMean()));
            Assertions.assertTrue(TMath.areEqual(d1.getVAR(), d11.getNumericalVariance()));
        }

        {
            NormalDist d1 = new NormalDist(1, 1);
            NormalDistribution d11 = new NormalDistribution(1, 1);
            Assertions.assertTrue(TMath.areEqual(d1.probability(0.5d), 0));
            Assertions.assertTrue(TMath.areEqual(d1.getE(), d11.getNumericalMean()));
            Assertions.assertTrue(TMath.areEqual(d1.getVAR(), d11.getNumericalVariance()));
        }

    }


}
