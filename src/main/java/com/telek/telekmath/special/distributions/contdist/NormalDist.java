package com.telek.telekmath.special.distributions.contdist;


import com.telek.telekmath.TMath;
import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.special.distributions.ContinuousDistribution;
import static com.telek.telekmath.exceptions.TelekMathException.*;


public class NormalDist extends ContinuousDistribution {

    private double mean, sigma;


    public NormalDist(double mean, double sigma){
        if( sigma <= 0 ) throw new NotGreaterThanZeroException("Sigma");
        this.mean = mean;
        this.sigma = sigma;
        this.E = mean;
        this.E2 = mean * mean + sigma * sigma;
        this.Var = sigma * sigma;
    }


    /**  Standard Normal Distribution  */
    public NormalDist(){
        this(0d, 1d);
    }


    /*  METHODS  */

    @Override
    public double probability(double x) {
        return ( 1d / ( sigma * Math.sqrt(2d * TMathConstants.PI) ) ) * Math.exp( (-0.5d) * ( (x-mean) / sigma ) * ( (x-mean) / sigma ) );
    }



}
