package com.telek.tests.dists;

import com.telek.telekmath.TMath;
import com.telek.telekmath.advanced.distributions.continuous.*;
import com.telek.telekmath.advanced.distributions.continuous.chisqrd.ChiSquaredDist;
import com.telek.telekmath.advanced.distributions.continuous.normal.NormalDist;
import org.apache.commons.math3.distribution.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContDistTest {

    @Test
    @DisplayName("contDistTest")
    void contDistTest() {

        {
            BetaDistribution d11 = new BetaDistribution(1, 1);
            Assertions.assertTrue(TMath.areEqual(BetaDist.expectedValue(1, 1), d11.getNumericalMean()));
            Assertions.assertTrue(TMath.areEqual(BetaDist.variance(1, 1), d11.getNumericalVariance()));
        }

        {
            ChiSquaredDistribution d11 = new ChiSquaredDistribution(1d);
            Assertions.assertTrue(TMath.areEqual(ChiSquaredDist.expectedValue(1d), d11.getNumericalMean()));
            Assertions.assertTrue(TMath.areEqual(ChiSquaredDist.variance(1d), d11.getNumericalVariance()));
        }

        {
            ExponentialDistribution d11 = new ExponentialDistribution(1d);
            Assertions.assertTrue(TMath.areEqual(ExponentialDist.expectedValue(1d), d11.getNumericalMean()));
            Assertions.assertTrue(TMath.areEqual(ExponentialDist.variance(1d), d11.getNumericalVariance()));
        }

        {
            GammaDistribution d11 = new GammaDistribution(0.5d, 0.5d);
            Assertions.assertTrue(TMath.areEqual(GammaDist.expectedValue(0.5d, 0.5d), d11.getNumericalMean()));
            Assertions.assertTrue(TMath.areEqual(GammaDist.variance(0.5d, 0.5d), d11.getNumericalVariance()));
        }

        {
            NormalDistribution d11 = new NormalDistribution(1, 1);
            Assertions.assertTrue(TMath.areEqual(NormalDist.expectedValue(1d), d11.getNumericalMean()));
            Assertions.assertTrue(TMath.areEqual(NormalDist.variance(1d), d11.getNumericalVariance()));
        }

    }


}
