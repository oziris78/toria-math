package com.telek.tests.stats;

import com.telek.telekmath.TMath;
import com.telek.telekmath.advanced.statistics.DataDescription;
import com.telek.telekmath.advanced.statistics.DataSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class SamplingTest {

    @Test
    @DisplayName("sampling")
    void sampling() {
        /*
        PRECALCULATED VALUES (age):
            Median	20
            Sample Standard Deviation	18.942504098943
            Sample Variance	358.81846153846


        PRECALCULATED VALUES (height):
                Median	176
                        + quartiles??

                Sample Standard Deviation	10.318989217047
                Sample Variance	106.48153846154
        */


        Person[] population = createPopulation();
        DataSet<Person> dataSet = new DataSet<>(population, Person.class);

        dataSet.sort((o1, o2) -> o1.height - o2.height);
        DataDescription<Person, ?> heightDesc =  dataSet.getDataDescription("height", int.class);
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

        dataSet.sort((o1, o2) -> o1.age - o2.age);
        DataDescription<Person, ?> ageDesc =  dataSet.getDataDescription("age", int.class);
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


    }


    private Person[] createPopulation() {
        String alphabet = "USPXMEJYRZQACGHLFKWNVODITB";

        int[] ages = new int[]{
                12, 15, 21, 18, 19, 30, 50, 70, 50, 55, 34, 21, 12, 13,
                18, 18, 18, 18, 18, 17, 18, 65, 75, 32, 24, 25
        };
        int[] heights = new int[]{
                160, 172, 162, 176, 180, 176, 182, 176, 176, 166, 158, 183, 165, 188,
                177, 178, 170, 180, 170, 180, 190, 173, 192, 167, 203, 171
        };
        Person[] population = new Person[alphabet.length()];
        for (int i = 0; i < population.length; i++) {
            String name = String.valueOf(alphabet.charAt(i));
            name = name.replace(name, name + name + name + name + name);
            population[i] = new Person(name, ages[i], heights[i]);
        }
        return population;
    }


}
