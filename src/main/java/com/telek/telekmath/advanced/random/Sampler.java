package com.telek.telekmath.advanced.random;


import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.utils.TelekMathException.*;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Random;



/**
 * Uses different algorithms to pick random values from an array
 * and returns a new array containing those randomly selected values.
 */
public class Sampler {

    private static Random random = new Random();

    /* No constructor */
    private Sampler(){}


    ///////////////////////
    /*  GENERIC METHODS  */
    ///////////////////////


    /**
     * Randomly picks values from the population array and puts it into the sample.
     * @param sampleSize sample's size
     * @return sample array
     */
    public static <T> T[] simpleRandomSampling(T[] population, Class<T> clazz, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        T[] sample = (T[]) Array.newInstance(clazz, sampleSize);

        // using hashmap for performance purposes, the values won't be used
        HashMap<Integer, Integer> chosenIndexes = new HashMap<>();

        while(chosenIndexes.size() != sampleSize){
            int randomIndex = random.nextInt(N);
            if(!chosenIndexes.containsKey(randomIndex)){
                sample[chosenIndexes.size()] = population[randomIndex];
                chosenIndexes.put(randomIndex, 0);
            }
        }

        return sample;
    }


    /**
     * Picks a random index and than takes k values starting from that index and puts those terms into the sample
     * @param sampleSize sample's size
     * @return sample array
     */
    public static <T> T[] systematicSampling(T[] population, Class<T> clazz, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        T[] sample = (T[]) Array.newInstance(clazz, sampleSize);

        int randi = random.nextInt(N);

        if(N - randi < sampleSize){ // we need to wrap this around
            int index = 0;
            for (int i = 0; i < N - randi; i++)
                sample[index++] = population[randi + i];

            for (int i = 0; i < sampleSize + randi - N; i++)
                sample[index++] = population[i];
        }
        else{
            for (int i = 0, index = 0; i < sampleSize; i++)
                sample[index++] = population[randi + i];
        }

        return sample;
    }


    /**
     * Divides the population into sampleSize clusters and choses a random term
     * from each cluster and adds it to the sample array.
     * @param sampleSize sample's size
     * @return sample array
     */
    public static <T> T[] clusterSampling(T[] population, Class<T> clazz, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        T[] sample = (T[]) Array.newInstance(clazz, sampleSize);

        TRange[] clusters = new TRange[sampleSize];
        int clusterTermCount = (int) Math.floor(N / sampleSize); // x = 3,

        int j = 0;
        for (int i = 0; i < clusters.length; i++) {
            clusters[i] = new TRange(j, j + clusterTermCount - 1); // 0, 2
            j += clusterTermCount;
            clusterTermCount = (i % 2 == 0) ? clusterTermCount + 1 : clusterTermCount - 1 ;
        }
        TRange lastRange = clusters[clusters.length - 1];
        if(lastRange.right != N - 1)
            clusters[clusters.length - 1] = new TRange(lastRange.left, N - 1);

        int sampleIndex = 0;
        for(TRange range : clusters){
            int index = random.nextInt((int) (range.right + 1d - range.left)) + (int) range.left;
            sample[sampleIndex++] = population[index];
        }

        return sample;
    }


    /////////////////////////
    /*  PRIMITIVE METHODS  */
    /////////////////////////

    public static int[] simpleRandomSampling(int[] population, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        int[] sample = new int[sampleSize];

        // using hashmap for performance purposes, the values won't be used
        HashMap<Integer, Integer> chosenIndexes = new HashMap<>();

        while(chosenIndexes.size() != sampleSize){
            int randomIndex = random.nextInt(N);
            if(!chosenIndexes.containsKey(randomIndex)){
                sample[chosenIndexes.size()] = population[randomIndex];
                chosenIndexes.put(randomIndex, 0);
            }
        }

        return sample;
    }


    public static int[] systematicSampling(int[] population, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        int[] sample = new int[sampleSize];

        int randi = random.nextInt(N);

        if(N - randi < sampleSize){ // we need to wrap this around
            int index = 0;
            for (int i = 0; i < N - randi; i++)
                sample[index++] = population[randi + i];

            for (int i = 0; i < sampleSize + randi - N; i++)
                sample[index++] = population[i];
        }
        else{
            for (int i = 0, index = 0; i < sampleSize; i++)
                sample[index++] = population[randi + i];
        }

        return sample;
    }


