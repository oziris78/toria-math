package com.telek.benchmarks;


import com.twistral.toriamath.advanced.statistics.descriptive.DataDescription;
import com.twistral.toriamath.advanced.statistics.descriptive.DescStats;
import com.twistral.toriamath.utils.TMath;
import com.twistral.toriautils.arrayref.oned.ArrayRef;
import com.twistral.toriautils.arrayref.oned.*;
import org.junit.jupiter.api.Assertions;
import java.util.Arrays;
import java.util.Random;


public class Test {

    static Random random = new Random();
    static final int BOUND = 30_000;
    static final int SIZE_BOUND = 1000;
    static final int TIMES = 100000;

    static Integer[] getRandomInteger(){
        Integer[] arr = new Integer[random.nextInt(SIZE_BOUND)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(BOUND);
        }
        return arr;
    }
    static Float[] getRandomFloat(){
        Float[] arr = new Float[random.nextInt(SIZE_BOUND)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextFloat() * BOUND;
        }
        return arr;
    }
    static Double[] getRandomDouble(){
        Double[] arr = new Double[random.nextInt(SIZE_BOUND)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextDouble() * BOUND;
        }
        return arr;
    }
    static Long[] getRandomLong(){
        Long[] arr = new Long[random.nextInt(SIZE_BOUND)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextLong() % BOUND;
        }
        return arr;
    }
    static int[] getRandomint(){
        int[] arr = new int[random.nextInt(SIZE_BOUND)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(BOUND);
        }
        return arr;
    }
    static float[] getRandomfloat(){
        float[] arr = new float[random.nextInt(SIZE_BOUND)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextFloat() * BOUND;
        }
        return arr;
    }
    static double[] getRandomdouble(){
        double[] arr = new double[random.nextInt(SIZE_BOUND)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextDouble() * BOUND;
        }
        return arr;
    }
    static long[] getRandomlong(){
        long[] arr = new long[random.nextInt(SIZE_BOUND)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextLong() % BOUND;
        }
        return arr;
    }


    static double getSum(NumericArray arr){
        double sum = 0d;
        for (int i = 0; i < arr.getLength(); i++) {
            sum += arr.getValue(i);
        }
        return sum;
    }

    static double getSum(ArrayRef arr){
        double sum = 0d;
        for (int i = 0; i < arr.getSize(); i++) {
            sum += arr.getValue(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        double[] numericTime = new double[TIMES];
        double[] arrayRefTime = new double[TIMES];

        for (int index = 0; index < TIMES; index++) {
            Integer[] arr1 = getRandomInteger();
            Float[] arr2 = getRandomFloat();
            Double[] arr3 = getRandomDouble();
            Long[] arr4 = getRandomLong();
            int[] arr5 = getRandomint();
            float[] arr6 = getRandomfloat();
            double[] arr7 = getRandomdouble();
            long[] arr8 = getRandomlong();
            double len11, len12, len13, len14, len15, len16, len17, len18;
            double len21, len22, len23, len24, len25, len26, len27, len28;
            double sum11, sum12, sum13, sum14, sum15, sum16, sum17, sum18;
            double sum21, sum22, sum23, sum24, sum25, sum26, sum27, sum28;

            long s1 = System.nanoTime();
            // code
            ArrayRef ref1 = new NumberArrRef(arr1);
            ArrayRef ref2 = new NumberArrRef(arr2);
            ArrayRef ref3 = new NumberArrRef(arr3);
            ArrayRef ref4 = new NumberArrRef(arr4);
            ArrayRef ref5 = new IntArrRef(arr5);
            ArrayRef ref6 = new FloatArrRef(arr6);
            ArrayRef ref7 = new DoubleArrRef(arr7);
            ArrayRef ref8 = new LongArrRef(arr8);
            len11 = ref1.getSize();
            sum11 = getSum(ref1);
            len12 = ref2.getSize();
            sum12 = getSum(ref2);
            len13 = ref3.getSize();
            sum13 = getSum(ref3);
            len14 = ref4.getSize();
            sum14 = getSum(ref4);
            len15 = ref5.getSize();
            sum15 = getSum(ref5);
            len16 = ref6.getSize();
            sum16 = getSum(ref6);
            len17 = ref7.getSize();
            sum17 = getSum(ref7);
            len18 = ref8.getSize();
            sum18 = getSum(ref8);
            // code
            long e1 = System.nanoTime();
            arrayRefTime[index] = (e1 - s1) / 1000000d;
            long s2 = System.nanoTime();
            // code
            NumericArray numeric1 = new NumericArray(arr1);
            NumericArray numeric2 = new NumericArray(arr2);
            NumericArray numeric3 = new NumericArray(arr3);
            NumericArray numeric4 = new NumericArray(arr4);
            NumericArray numeric5 = new NumericArray(arr5);
            NumericArray numeric6 = new NumericArray(arr6);
            NumericArray numeric7 = new NumericArray(arr7);
            NumericArray numeric8 = new NumericArray(arr8);
            len21 = numeric1.getLength();
            sum21 = getSum(numeric1);
            len22 = numeric2.getLength();
            sum22 = getSum(numeric2);
            len23 = numeric3.getLength();
            sum23 = getSum(numeric3);
            len24 = numeric4.getLength();
            sum24 = getSum(numeric4);
            len25 = numeric5.getLength();
            sum25 = getSum(numeric5);
            len26 = numeric6.getLength();
            sum26 = getSum(numeric6);
            len27 = numeric7.getLength();
            sum27 = getSum(numeric7);
            len28 = numeric8.getLength();
            sum28 = getSum(numeric8);
            // code
            long e2 = System.nanoTime();
            numericTime[index] = (e2 - s2) / 1000000d;

            // assertion
            Assertions.assertTrue(TMath.areEqual(len11, len21));
            Assertions.assertTrue(TMath.areEqual(len12, len22));
            Assertions.assertTrue(TMath.areEqual(len13, len23));
            Assertions.assertTrue(TMath.areEqual(len14, len24));
            Assertions.assertTrue(TMath.areEqual(len15, len25));
            Assertions.assertTrue(TMath.areEqual(len16, len26));
            Assertions.assertTrue(TMath.areEqual(len17, len27));
            Assertions.assertTrue(TMath.areEqual(len18, len28));
            Assertions.assertTrue(TMath.areEqual(sum11, sum21));
            Assertions.assertTrue(TMath.areEqual(sum12, sum22));
            Assertions.assertTrue(TMath.areEqual(sum13, sum23));
            Assertions.assertTrue(TMath.areEqual(sum14, sum24));
            Assertions.assertTrue(TMath.areEqual(sum15, sum25));
            Assertions.assertTrue(TMath.areEqual(sum16, sum26));
            Assertions.assertTrue(TMath.areEqual(sum17, sum27));
            Assertions.assertTrue(TMath.areEqual(sum18, sum28));
        }

        Arrays.sort(numericTime);
        Arrays.sort(arrayRefTime);
        DataDescription numDesc = DescStats.getDataDesc(numericTime);
        DataDescription refDesc = DescStats.getDataDesc(arrayRefTime);
        System.out.println("numDesc: " +  numDesc);
        System.out.println("refDesc: " + refDesc);
        System.out.println("numDesc: " +  numDesc.sum + " ms");
        System.out.println("refDesc: " + refDesc.sum + " ms");
    }


}
