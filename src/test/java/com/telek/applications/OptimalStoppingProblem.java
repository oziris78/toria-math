package com.telek.applications;


import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekutils.containers.TArrays;

import javax.xml.crypto.Data;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OptimalStoppingProblem {

    static Random random = new Random();
    static int MIN_ARRAY_SIZE = 10000;
    static int MIN_ARRAY_VALUE = 100;
    static int TEST_COUNT_PER_PERCENTAGE = 1000;

    /*
    I need to implement a time system
     */

    public static void main(String[] args) {

        HashMap<Integer, Double> scores = new HashMap<>();
        for (int i = 1; i < 100; i++) {
            scores.put(i, getScoreOfManyTests(i));
        }
        System.out.println(scores);
        double maxVal = -1;
        int index = -1;
        for(Map.Entry<Integer,Double> entry : scores.entrySet()){
            if(entry.getValue().doubleValue() > maxVal)
                index = entry.getKey().intValue();
        }
        System.out.println(index);


    }


    public static double getScoreOfManyTests(int lookPercentage){
        double[] scores = new double[TEST_COUNT_PER_PERCENTAGE];
        for (int i = 0; i < TEST_COUNT_PER_PERCENTAGE; i++) {
            scores[i] = testFor(lookPercentage);
        }
        scores = TArrays.getSortedCopy(scores);
        DataDescription scoreDesc = DescStats.getDataDesc(scores);
        return scoreDesc.mean;
    }



    private static double testFor(int lookPercentage){
        // create a random data set
        final int size = MIN_ARRAY_SIZE + random.nextInt(MIN_ARRAY_SIZE);
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = MIN_ARRAY_VALUE + random.nextInt(MIN_ARRAY_VALUE);
        }

        // get the actual results
        DataDescription actualDesc = DescStats.getDataDesc(TArrays.getSortedCopy(arr));
        double mean = actualDesc.mean;
        double max = actualDesc.max;

        /* find out what happens when you take a look at first lookChance% of data
        and then take the first value that's better than your observed mean */
        int howManyTerms = lookPercentage * size / 100;

        double[] observed = new double[howManyTerms];
        for (int i = 0; i < howManyTerms; i++) {
            observed[i] = arr[i];
        }
        DataDescription observedDesc = DescStats.getDataDesc(TArrays.getSortedCopy(observed));
        double observedMean = observedDesc.mean;
        double foundValue = -1;
        for (int i = howManyTerms; i < size; i++) {
            if(arr[i] >= observedMean){
                foundValue = arr[i];
                break;
            }
        }
        if(foundValue == -1){
            System.out.println("not found");
            System.out.println(lookPercentage);
            System.out.println("arr: " + TArrays.toString(arr));
            System.out.println("observed: " + TArrays.toString(observed));
            System.out.println("max: " + max);
            System.out.println("mean: " + mean);
            System.out.println("observedMean: " + observedMean);
        }

        // lets say max = 100, mean = 50 and we found = 70

        // how better our find could be? 100-50 = 50, we could get +50 points at max
        double maxBetterRate = max - mean;
        // how better was our find? 70 - 50 = 20, we got +20 points
        double howBetterItWas = foundValue - mean;
        // our final score, we could get 50 at max and we got 20 so 20/50 = 0.4 score
        double score = howBetterItWas / maxBetterRate;

        return score;
    }


}
