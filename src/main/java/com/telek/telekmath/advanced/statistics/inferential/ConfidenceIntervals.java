package com.telek.telekmath.advanced.statistics.inferential;

import com.telek.telekmath.advanced.distributions.cont.*;
import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.utils.TelekMathException.*;



/**
 * A class that calculates mean, proportion and variance confidence intervals for one or two populations. <br>
 * For more information please look <a href="https://en.wikipedia.org/wiki/Confidence_interval">here</a>
 */
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
        checkForCount(sampleCount);
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
        checkForCount(sampleCount);
        checkForAlpha(alpha);
        checkForStddev(sampleStddev);
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
     * If the population is normally distributed, this method will return exact results. And if not, then it will return
     * approximate results.
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
     * If you don't know the distribution type then just enter false. <br>
     * If the population is normally distributed, this method will return exact results. And if not, it will return
     * approximate results using the {@link #getIntervalForMean(DataDescription, double, double)} method.
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
     * Returns the confidence interval (CI) for the population's variance. <br>
     * If the population is normally distributed, this method will return exact results.
     * And if not, then it will return approximate results.
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
        checkForCount(sampleCount);
        checkForVariance(sampleVariance);
        checkForAlpha(alpha);
        // code
        double nss = sampleVariance * (sampleCount - 1); // (n-1)S^2
        double lChi = ChiSquaredDist.invCumLeftTailed(sampleCount - 1, 1d - alpha / 2d);
        double uChi = ChiSquaredDist.invCumLeftTailed(sampleCount - 1, alpha / 2d);
        return new TRange(nss / lChi, nss / uChi);
    }


    /* ----------------------------- PROPORTIONS ---------------------------- */


    /**
     * Returns the confidence interval (CI) for the population's proportion according to the successes parameter. <br>
     * There are many methods to calculate the CI for population's proportion such as Jeffreys, Agresti-Coull,
     * Wilson, Clopper-Pearson exact and Normal approximation. <br>
     * This method uses Normal approximation to calculate the CI, meaning that it uses ZDist to determine it's
     * initial interval before calculating the error. <br>
     * Also if the population is normally distributed, this method will return exact results.
     * And if not, then it will return approximate results. <br>
     * To check if your samples are approximately normally distributed or not, you can use the
     * {@link #isApproximatelyNormallyDistributed(DataDescription, double)} method.
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
     * Returns the confidence interval (CI) for the population's proportion. <br>
     * @see #getIntervalForProportion(DataDescription, int, double)
     * @param pHat rate of successes in this sample (definition of success may change)
     * @param sampleDesc the sample's DataDescription object
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return The confidence interval (CI) for the population proportion
     */
    public static TRange getIntervalForProportion(DataDescription sampleDesc, double pHat, double alpha){
        return getIntervalForProportion(sampleDesc.count, pHat, alpha);
    }


    /**  @see #getIntervalForProportion(DataDescription, int, double)  */
    public static TRange getIntervalForProportion(double sampleCount, double pHat, double alpha){
        // error checking
        checkForCount(sampleCount);
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


    /* -------------------------------- MEAN -------------------------------- */


    /**
     * This method calculates the confidence interval for the difference of population means by using {@link ZDist}
     * class when the standard deviations of these populations are known. <br>
     * To be honest us knowing these populations' standard deviations are somewhat an unrealistic
     * assumption but sometimes this assumption is met and we can use this method to calculate confidence intervals. <br>
     * In statistics, in order to calculate this interval we make the assumption that both of
     * these populations are normally distributed. If they're not we won't get an exacty answer. <br>
     * But even if they're not normally distributed (or we just don't know what type of distributions they have)
     * we can still use this method to do an approximation by passing our samples' standard deviations as our
     * populations' standard deviation values.
     * @param desc1 first sample's description
     * @param desc2 second sample's description
     * @param populationStddev1 first population's (known) standard deviation
     * @param populationStddev2 second population's (known) standard deviation
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return The confidence interval (CI) for the difference of population means
     */
    public static TRange getIntervalForDifferenceOfMeans(DataDescription desc1, DataDescription desc2,
                                                         double populationStddev1, double populationStddev2,
                                                         double alpha)
    {
        return getIntervalForDifferenceOfMeans(desc1.count, desc2.count, desc1.mean, desc2.mean,
                populationStddev1, populationStddev2, alpha);
    }





    /**
     * This method calculates the confidence interval for the difference of population means when the
     * standard deviations of these populations are unknown. <br>
     * Unlike the {@link #getIntervalForDifferenceOfMeans(DataDescription, DataDescription, double, double, double)}
     * method this is a very likely thing to happen in real life when we have no idea about our populations'
     * standard deviations. <br>
     * In statistics, in order to do such calculations we need to assume that our populations are normally distributed. <br>
     * And that's why this method assumes that both populations are normally distributed. <br>
     * If your populations are not normally distributed, or if you simply have no idea about their distributions
     * you can use the {@link #getIntervalForDifferenceOfMeans(DataDescription, DataDescription, double, double, double)}
     * method to do an approximation about the confidence interval. For more information look at that method's javadocs. <br>
     * Most of the times in real life statistics when you calculate degrees of freedom as a non-integer value you
     * round it to get better and approximate results. This method does not do approximations for degrees of freedom
     * and uses real valued degrees of freedoms. For example if degrees of freedom is calculated as 10.751 you would
     * round this to 11 but this method does not do this and takes it as 10.751.
     * @param desc1 first sample's description
     * @param desc2 second sample's description
     * @param arePopulationVariancesAreAssumedToBeEqual a boolean specifying if these two populations have the same
     *                                                  variances or not, if you have no idea about this equality
     *                                                  just enter false
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return The confidence interval (CI) for the difference of population means
     */
    public static TRange getIntervalForDifferenceOfMeans(DataDescription desc1, DataDescription desc2,
                                                         boolean arePopulationVariancesAreAssumedToBeEqual,
                                                         double alpha)
    {
        return getIntervalForDifferenceOfMeans(
                desc1.count, desc2.count, desc1.mean, desc2.mean, desc1.sampleStddev, desc2.sampleStddev,
                arePopulationVariancesAreAssumedToBeEqual, alpha
        );
    }





    /*  --------------  */
    /*  KNOWN VARIANCE  */
    /*  --------------  */
    /**  @see #getIntervalForDifferenceOfMeans(DataDescription, DataDescription, double, double, double)  */
    public static TRange getIntervalForDifferenceOfMeans(double sampleCount1, double sampleCount2,
                                                         double sampleMean1, double sampleMean2,
                                                         double populationStddev1, double populationStddev2,
                                                         double alpha)
    {
        double xDiff = sampleMean1 - sampleMean2;
        double error = ZDist.invCumRightTailed(0.5d - (alpha / 2d));
        error *= TMath.sqrt((populationStddev1 * populationStddev1 / sampleCount1) +
                (populationStddev2 * populationStddev2 / sampleCount2));
        return new TRange(xDiff - error, xDiff + error);
    }




    /*  ----------------  */
    /*  UNKNOWN VARIANCE  */
    /*  ----------------  */
    /**  @see #getIntervalForDifferenceOfMeans(DataDescription, DataDescription, boolean, double)   */
    public static TRange getIntervalForDifferenceOfMeans(double sampleCount1, double sampleCount2,
                                                         double sampleMean1, double sampleMean2,
                                                         double sampleStddev1, double sampleStddev2,
                                                         boolean arePopulationVariancesAreAssumedToBeEqual,
                                                         double alpha)
    {
        double xDiff = sampleMean1 - sampleMean2;
        double sVar1 = sampleStddev1 * sampleStddev1;
        double sVar2 = sampleStddev2 * sampleStddev2;

        if(arePopulationVariancesAreAssumedToBeEqual){
            double v = sampleCount1 + sampleCount2 - 2;
            double sk2 = ((sampleCount1 - 1) * sVar1 + (sampleCount2 - 1) * sVar2) / v;
            double error = TDist.invCumLeftTailed(v, 1d - alpha / 2d); // t_n-1_alpha/2
            error *= TMath.sqrt(sk2) * TMath.sqrt( (1d / sampleCount1) + (1d / sampleCount2) );
            return new TRange(xDiff - error, xDiff + error);
        }
        else{
            double s1OverN1 = sVar1 / sampleCount1;
            double s2OverN2 = sVar2 / sampleCount2;
            double v = (s1OverN1 + s2OverN2) * (s1OverN1 + s2OverN2)
                    / (s1OverN1 * s1OverN1 / (sampleCount1 - 1) + s2OverN2 * s2OverN2 / (sampleCount2 - 1));
            double error = TDist.invCumLeftTailed(v, 1d - alpha / 2d); // t_n-1_alpha/2
            error *= TMath.sqrt(s1OverN1 + s2OverN2);
            return new TRange(xDiff - error, xDiff + error);
        }
    }



    /* ----------------------------- PROPORTIONS ---------------------------- */


    /**  @see #getIntervalForDifferenceOfProportions(double, double, double, double, double)  */
    public static TRange getIntervalForDifferenceOfProportions(DataDescription desc1, DataDescription desc2,
                                                               double pHat1, double pHat2, double alpha)
    {
        return getIntervalForDifferenceOfProportions(desc1.count, desc2.count, pHat1, pHat2, alpha);
    }


    /**  @see #getIntervalForDifferenceOfProportions(double, double, double, double, double)  */
    public static TRange getIntervalForDifferenceOfProportions(DataDescription desc1, DataDescription desc2,
                                                               int successes1, int successes2, double alpha)
    {
        return getIntervalForDifferenceOfProportions(desc1.count, desc2.count,
                successes1 / desc1.count, successes2 / desc2.count, alpha);
    }


    /**
     * Returns the confidence interval (CI) for the difference of population proportions. <br>
     * If the population is normally distributed, this method will return exact results.
     * And if not, then it will return approximate results. <br>
     * To check if your samples are approximately normally distributed or not, you can use the
     * {@link #isApproximatelyNormallyDistributed(DataDescription, double)} method.
     * @param sampleCount1 first sample's count
     * @param sampleCount2 second sample's count
     * @param pHat1 rate of successes in the first sample (definition of success may change)
     * @param pHat2 rate of successes in the first sample (definition of success may change)
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return The confidence interval (CI) for the difference of population proportions
     */
    public static TRange getIntervalForDifferenceOfProportions(double sampleCount1, double sampleCount2,
                                                               double pHat1, double pHat2, double alpha)
    {
        // error checking
        checkForCount(sampleCount1);
        checkForCount(sampleCount2);
        checkForPHat(pHat1);
        checkForPHat(pHat2);
        checkForAlpha(alpha);
        // code
        double error = ZDist.invCumRightTailed(0.5d - (alpha / 2d));
        error *= TMath.sqrt((pHat1 * (1d - pHat1) / sampleCount1) + (pHat2 * (1d - pHat2) / sampleCount2));
        // error = z_a/2 * sqrt( p1*q1/n1 + p2*q2/n2 )
        double hatDiff = pHat1 - pHat2;
        return new TRange(hatDiff - error, hatDiff + error);
    }



    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    /////////////////////   VALIDITY METHODS   ////////////////////
    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////



    /**
     * Returns true if this sample is approximately normally distributed. <br>
     * You can use this method to check if your sample fits a confidence interval for proportions calculation or not.
     * @param sampleDesc sample's description
     * @param pHat rate of successes as a double value (successes / count)
     * @return true if this sample is approximately normally distributed
     */
    public static boolean isApproximatelyNormallyDistributed(DataDescription sampleDesc, double pHat){
        return sampleDesc.count * pHat >= 10 && sampleDesc.count * (1d - pHat) >= 10;
    }


    /**  @see #isApproximatelyNormallyDistributed(DataDescription, double)  */
    public static boolean isApproximatelyNormallyDistributed(double sampleCount, double pHat){
        return sampleCount * pHat >= 10 && sampleCount * (1d - pHat) >= 10;
    }


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

    private static void checkForVariance(double variance){
        if(variance < 0)
            throw new IsNegativeException("variance");
    }

    private static void checkForStddev(double stddev){
        if(stddev < 0)
            throw new IsNegativeException("standard deviation");
    }

    private static void checkForCount(double count){
        if(count < 0)
            throw new IsNegativeException("count (array size)");
    }




}
