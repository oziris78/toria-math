package com.telek.telekmath.advanced.statistics;


import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.exceptions.TelekMathException.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;


public class Sampler<T> {

    /*
    Her şeyi static yap ve constructor'ı private yap
    T[], int[], float[], double[], long[] için metotlar yaz gerisi gereksiz
    * */

    private T[] population;
    private Class<T> clazz;

    private Random random;
    private final int N; // population size


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public Sampler(T[] population, Class<T> clazz){
        this.population = population;
        this.clazz = clazz;
        this.random = new Random();
        this.N = population.length;
    }


    ///////////////
    /*  METHODS  */
    ///////////////


    /**
     * Randomly picks values from the population array and puts it into the sample.
     * @param sampleSize sample's size
     * @return sample array
     */
    public T[] simpleRandomSampling(int sampleSize) {
        checkForSampleSize(sampleSize); // error checking

        T[] sample = (T[]) Array.newInstance(this.clazz, sampleSize);

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
    public T[] systematicSampling(int sampleSize) {
        checkForSampleSize(sampleSize); // error checking

        T[] sample = (T[]) Array.newInstance(this.clazz, sampleSize);

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
    public T[] clusterSampling(int sampleSize) {
        checkForSampleSize(sampleSize); // error checking

        T[] sample = (T[]) Array.newInstance(this.clazz, sampleSize);

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

    private void checkForSampleSize(int sampleSize){
        if(sampleSize >= N || sampleSize <= 0)
            throw new SampleSizeIsGreaterThanPopulationSizeException();
    }



}
