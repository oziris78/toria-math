package com.twistral.tests.stats.freqtable;

import com.twistral.toriamath.utils.TMath;
import com.twistral.toriamath.advanced.statistics.freqtable.FreqDistTable;
import com.twistral.toriamath.advanced.statistics.freqtable.FrequencyClass;
import com.twistral.toriautils.containers.TCollections;
import com.twistral.tests.stats.exampledata.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;


public class FreqDistTableTest {

    @Test
    @DisplayName("freqTableGenericTest")
    void freqTableGenericTest() {

        /*
        Sorted Data:
        158, 160, 162, 165, 166, 167, 170, 170, 171, 172, 173, 176,
        176, 176, 176, 177, 178, 180, 180, 180, 182, 183, 188, 190, 192, 203

        n = 26, range = 45, k = 6, c = ceil(45/6) = 8

        Expected Results:
           ci       xi     freq             relFreq                 incCumFreq       incRelFreq
        [158-166)    162      4         4/26 (0.153846153846 )           4                0.153846153846
        [166-174)    170      7         7/26 (0.269230769231 )           11               0.423076923077
        [174-182)    178      9         9/26 (0.346153846154 )           20               0.769230769231
        [182-190)    186      3         3/26 (0.115384615385 )           23               0.884615384616
        [190-198)    194      2         2/26 (0.0769230769231)           25               0.961538461539
        [198-206)    202      1         1/26 (0.0384615384615)           26               1

        */


        // PREPERATION OF DATA
        Person[] population = Person.createPopulation();
        double[] heights = new double[population.length];
        for (int i = 0; i < population.length; i++) {
            heights[i] = population[i].height;
        }
        Arrays.sort(heights);
        FreqDistTable table = new FreqDistTable(heights, 6);

        // FOR VISUALIZATION:
        System.out.println(table);

        // TESTS
        FrequencyClass row0 = table.getTableRow(0);

        Assertions.assertTrue(TMath.areEqual(row0.cLeft, 158));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalLeft(1), 166));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalLeft(2), 174));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalLeft(3), 182));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalLeft(4), 190));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalLeft(5), 198));

        Assertions.assertTrue(TMath.areEqual(row0.cRight, 166));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalRight(1), 174));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalRight(2), 182));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalRight(3), 190));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalRight(4), 198));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalRight(5), 206));

        Assertions.assertTrue(TMath.areEqual(row0.midpoint, 162));
        Assertions.assertTrue(TMath.areEqual(table.getMidpoint(1), 170));
        Assertions.assertTrue(TMath.areEqual(table.getMidpoint(2), 178));
        Assertions.assertTrue(TMath.areEqual(table.getMidpoint(3), 186));
        Assertions.assertTrue(TMath.areEqual(table.getMidpoint(4), 194));
        Assertions.assertTrue(TMath.areEqual(table.getMidpoint(5), 202));

        Assertions.assertTrue(TMath.areEqual(row0.freq, 4));
        Assertions.assertTrue(TMath.areEqual(table.getFrequency(1), 7));
        Assertions.assertTrue(TMath.areEqual(table.getFrequency(2), 9));
        Assertions.assertTrue(TMath.areEqual(table.getFrequency(3), 3));
        Assertions.assertTrue(TMath.areEqual(table.getFrequency(4), 2));
        Assertions.assertTrue(TMath.areEqual(table.getFrequency(5), 1));

        Assertions.assertTrue(TMath.areEqual(row0.relFreq, 0.153846153846));
        Assertions.assertTrue(TMath.areEqual(table.getRelativeFreq(1), 0.269230769231));
        Assertions.assertTrue(TMath.areEqual(table.getRelativeFreq(2), 0.346153846154));
        Assertions.assertTrue(TMath.areEqual(table.getRelativeFreq(3), 0.115384615385));
        Assertions.assertTrue(TMath.areEqual(table.getRelativeFreq(4), 0.0769230769231));
        Assertions.assertTrue(TMath.areEqual(table.getRelativeFreq(5), 0.0384615384615));

        Assertions.assertTrue(TMath.areEqual(row0.incCumFreq, 4));
        Assertions.assertTrue(TMath.areEqual(table.getIncCumFreq(1), 11));
        Assertions.assertTrue(TMath.areEqual(table.getIncCumFreq(2), 20));
        Assertions.assertTrue(TMath.areEqual(table.getIncCumFreq(3), 23));
        Assertions.assertTrue(TMath.areEqual(table.getIncCumFreq(4), 25));
        Assertions.assertTrue(TMath.areEqual(table.getIncCumFreq(5), 26));

        Assertions.assertTrue(TMath.areEqual(row0.incRelFreq, 0.153846153846));
        Assertions.assertTrue(TMath.areEqual(table.getIncRelFreq(1), 0.423076923077));
        Assertions.assertTrue(TMath.areEqual(table.getIncRelFreq(2), 0.769230769231));
        Assertions.assertTrue(TMath.areEqual(table.getIncRelFreq(3), 0.884615384616));
        Assertions.assertTrue(TMath.areEqual(table.getIncRelFreq(4), 0.961538461539));
        Assertions.assertTrue(TMath.areEqual(table.getIncRelFreq(5), 1));

    }


    @Test
    @DisplayName("freqTablePrimiteWithoutConversionTest")
    void freqTablePrimiteWithoutConversionTest() {
        double[] arr2 = new double[]{
                160, 172, 162, 176, 180, 176, 182, 176, 176, 166, 158, 183, 165, 188,
                177, 178, 170, 180, 170, 180, 190, 173, 192, 167, 203, 171
        };

        FreqDistTable table = new FreqDistTable(arr2, 6);
        Assertions.assertTrue(TMath.areEqual(table.getIntervalLeft(0), 158));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalLeft(1), 166));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalLeft(2), 174));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalLeft(3), 182));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalLeft(4), 190));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalLeft(5), 198));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalRight(0), 166));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalRight(1), 174));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalRight(2), 182));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalRight(3), 190));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalRight(4), 198));
        Assertions.assertTrue(TMath.areEqual(table.getIntervalRight(5), 206));
        Assertions.assertTrue(TMath.areEqual(table.getMidpoint(0), 162));
        Assertions.assertTrue(TMath.areEqual(table.getMidpoint(1), 170));
        Assertions.assertTrue(TMath.areEqual(table.getMidpoint(2), 178));
        Assertions.assertTrue(TMath.areEqual(table.getMidpoint(3), 186));
        Assertions.assertTrue(TMath.areEqual(table.getMidpoint(4), 194));
        Assertions.assertTrue(TMath.areEqual(table.getMidpoint(5), 202));
        Assertions.assertTrue(TMath.areEqual(table.getFrequency(0), 4));
        Assertions.assertTrue(TMath.areEqual(table.getFrequency(1), 7));
        Assertions.assertTrue(TMath.areEqual(table.getFrequency(2), 9));
        Assertions.assertTrue(TMath.areEqual(table.getFrequency(3), 3));
        Assertions.assertTrue(TMath.areEqual(table.getFrequency(4), 2));
        Assertions.assertTrue(TMath.areEqual(table.getFrequency(5), 1));
        Assertions.assertTrue(TMath.areEqual(table.getRelativeFreq(0), 0.153846153846));
        Assertions.assertTrue(TMath.areEqual(table.getRelativeFreq(1), 0.269230769231));
        Assertions.assertTrue(TMath.areEqual(table.getRelativeFreq(2), 0.346153846154));
        Assertions.assertTrue(TMath.areEqual(table.getRelativeFreq(3), 0.115384615385));
        Assertions.assertTrue(TMath.areEqual(table.getRelativeFreq(4), 0.0769230769231));
        Assertions.assertTrue(TMath.areEqual(table.getRelativeFreq(5), 0.0384615384615));
        Assertions.assertTrue(TMath.areEqual(table.getIncCumFreq(0), 4));
        Assertions.assertTrue(TMath.areEqual(table.getIncCumFreq(1), 11));
        Assertions.assertTrue(TMath.areEqual(table.getIncCumFreq(2), 20));
        Assertions.assertTrue(TMath.areEqual(table.getIncCumFreq(3), 23));
        Assertions.assertTrue(TMath.areEqual(table.getIncCumFreq(4), 25));
        Assertions.assertTrue(TMath.areEqual(table.getIncCumFreq(5), 26));
        Assertions.assertTrue(TMath.areEqual(table.getIncRelFreq(0), 0.153846153846));
        Assertions.assertTrue(TMath.areEqual(table.getIncRelFreq(1), 0.423076923077));
        Assertions.assertTrue(TMath.areEqual(table.getIncRelFreq(2), 0.769230769231));
        Assertions.assertTrue(TMath.areEqual(table.getIncRelFreq(3), 0.884615384616));
        Assertions.assertTrue(TMath.areEqual(table.getIncRelFreq(4), 0.961538461539));
        Assertions.assertTrue(TMath.areEqual(table.getIncRelFreq(5), 1));
    }


    @Test
    @DisplayName("freqTablePrimiteWithConversionTest")
    void freqTablePrimiteWithConversionTest() {

            double[] heights = new double[]{
                    160, 172, 162, 176, 180, 176, 182, 176, 176, 166, 158, 183, 165, 188,
                    177, 178, 170, 180, 170, 180, 190, 173, 192, 167, 203, 171
            };
            FreqDistTable freqDistTable = new FreqDistTable(heights, 6);

            // TESTS
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIntervalLeft(0), 158));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIntervalLeft(1), 166));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIntervalLeft(2), 174));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIntervalLeft(3), 182));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIntervalLeft(4), 190));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIntervalLeft(5), 198));

            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIntervalRight(0), 166));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIntervalRight(1), 174));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIntervalRight(2), 182));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIntervalRight(3), 190));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIntervalRight(4), 198));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIntervalRight(5), 206));

            Assertions.assertTrue(TMath.areEqual(freqDistTable.getMidpoint(0), 162));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getMidpoint(1), 170));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getMidpoint(2), 178));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getMidpoint(3), 186));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getMidpoint(4), 194));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getMidpoint(5), 202));

            Assertions.assertTrue(TMath.areEqual(freqDistTable.getFrequency(0), 4));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getFrequency(1), 7));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getFrequency(2), 9));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getFrequency(3), 3));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getFrequency(4), 2));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getFrequency(5), 1));

            Assertions.assertTrue(TMath.areEqual(freqDistTable.getRelativeFreq(0), 0.153846153846));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getRelativeFreq(1), 0.269230769231));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getRelativeFreq(2), 0.346153846154));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getRelativeFreq(3), 0.115384615385));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getRelativeFreq(4), 0.0769230769231));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getRelativeFreq(5), 0.0384615384615));

            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIncCumFreq(0), 4));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIncCumFreq(1), 11));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIncCumFreq(2), 20));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIncCumFreq(3), 23));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIncCumFreq(4), 25));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIncCumFreq(5), 26));

            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIncRelFreq(0), 0.153846153846));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIncRelFreq(1), 0.423076923077));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIncRelFreq(2), 0.769230769231));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIncRelFreq(3), 0.884615384616));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIncRelFreq(4), 0.961538461539));
            Assertions.assertTrue(TMath.areEqual(freqDistTable.getIncRelFreq(5), 1));

    }


    @Test
    @DisplayName("freqTableWithKnownFreqsTest")
    void freqTableWithKnownFreqsTest() {
        FreqDistTable table2 = new FreqDistTable(new double[]{ 94, 93, 112, 101, 104, 95, 100, 99, 108, 94 }, 0d, 1d);

        ArrayList<Double> terms = new ArrayList<>();
        for (int i = 0; i < 94; i++) terms.add(0d);
        for (int i = 0; i < 93; i++) terms.add(1d);
        for (int i = 0; i < 112; i++) terms.add(2d);
        for (int i = 0; i < 101; i++) terms.add(3d);
        for (int i = 0; i < 104; i++) terms.add(4d);
        for (int i = 0; i < 95; i++) terms.add(5d);
        for (int i = 0; i < 100; i++) terms.add(6d);
        for (int i = 0; i < 99; i++) terms.add(7d);
        for (int i = 0; i < 108; i++) terms.add(8d);
        for (int i = 0; i < 94; i++) terms.add(9d);
        double[] arr = TCollections.collectionToArray(terms);
        FreqDistTable table5 = new FreqDistTable(arr, 10);

        Assertions.assertTrue(table2.equals(table5));
    }

}