package com.telek.telekmath.special.distributions.continuousdists;


import com.telek.telekmath.special.distributions.Experiment;
import static com.telek.telekmath.exceptions.TelekMathException.*;

public class NormalDist extends Experiment  {

    private double mean, sigma;


    public NormalDist(double mean, double sigma){
        if( sigma <= 0 ) throw new NotGreaterThanZeroException("Sigma");
        this.mean = mean;
        this.sigma = sigma;
        this.setE(calculateE(mean, sigma));
        this.setE2(calculateE_2(mean, sigma));
        this.setVar(calculateVAR(mean, sigma));
    }

    // Standard Normal Dist
    public NormalDist(){
        this.mean = standardDistMean();
        this.sigma = standardDistSigma();
        this.setE(calculateE(this.mean, this.sigma));
        this.setE2(calculateE_2(this.mean, this.sigma));
        this.setVar(calculateVAR(this.mean, this.sigma));
    }


    /* Methods */

    public double probability(double x) {
        return ( 1 / ( sigma * Math.sqrt(2 * Math.PI) ) ) * Math.exp( (-1/2) * ( (x-mean) / sigma ) * ( (x-mean) / sigma ) );
    }

    /* Helpers */

    private static double calculateE(double mean, double sigma){
        return mean;
    }

    private static double calculateE_2(double mean, double sigma){
        return mean * mean + sigma * sigma;
    }

    private static double calculateVAR(double mean, double sigma){
        return sigma * sigma;
    }

    private static double standardDistMean(){ return 0; }

    private static double standardDistSigma(){ return 1; }


}
