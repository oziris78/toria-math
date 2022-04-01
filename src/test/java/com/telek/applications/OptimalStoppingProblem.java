package com.telek.applications;

import com.telek.telekmath.advanced.statistics.descriptive.*;
import com.telek.telekutils.containers.TArrays;

import java.util.Arrays;
import java.util.Random;


public class OptimalStoppingProblem {

    // O(PROBLEM_SIZE^2 * ARRAY_SIZE)
    static final int PROBLEM_SIZE = 100;
    static final double SAMPLE_SIZE = 100_000;


    public static void main(String[] arg){
        Random random = new Random();

        double[] allScores = new double[PROBLEM_SIZE];
        int index = 0;

        for(int i = 1; i < PROBLEM_SIZE; i++){
            double success = 0;
            for(int j = 1; j < SAMPLE_SIZE; j++){
                // get random data
                double[] candidates = new double[PROBLEM_SIZE];
                for(int k = 0; k < PROBLEM_SIZE; k++)
                    candidates[k] = random.nextDouble();
                double best = TArrays.getMax(candidates);

                // analysis
                boolean foundTheBest = false;
                double bestUpUntilNow = Double.MIN_VALUE;
                for(int k = 0; k < candidates.length; k++){
                    if(candidates[k] > bestUpUntilNow) {
                        bestUpUntilNow = candidates[k];
                        if(k > i) {
                            if(bestUpUntilNow == best)
                                success++;
                            foundTheBest = true;
                            break;
                        }
                    }
                }

                // if you didnt find the best but the last one you're left with is the best, count a success
                if(!foundTheBest && candidates[candidates.length-1] == best)
                    success++;

            }
            allScores[index++] = success / SAMPLE_SIZE;
        }

        Arrays.sort(allScores);
        System.out.println(DescStats.getMax(allScores));
    }


}
