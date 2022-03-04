package com.telek.tests.stats.hypo;

import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekmath.advanced.statistics.inferential.HypothesisTestings;
import com.telek.telekutils.containers.TArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;


public class HypothesisTwoVarTest {



    @Test
    @DisplayName("differenceOfProportionsTest")
    void differenceOfProportionsTest() {
        // data
        int[] sample1 = TArrays.intArr(103, 96, 112, 130, 111, 125, 89, 100, 104, 117, 105, 116); // IQ scores
        int[] sortedSample1 = TArrays.getSortedCopy(sample1);
        DataDescription sampleDesc1 = DescStats.getDataDesc(sortedSample1);

        double[] sample2 = TArrays.doubleArr(150d, 130d, 95d, 86d, 160d, 157d, 80d, 130d, 130d, 152d); // IQ scores
        double[] sortedSample2 = TArrays.getSortedCopy(sample2);
        DataDescription sampleDesc2 = DescStats.getDataDesc(sortedSample2);

        /*
        n1 = 12
        mean1 = 109
        sampleStddev1 = 11.870513506545995

        n2 = 10
        mean2 = 127
        sampleStddev2 = 29.970355723985364
        */

        // TESTS
        double n1 = sample1.length;
        double n2 = sample2.length;
        double p1 = (double) Arrays.stream(sample1).filter(value -> value > 100).count() / sample1.length; // 0.75
        double p2 = (double) Arrays.stream(sample2).filter(value -> value > 100).count() / sample2.length; // 0.7

        // p value = 0.7932
        Assertions.assertTrue(HypothesisTestings.testForDifferenceOfProportions("=", 0d, n1, n2, p1, p2, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForDifferenceOfProportions("=", 0d, n1, n2, p1, p2, 0.28d));
        Assertions.assertTrue(HypothesisTestings.testForDifferenceOfProportions("=", 0d, n1, n2, p1, p2, 0.50d));
        Assertions.assertTrue(HypothesisTestings.testForDifferenceOfProportions("=", 0d, n1, n2, p1, p2, 0.7931d));
        Assertions.assertFalse(HypothesisTestings.testForDifferenceOfProportions("=", 0d, n1, n2, p1, p2, 0.7933));
        Assertions.assertFalse(HypothesisTestings.testForDifferenceOfProportions("=", 0d, n1, n2, p1, p2, 0.8));

        // p value = 0.6034
        Assertions.assertTrue(HypothesisTestings.testForDifferenceOfProportions(">=", 0d, n1, n2, p1, p2, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForDifferenceOfProportions(">=", 0d, n1, n2, p1, p2, 0.28d));
        Assertions.assertTrue(HypothesisTestings.testForDifferenceOfProportions(">=", 0d, n1, n2, p1, p2, 0.50d));
        Assertions.assertTrue(HypothesisTestings.testForDifferenceOfProportions(">=", 0d, n1, n2, p1, p2, 0.6033d));
        Assertions.assertFalse(HypothesisTestings.testForDifferenceOfProportions(">=", 0d, n1, n2, p1, p2, 0.6035d));
        Assertions.assertFalse(HypothesisTestings.testForDifferenceOfProportions(">=", 0d, n1, n2, p1, p2, 0.8));

        // p value = 0.3966
        Assertions.assertTrue(HypothesisTestings.testForDifferenceOfProportions("<=", 0d, n1, n2, p1, p2, 0.05615d));
        Assertions.assertTrue(HypothesisTestings.testForDifferenceOfProportions("<=", 0d, n1, n2, p1, p2, 0.1020d));
        Assertions.assertTrue(HypothesisTestings.testForDifferenceOfProportions("<=", 0d, n1, n2, p1, p2, 0.20d));
        Assertions.assertTrue(HypothesisTestings.testForDifferenceOfProportions("<=", 0d, n1, n2, p1, p2, 0.3965d));
        Assertions.assertFalse(HypothesisTestings.testForDifferenceOfProportions("<=", 0d, n1, n2, p1, p2, 0.3967d));
        Assertions.assertFalse(HypothesisTestings.testForDifferenceOfProportions("<=", 0d, n1, n2, p1, p2, 0.58d));
    }



    @Test
    @DisplayName("differenceOfMeansTest")
    void differenceOfMeansTest() {
        // data
        int[] sample1 = TArrays.intArr(103, 96, 112, 130, 111, 125, 89, 100, 104, 117, 105, 116); // IQ scores
        int[] sortedSample1 = TArrays.getSortedCopy(sample1);
        DataDescription sampleDesc1 = DescStats.getDataDesc(sortedSample1);

        double[] sample2 = TArrays.doubleArr(150d, 130d, 95d, 86d, 160d, 157d, 80d, 130d, 130d, 152d); // IQ scores
        double[] sortedSample2 = TArrays.getSortedCopy(sample2);
        DataDescription sampleDesc2 = DescStats.getDataDesc(sortedSample2);

        /*
        n1 = 12
        mean1 = 109
        sampleStddev1 = 11.870513506545995

        n2 = 10
        mean2 = 127
        sampleStddev2 = 29.970355723985364
        */

        // TESTS

        // known var

        // p value => 0.2403
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansKnown("=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansKnown("=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.10d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansKnown("=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.15d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansKnown("=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.2402d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansKnown("=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.2405d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansKnown("=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.25d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansKnown("=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.30d));

        // p value => 0.1202
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansKnown(">=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansKnown(">=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.10d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansKnown(">=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.1201d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansKnown(">=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.1203d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansKnown(">=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.13d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansKnown(">=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.15d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansKnown(">=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.30d));

        // p value => 0.8798
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansKnown("<=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansKnown("<=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.35d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansKnown("<=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.55d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansKnown("<=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.85d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansKnown("<=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.8797d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansKnown("<=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.8799d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansKnown("<=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.88d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansKnown("<=", 0d, sampleDesc1, sampleDesc2, 30d, 40d, 0.90d));



        // unknown var & normal dist

        // assume equal variances
        // p value => 0.0698
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("=", 0d, sampleDesc1, sampleDesc2, true, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("=", 0d, sampleDesc1, sampleDesc2, true, 0.06d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("=", 0d, sampleDesc1, sampleDesc2, true, 0.0697d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal("=", 0d, sampleDesc1, sampleDesc2, true, 0.0699d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal("=", 0d, sampleDesc1, sampleDesc2, true, 0.07d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal("=", 0d, sampleDesc1, sampleDesc2, true, 0.10d));

        // p value => 0.0349
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, true, 0.01d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, true, 0.02d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, true, 0.034d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, true, 0.0348d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, true, 0.035d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, true, 0.036d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, true, 0.04d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, true, 0.4d));

        // p value => 0.9651
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, true, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, true, 0.10d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, true, 0.50d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, true, 0.95d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, true, 0.96d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, true, 0.965d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, true, 0.9652d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, true, 0.97d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, true, 0.99d));



        // assume unequal variances
        // p value => 0.1008
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("=", 0d, sampleDesc1, sampleDesc2, false, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("=", 0d, sampleDesc1, sampleDesc2, false, 0.10d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("=", 0d, sampleDesc1, sampleDesc2, false, 0.1007d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal("=", 0d, sampleDesc1, sampleDesc2, false, 0.1009d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal("=", 0d, sampleDesc1, sampleDesc2, false, 0.15d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal("=", 0d, sampleDesc1, sampleDesc2, false, 0.20d));

        // p value => 0.0504
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, false, 0.01d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, false, 0.02d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, false, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, false, 0.0503d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, false, 0.0505d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, false, 0.06d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, false, 0.10d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal(">=", 0d, sampleDesc1, sampleDesc2, false, 0.20d));

        // p value => 0.9496
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, false, 0.05d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, false, 0.50d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, false, 0.75d));
        Assertions.assertTrue(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, false, 0.9495d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, false, 0.9497d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, false, 0.95d));
        Assertions.assertFalse(HypothesisTestings.testForDiffOfMeansUnknownNormal("<=", 0d, sampleDesc1, sampleDesc2, false, 0.98d));


        // unknown var & any dist
        // i tested this manually (with sysout lines lol), there's nothing to test anyways since this is an approximation
        // for close stddev values you get approximate results

        /*
        unknown any result:
        z: 1.959963986120195
        testStat: -1.7860813042870902

        known result with 12, 30:
        z: 1.959963986120195
        testStat: -1.7822655773580138
         */

//        HypothesisTestings.testForDiffOfMeansUnknownAny("=", 0d, sampleDesc1, sampleDesc2, 0.05d);
//        HypothesisTestings.testForDiffOfMeansKnown("=", 0d, sampleDesc1, sampleDesc2, 12d, 30d, 0.05d);



    }

}
