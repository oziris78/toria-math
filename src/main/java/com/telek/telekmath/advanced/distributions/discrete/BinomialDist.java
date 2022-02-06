package com.telek.telekmath.advanced.distributions.discrete;


import com.telek.telekmath.exceptions.InvalidValueException;
import com.telek.telekmath.TMath;


public class BinomialDist {

    private BinomialDist(){}


    ///////////////
    /*  METHODS  */
    ///////////////

    public static double probability(int n, double p, int x){
        checkForInput(n, p);
        if( x < 0 || x > n ) return 0;
        return TMath.combination(n,x) * Math.pow(p,x) * Math.pow(1-p, n-x);
    }


    public static double expectedValue(int n, double p){
        return n * p;
    }


    public static double variance(int n, double p){
        return n * p * (1-p);
    }


    ///////////////
    /*  HELPERS  */
    ///////////////


    private static void checkForInput(int n, double p){
        if( n < 1 ) throw new InvalidValueException("n", n);
        if( !(p >= 0 && p <= 1) ) throw new InvalidValueException("p", p);
    }




}
