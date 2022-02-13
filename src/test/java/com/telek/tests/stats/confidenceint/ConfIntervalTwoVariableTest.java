package com.telek.tests.stats.confidenceint;

import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekmath.advanced.statistics.inferential.ConfidenceIntervals;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekutils.plain.TArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;


public class ConfIntervalTwoVariableTest {


    @Test
    @DisplayName("meanTwoVariableTest")
    void meanTwoVariableTest() {

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
        System.out.println(successes1);
        System.out.println(successes2);

        // TESTS
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
