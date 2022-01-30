package com.telek.tests.stats;

import com.telek.telekmath.TMath;
import com.telek.telekmath.advanced.random.TNoise;
import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.advanced.statistics.descriptive.DataSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DataDescriptionTest {


    @Test
    @DisplayName("dataDescGenericTest")
    void dataDescGenericTest() {
        // data
        Person[] population = Person.createPopulation();
        DataSet<Person> dataSet = new DataSet<>(population, Person.class);

        // get data descriptions after doing a sort
        dataSet.sort((o1, o2) -> o1.height - o2.height);
        DataDescription heightDesc = dataSet.getDataDescription("height");
        dataSet.sort((o1, o2) -> o1.age - o2.age);
        DataDescription ageDesc =  dataSet.getDataDescription("age");

        // TESTS
        Assertions.assertTrue(TMath.areEqual(heightDesc.count, 26));
        Assertions.assertTrue(TMath.areEqual(heightDesc.min, 158));
        Assertions.assertTrue(TMath.areEqual(heightDesc.max, 203));
        Assertions.assertTrue(TMath.areEqual(heightDesc.range, 45));
        Assertions.assertTrue(TMath.areEqual(heightDesc.sum, 4571));
        Assertions.assertTrue(TMath.areEqual(heightDesc.mean, 175.80769230769));
        Assertions.assertTrue(TMath.areEqual(heightDesc.geomean, 175.52108614845));
        Assertions.assertTrue(TMath.areEqual(heightDesc.variance, 102.38609467456));
        Assertions.assertTrue(TMath.areEqual(heightDesc.stddev, 10.11860141889956));
        Assertions.assertTrue(TMath.areEqual(heightDesc.mode, 176));
        Assertions.assertTrue(TMath.areEqual(heightDesc.modeCount, 4));
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
        Assertions.assertTrue(TMath.areEqual(ageDesc.geomean, 25.012805395149));
        Assertions.assertTrue(TMath.areEqual(ageDesc.variance, 345.01775147929));
        Assertions.assertTrue(TMath.areEqual(ageDesc.stddev, 18.574653468619));
        Assertions.assertTrue(TMath.areEqual(ageDesc.mode, 18));
        Assertions.assertTrue(TMath.areEqual(ageDesc.modeCount, 7));
        Assertions.assertTrue(TMath.areEqual(ageDesc.quartile1, 18));
        Assertions.assertTrue(TMath.areEqual(ageDesc.quartile2, 20));
        Assertions.assertTrue(TMath.areEqual(ageDesc.quartile3, 38));
        Assertions.assertTrue(TMath.areEqual(ageDesc.median, 20));
        Assertions.assertTrue(TMath.areEqual(ageDesc.interquartileRange, 20));
        Assertions.assertTrue(TMath.areEqual(ageDesc.bowleySkewCoef, 0.8));
        Assertions.assertTrue(TMath.areEqual(ageDesc.pearsonSkewCoef, 1.5281));

    }


    @Test
    @DisplayName("dataDescPrimitiveTest")
    void dataDescPrimitiveTest() {

        for (int type = 0; type < 4; type++) {
            DataDescription heightDesc = null;
            if(type == 0){
                int[] population = new int[]{
                        160, 172, 162, 176, 180, 176, 182, 176, 176, 166, 158, 183, 165, 188,
                        177, 178, 170, 180, 170, 180, 190, 173, 192, 167, 203, 171
                };
                heightDesc = DataSet.getDataDescription(population);
            }
            if(type == 1){
                double[] population = new double[]{
                        160, 172, 162, 176, 180, 176, 182, 176, 176, 166, 158, 183, 165, 188,
                        177, 178, 170, 180, 170, 180, 190, 173, 192, 167, 203, 171
                };
                heightDesc = DataSet.getDataDescription(population);
            }
            if(type == 2){
                float[] population = new float[]{
                        160, 172, 162, 176, 180, 176, 182, 176, 176, 166, 158, 183, 165, 188,
                        177, 178, 170, 180, 170, 180, 190, 173, 192, 167, 203, 171
                };
                heightDesc = DataSet.getDataDescription(population);
            }
            if(type == 3){
                long[] population = new long[]{
                        160, 172, 162, 176, 180, 176, 182, 176, 176, 166, 158, 183, 165, 188,
                        177, 178, 170, 180, 170, 180, 190, 173, 192, 167, 203, 171
                };
                heightDesc = DataSet.getDataDescription(population);
            }


            // TESTS
            Assertions.assertTrue(TMath.areEqual(heightDesc.count, 26));
            Assertions.assertTrue(TMath.areEqual(heightDesc.min, 158));
            Assertions.assertTrue(TMath.areEqual(heightDesc.max, 203));
            Assertions.assertTrue(TMath.areEqual(heightDesc.range, 45));
            Assertions.assertTrue(TMath.areEqual(heightDesc.sum, 4571));
            Assertions.assertTrue(TMath.areEqual(heightDesc.mean, 175.80769230769));
            Assertions.assertTrue(TMath.areEqual(heightDesc.geomean, 175.52108614845));
            Assertions.assertTrue(TMath.areEqual(heightDesc.variance, 102.38609467456));
            Assertions.assertTrue(TMath.areEqual(heightDesc.stddev, 10.11860141889956));
            Assertions.assertTrue(TMath.areEqual(heightDesc.mode, 176));
            Assertions.assertTrue(TMath.areEqual(heightDesc.modeCount, 4));
            Assertions.assertTrue(TMath.areEqual(heightDesc.quartile1, 169.25));
            Assertions.assertTrue(TMath.areEqual(heightDesc.quartile2, 176));
            Assertions.assertTrue(TMath.areEqual(heightDesc.quartile3, 180.5));
            Assertions.assertTrue(TMath.areEqual(heightDesc.median, 176));
            Assertions.assertTrue(TMath.areEqual(heightDesc.interquartileRange, 11.25));
            Assertions.assertTrue(TMath.areEqual(heightDesc.bowleySkewCoef, -0.2));
            Assertions.assertTrue(TMath.areEqual(heightDesc.pearsonSkewCoef, -0.057));

        }



    }


    @Test
    @DisplayName("test1")
    void test1() {
        final int SIZE = 10000;
        double[] population = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            population[i] = TNoise.canonicalRandom(i, i * 2);
        }


        DataDescription dataDesc = DataSet.getDataDescription(population);
        Assertions.assertTrue(TMath.areEqual(dataDesc.mean, 0.5018593d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.median, 0.5d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.mode, 0.2929687d));
        Assertions.assertTrue(TMath.areEqual(dataDesc.modeCount, 41d));
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


    static class MyClass{
        double theMostImportantFieldInTheWorld;
        int junk;
        String otherJunk;
        Person literalFuckingJunk;
        public MyClass(double value){
            this.theMostImportantFieldInTheWorld = value;
            junk = 0;
            otherJunk = "test";
            literalFuckingJunk = new Person("who cares", -1, 9999);
        }
    }

    @Test
    @DisplayName("test2")
    void test2() {

        final int SIZE = 10000;
        MyClass[] classPopulation = new MyClass[SIZE];
        double[] doublePopulation = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            doublePopulation[i] = TNoise.canonicalRandom(i, i * 2);
            classPopulation[i] = new MyClass(TNoise.canonicalRandom(i, i * 2));
        }


        // data desc 1
        DataDescription dataDesc = DataSet.getDataDescription(doublePopulation);

        // data desc 2
        DataSet<MyClass> dataSet = new DataSet<>(classPopulation, MyClass.class);
        dataSet.sort(((o1, o2) -> (int) (o1.theMostImportantFieldInTheWorld - o2.theMostImportantFieldInTheWorld)));
        DataDescription dataDesc2 = dataSet.getDataDescription("theMostImportantFieldInTheWorld");

        // tests
        Assertions.assertTrue(TMath.areEqual(dataDesc2.mean, dataDesc.mean));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.median, dataDesc.median));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.mode, dataDesc.mode));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.modeCount, dataDesc.modeCount));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.max, dataDesc.max));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.min, dataDesc.min));
        Assertions.assertTrue(TMath.areEqual(dataDesc2.range, dataDesc.range));
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


}
