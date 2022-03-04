package com.telek.tests.stats.hypo;

import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekmath.advanced.statistics.inferential.HypothesisTestings;
import com.telek.telekutils.containers.TArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class HypothesisSingleVarTest {


    @Test
    @DisplayName("singleVarTest")
    void singleVarTest() {
        // data
        int[] sample = TArrays.intArr(103, 96, 112, 130, 111, 125, 89, 100, 104, 117, 105, 116); // IQ scores
        int[] sortedSample = TArrays.getSortedCopy(sample);
        DataDescription sampleDesc = DescStats.getDataDesc(sortedSample);

        /*
        n = 12
        mean = 109
        sampleVariance=140.909,
        sampleStddev=11.870513506545995,
        */

        // MEAN TESTS
        // p >= alpha   =>  accept H_0 (true)
        // p value = 0.2677714447016315
        Assertions.assertTrue(HypothesisTestings.testForMean("=", 105, sampleDesc, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForMean("=", 105, sampleDesc, 0.10d));
        Assertions.assertTrue(HypothesisTestings.testForMean("=", 105, sampleDesc, 0.15d));
        Assertions.assertTrue(HypothesisTestings.testForMean("=", 105, sampleDesc, 0.25d));
        Assertions.assertFalse(HypothesisTestings.testForMean("=", 105, sampleDesc, 0.28d));
        Assertions.assertFalse(HypothesisTestings.testForMean("=", 105, sampleDesc, 0.5d));
        Assertions.assertFalse(HypothesisTestings.testForMean("=", 105, sampleDesc, 0.99d));

        // p value = 0.1658566603429099
        Assertions.assertTrue(HypothesisTestings.testForMean("=", 105, sampleDesc, 10d, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForMean("=", 105, sampleDesc, 10d, 0.10d));
        Assertions.assertTrue(HypothesisTestings.testForMean("=", 105, sampleDesc, 10d, 0.15d));
        Assertions.assertFalse(HypothesisTestings.testForMean("=", 105, sampleDesc, 10d, 0.25d));
        Assertions.assertFalse(HypothesisTestings.testForMean("=", 105, sampleDesc, 10d, 0.28d));
        Assertions.assertFalse(HypothesisTestings.testForMean("=", 105, sampleDesc, 10d, 0.5d));
        Assertions.assertFalse(HypothesisTestings.testForMean("=", 105, sampleDesc, 10d, 0.99d));

        // p value = 0.13388572235081575
        Assertions.assertTrue(HypothesisTestings.testForMean("<=", 105, sampleDesc, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForMean("<=", 105, sampleDesc, 0.10d));
        Assertions.assertTrue(HypothesisTestings.testForMean("<=", 105, sampleDesc, 0.11d));
        Assertions.assertTrue(HypothesisTestings.testForMean("<=", 105, sampleDesc, 0.13d));
        Assertions.assertFalse(HypothesisTestings.testForMean("<=", 105, sampleDesc, 0.14d));
        Assertions.assertFalse(HypothesisTestings.testForMean("<=", 105, sampleDesc, 0.16d));
        Assertions.assertFalse(HypothesisTestings.testForMean("<=", 105, sampleDesc, 0.20d));

        // p value = 0.0829283301714549
        Assertions.assertTrue(HypothesisTestings.testForMean("<=", 105, sampleDesc, 10d, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForMean("<=", 105, sampleDesc, 10d, 0.0829283301714d));
        Assertions.assertFalse(HypothesisTestings.testForMean("<=", 105, sampleDesc, 10d, 0.10d));
        Assertions.assertFalse(HypothesisTestings.testForMean("<=", 105, sampleDesc, 10d, 0.11d));
        Assertions.assertFalse(HypothesisTestings.testForMean("<=", 105, sampleDesc, 10d, 0.13d));
        Assertions.assertFalse(HypothesisTestings.testForMean("<=", 105, sampleDesc, 10d, 0.14d));
        Assertions.assertFalse(HypothesisTestings.testForMean("<=", 105, sampleDesc, 10d, 0.16d));
        Assertions.assertFalse(HypothesisTestings.testForMean("<=", 105, sampleDesc, 10d, 0.20d));

        // p value = 0.8661142776491842
        Assertions.assertTrue(HypothesisTestings.testForMean(">=", 105, sampleDesc, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForMean(">=", 105, sampleDesc, 0.10d));
        Assertions.assertTrue(HypothesisTestings.testForMean(">=", 105, sampleDesc, 0.11d));
        Assertions.assertTrue(HypothesisTestings.testForMean(">=", 105, sampleDesc, 0.13d));
        Assertions.assertTrue(HypothesisTestings.testForMean(">=", 105, sampleDesc, 0.14d));
        Assertions.assertTrue(HypothesisTestings.testForMean(">=", 105, sampleDesc, 0.16d));
        Assertions.assertTrue(HypothesisTestings.testForMean(">=", 105, sampleDesc, 0.20d));
        Assertions.assertTrue(HypothesisTestings.testForMean(">=", 105, sampleDesc, 0.866d));
        Assertions.assertFalse(HypothesisTestings.testForMean(">=", 105, sampleDesc, 0.867d));
        Assertions.assertFalse(HypothesisTestings.testForMean(">=", 105, sampleDesc, 0.9d));

        // p value = 0.917071669828545
        Assertions.assertTrue(HypothesisTestings.testForMean(">=", 105, sampleDesc, 10d, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForMean(">=", 105, sampleDesc, 10d, 0.10d));
        Assertions.assertTrue(HypothesisTestings.testForMean(">=", 105, sampleDesc, 10d, 0.16d));
        Assertions.assertTrue(HypothesisTestings.testForMean(">=", 105, sampleDesc, 10d, 0.20d));
        Assertions.assertTrue(HypothesisTestings.testForMean(">=", 105, sampleDesc, 10d, 0.91706d));
        Assertions.assertFalse(HypothesisTestings.testForMean(">=", 105, sampleDesc, 10d, 0.91708d));
        Assertions.assertFalse(HypothesisTestings.testForMean(">=", 105, sampleDesc, 10d, 0.92d));

        // VARIANCE TESTS
        Assertions.assertTrue(HypothesisTestings.testForVariance("=", 100, sampleDesc, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForVariance("=", 100, sampleDesc, 0.08d));
        Assertions.assertTrue(HypothesisTestings.testForVariance("=", 100, sampleDesc, 0.23d));

        Assertions.assertTrue(HypothesisTestings.testForVariance(">=", 100, sampleDesc, 0.12d));
        Assertions.assertTrue(HypothesisTestings.testForVariance("=", 100, sampleDesc, 0.12d));
        Assertions.assertTrue(HypothesisTestings.testForVariance("<=", 100, sampleDesc, 0.12d));

        Assertions.assertFalse(HypothesisTestings.testForVariance("=", 1000, sampleDesc, 0.05d));
        Assertions.assertFalse(HypothesisTestings.testForVariance("=", 1000, sampleDesc, 0.08d));
        Assertions.assertFalse(HypothesisTestings.testForVariance("=", 1000, sampleDesc, 0.12d));
        Assertions.assertFalse(HypothesisTestings.testForVariance("=", 1000, sampleDesc, 0.23d));


        // PROPORTION TESTS
        int suc10 = (int) Arrays.stream(sample).filter(value -> value >= 100).count(); // 10
        double p1 = (double) suc10 / (double) sample.length; // 10 / 12 = 0.8333

        Assertions.assertFalse(HypothesisTestings.testForProportion("=", 0.5d, sample.length, p1, 0.05d));
        Assertions.assertFalse(HypothesisTestings.testForProportion("=", 0.4d, sample.length, p1, 0.05d));
        Assertions.assertFalse(HypothesisTestings.testForProportion("=", 0.3d, sample.length, p1, 0.05d));

        Assertions.assertTrue(HypothesisTestings.testForProportion(">=", 0.5d, sample.length, p1, 0.05d));
        Assertions.assertFalse(HypothesisTestings.testForProportion("<=", 0.5d, sample.length, p1, 0.05d));

        Assertions.assertFalse(HypothesisTestings.testForProportion("<=", 0.1d, 300, 0.15d, 0.01d));
    }

}
