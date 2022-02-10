package com.telek.tests.dists;

import com.telek.telekmath.advanced.statistics.measures.DataSet;
import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.advanced.distributions.cont.ChiSquaredDist;
import com.telek.telekutils.plain.TCollections;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.distribution.FDistribution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class ChiSqDistTest {

    @Test
    @DisplayName("densityTest")
    void densityTest() {
        Random random = new Random();
        for (int unused = 0; unused < TIMES; unused++) {
            int v = 1 + random.nextInt(1000000);
            ChiSquaredDistribution csd = new ChiSquaredDistribution(v);
            double x = random.nextDouble() * random.nextInt(1000000);
            double val1 = csd.density(x);
            double val2 = ChiSquaredDist.density(v, x);
            boolean b = TMath.areEqual(val1, val2);
            if(!b){
                System.out.println("v : " + v);
                System.out.println("x : " + x);
                System.out.println("val1 : " + val1);
                System.out.println("val2 : " + val2);
            }
            Assertions.assertTrue(b);
        }
    }



    @Test
    @DisplayName("cumProbTest")
    void cumProbTest() {
        Random random = new Random();

        for (int i = 0; i < TIMES; i++) {
            double x = random.nextInt(99999999) * random.nextDouble();
            double v = 1 + (int) (random.nextInt(99999999) * random.nextDouble());

            double val1 = new ChiSquaredDistribution(v).cumulativeProbability(x);
            double val2 = ChiSquaredDist.cumulativeProbability(v, x);
            boolean res = TMath.areEqual(val1, val2);
            if(!res){
                System.out.println("x: " + x);
                System.out.println("v: " + v);
                System.out.println("val1: " + val1);
                System.out.println("val2: " + val2);
            }
            Assertions.assertTrue(res);
        }


        for (int i = 0; i < TIMES; i++) {
            double x = (random.nextInt(10000) * random.nextDouble());
            double v = 1 + (int) (random.nextInt(10000) * random.nextDouble());

            double val1 = new ChiSquaredDistribution(v).cumulativeProbability(x);
            double val2 = ChiSquaredDist.cumulativeProbability(v, x);
            boolean res = TMath.areEqual(val1, val2);
            if(!res){
                System.out.println("x: " + x);
                System.out.println("v: " + v);
                System.out.println("val1: " + val1);
                System.out.println("val2: " + val2);
            }
            Assertions.assertTrue(res);
        }
    }



    static final int TIMES = 10_000;


    /*
    @Test
    @DisplayName("invCumProbTest")
    void invCumProbTest() {
        Random random = new Random();
        ArrayList<Integer> myTime = new ArrayList<>();
        ArrayList<Integer> apacheTime = new ArrayList<>();

        for (int unused = 0; unused < TIMES; unused++) {
            double v = 1 + (int) (random.nextInt(1_000_000) * random.nextDouble());
            double p = random.nextDouble();

            long s1 = System.nanoTime();
            double val1 = new ChiSquaredDistribution(v).inverseCumulativeProbability(p);
            long e1 = System.nanoTime();
            apacheTime.add((int) (e1 - s1));

            long s2 = System.nanoTime();
            double val2 = ChiSquaredDist.inverseCumulativeProbability(v, p);
            long e2 = System.nanoTime();
            myTime.add((int) (e2 - s2));

            boolean b = TMath.areEqual(val1, val2);
            if(!b){
                System.out.println("i: " + unused);
                System.out.println("v: " + v);
                System.out.println("p: " + p);
                System.out.println("apache: " + val1);
                System.out.println("my val: " + val2);
            }
            Assertions.assertTrue(b);
        }
        Integer[] myTimeArr = myTime.toArray(new Integer[0]);
        Integer[] myTimeArr2 = TCollections.getSortedCopy(myTimeArr, Integer.class, (o1, o2) -> o1.intValue() - o2.intValue());
        DataSet myDataSet = new DataSet(myTimeArr2);
        System.out.println("My results: " + myDataSet.getDataDesc());


        Integer[] apacheTimeArr = apacheTime.toArray(new Integer[0]);
        Integer[] apacheTimeArr2 = TCollections.getSortedCopy(apacheTimeArr, Integer.class, (o1, o2) -> o1.intValue() - o2.intValue());
        DataSet apacheDataSet = new DataSet(apacheTimeArr2);
        System.out.println("Apache results: " + apacheDataSet.getDataDesc());

    }
     */

    @Test
    @DisplayName("test")
    void test() {

        ChiSquaredDistribution csd = new ChiSquaredDistribution(5);
        System.out.println(csd.inverseCumulativeProbability(1d - 0.995));
    }


}
