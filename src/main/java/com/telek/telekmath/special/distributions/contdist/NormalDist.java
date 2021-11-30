package com.telek.telekmath.special.distributions.contdist;


import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.special.distributions.ContinuousDistribution;
import static com.telek.telekmath.exceptions.TelekMathException.*;


public class NormalDist extends ContinuousDistribution {

    public static final double SNORMAL_MEAN = 0d;
    public static final double SNORMAL_SIGMA = 1d;

    private double mean, sigma;


    public NormalDist(double mean, double sigma){
        if( sigma <= 0 ) throw new NotGreaterThanZeroException("Sigma");
        this.mean = mean;
        this.sigma = sigma;
        this.E = mean;
        this.Var = sigma * sigma;
    }


    /**  Standard Normal Distribution  */
    public NormalDist(){
        this(SNORMAL_MEAN, SNORMAL_SIGMA);
    }



}
