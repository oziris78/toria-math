package com.telek.tests.stats.sampling;

import com.telek.telekmath.advanced.random.Sampler;
import com.telek.tests.stats.exampledata.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class SamplingTest {


    @Test
    @DisplayName("samplingTestClass")
    void samplingTestClass() {
        Person[] population = Person.createPopulation();

        // these can't be tested because well... they're random.
        Person[] sample1 = Sampler.simpleRandomSampling(population, Person.class, 15);
        Person[] sample2 = Sampler.systematicSampling(population, Person.class, 10);

        System.out.println(sample1);
        System.out.println(sample2);

        // tests sample3 10 times
        final int TIMES = 10;
        for (int unused = 0; unused < TIMES; unused++) {
            //US PXM EJ YRZ QA CGH LF KWN VO DITB
            Person[] sample3 = Sampler.clusterSampling(population, Person.class, 10);
            int i = 0;
            Assertions.assertTrue( sample3[i].name.contentEquals("UUUUU") ||  sample3[i].name.contentEquals("SSSSS") );
            i++;
            Assertions.assertTrue( sample3[i].name.contentEquals("PPPPP") ||  sample3[i].name.contentEquals("XXXXX") ||  sample3[i].name.contentEquals("MMMMM") );
            i++;
            Assertions.assertTrue( sample3[i].name.contentEquals("EEEEE") ||  sample3[i].name.contentEquals("JJJJJ") );
            i++;
            Assertions.assertTrue( sample3[i].name.contentEquals("YYYYY") ||  sample3[i].name.contentEquals("RRRRR") ||  sample3[i].name.contentEquals("ZZZZZ") );
            i++;
            Assertions.assertTrue( sample3[i].name.contentEquals("QQQQQ") ||  sample3[i].name.contentEquals("AAAAA") );
            i++;
            Assertions.assertTrue( sample3[i].name.contentEquals("CCCCC") ||  sample3[i].name.contentEquals("HHHHH") ||  sample3[i].name.contentEquals("GGGGG") );
            i++;
            Assertions.assertTrue( sample3[i].name.contentEquals("LLLLL") ||  sample3[i].name.contentEquals("FFFFF") );
            i++;
            Assertions.assertTrue( sample3[i].name.contentEquals("KKKKK") ||  sample3[i].name.contentEquals("WWWWW") ||  sample3[i].name.contentEquals("NNNNN") );
            i++;
            Assertions.assertTrue( sample3[i].name.contentEquals("VVVVV") ||  sample3[i].name.contentEquals("OOOOO") );
            i++;
            Assertions.assertTrue( sample3[i].name.contentEquals("DDDDD") ||  sample3[i].name.contentEquals("IIIII") ||  sample3[i].name.contentEquals("TTTTT") ||  sample3[i].name.contentEquals("BBBBB") );
        }

    }

    @Test
    @DisplayName("samplingTestPrimitiveINT")
    void samplingTestPrimitiveInt() {
        int[] population = new int[]{
                160, 172, 162, 176, 180, 176, 182, 176, 176, 166, 158, 183, 165, 188,
                177, 178, 170, 180, 170, 180, 190, 173, 192, 167, 203, 171
        };

        int[] sample1 = Sampler.simpleRandomSampling(population, 15);
        int[] sample2 = Sampler.systematicSampling(population, 10);

        System.out.println(Arrays.toString(sample1));
        System.out.println(Arrays.toString(sample2));

        // tests sample3 100 times
        final int TIMES = 100;
        for (int unused = 0; unused < TIMES; unused++) {
            // instead of: US PXM EJ YRZ QA CGH LF KWN VO DITB
            // we have:
            // 160, 172
            // 162, 176, 180
            // 176, 182
            // 176, 176, 166
            // 158, 183
            // 165, 188, 177
            // 178, 170
            // 180, 170, 180
            // 190, 173
            // 192, 167, 203, 171

            int[] sample3 = Sampler.clusterSampling(population, 10);

            int i = 0;
            Assertions.assertTrue( sample3[i] == 160 ||  sample3[i] == 172 );
            i++;
            Assertions.assertTrue( sample3[i] == 162 ||  sample3[i] == 176 ||  sample3[i] == 180 );
            i++;
            Assertions.assertTrue( sample3[i] == 176 ||  sample3[i] == 182 );
            i++;
            Assertions.assertTrue( sample3[i] == 176 ||  sample3[i] == 176 ||  sample3[i] == 166 );
            i++;
            Assertions.assertTrue( sample3[i] == 158 ||  sample3[i] == 183 );
            i++;
            Assertions.assertTrue( sample3[i] == 165 ||  sample3[i] == 188 ||  sample3[i] == 177 );
            i++;
            Assertions.assertTrue( sample3[i] == 178 ||  sample3[i] == 170 );
            i++;
            Assertions.assertTrue( sample3[i] == 180 ||  sample3[i] == 170 ||  sample3[i] == 180 );
            i++;
            Assertions.assertTrue( sample3[i] == 190 ||  sample3[i] == 173 );
            i++;
            Assertions.assertTrue( sample3[i] == 192 ||  sample3[i] == 167 ||  sample3[i] == 203 ||  sample3[i] == 171 );
        }


    }




    @Test
    @DisplayName("samplingTestPrimitiveFloat")
    void samplingTestPrimitiveFloat() {
        float[] population = new float[]{
                160, 172, 162, 176, 180, 176, 182, 176, 176, 166, 158, 183, 165, 188,
                177, 178, 170, 180, 170, 180, 190, 173, 192, 167, 203, 171
        };

        float[] sample1 = Sampler.simpleRandomSampling(population, 15);
        float[] sample2 = Sampler.systematicSampling(population, 10);

        System.out.println(Arrays.toString(sample1));
        System.out.println(Arrays.toString(sample2));

        // tests sample3 100 times
        final int TIMES = 100;
        for (int unused = 0; unused < TIMES; unused++) {
            // instead of: US PXM EJ YRZ QA CGH LF KWN VO DITB
            // we have:
            // 160, 172
            // 162, 176, 180
            // 176, 182
            // 176, 176, 166
            // 158, 183
            // 165, 188, 177
            // 178, 170
            // 180, 170, 180
            // 190, 173
            // 192, 167, 203, 171

            float[] sample3 = Sampler.clusterSampling(population, 10);

            int i = 0;
            Assertions.assertTrue( sample3[i] == 160 ||  sample3[i] == 172 );
            i++;
            Assertions.assertTrue( sample3[i] == 162 ||  sample3[i] == 176 ||  sample3[i] == 180 );
            i++;
            Assertions.assertTrue( sample3[i] == 176 ||  sample3[i] == 182 );
            i++;
            Assertions.assertTrue( sample3[i] == 176 ||  sample3[i] == 176 ||  sample3[i] == 166 );
            i++;
            Assertions.assertTrue( sample3[i] == 158 ||  sample3[i] == 183 );
            i++;
            Assertions.assertTrue( sample3[i] == 165 ||  sample3[i] == 188 ||  sample3[i] == 177 );
            i++;
            Assertions.assertTrue( sample3[i] == 178 ||  sample3[i] == 170 );
            i++;
            Assertions.assertTrue( sample3[i] == 180 ||  sample3[i] == 170 ||  sample3[i] == 180 );
            i++;
            Assertions.assertTrue( sample3[i] == 190 ||  sample3[i] == 173 );
            i++;
            Assertions.assertTrue( sample3[i] == 192 ||  sample3[i] == 167 ||  sample3[i] == 203 ||  sample3[i] == 171 );
        }


    }



}
