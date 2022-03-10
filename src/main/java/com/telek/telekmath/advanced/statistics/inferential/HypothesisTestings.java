package com.telek.telekmath.advanced.statistics.inferential;

import com.telek.telekmath.advanced.distributions.cont.*;
import com.telek.telekmath.advanced.statistics.descriptive.*;
import com.telek.telekmath.utils.TMath;
import com.telek.telekmath.utils.TelekMathException.*;


public class HypothesisTestings {

    public static final String EQUAL = "=";
    public static final String GREATER_OR_EQUAL = ">=";
    public static final String LESS_OR_EQUAL = "<=";

    /* No constructor */
    private HypothesisTestings(){}


    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    ///////////////////   FOR SINGLE VARIABLES   //////////////////
    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////


    /* -------------------------------- MEAN -------------------------------- */


    // PRIVATE METHOD THAT HAS ALL THE ALGORITHMS INSIDE IT, ALL METHODS WILL USE THIS METHOD
    private static boolean tForMeanPrivate(String signStr, double hypoMean, double sampleCount,
                                           double sampleMean, double aStddev, boolean stddevIsFromPop, double alpha)
    {
        // error checking
        verifyMean(sampleCount, aStddev, alpha);

        // code
        double testStat = (sampleMean - hypoMean) / (aStddev / TMath.sqrt(sampleCount));

        if(signStr == "="){
            double bound = stddevIsFromPop ? ZDist.invCumRightTailed(0.5d - alpha / 2d) :
                    TDist.invCumLeftTailed(sampleCount - 1d, 1d - alpha / 2d);
            return -bound <= testStat && testStat <= bound;
        }
        if(signStr == ">="){
            double bound = stddevIsFromPop ? ZDist.invCumRightTailed(0.5d - alpha) :
                    TDist.invCumLeftTailed(sampleCount - 1d, 1d - alpha);
            return -bound <= testStat;
        }
        if(signStr == "<="){
            double bound = stddevIsFromPop ? ZDist.invCumRightTailed(0.5d - alpha) :
                    TDist.invCumLeftTailed(sampleCount - 1d, 1d - alpha);
            return testStat <= bound;
        }

        throw new InvalidSignStringException();
    }



    /**
     * This method conducts a Z test for one population mean when the population's standard deviation is known. <br>
     * Us knowing the population's standard deviation is kinda unrealistic but it does happen sometimes. <br>
     * This method will produce exact results (Z test) under the assumption that
     * the population is approximately normally distributed. <br>
     * The null hypothesis is specified with the signStr parameter: <br>
     * <ul>
     *     <li>if signStr is "=" then H<sub>0</sub>: xPar = hypoMean</li>
     *     <li>if signStr is ">=" then H<sub>0</sub>: xPar >= hypoMean </li>
     *     <li>if signStr is "<=" then H<sub>0</sub>: xPar <= hypoMean</li>
     * </ul>
     *
     *
     * @param signStr ">=", "<=" or "=" to specify the sign of the inequality in the null hypothesis
     * @param hypoMean the hypothesized mean
     * @param sampleDesc sample's description
     * @param populationStddev this sample's population's standard deviation
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return true if the null hypothesis is accepted (the alternative hypothesis is rejected)
     */
    public static boolean testForMean(String signStr, double hypoMean, DataDescription sampleDesc,
                                      double populationStddev, double alpha)
    {
        return tForMeanPrivate(signStr, hypoMean, sampleDesc.count, sampleDesc.mean, populationStddev, true, alpha);
    }



