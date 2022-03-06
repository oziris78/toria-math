package com.telek.telekmath.advanced.statistics.inferential;

import com.telek.telekmath.advanced.distributions.cont.*;
import com.telek.telekmath.advanced.statistics.descriptive.DataDescription;
import com.telek.telekmath.advanced.statistics.descriptive.DescStats;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.utils.TMath;


/**
 * A class that calculates mean, proportion and variance confidence intervals for one or two populations. <br>
 * All these methods calculate an estimated interval for the <b>population's</b> mean/proportion/variance. <br>
 * For more information please look <a href="https://en.wikipedia.org/wiki/Confidence_interval">here</a>
 */
public class ConfidenceIntervals {


    /* No constructor */
    private ConfidenceIntervals(){}

    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    ///////////////////   FOR SINGLE VARIABLES   //////////////////
    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////


    /* -------------------------------- MEAN -------------------------------- */


    // PRIVATE METHOD THAT HAS ALL THE ALGORITHMS INSIDE IT, ALL METHODS WILL USE THIS METHOD
    private static TRange getIntForMeanPrivate(double sampleCount, double sampleMean, double aStddev,
                                               boolean isNormallyDist, boolean stdDevIsFromPop, double alpha)
    {
        // error checking
        DescStats.verifyCount(sampleCount);
        DescStats.verifyAlpha(alpha);
        DescStats.verifyStddev(aStddev);

        // code
        double error = aStddev / TMath.sqrt(sampleCount);
        if(stdDevIsFromPop || !isNormallyDist)
            error *= ZDist.invCumRightTailed(0.5d - (alpha / 2d));
        else
            error *= TDist.invCumLeftTailed(sampleCount - 1, 1d - alpha / 2d); // t_n-1_alpha/2

        return new TRange(sampleMean - error,  sampleMean + error);
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
    public static TRange getIntervalForMean(DataDescription sampleDesc, double populationStddev, double alpha)
    {
        return getIntForMeanPrivate(sampleDesc.count, sampleDesc.mean, populationStddev, true /*rand val*/, true, alpha);
    }



    /**
     * Returns the confidence interval (CI) for the population's mean when the population's standard deviation is unknown. <br>
     * You will need to specify the distribution type with the `isNormallyDist` parameter. <br>
     * If you don't know the distribution type then just enter false. <br>
     * If the population is normally distributed, this method will return exact results. And if not, it will return
     * approximate results.
     * @param sampleDesc the sample's DataDescription object
     * @param isNormallyDist a boolean value specifying the distribution type, you need to enter true
     *                       if this sample is normally distributed, if you don't know what type of
     *                       distribution this data has then just enter false
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return The confidence interval (CI) for the population mean
    */
    public static TRange getIntervalForMean(DataDescription sampleDesc, boolean isNormallyDist, double alpha)
    {
        return getIntForMeanPrivate(sampleDesc.count, sampleDesc.mean,
                sampleDesc.sampleStddev, isNormallyDist, false, alpha);
    }



    /** @see #getIntervalForMean(DataDescription, double, double)  */
    public static TRange getIntervalForMean(double sampleCount, double sampleMean, double populationStddev, double alpha)
    {
        return getIntForMeanPrivate(sampleCount, sampleMean, populationStddev, true /*rand val*/, true, alpha);
    }



    /** @see #getIntervalForMean(DataDescription, boolean, double)  */
    public static TRange getIntervalForMean(double sampleCount, double sampleMean, double sampleStddev,
                                            boolean isNormallyDist, double alpha)
    {
        return getIntForMeanPrivate(sampleCount, sampleMean, sampleStddev, isNormallyDist, false, alpha);
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
        DescStats.verifyCount(sampleCount);
        DescStats.verifyVariance(sampleVariance);
        DescStats.verifyAlpha(alpha);
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
        DescStats.verifyCount(sampleCount);
        DescStats.verifyProportion(pHat);
        DescStats.verifyAlpha(alpha);
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
    public static TRange getIntervalForDiffOfMeansKnown(DataDescription desc1, DataDescription desc2,
                                                         double populationStddev1, double populationStddev2,
                                                         double alpha)
    {
        return getIntervalForDiffOfMeansKnown(desc1.count, desc2.count, desc1.mean, desc2.mean,
                populationStddev1, populationStddev2, alpha);
    }


    /**  @see #getIntervalForDiffOfMeansKnown(DataDescription, DataDescription, double, double, double)  */
    public static TRange getIntervalForDiffOfMeansKnown(double sampleCount1, double sampleCount2,
                                                        double sampleMean1, double sampleMean2,
                                                        double populationStddev1, double populationStddev2,
                                                        double alpha)
    {
        // error checking
        verifyDifMeans(sampleCount1, sampleCount1, populationStddev1, populationStddev2, alpha);

        // code
        double xDiff = sampleMean1 - sampleMean2;
        double error = ZDist.invCumRightTailed(0.5d - (alpha / 2d));
        error *= TMath.sqrt((populationStddev1 * populationStddev1 / sampleCount1) +
                (populationStddev2 * populationStddev2 / sampleCount2));
        return new TRange(xDiff - error, xDiff + error);
    }






    /**
     * This method calculates the confidence interval for the difference of population means by using {@link ZDist}
     * class when the distribution types and standard deviations of these populations are unknown. <br>
     * This method gives approximate results using
     * {@link #getIntervalForDiffOfMeansKnown(DataDescription, DataDescription, double, double, double)} method.
     * For more information look at that method's javadocs. <br>
     * @param desc1 first sample's description
     * @param desc2 second sample's description
     * @param sampleStddev1 first sample's standard deviation
     * @param sampleStddev2 second sample's" standard deviation
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return The confidence interval (CI) for the difference of population means
     */
    public static TRange getIntervalForDiffOfMeansUnknownAny(DataDescription desc1, DataDescription desc2, double alpha)
    {
        return getIntervalForDiffOfMeansKnown(desc1.count, desc2.count, desc1.mean, desc2.mean,
                desc1.sampleStddev, desc2.sampleStddev, alpha);
    }



    /** @see #getIntervalForDiffOfMeansUnknownAny(DataDescription, DataDescription, double)   */
    public static TRange getIntervalForDiffOfMeansUnknownAny(double sampleCount1, double sampleCount2,
                                                        double sampleMean1, double sampleMean2,
                                                        double sampleStddev1, double sampleStddev2,
                                                        double alpha)
    {
        return getIntervalForDiffOfMeansKnown(sampleCount1, sampleCount2, sampleMean1, sampleMean2,
                sampleStddev1, sampleStddev2, alpha);
    }






    /**
     * This method calculates the confidence interval for the difference of population means when the
     * standard deviations of these populations are unknown and the populations are normally distributed. <br>
     * In statistics, in order to get exact results even when the populations' stddev values are unknown we
     * need the populations to be normally distributed. That's why this method assumes that both
     * populations are normally distributed. If your populations are not normally distributed (or if you simply
     * don't know what type of distribution they have you can always use
     * {@link #getIntervalForDiffOfMeansUnknownAny(DataDescription, DataDescription, double)}
     * method to get approximate resulsts. For more information look at that method's javadocs. <br>
     * Most of the times in real life statistics when you calculate degrees of freedom as a non-integer value you
     * round it to get better and approximate results. This method does not do approximations for degrees of freedom
     * and uses real valued degrees of freedoms. For example if degrees of freedom is calculated as 10.751 you would
     * round this to 11 but this method does not do this and takes it as 10.751.
     * @param desc1 first sample's description
     * @param desc2 second sample's description
     * @param assumeEqualStddevs a boolean specifying if these two populations have the same
     *                                                  variances or not, if you have no idea about this equality
     *                                                  just enter false
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return The confidence interval (CI) for the difference of population means
     */
    public static TRange getIntervalForDiffOfMeansUnknownNormal(DataDescription desc1, DataDescription desc2,
                                                         boolean assumeEqualStddevs, double alpha)
    {
        return getIntervalForDiffOfMeansUnknownNormal(
                desc1.count, desc2.count, desc1.mean, desc2.mean, desc1.sampleStddev, desc2.sampleStddev,
                assumeEqualStddevs, alpha
        );
    }



    /**  @see #getIntervalForDiffOfMeansUnknownNormal(DataDescription, DataDescription, boolean, double)   */
    public static TRange getIntervalForDiffOfMeansUnknownNormal(double sampleCount1, double sampleCount2,
                                                         double sampleMean1, double sampleMean2,
                                                         double sampleStddev1, double sampleStddev2,
                                                         boolean assumeEqualStddevs, double alpha)
    {
        // error checking
        verifyDifMeans(sampleCount1, sampleCount1, sampleStddev1, sampleStddev2, alpha);

        // code
        double xDiff = sampleMean1 - sampleMean2;
        double sVar1 = sampleStddev1 * sampleStddev1;
        double sVar2 = sampleStddev2 * sampleStddev2;

        if(assumeEqualStddevs){
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
        DescStats.verifyCount(sampleCount1);
        DescStats.verifyCount(sampleCount2);
        DescStats.verifyProportion(pHat1);
        DescStats.verifyProportion(pHat2);
        DescStats.verifyAlpha(alpha);
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
        return isApproximatelyNormallyDistributed(sampleDesc.count, pHat);
    }


    /**  @see #isApproximatelyNormallyDistributed(DataDescription, double)  */
    public static boolean isApproximatelyNormallyDistributed(double sampleCount, double pHat){
        // error checking
        DescStats.verifyCount(sampleCount);
        DescStats.verifyProportion(pHat);
        // code
        return sampleCount * pHat >= 10 && sampleCount * (1d - pHat) >= 10;
    }


    ///////////////
    /*  HELPERS  */
    ///////////////

    private static void verifyDifMeans(double n1, double n2, double s1, double s2, double alpha) {
        DescStats.verifyCount(n1);
        DescStats.verifyCount(n2);
        DescStats.verifyStddev(s1);
        DescStats.verifyStddev(s2);
        DescStats.verifyAlpha(alpha);
    }




}
