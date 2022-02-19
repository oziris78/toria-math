package com.telek.tests.stats;

import com.telek.telekmath.advanced.statistics.inferential.ChiSquareTests;
import com.telek.telekmath.core.matrices.TMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class ChiSqTestsTest {


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
