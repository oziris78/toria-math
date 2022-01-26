package com.telek.tests.stats;

import com.telek.telekmath.advanced.statistics.Sampler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class SamplingTest {

    @Test
    @DisplayName("samplingTest")
    void samplingTest() {
        Person[] population = Person.createPopulation();

        Sampler<Person> personSampler = new Sampler<>(population, Person.class);

        Person[] sample1 = personSampler.simpleRandomSampling(15);
        Person[] sample2 = personSampler.systematicSampling(10);
        Person[] sample3 = personSampler.clusterSampling(10);

        System.out.println(Arrays.toString(sample1));
        System.out.println(Arrays.toString(sample2));
        System.out.println(Arrays.toString(sample3));

        // do wtf ever you want with that sample array...
    }


}
