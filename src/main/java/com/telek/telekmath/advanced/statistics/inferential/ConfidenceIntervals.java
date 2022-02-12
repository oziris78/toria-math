package com.telek.telekmath.advanced.statistics.inferential;

import com.telek.telekmath.advanced.distributions.cont.ChiSquaredDist;
import com.telek.telekmath.advanced.distributions.cont.TDist;
import com.telek.telekmath.advanced.distributions.cont.ZDist;
import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.utils.TelekMathException.*;

public class ConfidenceIntervals {



    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    ///////////////////   FOR SINGLE VARIABLES   //////////////////
    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////


    /* -------------------------------- MEAN -------------------------------- */


    /**  @see #getIntervalForMean(DataDescription, double, double)   */
    public static TRange getIntervalForMean(double sampleCount, double sampleMean, double populationStddev, double alpha) {
        // error checking
        checkForAlpha(alpha);
        checkForStddev(populationStddev);
        // code
        double sigmaOverSqrtN = populationStddev / TMath.sqrt(sampleCount);

        double z = ZDist.invCumRightTailed(0.5d - (alpha / 2d));
        return new TRange(sampleMean - z * sigmaOverSqrtN,  sampleMean + z * sigmaOverSqrtN);
    }


    /**  @see #getIntervalForMean(DataDescription, boolean, double)  */
    public static TRange getIntervalForMean(double sampleCount, double sampleMean, double sampleStddev,
                                            boolean isNormallyDist, double alpha) {
        // error checking
        checkForAlpha(alpha);
        // code
        double sigmaOverSqrtN = sampleStddev / TMath.sqrt(sampleCount);

        if(isNormallyDist){
            double t = TDist.invCumLeftTailed(sampleCount - 1, 1d - alpha / 2d); // t_n-1_alpha/2
            return new TRange(sampleMean - t * sigmaOverSqrtN,  sampleMean + t * sigmaOverSqrtN);
        }
        else{
            double z = ZDist.invCumRightTailed(0.5d - (alpha / 2d));
            return new TRange(sampleMean - z * sigmaOverSqrtN,  sampleMean + z * sigmaOverSqrtN);
        }
    }


    /**
     * Returns the confidence interval (CI) for the population's mean when the population's standard deviation is known. <br>
     * The sample can be distributed with any distribution (normal distribution etc.).
     * @param sampleDesc the sample's DataDescription object
     * @param populationStddev the population's known standard deviation
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return The confidence interval (CI) for the population mean
     */
    public static TRange getIntervalForMean(DataDescription sampleDesc, double populationStddev, double alpha) {
        return getIntervalForMean(sampleDesc.count, sampleDesc.mean, populationStddev, alpha);
    }


    /**
     * Returns the confidence interval (CI) for the population's mean when the population's standard deviation is unknown. <br>
     * You will need to specify the distribution type with the `isNormallyDist` parameter. <br>
     * If you don't know the distribution type then just enter false.
     * @param sampleDesc the sample's DataDescription object
     * @param isNormallyDist a boolean value specifying the distribution type, you need to enter true
     *                       if this sample is normally distributed, if you don't know what type of
     *                       distribution this data has then just enter false
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return The confidence interval (CI) for the population mean
     */
    public static TRange getIntervalForMean(DataDescription sampleDesc, boolean isNormallyDist, double alpha) {
        return getIntervalForMean(sampleDesc.count, sampleDesc.mean, sampleDesc.sampleStddev, isNormallyDist, alpha);
    }


    /* ------------------------------ VARIANCE ------------------------------ */


    /**
     * Returns the confidence interval (CI) for the population's variance.
     * @param sampleDesc the sample's DataDescription object
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return The confidence interval (CI) for the population variance
     */
    public static TRange getIntervalForVariance(DataDescription sampleDesc, double alpha){
        return getIntervalForVariance(sampleDesc.count, sampleDesc.sampleVariance, alpha);
    }


    /**  @see #getIntervalForVariance(DataDescription, double)  */
    public static TRange getIntervalForVariance(double sampleCount, double sampleVariance, double alpha){
        // error checking
        checkForAlpha(alpha);
        // code
        double nss = sampleVariance * (sampleCount - 1); // (n-1)S^2
        double lChi = ChiSquaredDist.invCumLeftTailed(sampleCount - 1, 1d - alpha / 2d);
        double uChi = ChiSquaredDist.invCumLeftTailed(sampleCount - 1, alpha / 2d);
        return new TRange(nss / lChi, nss / uChi);
    }


    /* ----------------------------- PROPORTIONS ---------------------------- */


    /**
     * Returns the confidence interval (CI) for the population's proportion according to the successes parameter.
     * @param sampleDesc the sample's DataDescription object
     * @param successes specifies how many successes are in this sample, the definition of a "success" may change
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return The confidence interval (CI) for the population proportion
     */
    public static TRange getIntervalForProportion(DataDescription sampleDesc, int successes, double alpha){
        return getIntervalForProportion(sampleDesc, successes / sampleDesc.count, alpha);
    }


    /**
     * Returns the confidence interval (CI) for the population's proportion.
     * @param pHat rate of successes in this sample (definition of success may change)
     * @param sampleDesc the sample's DataDescription object
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return The confidence interval (CI) for the population proportion
     */
    public static TRange getIntervalForProportion(DataDescription sampleDesc, double pHat, double alpha){
        return getIntervalForProportion(sampleDesc.count, pHat, alpha);
    }


    /**  @see #getIntervalForProportion(DataDescription, double, double)  */
    public static TRange getIntervalForProportion(double sampleCount, double pHat, double alpha){
        // error checking
        checkForPHat(pHat);
        checkForAlpha(alpha);
        // code
        double error = ZDist.invCumRightTailed(0.5d - (alpha / 2d));
        error *= TMath.sqrt(pHat * (1d - pHat) / sampleCount);
        return new TRange(pHat - error, pHat + error);
    }



    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    /////////////////////   FOR TWO VARIABLES   ///////////////////
    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////







    ///////////////
    /*  HELPERS  */
    ///////////////


    private static void checkForPHat(double pHat) {
        if(!TRange.ZERO_TO_ONE.isInRange(pHat))
            throw new InvalidValueException("pHat", pHat);
    }

    private static void checkForAlpha(double alpha){
        if(!TRange.ZERO_TO_ONE.isInRange(alpha))
            throw new InvalidValueException("alpha", alpha);
    }

    private static void checkForStddev(double stddev){
        if(stddev < 0)
            throw new InvalidValueException("standard deviation", stddev);
    }




}