    /**
     * This method conducts a T test for one population mean when the population's standard deviation is <b>unknown</b>. <br>
     * This method will produce <b>approximate</b> results by using this sample's standard deviation as
     * it was the population's standard deviation. (Under the assumption that the population is
     * approximately normally distributed.) <br>
     * The null hypothesis is specified with the signStr parameter: <br>
     * <ul>
     *     <li>if signStr is "=" then H<sub>0</sub>: xPar = hypoMean</li>
     *     <li>if signStr is ">=" then H<sub>0</sub>: xPar >= hypoMean </li>
     *     <li>if signStr is "<=" then H<sub>0</sub>: xPar <= hypoMean</li>
     * </ul>
     *
     * @param signStr ">=", "<=" or "=" to specify the sign of the inequality in the null hypothesis
     * @param hypoMean the hypothesized mean
     * @param sampleDesc sample's description
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return true if the null hypothesis is accepted (the alternative hypothesis is rejected)
     */
    public static boolean testForMean(String signStr, double hypoMean, DataDescription sampleDesc, double alpha)
    {
        return tForMeanPrivate(signStr, hypoMean, sampleDesc.count, sampleDesc.mean, sampleDesc.sampleStddev, false, alpha);
    }



    /**
     * This method is a compact version of these two methods:
     * {@link #testForMean(String, double, DataDescription, double)},
     * {@link #testForMean(String, double, DataDescription, double, double)} <br>
     * The outcome depends on the `stddevIsFromPopulation` parameter since it will determine the distribution
     * this method will use to calculate it's boundaries. (Z or T dist) <br>
     * @param signStr ">=", "<=" or "=" to specify the sign of the inequality in the null hypothesis
     * @param hypoMean the hypothesized mean
     * @param sampleCount number of terms in the sample
     * @param sampleMean sample's mean (x par)
     * @param sampleOrPopulationStddev a stddev value (can be from your sample or your population)
     * @param stddevIsFromPopulation specifies if the stddev entered was the population's or the sample's stddev value
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return true if the null hypothesis is accepted (the alternative hypothesis is rejected)
     */
    public static boolean testForMean(String signStr, double hypoMean, double sampleCount, double sampleMean,
                                      double sampleOrPopulationStddev, boolean stddevIsFromPopulation, double alpha)
    {
        return tForMeanPrivate(signStr, hypoMean, sampleCount, sampleMean, sampleOrPopulationStddev,
                stddevIsFromPopulation, alpha);
    }



    /* ------------------------------ VARIANCE ------------------------------ */



    /**
     * This method conducts a Chi-Square test for one population variance. <br>
     * The null hypothesis is specified with the signStr parameter: <br>
     * <ul>
     *     <li>if signStr is "=" then H<sub>0</sub>: populationVariance = hypoVariance</li>
     *     <li>if signStr is ">=" then H<sub>0</sub>: populationVariance >= hypoVariance </li>
     *     <li>if signStr is "<=" then H<sub>0</sub>: populationVariance <= hypoVariance</li>
     * </ul>
     *
     * @param signStr ">=", "<=" or "=" to specify the sign of the inequality in the null hypothesis
     * @param hypoVariance the hypothesized variance
     * @param sampleDesc sample's description
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return true if the null hypothesis is accepted (the alternative hypothesis is rejected)
     */
    public static boolean testForVariance(String signStr, double hypoVariance, DataDescription sampleDesc, double alpha)
    {
        return testForVariance(signStr, hypoVariance, sampleDesc.count, sampleDesc.sampleVariance, alpha);
    }



    /**  @see #testForVariance(String, double, DataDescription, double)  */
    public static boolean testForVariance(String signStr, double hypoVariance, double sampleCount,
                                          double sampleVariance, double alpha)
    {
        // error checking
        verifyVariance(hypoVariance, sampleCount, sampleVariance, alpha);
        // code
        double testStat = (sampleCount - 1d) * sampleVariance / hypoVariance;
        if(signStr == "="){
            double left = ChiSquaredDist.invCumLeftTailed(sampleCount - 1, alpha / 2d);
            double right = ChiSquaredDist.invCumLeftTailed(sampleCount - 1, 1d - alpha / 2d);
            return left <= testStat && testStat <= right;
        }
        if(signStr == ">="){
            double left = ChiSquaredDist.invCumLeftTailed(sampleCount - 1, alpha);
            return left <= testStat;
        }
        if(signStr == "<="){
            double right = ChiSquaredDist.invCumLeftTailed(sampleCount - 1, 1d - alpha);
            return testStat <= right;
        }

        throw new InvalidSignStringException();
    }



