package com.telek.tests.stats.confidenceint;

import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekmath.advanced.statistics.inferential.ConfidenceIntervals;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekutils.containers.TArrays;
import com.telek.telekutils.plain.TClassUtils;
import com.telek.tests.stats.exampledata.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ConfIntervalSingleVariableTest {


    @Test
    @DisplayName("meanSingleVariableTest")
    void meanSingleVariableTest() {
        // get necessary descriptions from your data
        int[] sample = TArrays.intArr(103, 96, 112, 130, 111, 125, 89, 100, 104, 117, 99, 118); // IQ scores
        int[] sortedSample = TArrays.getSortedCopy(sample);
        DataDescription sampleDesc = DescStats.getDataDesc(sortedSample);

        // TESTS
        /*
            i used this site to check my answers since apache doesn't have confidence intervals:
            https://www.statskingdom.com/confidence-interval-calculator.html
        */
        TRange r1, r2;

        // TESTS FOR ANY/NORMAL DIST & KNOWN VARIANCE
        r1 = ConfidenceIntervals.getIntervalForMean(sampleDesc, 5d, 0.05d);
        r2 = new TRange(105.8377023d, 111.495631d);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForMean(sampleDesc, 60d, 0.05d);
        r2 = new TRange(74.71909464, 142.6142387);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForMean(sampleDesc, 12d, 0.01d);
        r2 = new TRange(99.74373222, 117.5896011);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForMean(sampleDesc, 2d, 0.44d);
        r2 = new TRange(108.2208407, 109.1124926);
        Assertions.assertTrue(r1.equals(r2));

        // TESTS FOR ANY DIST & UNKNOWN VARIANCE
        r1 = ConfidenceIntervals.getIntervalForMean(sampleDesc, false, 0.05);
        r2 = new TRange(101.706417807, 115.626915513);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForMean(sampleDesc, false, 0.12);
        r2 = new TRange(103.145335041, 114.187998279);
        Assertions.assertTrue(r1.equals(r2));

        // TESTS FOR NORMAL DIST & UNKNOWN VARIANCE
        r1 = ConfidenceIntervals.getIntervalForMean(sampleDesc, true, 0.05);
        r2 = new TRange(100.8505004, 116.4828329);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForMean(sampleDesc, true, 0.12);
        r2 = new TRange(102.6808047 , 114.6525286);
        Assertions.assertTrue(r1.equals(r2));
    }


    @Test
    @DisplayName("varianceSingleVariableTest")
    void varianceSingleVariableTest() {
        // get necessary descriptions from your data
        int[] sample = TArrays.intArr(103, 96, 112, 130, 111, 125, 89, 100, 104, 117, 99, 118); // IQ scores
        int[] sortedSample = TArrays.getSortedCopy(sample);
        DataDescription sampleDesc = DescStats.getDataDesc(sortedSample);

        // TESTS
        TRange r1, r2;

        r1 = ConfidenceIntervals.getIntervalForVariance(sampleDesc, 0.05d);
        r2 = new TRange(75.94265172, 436.2621841);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForVariance(sampleDesc, 0.12d);
        r2 = new TRange(87.33175696, 346.0562431);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForVariance(sampleDesc, 0.36d);
        r2 = new TRange(110.5735094, 247.1100025);
        Assertions.assertTrue(r1.equals(r2));

        // STD DEV
        // do whatever you want with them
        TRange stdRange1 = ConfidenceIntervals.getIntervalForVariance(sampleDesc, 0.05d).getSqrt();
        TRange stdRange2 = ConfidenceIntervals.getIntervalForVariance(sampleDesc, 0.12d).getSqrt();
        TRange stdRange3 = ConfidenceIntervals.getIntervalForVariance(sampleDesc, 0.36d).getSqrt();

    }



    @Test
    @DisplayName("proportionsSingleVariableTest")
    void proportionsSingleVariableTest() {
        // get necessary descriptions from your data
        int[] sample = TArrays.intArr(103, 96, 112, 130, 111, 125, 89, 100, 104, 117, 99, 118); // IQ scores
        int[] sortedSample = TArrays.getSortedCopy(sample);
        DataDescription sampleDesc = DescStats.getDataDesc(sortedSample);

        int successes; // which is equal to "p hat"
        // you can determine this variable however you want
        // for my example I will count "success" as "having an IQ score higher than 100"
        successes = (int) Arrays.stream(sample).filter(value -> value > 100).count(); // 8

        double pHat = (double) successes / (double) sample.length;

        // TESTS
        TRange r1, r2;

        r1 = ConfidenceIntervals.getIntervalForProportion(sampleDesc, successes, 0.05d);
        r2 = new TRange(0.3999493513, 0.9333839820);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForProportion(sampleDesc, successes, 0.02d);
        r2 = new TRange(0.3500908191, 0.9832425142);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForProportion(sampleDesc, successes, 0.1d);
        r2 = new TRange(0.4428304396, 0.8905028938);
        Assertions.assertTrue(r1.equals(r2));

        // SAME TESTS FOR P
        r1 = ConfidenceIntervals.getIntervalForProportion(sampleDesc, pHat, 0.05d);
        r2 = new TRange(0.3999493513, 0.9333839820);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForProportion(sampleDesc, pHat, 0.02d);
        r2 = new TRange(0.3500908191, 0.9832425142);
        Assertions.assertTrue(r1.equals(r2));

        r1 = ConfidenceIntervals.getIntervalForProportion(sampleDesc, pHat, 0.1d);
        r2 = new TRange(0.4428304396, 0.8905028938);
        Assertions.assertTrue(r1.equals(r2));
    }


    @Test
    @DisplayName("forPersonDataTest")
    void forPersonDataTest() {
        Person[] people = Person.createPopulation();
        Person[] sortedPeople = TArrays.getSortedCopy(people, Person.class, (o1, o2) -> o1.height - o2.height);

        Field heightField = TClassUtils.getField(Person.class, "height");
        heightField.setAccessible(true);
        DataDescription description = DescStats.getDataDesc(sortedPeople, heightField);

        double alpha = 0.01d;

        // uncomment sysout lines to see results

        // mean confidence interval
        TRange meanRange = ConfidenceIntervals.getIntervalForMean(description, false, alpha);
//        System.out.println("CI for people population's height's mean: " + meanRange + " for alpha: " + alpha);

        // proportions confidence interval
        int successes = (int) Arrays.stream(people).filter(person -> person.height > 180).count();
        TRange proportionRange = ConfidenceIntervals.getIntervalForProportion(description, successes, alpha);
//        System.out.println("CI for people population's height's proportions: " + proportionRange + " for alpha: " + alpha);

        // variance confidence interval
        TRange varianceRange = ConfidenceIntervals.getIntervalForVariance(description, alpha);
//        System.out.println("CI for people population's height's variance: " + varianceRange + " for alpha: " + alpha);
    }


}
