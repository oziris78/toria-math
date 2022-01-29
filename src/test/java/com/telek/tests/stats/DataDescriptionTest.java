package com.telek.tests.stats;

import com.telek.telekmath.TMath;
import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.advanced.statistics.descriptive.DataSet;
import com.telek.telekmath.advanced.statistics.descriptive.FreqDistTable;
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

}