    /* ----------------------------- PROPORTIONS ---------------------------- */



    /**
     * This method conducts a Z test for one population proportion. <br>
     * The null hypothesis is specified with the signStr parameter: <br>
     * <ul>
     *     <li>if signStr is "=" then H<sub>0</sub>: p = hypoProportion</li>
     *     <li>if signStr is ">=" then H<sub>0</sub>: p >= hypoProportion </li>
     *     <li>if signStr is "<=" then H<sub>0</sub>: p <= hypoProportion</li>
     * </ul>
     *
     * @param signStr ">=", "<=" or "=" to specify the sign of the inequality in the null hypothesis
     * @param hypoProportion the hypothesized proportion
     * @param sampleCount number of terms in this sample
     * @param sampleProportion sample's proportion value (successes / count)
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return true if the null hypothesis is accepted (the alternative hypothesis is rejected)
     */
    public static boolean testForProportion(String signStr, double hypoProportion, double sampleCount,
                                            double sampleProportion, double alpha)
    {
        // error checking
        DescStats.verifyCount(sampleCount);
        DescStats.verifyProportion(hypoProportion);
        DescStats.verifyProportion(sampleProportion);
        DescStats.verifyAlpha(alpha);

        // code
        double testStat = (sampleProportion - hypoProportion) / TMath.sqrt(hypoProportion * (1d - hypoProportion) / sampleCount);
        if(signStr == "="){
            double z = ZDist.invCumRightTailed(0.5d - (alpha / 2d));
            return -z <= testStat && testStat <= z;
        }
        if(signStr == ">="){
            double z = ZDist.invCumRightTailed(0.5d - alpha);
            return -z <= testStat;
        }
        if(signStr == "<="){
            double z = ZDist.invCumRightTailed(0.5d - alpha);
            return testStat <= z;
        }

        throw new InvalidSignStringException();
    }




    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    /////////////////////   FOR TWO VARIABLES   ///////////////////
    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////


    /* -------------------------------- MEAN -------------------------------- */



    /**
     * This method conducts a Z test for two population's difference of means when the populations' stddev values
     * are known. Us knowing the populations' stddev values are usually an unrealistic assumption
     * but sometimes they're known. <br>
     * This method finds exact results. <br>
     * This method is also used by the
     * {@link #testForDiffOfMeansUnknownAny(String, double, DataDescription, DataDescription, double)}
     * method to get approximate resulsts when both the stddev values and the distribution type is unknown.  <br>
     * This method has an additional parameter that's usually not used in daily statistics: `hypoMean` <br><br>
     * Usually we have 3 sets of difference of means:
     * <ul>
     *     <li>H<sub>0</sub> : xPar1 - xPar2 = 0</li>
     *     <li>H<sub>0</sub> : xPar1 - xPar2 >= 0</li>
     *     <li>H<sub>0</sub> : xPar1 - xPar2 <= 0</li>
     * </ul>
     * Instead of always taking the right side of the equation as 0, this method takes it as
     * hypoMean and calculates the result with that. <br>
     * The null hypothesis is specified with the signStr parameter: <br>
     * <ul>
     *     <li>if signStr is "=" then H<sub>0</sub>: xPar1 - xPar2 = hypoMean</li>
     *     <li>if signStr is ">=" then H<sub>0</sub>: xPar1 - xPar2 >= hypoMean</li>
     *     <li>if signStr is "<=" then H<sub>0</sub>: xPar1 - xPar2 <= hypoMean</li>
     * </ul>
     *
     * @param signStr ">=", "<=" or "=" to specify the sign of the inequality in the null hypothesis
     * @param hypoMean the hypothesized mean
     * @param sampleDesc1 first sample's description
     * @param sampleDesc2 second sample's description
     * @param populationStddev1 first population's known stddev value
     * @param populationStddev2 second population's known stddev value
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return true if the null hypothesis is accepted (the alternative hypothesis is rejected)
     */
    public static boolean testForDiffOfMeansKnown(String signStr, double hypoMean, DataDescription sampleDesc1,
                                                  DataDescription sampleDesc2, double populationStddev1,
                                                  double populationStddev2, double alpha)
    {
        return knownOrUnknownAny(signStr, hypoMean, sampleDesc1.count, sampleDesc2.count,
                sampleDesc1.mean, sampleDesc2.mean, populationStddev1, populationStddev2, alpha);
    }



