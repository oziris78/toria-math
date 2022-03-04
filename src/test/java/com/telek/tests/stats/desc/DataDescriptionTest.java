package com.telek.tests.stats.desc;

import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.advanced.random.TNoise;
import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekutils.containers.TArrays;
import com.telek.telekutils.plain.TClassUtils;
import com.telek.tests.stats.exampledata.Person;
import com.telek.tests.stats.exampledata.SampleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;


public class DataDescriptionTest {



    @Test
    @DisplayName("dataDescGenericTest")
    void dataDescGenericTest() {
        // data
        Person[] population = Person.createPopulation();
        // get sorted data
        Person[] sortedWithHeight = TArrays.getSortedCopy(population, Person.class, (o1, o2) -> o1.height - o2.height);
        Person[] sortedWithAge = TArrays.getSortedCopy(population, Person.class, (o1, o2) -> o1.age - o2.age);

        // get data sets and desc
        Field heightField = TClassUtils.getField(Person.class, "height");
        heightField.setAccessible(true); // bad in terms of security, be careful when you do this
        DataDescription heightDesc = DescStats.getDataDesc(sortedWithHeight, heightField);

        Field ageField = TClassUtils.getField(Person.class, "age");
        ageField.setAccessible(true); // bad in terms of security, be careful when you do this
        DataDescription ageDesc = DescStats.getDataDesc(sortedWithAge, ageField);

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


    @Test
    @DisplayName("randomTest2")
    void randomTest2() {
        // DATA
        double[] doublePopulation = SampleData.sampleData();
        double[] sorted = TArrays.getSortedCopy(doublePopulation);
        Double[] param = TArrays.getAsClassArray(sorted);

        // data desc 1
        DataDescription dataDesc = DescStats.getDataDesc(param);

        // data desc 2
        int index = 0;
        TestClass[] classPopulation = new TestClass[doublePopulation.length];
        for(double i : doublePopulation){
            classPopulation[index++] = new TestClass(i);
        }
        TestClass[] sortedPop = TArrays.getSortedCopy(classPopulation, TestClass.class,
                Comparator.comparingDouble(TestClass::getField));

        Field theField = TClassUtils.getField(TestClass.class, "theField");
        theField.setAccessible(true); // bad in terms of security, be careful when you do this
        DataDescription dataDesc2 = DescStats.getDataDesc(sortedPop, theField);


        // tests
        Assertions.assertTrue(TMath.areEqual(dataDesc2.mean, dataDesc.mean));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.mode.value, dataDesc.mode.value));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.mode.count, dataDesc.mode.count));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.max, dataDesc.max));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.min, dataDesc.min));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.range, dataDesc.range));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.median, dataDesc.median));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.count, dataDesc.count));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.sum, dataDesc.sum));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.variance, dataDesc.variance));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.stddev, dataDesc.stddev));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.quartile1, dataDesc.quartile1));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.quartile2, dataDesc.quartile2));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.quartile3, dataDesc.quartile3));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.bowleySkewCoef, dataDesc.bowleySkewCoef));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.pearsonSkewCoef, dataDesc.pearsonSkewCoef));
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("dataDescPrimitiveTest")
    void dataDescPrimitiveTest() {
        for (int type = 0; type < 3; type++) {
            // GET DATA DESCRIPTIONS
            DataDescription heightDesc = null;
            if(type == 0){
                int[] population = new int[]{
                        160, 172, 162, 176, 180, 176, 182, 176, 176, 166, 158, 183, 165, 188,
                        177, 178, 170, 180, 170, 180, 190, 173, 192, 167, 203, 171
                };
                int[] sorted = TArrays.getSortedCopy(population);
                Integer[] param = TArrays.getAsClassArray(sorted);
                heightDesc = DescStats.getDataDesc(param);
            }
            if(type == 1){
                double[] population = new double[]{
                        160, 172, 162, 176, 180, 176, 182, 176, 176, 166, 158, 183, 165, 188,
                        177, 178, 170, 180, 170, 180, 190, 173, 192, 167, 203, 171
                };
                double[] sorted = TArrays.getSortedCopy(population);
                Double[] param = TArrays.getAsClassArray(sorted);
                heightDesc = DescStats.getDataDesc(param);
            }
            if(type == 2){
                long[] population = new long[]{
                        160, 172, 162, 176, 180, 176, 182, 176, 176, 166, 158, 183, 165, 188,
                        177, 178, 170, 180, 170, 180, 190, 173, 192, 167, 203, 171
                };
                long[] sorted = TArrays.getSortedCopy(population);
                Long[] param = TArrays.getAsClassArray(sorted);
                heightDesc = DescStats.getDataDesc(param);
            }

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

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Test
    @DisplayName("dataDescPrimitiveWithoutConversionTest")
    void dataDescPrimitiveWithoutConversionTest() {
        double[] arr1 = TArrays.getSortedCopy(TArrays.doubleArr(1, 2, 3, -2, -3, 5, 7, -9, 10, 100));
        int[] arr2 = TArrays.getSortedCopy(TArrays.intArr(1, 2, 3, -2, -3, 5, 7, -9, 10, 100));
        float[] arr3 = TArrays.getSortedCopy(TArrays.floatArr(1, 2, 3, -2, -3, 5, 7, -9, 10, 100));

        // you need to sort it for some values
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        Arrays.sort(arr3);

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

        count = DescStats.getCount(arr2);
        Assertions.assertTrue(TMath.areEqual(count, 10));
        Assertions.assertTrue(TMath.areEqual(DescStats.getMax(arr2), 100));
        Assertions.assertTrue(TMath.areEqual(DescStats.getMin(arr2), -9));
        sum = DescStats.getSum(arr2);
        Assertions.assertTrue(TMath.areEqual(sum, 114));
        mean = DescStats.getMean(sum, count);
        Assertions.assertTrue(TMath.areEqual(mean, 11.4));
        Assertions.assertTrue(TMath.areEqual(DescStats.getVariance(arr2, mean, false), 898.24));
        Assertions.assertTrue(TMath.areEqual(DescStats.getModeAndModeCount(arr2).value, Double.NaN));
        Assertions.assertTrue(TMath.areEqual(DescStats.getModeAndModeCount(arr2).count, Double.NaN));
        Assertions.assertTrue(TMath.areEqual(DescStats.getQuartile(arr2, 1), -2.25d));
        Assertions.assertTrue(TMath.areEqual(DescStats.getQuartile(arr2, 2), 2.5d));
        Assertions.assertTrue(TMath.areEqual(DescStats.getQuartile(arr2, 3), 7.75d));

        count = DescStats.getCount(arr3);
        Assertions.assertTrue(TMath.areEqual(count, 10));
        Assertions.assertTrue(TMath.areEqual(DescStats.getMax(arr3), 100));
        Assertions.assertTrue(TMath.areEqual(DescStats.getMin(arr3), -9));
        sum = DescStats.getSum(arr3);
        Assertions.assertTrue(TMath.areEqual(sum, 114));
        mean = DescStats.getMean(sum, count);
        Assertions.assertTrue(TMath.areEqual(mean, 11.4));
        Assertions.assertTrue(TMath.areEqual(DescStats.getVariance(arr3, mean, false), 898.24));
        Assertions.assertTrue(TMath.areEqual(DescStats.getModeAndModeCount(arr3).value, Double.NaN));
        Assertions.assertTrue(TMath.areEqual(DescStats.getModeAndModeCount(arr3).count, Double.NaN));
        Assertions.assertTrue(TMath.areEqual(DescStats.getQuartile(arr3, 1), -2.25d));
        Assertions.assertTrue(TMath.areEqual(DescStats.getQuartile(arr3, 2), 2.5d));
        Assertions.assertTrue(TMath.areEqual(DescStats.getQuartile(arr3, 3), 7.75d));
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
        Double[] param = TArrays.getAsClassArray(sorted);
        DataDescription dataDesc = DescStats.getDataDesc(param);

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
        Double[] param = TArrays.getAsClassArray(sorted);

        // data desc 1
        DataDescription dataDesc = DescStats.getDataDesc(param);

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
