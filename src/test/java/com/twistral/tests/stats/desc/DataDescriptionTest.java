package com.twistral.tests.stats.desc;

import com.twistral.toriamath.utils.TMath;
import com.twistral.toriamath.advanced.random.TNoise;
import com.twistral.toriamath.advanced.statistics.descriptive.DataDescription;
import com.twistral.toriamath.advanced.statistics.descriptive.DescStats;
import com.twistral.toriautils.containers.TArrays;
import com.twistral.tests.stats.exampledata.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class DataDescriptionTest {



    @Test
    @DisplayName("dataDescGenericTest")
    void dataDescGenericTest() {
        // data
        Person[] population = Person.createPopulation();
        // get sorted data
        double[] sortedWithHeight = new double[population.length];
        double[] sortedWithAge = new double[population.length];
        for (int i = 0; i < population.length; i++) {
            sortedWithHeight[i] = population[i].height;
            sortedWithAge[i] = population[i].age;
        }
        Arrays.sort(sortedWithHeight);
        Arrays.sort(sortedWithAge);

        // get data sets and desc
        DataDescription heightDesc = DescStats.getDataDesc(sortedWithHeight);

        DataDescription ageDesc = DescStats.getDataDesc(sortedWithAge);

        // TESTS
        Assertions.assertTrue(TMath.areEqual(heightDesc.count, 26));
        Assertions.assertTrue(TMath.areEqual(heightDesc.min, 158));
        Assertions.assertTrue(TMath.areEqual(heightDesc.max, 203));
        Assertions.assertTrue(TMath.areEqual(heightDesc.range, 45));
        Assertions.assertTrue(TMath.areEqual(heightDesc.sum, 4571));
        Assertions.assertTrue(TMath.areEqual(heightDesc.mean, 175.80769230769));
        Assertions.assertTrue(TMath.areEqual(heightDesc.variance, 102.38609467456));
        Assertions.assertTrue(TMath.areEqual(heightDesc.stddev, 10.11860141889956));
        Assertions.assertTrue(TMath.areEqual(heightDesc.mode.value, 176));
        Assertions.assertTrue(TMath.areEqual(heightDesc.mode.count, 4));
        Assertions.assertTrue(TMath.areEqual(heightDesc.quartile1, 169.25));
        Assertions.assertTrue(TMath.areEqual(heightDesc.quartile2, 176));
        Assertions.assertTrue(TMath.areEqual(heightDesc.quartile3, 180.5));
        Assertions.assertTrue(TMath.areEqual(heightDesc.median, 176));
        Assertions.assertTrue(TMath.areEqual(heightDesc.interquartileRange, 11.25));
        Assertions.assertTrue(TMath.areEqual(heightDesc.bowleySkewCoef, -0.2));
        Assertions.assertTrue(TMath.areEqual(heightDesc.pearsonSkewCoef, -0.057));

        Assertions.assertTrue(TMath.areEqual(ageDesc.count, 26));
        Assertions.assertTrue(TMath.areEqual(ageDesc.min, 12));
        Assertions.assertTrue(TMath.areEqual(ageDesc.max, 75));
        Assertions.assertTrue(TMath.areEqual(ageDesc.range, 63));
        Assertions.assertTrue(TMath.areEqual(ageDesc.sum, 766));
        Assertions.assertTrue(TMath.areEqual(ageDesc.mean, 29.461538461538));
        Assertions.assertTrue(TMath.areEqual(ageDesc.variance, 345.01775147929));
        Assertions.assertTrue(TMath.areEqual(ageDesc.stddev, 18.574653468619));
        Assertions.assertTrue(TMath.areEqual(ageDesc.mode.value, 18));
        Assertions.assertTrue(TMath.areEqual(ageDesc.mode.count, 7));
        Assertions.assertTrue(TMath.areEqual(ageDesc.quartile1, 18));
        Assertions.assertTrue(TMath.areEqual(ageDesc.quartile2, 20));
        Assertions.assertTrue(TMath.areEqual(ageDesc.quartile3, 38));
        Assertions.assertTrue(TMath.areEqual(ageDesc.median, 20));
        Assertions.assertTrue(TMath.areEqual(ageDesc.interquartileRange, 20));
        Assertions.assertTrue(TMath.areEqual(ageDesc.bowleySkewCoef, 0.8));
        Assertions.assertTrue(TMath.areEqual(ageDesc.pearsonSkewCoef, 1.5281));
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    static class TestClass {
        double theField;
        int junk;
        String otherJunk;
        Person literalFuckingJunk;
        public TestClass(double value){
            this.theField = value;
            junk = 0;
            otherJunk = "test";
            literalFuckingJunk = new Person("joe momma", -1, 9999);
        }

        public double getField() {
            return theField;
        }

        @Override
        public String toString() {
            return String.valueOf(theField);
        }
    }





    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("dataDescPrimitiveTest")
    void dataDescPrimitiveTest() {
        double[] population = new double[]{
                160, 172, 162, 176, 180, 176, 182, 176, 176, 166, 158, 183, 165, 188,
                177, 178, 170, 180, 170, 180, 190, 173, 192, 167, 203, 171
        };
        Arrays.sort(population);
        DataDescription heightDesc = DescStats.getDataDesc(population);

        // TESTS
        Assertions.assertTrue(TMath.areEqual(heightDesc.count, 26));
        Assertions.assertTrue(TMath.areEqual(heightDesc.min, 158));
        Assertions.assertTrue(TMath.areEqual(heightDesc.max, 203));
        Assertions.assertTrue(TMath.areEqual(heightDesc.range, 45));
        Assertions.assertTrue(TMath.areEqual(heightDesc.sum, 4571));
        Assertions.assertTrue(TMath.areEqual(heightDesc.mean, 175.80769230769));
        Assertions.assertTrue(TMath.areEqual(heightDesc.variance, 102.38609467456));
        Assertions.assertTrue(TMath.areEqual(heightDesc.stddev, 10.11860141889956));
        Assertions.assertTrue(TMath.areEqual(heightDesc.mode.value, 176));
        Assertions.assertTrue(TMath.areEqual(heightDesc.mode.count, 4));
        Assertions.assertTrue(TMath.areEqual(heightDesc.quartile1, 169.25));
        Assertions.assertTrue(TMath.areEqual(heightDesc.quartile2, 176));
        Assertions.assertTrue(TMath.areEqual(heightDesc.quartile3, 180.5));
        Assertions.assertTrue(TMath.areEqual(heightDesc.median, 176));
        Assertions.assertTrue(TMath.areEqual(heightDesc.interquartileRange, 11.25));
        Assertions.assertTrue(TMath.areEqual(heightDesc.bowleySkewCoef, -0.2));
        Assertions.assertTrue(TMath.areEqual(heightDesc.pearsonSkewCoef, -0.057));
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Test
    @DisplayName("dataDescPrimitiveWithoutConversionTest")
    void dataDescPrimitiveWithoutConversionTest() {
        double[] arr1 = TArrays.getSortedCopy(TArrays.doubleArr(1, 2, 3, -2, -3, 5, 7, -9, 10, 100));

        // you need to sort it for some values
        Arrays.sort(arr1);

        double sum, count, mean;

        // TESTS
        count = DescStats.getCount(arr1);
        Assertions.assertTrue(TMath.areEqual(count, 10));
        Assertions.assertTrue(TMath.areEqual(DescStats.getMax(arr1), 100));
        Assertions.assertTrue(TMath.areEqual(DescStats.getMin(arr1), -9));
        sum = DescStats.getSum(arr1);
        Assertions.assertTrue(TMath.areEqual(sum, 114));
        mean = DescStats.getMean(sum, count);
        Assertions.assertTrue(TMath.areEqual(mean, 11.4));
        Assertions.assertTrue(TMath.areEqual(DescStats.getVariance(arr1, mean, false), 898.24));
        Assertions.assertTrue(TMath.areEqual(DescStats.getModeAndModeCount(arr1).value, Double.NaN));
        Assertions.assertTrue(TMath.areEqual(DescStats.getModeAndModeCount(arr1).count, Double.NaN));
        Assertions.assertTrue(TMath.areEqual(DescStats.getQuartile(arr1, 1), -2.25d));
        Assertions.assertTrue(TMath.areEqual(DescStats.getQuartile(arr1, 2), 2.5d));
        Assertions.assertTrue(TMath.areEqual(DescStats.getQuartile(arr1, 3), 7.75d));
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("randomTest1")
    void randomTest1() {
        // DATA
        final int SIZE = 10000;
        double[] population = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            population[i] = TNoise.canonicalRandom(i, i * 2);
        }
        double[] sorted = TArrays.getSortedCopy(population);
        DataDescription dataDesc = DescStats.getDataDesc(sorted);

        // TESTS
        Assertions.assertTrue(TMath.areEqual(dataDesc.mean, 0.5018593d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.median, 0.5d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.mode.value, 0.2929687d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.mode.count, 41d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.max, 0.9995d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.min, 0d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.range, 0.9995d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.count, 10000d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.sum, 5018.5938d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.variance, 0.0829d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.stddev, 0.2880d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.quartile1, 0.2578d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.quartile2, 0.5d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.quartile3, 0.75d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.bowleySkewCoef, 0.01587d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.pearsonSkewCoef, 0.0193d));
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Test
    @DisplayName("noiseTest1")
    void noiseTest1() {
        // DATA
        final int SIZE = 100_000;
        double[] arr = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = TNoise.valueNoise(i * i, (i + 2) * (i * i - 2) ) * 10000d;
        }
        double[] sorted = TArrays.getSortedCopy(arr);

        // data desc 1
        DataDescription dataDesc = DescStats.getDataDesc(sorted);

        // TEST
        Assertions.assertTrue(TMath.areEqual(dataDesc.count, 100000.0));
        Assertions.assertTrue(TMath.areEqual(dataDesc.mean, 5010.927765458822));
        Assertions.assertTrue(TMath.areEqual(dataDesc.sum, 5.010927765458822E8));
        Assertions.assertTrue(TMath.areEqual(dataDesc.interquartileRange, 4980.46875));
        Assertions.assertTrue(TMath.areEqual(dataDesc.mode.value, 1132.8125));
        Assertions.assertTrue(TMath.areEqual(dataDesc.mode.count, 309.0));
        Assertions.assertTrue(TMath.areEqual(dataDesc.variance, 8309992.238762693));
        Assertions.assertTrue(TMath.areEqual(dataDesc.median, 5019.53125));
        Assertions.assertTrue(TMath.areEqual(dataDesc.quartile1, 2519.53125));
        Assertions.assertTrue(TMath.areEqual(dataDesc.quartile2, 5019.53125));
        Assertions.assertTrue(TMath.areEqual(dataDesc.quartile3, 7500.0));
        Assertions.assertTrue(TMath.areEqual(dataDesc.min, 0.0));
        Assertions.assertTrue(TMath.areEqual(dataDesc.max, 9997.55859375));
        Assertions.assertTrue(TMath.areEqual(dataDesc.range, 9997.55859375));
        Assertions.assertTrue(TMath.areEqual(dataDesc.stddev, 2882.7057149079046));
        Assertions.assertTrue(TMath.areEqual(dataDesc.pearsonSkewCoef, -0.008953551342426166));
        Assertions.assertTrue(TMath.areEqual(dataDesc.bowleySkewCoef, -0.00392156862745098));


    }


}