    /**
     * This method conducts a Z test for two population's difference of means when the populations' stddev values
     * and the distributin type are unknown. <br>
     * This method finds approximate results by using the samples' stddev values as populations' stddev values. <br>
     * For more information look at
     * {@link #testForDiffOfMeansKnown(String, double, DataDescription, DataDescription, double, double, double)}.
     *
     * @param signStr ">=", "<=" or "=" to specify the sign of the inequality in the null hypothesis
     * @param hypoMean the hypothesized mean
     * @param sampleDesc1 first sample's description
     * @param sampleDesc2 second sample's description
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return true if the null hypothesis is accepted (the alternative hypothesis is rejected)
     */
    public static boolean testForDiffOfMeansUnknownAny(String signStr, double hypoMean, DataDescription sampleDesc1,
                                                       DataDescription sampleDesc2, double alpha)
    {
        return testForDiffOfMeansKnown(signStr, hypoMean, sampleDesc1, sampleDesc2, sampleDesc1.sampleStddev,
                sampleDesc2.sampleStddev, alpha);
    }



    /**
     * This method conducts a T test for two population's difference of means when the populations' stddev values
     * are unknown and the populations are (approximately or exactly) normally distributed.
     * This method finds exact results. <br>
     * The `hypoMean` parameter works the same as it was in the other methods. <br>
     * The `assumeEqualStddevs` parameter specifies if both populations have the same unknown stddev values or not. <br>
     *
     * @param signStr ">=", "<=" or "=" to specify the sign of the inequality in the null hypothesis
     * @param hypoMean the hypothesized mean
     * @param sampleDesc1 first sample's description
     * @param sampleDesc2 second sample's description
     * @param assumeEqualStddevs specifies if both populations have the same unknown stddev values or not
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return true if the null hypothesis is accepted (the alternative hypothesis is rejected)
     */
    public static boolean testForDiffOfMeansUnknownNormal(String signStr, double hypoMean,
                                                          DataDescription sampleDesc1, DataDescription sampleDesc2,
                                                          boolean assumeEqualStddevs, double alpha)
    {
        return unkwownNORMAL(signStr, hypoMean, sampleDesc1.count, sampleDesc2.count, sampleDesc1.mean, sampleDesc2.mean,
                sampleDesc1.sampleStddev, sampleDesc2.sampleStddev, assumeEqualStddevs, alpha);
    }





    /** @see #testForDiffOfMeansKnown(String, double, DataDescription, DataDescription, double, double, double) */
    public static boolean testForDiffOfMeansKnown(String signStr, double hypoMean, double sampleCount1, double sampleCount2,
                                                  double sampleMean1, double sampleMean2, double populationStddev1,
                                                  double populationStddev2, double alpha)
    {
        return knownOrUnknownAny(signStr, hypoMean, sampleCount1, sampleCount2, sampleMean1,
                sampleMean2, populationStddev1, populationStddev2, alpha);
    }


    /** @see #testForDiffOfMeansUnknownAny(String, double, DataDescription, DataDescription, double)  */
    public static boolean testForDiffOfMeansUnknownAny(String signStr, double hypoMean, double sampleCount1, double sampleCount2,
                                                       double sampleMean1, double sampleMean2,
                                                       double sampleStddev1, double sampleStddev2, double alpha)
    {
        return testForDiffOfMeansKnown(signStr, hypoMean, sampleCount1, sampleCount2, sampleMean1, sampleMean2,
                sampleStddev1, sampleStddev2, alpha);
    }


