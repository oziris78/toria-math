package com.telek.tests.dists;

import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.advanced.distributions.cont.TDist;
import org.apache.commons.math3.distribution.TDistribution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;


public class TDistTest {

    static final int TIMES = 250_000;


    @Test
    @DisplayName("densityTest")
    void densityTest() {
        Random random = new Random();

        for (int i = 0; i < TIMES; i++) {
            double v = 1d + random.nextDouble() * random.nextInt(1000000);
            double x = (random.nextBoolean() ? -1 : 1) * random.nextDouble() * random.nextInt(1000000);

            double val1 = new TDistribution(v).density(x);
            double val2 = TDist.density(v, x);

            boolean b = TMath.areEqual(val1, val2);
            if(!b){
                System.out.println("v: " + v);
                System.out.println("x: " + x);
                System.out.println("val1: " + val1);
                System.out.println("val2: " + val2);
            }
            Assertions.assertTrue(b);
        }
    }




    @Test
    @DisplayName("cumProbTest")
    void cumProbTest() {
        Random random = new Random();
        for (int i = 0; i < TIMES; i++) {
            double x = (random.nextBoolean() ? -1 : 1) * (random.nextInt(99999999) * random.nextDouble());
            int v = 1 + (int) (random.nextInt(99999999) * random.nextDouble());

            double val1 = new TDistribution(v).cumulativeProbability(x);
            double val2 = TDist.cumulativeProbability(v, x);
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
        Random random = new Random();

        for (int unused = 0; unused < TIMES; unused++) {
            int v = 1 + (int) (random.nextInt(99999999) * random.nextDouble());
            double p = random.nextDouble();

            double val1 = new TDistribution(v).inverseCumulativeProbability(p);
            double val2 = TDist.invCumLeftTailed(v, p);

            boolean b = TMath.areEqual(val1, val2);
            if(!b){
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
        // single tests
        Assertions.assertTrue(TMath.areEqual(TDist.invCumRightTailed(500, 0.75), -0.674980734036162));
        Assertions.assertTrue(TMath.areEqual(TDist.invCumRightTailed(500, 0.32), 0.4679839324361787));

        // big test
        double[] pVals = new double[]{0.40, 0.25, 0.10, 0.05, 0.025, 0.01, 0.005, 0.0005};
        double[] arr = new double[]{
                0.324920, 1.000000, 3.077684, 6.313752, 12.70620, 31.82052,
                63.65674, 636.6192, 0.288675, 0.816497, 1.885618, 2.919986,
                4.30265, 6.96456, 9.92484, 31.5991, 0.276671, 0.764892, 1.637744,
                2.353363, 3.18245, 4.54070, 5.84091, 12.9240, 0.270722, 0.740697,
                1.533206, 2.131847, 2.77645, 3.74695, 4.60409, 8.6103, 0.267181,
                0.726687, 1.475884, 2.015048, 2.57058, 3.36493, 4.03214, 6.8688,
                0.264835, 0.717558, 1.439756, 1.943180, 2.44691, 3.14267, 3.70743,
                5.9588, 0.263167, 0.711142, 1.414924, 1.894579, 2.36462, 2.99795,
                3.49948, 5.4079, 0.261921, 0.706387, 1.396815, 1.859548, 2.30600,
                2.89646, 3.35539, 5.0413, 0.260955, 0.702722, 1.383029, 1.833113,
                2.26216, 2.82144, 3.24984, 4.7809, 0.260185, 0.699812, 1.372184,
                1.812461, 2.22814, 2.76377, 3.16927, 4.5869
        };
        for (int i = 0; i < arr.length; i++) {
            double p = pVals[i % pVals.length];
            int v = 1 + (i / pVals.length);
            double val1 = arr[i];
            double val2 = TDist.invCumRightTailed(v, p);

            boolean b = TMath.areEqual(val1, val2);
            if(!b){
                System.out.println("v: " + v);
                System.out.println("p: " + p);
                System.out.println("table: " + val1);
                System.out.println("my val: " + val2);
            }
            Assertions.assertTrue(b);

        }


        /*
        @Test
        @DisplayName("invCumLeftTailedBenchmark")
        void invCumLeftTailedBenchmark() {
            Random random = new Random();
            ArrayList<Integer> myTime = new ArrayList<>();
            ArrayList<Integer> apacheTime = new ArrayList<>();

            for (int unused = 0; unused < TIMES; unused++) {
                int v = 1 + (int) (random.nextInt(99999999) * random.nextDouble());
                double p = random.nextDouble();

                long s1 = System.nanoTime();
                double val1 = new TDistribution(v).inverseCumulativeProbability(p);
                long e1 = System.nanoTime();
                apacheTime.add((int) (e1 - s1));

                long s2 = System.nanoTime();
                double val2 = TDist.invCumLeftTailed(v, p);
                long e2 = System.nanoTime();
                myTime.add((int) (e2 - s2));

                boolean b = TMath.areEqual(val1, val2);
                if(!b){
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

}
