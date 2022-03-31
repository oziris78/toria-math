package com.telek.telekmath.advanced.random;


public class TRandom {

    //////////////
    /*  FIELDS  */
    //////////////

    /** Defines how many times the linear-congruential generation algorithm will be iterated in each method */
    public static int LCG_ITER_COUNT = 20;


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
    public static int lcg(int a, int seed, int c, int M){
        for (int i = 0; i < LCG_ITER_COUNT; i++)
            seed = (a * seed + c) % M;
        return seed;
    }


    /**
     * Returns a random number in range [0, 2^31-1] using linear-congruential generation algorithm.
     * Uses Park and Miller numbers.  a = 16807, M = 2^31-1 (mersenne prime).
     * Also uses {@link #LCG_ITER_COUNT} as iterationCount
     * @param seed your seed
     * @param c constant number to add
     * @return a random number in range [0, 2^31-1]
     */
    public static int lcg(int seed, int c){
        return lcg(16807, seed, c, 214748647);
    }





}