    /** @see #testForDiffOfMeansUnknownNormal(String, double, DataDescription, DataDescription, boolean, double)   */
    public static boolean testForDiffOfMeansUnknownNormal(String signStr, double hypoMean,
                                                          double sampleCount1, double sampleCount2,
                                                          double sampleMean1, double sampleMean2,
                                                          double sampleStddev1, double sampleStddev2,
                                                          boolean assumeEqualStddevs, double alpha)
    {
        return unkwownNORMAL(signStr, hypoMean, sampleCount1, sampleCount2, sampleMean1, sampleMean2,
                sampleStddev1, sampleStddev2, assumeEqualStddevs, alpha);
    }





    // ACTUAL FUNCTION, KNOWN VARIANCE & UNKNOWN ANY
    private static boolean knownOrUnknownAny(String signStr, double hypoMean, double sampleCount1, double sampleCount2,
                                             double sampleMean1, double sampleMean2, double aStddev1, double aStddev2,
                                             double alpha)
    {
        // error checking
        verifyDifMean(sampleCount1, sampleCount2, aStddev1, aStddev2, alpha);

        double testStat = (sampleMean1 - sampleMean2 - hypoMean) /
                TMath.sqrt((aStddev1 * aStddev1 / sampleCount1) + (aStddev2 * aStddev2 / sampleCount2));

        if(signStr == "=") // cover two tailed tests
            alpha /= 2d;

        double z = ZDist.invCumRightTailed(0.5d - alpha);
        if(signStr == "=")
            return -z <= testStat && testStat <= z;
        if(signStr == ">=")
            return -z <= testStat;
        if(signStr == "<=")
            return testStat <= z;

        throw new InvalidSignStringException();
    }



    // ACTUAL FUNCTION, UNKNOWN NORMAL
    private static boolean unkwownNORMAL(String signStr, double hypoMean, double sampleCount1, double sampleCount2,
                                       double sampleMean1, double sampleMean2, double aStddev1, double aStddev2,
                                       boolean assumeEqualStddevs, double alpha)
    {

        // error checking
        verifyDifMean(sampleCount1, sampleCount2, aStddev1, aStddev2, alpha);

        // code
        double var1 = aStddev1 * aStddev1;
        double var2 = aStddev2 * aStddev2;
        double var1n = var1 / sampleCount1;
        double var2n = var2 / sampleCount2;
        double testStat;
        if(assumeEqualStddevs){
            double sk2 = ((sampleCount1 - 1d) * var1 + (sampleCount2 - 1d) * var2) / (sampleCount1 + sampleCount2 - 2d);
            testStat = (sampleMean1 - sampleMean2 - hypoMean) / TMath.sqrt(sk2 / sampleCount1 + sk2 / sampleCount2);
        }
        else{
            testStat = (sampleMean1 - sampleMean2 - hypoMean) / TMath.sqrt(var1n + var2n);
        }

        if(signStr == "=") // cover two tailed tests
            alpha /= 2d;

        double v = assumeEqualStddevs ? sampleCount1 + sampleCount2 - 2d :
                (var1n + var2n) * (var1n + var2n) /
                        (var1n * var1n / (sampleCount1 - 1d) + var2n * var2n / (sampleCount2 - 1d));


        double t = TDist.invCumLeftTailed(v, 1d - alpha);

        if(signStr == "=")
            return -t <= testStat && testStat <= t;
        if(signStr == ">=")
            return -t <= testStat;
        if(signStr == "<=")
            return testStat <= t;

        throw new InvalidSignStringException();
    }



    /* ----------------------------- PROPORTIONS ---------------------------- */