    public static int[] clusterSampling(int[] population, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        int[] sample = new int[sampleSize];

        TRange[] clusters = new TRange[sampleSize];
        int clusterTermCount = (int) Math.floor(N / sampleSize); // x = 3,

        int j = 0;
        for (int i = 0; i < clusters.length; i++) {
            clusters[i] = new TRange(j, j + clusterTermCount - 1); // 0, 2
            j += clusterTermCount;
            clusterTermCount = (i % 2 == 0) ? clusterTermCount + 1 : clusterTermCount - 1 ;
        }
        TRange lastRange = clusters[clusters.length - 1];
        if(lastRange.right != N - 1)
            clusters[clusters.length - 1] = new TRange(lastRange.left, N - 1);

        int sampleIndex = 0;
        for(TRange range : clusters){
            int index = random.nextInt((int) (range.right + 1d - range.left)) + (int) range.left;
            sample[sampleIndex++] = population[index];
        }

        return sample;
    }

    //////////////

    public static float[] simpleRandomSampling(float[] population, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        float[] sample = new float[sampleSize];

        // using hashmap for performance purposes, the values won't be used
        HashMap<Integer, Integer> chosenIndexes = new HashMap<>();

        while(chosenIndexes.size() != sampleSize){
            int randomIndex = random.nextInt(N);
            if(!chosenIndexes.containsKey(randomIndex)){
                sample[chosenIndexes.size()] = population[randomIndex];
                chosenIndexes.put(randomIndex, 0);
            }
        }

        return sample;
    }


    public static float[] systematicSampling(float[] population, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        float[] sample = new float[sampleSize];

        int randi = random.nextInt(N);

        if(N - randi < sampleSize){ // we need to wrap this around
            int index = 0;
            for (int i = 0; i < N - randi; i++)
                sample[index++] = population[randi + i];

            for (int i = 0; i < sampleSize + randi - N; i++)
                sample[index++] = population[i];
        }
        else{
            for (int i = 0, index = 0; i < sampleSize; i++)
                sample[index++] = population[randi + i];
        }

        return sample;
    }


    public static float[] clusterSampling(float[] population, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        float[] sample = new float[sampleSize];

        TRange[] clusters = new TRange[sampleSize];
        int clusterTermCount = (int) Math.floor(N / sampleSize); // x = 3,

        int j = 0;
        for (int i = 0; i < clusters.length; i++) {
            clusters[i] = new TRange(j, j + clusterTermCount - 1); // 0, 2
            j += clusterTermCount;
            clusterTermCount = (i % 2 == 0) ? clusterTermCount + 1 : clusterTermCount - 1 ;
        }
        TRange lastRange = clusters[clusters.length - 1];
        if(lastRange.right != N - 1)
            clusters[clusters.length - 1] = new TRange(lastRange.left, N - 1);

        int sampleIndex = 0;
        for(TRange range : clusters){
            int index = random.nextInt((int) (range.right + 1d - range.left)) + (int) range.left;
            sample[sampleIndex++] = population[index];
        }

        return sample;
    }

    //////////////

    public static double[] simpleRandomSampling(double[] population, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        double[] sample = new double[sampleSize];

        // using hashmap for performance purposes, the values won't be used
        HashMap<Integer, Integer> chosenIndexes = new HashMap<>();

        while(chosenIndexes.size() != sampleSize){
            int randomIndex = random.nextInt(N);
            if(!chosenIndexes.containsKey(randomIndex)){
                sample[chosenIndexes.size()] = population[randomIndex];
                chosenIndexes.put(randomIndex, 0);
            }
        }

        return sample;
    }

    public static double[] systematicSampling(double[] population, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        double[] sample = new double[sampleSize];

        int randi = random.nextInt(N);

        if(N - randi < sampleSize){ // we need to wrap this around
            int index = 0;
            for (int i = 0; i < N - randi; i++)
                sample[index++] = population[randi + i];

            for (int i = 0; i < sampleSize + randi - N; i++)
                sample[index++] = population[i];
        }
        else{
            for (int i = 0, index = 0; i < sampleSize; i++)
                sample[index++] = population[randi + i];
        }

        return sample;
    }

