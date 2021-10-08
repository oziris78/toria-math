package com.telek.telekmath.special.distributions.continuousdists;


import com.telek.telekmath.special.distributions.Experiment;

public class ContinuousUniformDist extends Experiment  {

    private double a, b;

    public ContinuousUniformDist(double a, double b){
        this.a = a;
        this.b = b;
        this.setE(calculateE(a,b));
        this.setE2(calculateE_2(a,b));
        this.setVar(calculateVAR(a,b));
    }


    /* Methods */

    public double probability(double x) {
        if( !( a <= x && x <= b ) ) return 0;
        return 1 / ( b - a ) ;
    }

    /* Helpers */

    private static double calculateE(double a, double b) {
        return (a+b)/2;
    }

    private static double calculateE_2(double a, double b) {
        return  ((b*b*b) - (a*a*a)) / ( 3 * (b-a) );
    }

    private static double calculateVAR(double a, double b) {
        return (b-a) * (b-a) / 12;
    }


}