    /**
     * This method conducts a Z test for two population's difference of proportions. <br>
     * This method has an additional parameter that's usually not used in daily statistics: `hypoProportion` <br><br>
     * Usually we have 3 sets of difference of proportions:
     * <ul>
     *     <li>H<sub>0</sub> : p1 - p2 = 0</li>
     *     <li>H<sub>0</sub> : p1 - p2 >= 0</li>
     *     <li>H<sub>0</sub> : p1 - p2 <= 0</li>
     * </ul>
     * Instead of always taking the right side of the equation as 0, this method takes it as
     * hypoProportion and calculates the result with that. <br>
     * The formula used to calculate the test statistics is as follows: <br><br>
     *  z<sub>0</sub> = ((pHat1 - pHat2) - hypoProportion) / sqrt( p<sub>w</sub> * q<sub>w</sub> * (1/n1 + 1/n2) ) <br><br>
     * The null hypothesis is specified with the signStr parameter: <br>
     * <ul>
     *     <li>if signStr is "=" then H<sub>0</sub>: p1 - p2 = hypoProportion</li>
     *     <li>if signStr is ">=" then H<sub>0</sub>: p1 - p2 >= hypoProportion </li>
     *     <li>if signStr is "<=" then H<sub>0</sub>: p1 - p2 <= hypoProportion</li>
     * </ul>
     *
     * @param signStr ">=", "<=" or "=" to specify the sign of the inequality in the null hypothesis
     * @param hypoProportion the hypothesized proportion
     * @param sampleCount1 number of terms in the first sample
     * @param sampleCount2 number of terms in the second sample
     * @param sampleProportion1 the first sample's proportion value (successes / count)
     * @param sampleProportion2 the second sample's proportion value (successes / count)
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return true if the null hypothesis is accepted (the alternative hypothesis is rejected)
     */
    public static boolean testForDifferenceOfProportions(String signStr, double hypoProportion,
                                                         double sampleCount1, double sampleCount2,
                                                         double sampleProportion1, double sampleProportion2,
                                                         double alpha)
    {
        // error checking
        DescStats.verifyProportion(hypoProportion);
        DescStats.verifyCount(sampleCount1);
        DescStats.verifyCount(sampleCount2);
        DescStats.verifyProportion(sampleProportion1);
        DescStats.verifyProportion(sampleProportion2);
        DescStats.verifyAlpha(alpha);

        // code
        double pw = (sampleCount1 * sampleProportion1 + sampleCount2 * sampleProportion2) / (sampleCount1 + sampleCount2);
        double qw = 1d - pw;
        double testStat = (sampleProportion1 - sampleProportion2 - hypoProportion)
                / TMath.sqrt(pw * qw * (1d / sampleCount1 + 1d / sampleCount2));
        if(signStr == "="){
            double z = ZDist.invCumRightTailed(0.5d - (alpha / 2d));
            return -z <= testStat && testStat <= z;
        }
        if(signStr == ">="){
            double z = ZDist.invCumRightTailed(0.5d - alpha);
            return -z <= testStat;
        }
        if(signStr == "<="){
            double z = ZDist.invCumRightTailed(0.5d - alpha);
            return testStat <= z;
        }

        throw new InvalidSignStringException();
    }




    ///////////////
    /*  HELPERS  */
    ///////////////


    private static void verifyDifMean(double n1, double n2, double s1, double s2, double alpha) {
        DescStats.verifyCount(n1);
        DescStats.verifyCount(n2);
        DescStats.verifyStddev(s1);
        DescStats.verifyStddev(s2);
        DescStats.verifyAlpha(alpha);
    }


    private static void verifyMean(double sampleCount, double populationStddev, double alpha) {
        DescStats.verifyCount(sampleCount);
        DescStats.verifyStddev(populationStddev);
        DescStats.verifyAlpha(alpha);
    }



    private static void verifyVariance(double hypoVariance, double sampleCount, double sampleVariance, double alpha){
        DescStats.verifyVariance(hypoVariance);
        DescStats.verifyCount(sampleCount);
        DescStats.verifyVariance(sampleVariance);
        DescStats.verifyAlpha(alpha);
    }


}