    public static double[] clusterSampling(double[] population, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        double[] sample = new double[sampleSize];

        TRange[] clusters = new TRange[sampleSize];
        int clusterTermCount = (int) Math.floor(N / sampleSize); // x = 3,

        int j = 0;
        for (int i = 0; i < clusters.length; i++) {
            clusters[i] = new TRange(j, j + clusterTermCount - 1); // 0, 2
            j += clusterTermCount;
            clusterTermCount = (i % 2 == 0) ? clusterTermCount + 1 : clusterTermCount - 1 ;
        }
        TRange lastRange = clusters[clusters.length - 1];
        if(lastRange.right != N - 1)
            clusters[clusters.length - 1] = new TRange(lastRange.left, N - 1);

        int sampleIndex = 0;
        for(TRange range : clusters){
            int index = random.nextInt((int) (range.right + 1d - range.left)) + (int) range.left;
            sample[sampleIndex++] = population[index];
        }

        return sample;
    }

    //////////////

    public static long[] simpleRandomSampling(long[] population, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        long[] sample = new long[sampleSize];

        // using hashmap for performance purposes, the values won't be used
        HashMap<Integer, Integer> chosenIndexes = new HashMap<>();

        while(chosenIndexes.size() != sampleSize){
            int randomIndex = random.nextInt(N);
            if(!chosenIndexes.containsKey(randomIndex)){
                sample[chosenIndexes.size()] = population[randomIndex];
                chosenIndexes.put(randomIndex, 0);
            }
        }

        return sample;
    }

    public static long[] systematicSampling(long[] population, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        long[] sample = new long[sampleSize];

        int randi = random.nextInt(N);

        if(N - randi < sampleSize){ // we need to wrap this around
            int index = 0;
            for (int i = 0; i < N - randi; i++)
                sample[index++] = population[randi + i];

            for (int i = 0; i < sampleSize + randi - N; i++)
                sample[index++] = population[i];
        }
        else{
            for (int i = 0, index = 0; i < sampleSize; i++)
                sample[index++] = population[randi + i];
        }

        return sample;
    }

    public static long[] clusterSampling(long[] population, int sampleSize) {
        checkForSampleSize(population, sampleSize); // error checking

        final int N = population.length; // population size
        long[] sample = new long[sampleSize];

        TRange[] clusters = new TRange[sampleSize];
        int clusterTermCount = (int) Math.floor(N / sampleSize); // x = 3,

        int j = 0;
        for (int i = 0; i < clusters.length; i++) {
            clusters[i] = new TRange(j, j + clusterTermCount - 1); // 0, 2
            j += clusterTermCount;
            clusterTermCount = (i % 2 == 0) ? clusterTermCount + 1 : clusterTermCount - 1 ;
        }
        TRange lastRange = clusters[clusters.length - 1];
        if(lastRange.right != N - 1)
            clusters[clusters.length - 1] = new TRange(lastRange.left, N - 1);

        int sampleIndex = 0;
        for(TRange range : clusters){
            int index = random.nextInt((int) (range.right + 1d - range.left)) + (int) range.left;
            sample[sampleIndex++] = population[index];
        }

        return sample;
    }


    ///////////////
    /*  HELPERS  */
    ///////////////

    private static <T> void checkForSampleSize(T[] population, int sampleSize){
        if(sampleSize >= population.length || sampleSize <= 0)
            throw new SampleSizeIsGreaterThanPopulationSizeException();
    }

    private static void checkForSampleSize(int[] population, int sampleSize){
        if(sampleSize >= population.length || sampleSize <= 0)
            throw new SampleSizeIsGreaterThanPopulationSizeException();
    }

    private static void checkForSampleSize(float[] population, int sampleSize){
        if(sampleSize >= population.length || sampleSize <= 0)
            throw new SampleSizeIsGreaterThanPopulationSizeException();
    }

    private static void checkForSampleSize(double[] population, int sampleSize){
        if(sampleSize >= population.length || sampleSize <= 0)
            throw new SampleSizeIsGreaterThanPopulationSizeException();
    }

    private static void checkForSampleSize(long[] population, int sampleSize){
        if(sampleSize >= population.length || sampleSize <= 0)
            throw new SampleSizeIsGreaterThanPopulationSizeException();
    }



}
