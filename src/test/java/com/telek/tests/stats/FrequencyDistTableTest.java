package com.telek.tests.stats;

import com.telek.telekmath.TMath;
import com.telek.telekmath.advanced.statistics.FrequencyDistTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FrequencyDistTableTest {
    
    @Test
    @DisplayName("freqDistTable")
    void freqDistTable() {

        /*
        Sorted Data:
        158, 160, 162, 165, 166, 167, 170, 170, 171, 172, 173, 176,
        176, 176, 176, 177, 178, 180, 180, 180, 182, 183, 188, 190, 192, 203

        n = 26
        range = 45
        k = 6

        c = ceil(45/6) = 8

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
        FrequencyDistTable<Person> freqDistTable = new FrequencyDistTable<>(population, Person.class, "height", 6);
        freqDistTable.fillTheTable();

        System.out.println(freqDistTable.getTableAsString());

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


}
