package com.telek.tests.stats;

import com.telek.telekmath.advanced.statistics.Sampler;
import com.telek.telekutils.plain.TCollections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class SamplingTest {

    @Test
    @DisplayName("samplingTestClass")
    void samplingTestClass() {
        Person[] population = Person.createPopulation();

        Sampler<Person> personSampler = new Sampler<>(population, Person.class);

        Person[] sample1 = personSampler.simpleRandomSampling(15);
        Person[] sample2 = personSampler.systematicSampling(10);
        Person[] sample3 = personSampler.clusterSampling(10); //US PXM EJ YRZ QA CGH LF KWN VO DITB

        System.out.println(Arrays.toString(sample1));
        System.out.println(Arrays.toString(sample2));
        System.out.println(Arrays.toString(sample3));

        // do wtf ever you want with that sample array...
    }

    @Test
    @DisplayName("samplingTestPrimitive")
    void samplingTestPrimitive() {
        int[] heights = new int[]{
                160, 172, 162, 176, 180, 176, 182, 176, 176, 166, 158, 183, 165, 188,
                177, 178, 170, 180, 170, 180, 190, 173, 192, 167, 203, 171
        };

        Integer[] population = TCollections.getAsClassArray(heights);

        Sampler<Integer> intSampler = new Sampler(population, Integer.class);

        Integer[] sample1 = intSampler.simpleRandomSampling(15);
        Integer[] sample2 = intSampler.systematicSampling(10);
        Integer[] sample3 = intSampler.clusterSampling(10);

        System.out.println(Arrays.toString(sample1));
        System.out.println(Arrays.toString(sample2));
        System.out.println(Arrays.toString(sample3));

        // do wtf ever you want with that sample array...
    }


}
