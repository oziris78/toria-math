package com.telek.tests.stats.confidenceint;

import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekmath.advanced.statistics.inferential.ConfidenceIntervals;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekutils.containers.TArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;


public class ConfIntervalTwoVariableTest {

    @Test
    @DisplayName("meanKnownVarianceTest")
    void meanKnownVarianceTest() {
        // https://mathcracker.com/confidence-interval-difference-means-calculator
        // https://www.socscistatistics.com/confidenceinterval/default4.aspx

        // get data
        int[] sample1 = TArrays.intArr(10, 50, 90, 24, 30, 70, 70, 50, 60, 60, 60, 50);
        int[] sample2 = TArrays.intArr(15, 35, 95, 97, 0, 0, 30, 40);
        Arrays.sort(sample1);
        Arrays.sort(sample2);
        DataDescription desc1 = DescStats.getDataDesc(sample1);
        DataDescription desc2 = DescStats.getDataDesc(sample2);


        // TESTS
        TRange r1, r2;



        // known variance (dist doesn't matter)
        r1 = ConfidenceIntervals.getIntervalForDiffOfMeansKnown(desc1, desc2, 2d, 4d, 0.05d);
        r2 = new TRange(10.006106, 15.993894);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForDiffOfMeansKnown(desc1, desc2, 20, 4, 0.05d);
        r2 = new TRange(1.349612, 24.650388);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForDiffOfMeansKnown(desc1, desc2, 1, 10, 0.05d);
        r2 = new TRange(6.047421, 19.952579);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForDiffOfMeansKnown(desc1, desc2, 2, 4, 0.01d);
        r2 = new TRange(9.065356, 16.934644);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForDiffOfMeansKnown(desc1, desc2, 20, 4, 0.01d);
        r2 = new TRange(-2.311205, 28.311205);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForDiffOfMeansKnown(desc1, desc2, 1, 10, 0.01d);
        r2 = new TRange(3.862762, 22.137238);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForDiffOfMeansKnown(desc1, desc2, 2, 4, 0.12d);
        r2 = new TRange(10.625044, 15.374956);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForDiffOfMeansKnown(desc1, desc2, 20, 4, 0.12d);
        r2 = new TRange(3.758138, 22.241862);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForDiffOfMeansKnown(desc1, desc2, 1, 10, 0.12d);
        r2 = new TRange(7.484752, 18.515248);
        Assertions.assertTrue(r1.equals(r2));

    }



    @Test
    @DisplayName("meanUnknownVarianceTest")
    void meanUnknownVarianceTest() {
        // get data
        int[] sample1 = TArrays.intArr(10, 50, 90, 24, 30, 70, 70, 50, 60, 60, 60, 50);
        int[] sample2 = TArrays.intArr(15, 35, 95, 97, 0, 0, 30, 40);
        Arrays.sort(sample1);
        Arrays.sort(sample2);
        DataDescription desc1 = DescStats.getDataDesc(sample1);
        DataDescription desc2 = DescStats.getDataDesc(sample2);


        TRange r3, r4, r5, r6, r7, r8;
        // TESTS

        // unknown variance & normal dist

        // assume equal stddevs
        r3 = ConfidenceIntervals.getIntervalForDiffOfMeansUnknownNormal(desc1, desc2, true, 0.05d);
        r4 = ConfidenceIntervals.getIntervalForDiffOfMeansUnknownNormal(desc1, desc2, true, 0.01d);
        r5 = ConfidenceIntervals.getIntervalForDiffOfMeansUnknownNormal(desc1, desc2, true, 0.23d);
        r6 = new TRange(-15.179576, 41.179576);
        r7 = new TRange(-25.608397, 51.608397);
        r8 = new TRange(-3.665457, 29.665457);
        Assertions.assertTrue(r3.equals(r6));
        Assertions.assertTrue(r4.equals(r7));
        Assertions.assertTrue(r5.equals(r8));


        // assume unequal stddevs
        r3 = ConfidenceIntervals.getIntervalForDiffOfMeansUnknownNormal(desc1, desc2, false, 0.05d);
        r4 = ConfidenceIntervals.getIntervalForDiffOfMeansUnknownNormal(desc1, desc2, false, 0.01d);
        r5 = ConfidenceIntervals.getIntervalForDiffOfMeansUnknownNormal(desc1, desc2, false, 0.23d);
        r6 = new TRange(-20.202192, 46.202192);
        r7 = new TRange(-34.174873, 60.174873);
        r8 = new TRange(-6.065638, 32.065638);
        Assertions.assertTrue(r3.equals(r6));
        Assertions.assertTrue(r4.equals(r7));
        Assertions.assertTrue(r5.equals(r8));


        /*              unknown variance & any dist

        Produces approximate results so it can't be tested

        SAMPLE CODE:

        r3 = ConfidenceIntervals.getIntervalForDiffOfMeansUnknownAny(desc1, desc2, 0.05d);
        r4 = ConfidenceIntervals.getIntervalForDiffOfMeansKnown(desc1, desc2, desc1.sampleStddev - 3d,
                desc2.sampleStddev + 2d, 0.05d);
        System.out.println(r3); // [-16.254864, 42.254864]
        System.out.println(r4); // [-16.862244, 42.862244]

        */

    }



    @Test
    @DisplayName("proportionsTwoVariableTest")
    void proportionsTwoVariableTest() {
        // get data and it's descriptions
        int[] sample1 = TArrays.intArr(10, 50, 90, 20, 30, 70, 70, 50, 60, 60, 60, 50); // 12 terms
        int[] sample2 = TArrays.intArr(15, 35, 95, 100, 0, 0, 30, 40); // 8 term
        Arrays.sort(sample1);
        Arrays.sort(sample2);
        DataDescription desc1 = DescStats.getDataDesc(sample1);
        DataDescription desc2 = DescStats.getDataDesc(sample2);

        int successes1, successes2;
        successes1 = (int) Arrays.stream(sample1).filter(value -> value > 50).count(); // 6
        successes2 = (int) Arrays.stream(sample2).filter(value -> value > 50).count(); // 2

        // TESTS
        // used this site: https://www.statology.org/confidence-interval-difference-in-proportions-calculator/
        TRange r1, r2;

        r1 = ConfidenceIntervals.getIntervalForDifferenceOfProportions(desc1, desc2, successes1, successes2, 0.05d);
        r2 = new TRange(-0.1624d, 0.6624d);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForDifferenceOfProportions(desc1, desc2, successes1, successes2, 0.25d);
        r2 = new TRange(0.0080, 0.4920);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForDifferenceOfProportions(desc1, desc2, successes1, successes2, 0.12d);
        r2 = new TRange(-0.0771, 0.5771);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForDifferenceOfProportions(desc1, desc2, successes1, successes2, 0.001);
        r2 = new TRange(-0.4423, 0.9423);
        Assertions.assertTrue(r1.equals(r2));
    }



}
