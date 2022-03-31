package com.telek.telekmath.advanced.random;

import com.telek.telekutils.containers.TCollections;

import java.util.ArrayList;

public class TRandom {

    //////////////
    /*  FIELDS  */
    //////////////

    /** Defines how many times the linear-congruential generation algorithm will be iterated in each method */
    public static int LCG_DEFAULT_ITERATION_COUNT = 20;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    /* No constructor */
    private TRandom(){}


    ///////////////
    /*  METHODS  */
    ///////////////

    /**
     * Returns a random number in range [0, M] using linear-congruential generation algorithm.
     * M value should be a prime for good generations
     * @param a a constant number to multiply
     * @param seed your seed
     * @param c constant number to add
     * @param M a constant number take the modulus, this value should be a prime for good results
     * @param iterationCount how many times this process will be applied
     * @return a random number in range [0,M]
     */
    public static int linearCongruential(int a, int seed, int c, int M, int iterationCount){
        int result = seed;
        for (int i = 0; i < iterationCount; i++) {
            result = (a * result + c) % M;
        }
        return result;
    }




    /**
     * Returns a random number in range [0, 2^31-1] using linear-congruential generation algorithm.
     * Uses Park and Miller numbers.  a = 16807, M = 2^31-1 (mersenne prime).
     * Also uses {@link #LCG_DEFAULT_ITERATION_COUNT} as iterationCount
     * @param seed your seed
     * @param c constant number to add
     * @return a random number in range [0, 2^31-1]
     */
    public static int linearCongruential(int seed, int c){
        return linearCongruential(16807, seed, c, 214748647, LCG_DEFAULT_ITERATION_COUNT);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Returns the generated steps using the number seed, if number is even then do x/2, if odd then do 3x+1
     * @param seed any number to start
     * @return the steps as a int array
     */
    public static int[] getStepsForCollatzConjecture(int seed){
        ArrayList<Integer> steps = new ArrayList<>();
        steps.add(seed);
        while(seed != 1){
            if(seed % 2 == 0) seed /= 2;
            else seed = 3 * seed + 1;
            steps.add(seed);
        }
        return TCollections.collectionToArray(steps);
    }





}
