package com.telek.applications;

import com.telek.telekmath.advanced.statistics.descriptive.*;
import com.telek.telekutils.containers.TArrays;
import java.util.Random;


public class OptimalStoppingProblem {

    // O(PROBLEM_SIZE^2 * ARRAY_SIZE)
    static final int PROBLEM_SIZE = 500;
    static final double ARRAY_SIZE = 100_000;


    public static void main(String[] arg){
        Random random = new Random();

        double[] allScores = new double[PROBLEM_SIZE];
        int index = 0;

        for(int i = 1; i < PROBLEM_SIZE; i++){
            double success = 0;
            for(int j = 1; j < ARRAY_SIZE; j++){

                double best = Double.MIN_VALUE;
                double[] candidates = new double[PROBLEM_SIZE];
                for(int k = 0; k < PROBLEM_SIZE; k++){
                    double value = random.nextDouble();
                    if(best < value)
                        best = value;
                    candidates[k] = value;
                }

                boolean solved = false;
                boolean didBreak = false;
                double bestUpUntilNow = Double.MIN_VALUE;
                for(int t = 0; t < candidates.length; t++){
                    if(candidates[t] > bestUpUntilNow) {
                        bestUpUntilNow = candidates[t];
                        if(t > i){
                            solved = bestUpUntilNow == best;
                            didBreak = true;
                            break;
                        }
                    }
                }
                if(!didBreak)
                    solved = candidates[candidates.length-1] == best;
                if(solved)
                    success++;
            }
            allScores[index] = success / ARRAY_SIZE;
            index++;
        }

        DataDescription desc = DescStats.getDataDesc(TArrays.getSortedCopy(allScores));
        System.out.println(desc.max);
    }


}
