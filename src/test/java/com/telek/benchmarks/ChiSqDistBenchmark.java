package com.telek.benchmarks;

import com.telek.telekmath.advanced.distributions.cont.ChiSquaredDist;
import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekmath.utils.TMath;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.junit.jupiter.api.Assertions;
import java.util.Arrays;
import java.util.Random;


public class ChiSqDistBenchmark {


    private static final Random random = new Random();
    private static final int TIMES = 100;


    public static void main(String[] args) {
        double[] myTime = new double[TIMES];
        double[] apacheTime = new double[TIMES];

        for (int index = 0; index < TIMES; index++) {
            double v = 1 + (int) (random.nextInt(10_000_000) * random.nextDouble());
            double p = random.nextDouble();

            long s1 = System.nanoTime();
            double val1 = new ChiSquaredDistribution(v).inverseCumulativeProbability(p);
            long e1 = System.nanoTime();
            apacheTime[index] = (e1 - s1) / 1000000d;

            long s2 = System.nanoTime();
            double val2 = ChiSquaredDist.invCumLeftTailed(v, p);
            long e2 = System.nanoTime();
            myTime[index] = (e2 - s2) / 1000000d;

            boolean b = TMath.areEqual(val1, val2);
            if(!b){
                System.out.println("i: " + index);
                System.out.println("v: " + v);
                System.out.println("p: " + p);
                System.out.println("apache: " + val1);
                System.out.println("my val: " + val2);
            }
            Assertions.assertTrue(b);
        }

        Arrays.sort(myTime);
        Arrays.sort(apacheTime);
        DataDescription tmath = DescStats.getDataDesc(myTime);
        DataDescription apache = DescStats.getDataDesc(apacheTime);
        System.out.println("TMath: " +  tmath);
        System.out.println("Apache: " + apache);
        System.out.println("TMath: " +  tmath.sum + " ms");
        System.out.println("Apache: " + apache.sum + " ms");

    }


}
