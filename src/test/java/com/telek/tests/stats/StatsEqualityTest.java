package com.telek.tests.stats;

import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekmath.advanced.statistics.freqtable.FreqDistTable;
import com.telek.telekmath.advanced.statistics.freqtable.FrequencyClass;
import com.telek.telekmath.advanced.statistics.regression.RegressionResult;
import com.telek.telekmath.advanced.statistics.regression.SimpleRegression;
import com.telek.telekutils.arrayref.oned.DoubleArrRef;
import com.telek.telekutils.containers.TArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class StatsEqualityTest {

    @Test
    @DisplayName("eqTest")
    void eqTest() {
        DoubleArrRef arr1 = new DoubleArrRef(new double[]{89, 100, 105, 110, 100, 100});
        DoubleArrRef arr2 = new DoubleArrRef(new double[]{89, 100, 105, 110, 100, 100});
        Arrays.sort(arr1.getArray());
        Arrays.sort(arr2.getArray());


        DataDescription desc1 = DescStats.getDataDesc(arr1);
        DataDescription desc2 = DescStats.getDataDesc(arr2);
        Assertions.assertEquals(desc1, desc2);

        ///////////////////////////////////////////////////////

        RegressionResult res1 = SimpleRegression.getResult(arr1, 0.05d);
        RegressionResult res2 = SimpleRegression.getResult(arr2, 0.05d);
        Assertions.assertEquals(res1, res2);

        ///////////////////////////////////////////////////////

        FreqDistTable table1 = new FreqDistTable(arr1, DescStats.getMin(arr1), 3);
        FreqDistTable table2 = new FreqDistTable(arr2, DescStats.getMin(arr2), 3);
        Assertions.assertEquals(table1, table2);

    }

}
