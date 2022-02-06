package com.telek.telekmath.advanced.distributions.discrete;


import com.telek.telekmath.exceptions.InvalidValueException;


public class DiscreteUniformDist {

    private DiscreteUniformDist(){}


    ///////////////
    /*  METHODS  */
    ///////////////


    public static double probability(int n){
        if( n < 1 ) throw new InvalidValueException("n", n);
        return 1d / n;
    }

    public static double expectedValue(int n){
        return (n+1)/2;
    }

    public static double variance(int n){
        return (n * n - 1 ) / 12;
    }




}