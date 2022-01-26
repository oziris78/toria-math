package com.telek.tests.stats;

import com.telek.telekmath.advanced.statistics.FrequencyDistTable;
import org.apache.commons.math3.stat.Frequency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FrequencyDistTableTest {
    
    @Test
    @DisplayName("freqDistTable")
    void freqDistTable() {


        Person[] population = Person.createPopulation();
        FrequencyDistTable<Person> freqDistTable = new FrequencyDistTable<>(population, Person.class, "height", 6);
        freqDistTable.fillTheTable();


        /*
        classInterval = Math.ceil

        Class	        Count	Percentage
        [158 - 166)	    4	     15.4
        [166 - 173)	    7	     26.9
        [174 - 181)	    9	     34.6
        [182 - 189)	    3	     11.5
        [190 - 197)	    2	     7.7
        [198 - 205)	    1	     3.8

        Total	        26	     99.9
         */
    }


}
