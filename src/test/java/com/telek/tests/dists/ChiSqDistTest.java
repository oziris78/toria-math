package com.telek.tests.dists;

import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.advanced.distributions.cont.ChiSquaredDist;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;


public class ChiSqDistTest {


    static final int TIMES = 250_000;


    @Test
    @DisplayName("densityTest")
    void densityTest() {
        Random random = new Random();
        for (int unused = 0; unused < TIMES; unused++) {
            int v = 1 + random.nextInt(999999999);
            double x = (random.nextBoolean() ? -1 : 1) * random.nextDouble() * random.nextInt(99999999);
            double val1 = new ChiSquaredDistribution(v).density(x);
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
            double x = (random.nextBoolean() ? -1 : 1) * random.nextInt(99999999) * random.nextDouble();
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
    }





    @Test
    @DisplayName("invCumLeftTailed")
    void invCumLeftTailed() {
        // THIS TEST IS REALLY SLOW IF YOU USE BIG X VALUES
        // IT WILL EVEN FAIL FOR X VALUES BIGGER THAN 10 MILLION
//        final int TIMES = 250_000;
        final int TIMES = 100;
        // THIS TEST IS REALLY SLOW

        Random random = new Random();

        for (int unused = 0; unused < TIMES; unused++) {
            double v = 1 + random.nextInt(250_000) * random.nextDouble(); // more than 10 million will break it...
            double p = random.nextDouble();

            double val1 = new ChiSquaredDistribution(v).inverseCumulativeProbability(p);
            double val2 = ChiSquaredDist.invCumLeftTailed(v, p);
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
    }



    @Test
    @DisplayName("invCumRightTailed")
    void invCumRightTailed() {
        double[] pValues = new double[]{0.995d, 0.990d, 0.975d, 0.950d, 0.900d,
                0.750d, 0.500d, 0.250d, 0.100d, 0.050d, 0.025d, 0.010d, 0.005d
        };
        double[] results = new double[]{0.00004, 0.00016, 0.00098, 0.00393, 0.01579,
                0.10153, 0.45494, 1.32330, 2.70554, 3.84146, 5.02389, 6.63490, 7.87944,
                0.01003, 0.02010, 0.05064, 0.10259, 0.21072, 0.57536, 1.38629, 2.77259,
                4.60517, 5.99146, 7.37776, 9.21034, 10.59663, 0.07172, 0.11483, 0.21580,
                0.35185, 0.58437, 1.21253, 2.36597, 4.10834, 6.25139, 7.81473, 9.34840,
                11.34487, 12.83816, 0.20699, 0.29711, 0.48442, 0.71072, 1.06362, 1.92256,
                3.35669, 5.38527, 7.77944, 9.48773, 11.14329, 13.27670, 14.86026, 0.41174,
                0.55430, 0.83121, 1.14548, 1.61031, 2.67460, 4.35146, 6.62568, 9.23636,
                11.07050, 12.83250, 15.08627, 16.74960, 0.67573, 0.87209, 1.23734, 1.63538,
                2.20413, 3.45460, 5.34812, 7.84080, 10.64464, 12.59159, 14.44938, 16.81189,
                18.54758, 0.98926, 1.23904, 1.68987, 2.16735, 2.83311, 4.25485, 6.34581,
                9.03715, 12.01704, 14.06714, 16.01276, 18.47531, 20.27774, 1.34441, 1.64650,
                2.17973, 2.73264, 3.48954, 5.07064, 7.34412, 10.21885, 13.36157, 15.50731,
                17.53455, 20.09024, 21.95495, 1.73493, 2.08790, 2.70039, 3.32511, 4.16816,
                5.89883, 8.34283, 11.38875, 14.68366, 16.91898, 19.02277, 21.66599, 23.58935,
                2.15586, 2.55821, 3.24697, 3.94030, 4.86518, 6.73720, 9.34182, 12.54886, 15.98718,
                18.30704, 20.48318, 23.20925, 25.18818, 2.60322, 3.05348, 3.81575, 4.57481, 5.57778,
                7.58414, 10.34100, 13.70069, 17.27501, 19.67514, 21.92005, 24.72497, 26.75685, 3.07382,
                3.57057, 4.40379, 5.22603, 6.30380, 8.43842, 11.34032, 14.84540, 18.54935, 21.02607,
                23.33666, 26.21697, 28.29952, 3.56503, 4.10692, 5.00875, 5.89186, 7.04150, 9.29907, 12.33976,
                15.98391, 19.81193, 22.36203, 24.73560, 27.68825, 29.81947, 4.07467, 4.66043, 5.62873,
                6.57063, 7.78953, 10.16531, 13.33927, 17.11693, 21.06414, 23.68479, 26.11895, 29.14124,
                31.31935, 4.60092, 5.22935, 6.26214, 7.26094, 8.54676, 11.03654, 14.33886, 18.24509, 22.30713,
                24.99579, 27.48839, 30.57791, 32.80132
        };

        for (int i = 0; i < results.length; i++) {
            double val1 = results[i];
            double p = pValues[i % pValues.length];
            double v = 1 + (i / 13);
            double val2 = ChiSquaredDist.invCumRightTailed(v, p);
            boolean b = TMath.areEqual(val1, val2);
            if(!b){
                System.out.println("i: " + i);
                System.out.println("p: " + p);
                System.out.println("v: " + v);
                System.out.println("val1: " + val1);
                System.out.println("val2: " + val2);
            }
            Assertions.assertTrue(b);
        }


    }


    // BENCHMARK
    /*
    @Test
    @DisplayName("invCumProbBenchmark")
    void invCumProbBenchmark() {
        Random random = new Random();
        ArrayList<Integer> myTime = new ArrayList<>();
        ArrayList<Integer> apacheTime = new ArrayList<>();

        for (int unused = 0; unused < TIMES; unused++) {
            System.out.println("next");
            double v = 1 + (int) (random.nextInt(10_000_000) * random.nextDouble());
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

}
