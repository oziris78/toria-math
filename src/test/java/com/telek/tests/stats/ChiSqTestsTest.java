package com.telek.tests.stats;

import com.telek.telekmath.advanced.statistics.freqtable.FreqDistTable;
import com.telek.telekmath.advanced.statistics.inferential.ChiSquareTests;
import com.telek.telekmath.core.matrices.TMatrix;
import com.telek.telekutils.containers.TArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class ChiSqTestsTest {


    @Test
    @DisplayName("aliasFunctionChecksTest")
    void aliasFunctionChecksTest() {

        double[] observedFreqs = TArrays.doubleArr(94, 93, 112, 101, 104, 95, 100, 99, 108, 94);
        float[] ob1 = TArrays.floatArr(94, 93, 112, 101, 104, 95, 100, 99, 108, 94);
        int[] ob2 = TArrays.intArr(94, 93, 112, 101, 104, 95, 100, 99, 108, 94);
        Number[] ob3 = new Number[]{94, 93, 112, 101, 104, 95, 100, 99, 108, 94};
        FreqDistTable table = new FreqDistTable(observedFreqs, 0d, 1d); // start from 0d and increase by 1d
        double[] expectedFrequencies = TArrays.doubleFilledArr(table.getRowCount(),
                table.getTotalFrequency() / table.getRowCount());

        Assertions.assertTrue(ChiSquareTests.fitsDistribution(observedFreqs, expectedFrequencies, "uniform", 0.05d));
        Assertions.assertTrue(ChiSquareTests.fitsDistribution(ob1, expectedFrequencies, "uniform", 0.05d));
        Assertions.assertTrue(ChiSquareTests.fitsDistribution(ob3, expectedFrequencies, "uniform", 0.05d));
        Assertions.assertTrue(ChiSquareTests.fitsDistribution(ob2, expectedFrequencies, "uniform", 0.05d));

        Assertions.assertTrue(ChiSquareTests.isIndependent(observedFreqs, expectedFrequencies, 0.05d));
        Assertions.assertTrue(ChiSquareTests.isIndependent(ob1, expectedFrequencies, 0.05d));
        Assertions.assertTrue(ChiSquareTests.isIndependent(ob3, expectedFrequencies, 0.05d));
        Assertions.assertTrue(ChiSquareTests.isIndependent(ob2, expectedFrequencies, 0.05d));

        Assertions.assertTrue(ChiSquareTests.fitsDistribution(table, expectedFrequencies, "uniform", 0.05d));
        Assertions.assertTrue(ChiSquareTests.isIndependent(table, expectedFrequencies, 0.05d));

        TMatrix mat2 = new TMatrix(new double[][]{
                {6, 14, 16},
                {25, 22, 17},
                {9, 6, 5}
        });

        Assertions.assertTrue(ChiSquareTests.isHomogeneous(mat2, 0.05d));
        Assertions.assertTrue(ChiSquareTests.isIndependent(mat2, 0.05d));

    }


    @Test
    @DisplayName("goodnessOfFitTest")
    void goodnessOfFitTest() {
        /*
        Example 1:
        In order to test a computer program, 1000 random integers between 0 and 9
        have been generated. The numbers of the generated integers are given in the following
        table. Accordingly, test whether this computer program generates the random numbers
        uniformly. (alpha = 0.05)
            integers      observed frequencies
                0                  94
                1                  93
                2                  112
                3                  101
                4                  104
                5                  95
                6                  100
                7                  99
                8                  108
                9                  94
        */
        // if they're actually generated uniformly, all of them will have the same expected frequency
        // 10 classes, 1000 generations  =>   each should be generated 100 times
        double[] observedFreqs = TArrays.doubleArr(94, 93, 112, 101, 104, 95, 100, 99, 108, 94);
        FreqDistTable table = new FreqDistTable(observedFreqs, 0d, 1d); // start from 0d and increase by 1d
        double[] expectedFrequencies = TArrays.doubleFilledArr(table.getRowCount(),
                table.getTotalFrequency() / table.getRowCount());

        // SOLVE THE QUESTION USING THE TABLE
        Assertions.assertTrue(ChiSquareTests.fitsDistribution(table, expectedFrequencies, "uniform", 0.05d));
        // SOLVE THE QUESTION USING THE ARRAY
        Assertions.assertTrue(ChiSquareTests.fitsDistribution(observedFreqs, expectedFrequencies, "uniform", 0.05d));


        /*
        Example:
        One year television sales in a store were observed and their number by month was found as follows.
        According to this test whether the television sales are dependent to the months with 95% confidence level.

               classes            observed freqs
               January                 18
               February                19
               March                   25
               April                   24
               May                     20
               June                    18
               July                    16
               August                  22
               September               30
               October                 35
               November                28
               December                21
        */

        observedFreqs = TArrays.doubleArr(18, 19, 25, 24, 20, 18, 16, 22, 30, 35, 28, 21);
        table = new FreqDistTable(observedFreqs, 0d, 1d);
        expectedFrequencies = TArrays.doubleFilledArr(table.getRowCount(), table.getTotalFrequency() / table.getRowCount());

        // SOLVE THE QUESTION USING THE TABLE
        Assertions.assertTrue(ChiSquareTests.fitsDistribution(table, expectedFrequencies, "uniform", 0.05d));
        // SOLVE THE QUESTION USING THE ARRAY
        Assertions.assertTrue(ChiSquareTests.fitsDistribution(observedFreqs, expectedFrequencies, "uniform", 0.05d));
    }


    @Test
    @DisplayName("homogeneityTest")
    void homogeneityTest() {
        /*
        Example:
        There are 3 different retirement plans in a company. Test whether there is an association between these
        pension plan preferences and people's hourly and salaried work, i.e. whether the two criteria are
        independent from each other at the significance level αlpha = 0.05

                        plan1  plan2  plan3
        salaried work    160    140    40
        hourly work      40     60     60
        */

        TMatrix mat = new TMatrix(new double[][]{
                {160, 140, 40},
                {40, 60, 60}
        });

        Assertions.assertFalse(ChiSquareTests.isIndependent(mat, 0.05d));
        // we reject H_0, the retirement plans and working types are dependent


        /*
        Example:
        In order to examine whether the frequency of going to the theater is the
        same for students studying in different faculties; 40 students from Science Faculty, 42
        students from Faculty of Economics 38 students from Communication Faculty were
        asked about the frequency of going to the theater. Analyze whether the frequency of
        going to the theater is homogeneously distributed according to the data in the table. (alpha = 0.05)

                        science  economics  communication
        frequently        6         14          16
        rarely            25        22          17
        rarely            9         6           5
        */

        TMatrix mat2 = new TMatrix(new double[][]{
                {6, 14, 16},
                {25, 22, 17},
                {9, 6, 5}
        });

        Assertions.assertTrue(ChiSquareTests.isHomogeneous(mat2, 0.05d));
        // we accept H_0, The frequencies of going to theater are same for given faculties
    }



}
