package com.twistral.toriamath.advanced.statistics.inferential;


import com.twistral.toriamath.advanced.distributions.cont.ChiSquaredDist;
import com.twistral.toriamath.advanced.statistics.freqtable.FreqDistTable;
import com.twistral.toriamath.utils.ToriaMathException.*;


/**
 * A class that has methods for Chi Square tests. <br>
 * Assumptions of Chi Square Tests:
 * <ul>
 *     <li>All groups are disjoint.</li>
 *     <li>All observed values are independent of each other.</li>
 *     <li>All groups have an expected value higher than 5. (If not the groups will be merged into one)</li>
 * </ul>
 */
public class ChiSquareTests {

    public static final String UNIFORM = "uniform";
    public static final String NORMAL = "normal";
    public static final String POISSON = "poisson";

    /*  No constructor  */
    private ChiSquareTests(){}


    ///////////////
    /*  METHODS  */
    ///////////////



    /**
     * Performs a "goodness of fit" test. Which is the same as "test for independence" with one variable. <br>
     * This is the recommended method for you to use if you want to do this test without creating
     * a frequency distribution table.
     * @param observedFreqs observed frequencies array
     * @param expectedFreqs expected frequencies array, you will need to create this array
     *                      on your own using the distribution that you're going to test
     * @param distType a string specifying the distribution type, must be one of these: "uniform", "normal", "poisson"
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return true if the frequency distribution fits the distribution specified with the distType parameter
     */
    public static boolean fitsDistribution(double[] observedFreqs, double[] expectedFreqs, String distType, double alpha){
        final int k = observedFreqs.length;
        if(expectedFreqs.length != k)
            throw new NotEqualArrayLengthException("expectedFreqs", "observedFreqs");
        final int p = getParameterNumber(distType);
        final double chi = ChiSquaredDist.invCumLeftTailed(k - p - 1d, 1d - alpha);

        // test statistic
        double testStat = 0;
        for (int i = 0; i < k; i++) {
            double oi = observedFreqs[i];
            double ei = expectedFreqs[i];
            double diff = oi - ei;
            if(ei < 5d)
                throw new ExpectedValueAssumptionException(ei);
            testStat += diff * diff / ei;
        }

        return testStat <= chi;
    }



    /**  @see #fitsDistribution(double[], double[], String, double)  */
    public static boolean isIndependent(double[] observedFreqs, double[] expectedFreqs, double alpha){
        return fitsDistribution(observedFreqs, expectedFreqs, "uniform", alpha);
    }


    // USING FREQ DIST TABLE

    /**
     * Performs a "goodness of fit" test. Which is the same as "test for independence" with one variable. <br>
     * @param table the frequency distribution table
     * @param expectedFreqs expected frequencies array, you will need to create this array
     *                      on your own using the distribution that you're going to test
     * @param distType a string specifying the distribution type, must be one of these: "uniform", "normal", "poisson"
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return true if the frequency distribution fits the distribution specified with the distType parameter
     */
    public static boolean fitsDistribution(FreqDistTable table, double[] expectedFreqs, String distType, double alpha){
        final int k = table.getRowCount();
        if(expectedFreqs.length != k)
            throw new NotEqualArrayLengthException("expectedFreqs", "freqDistTable");
        final int p = getParameterNumber(distType);
        final double chi = ChiSquaredDist.invCumLeftTailed(k - p - 1d, 1d - alpha);

        // test statistic
        double testStat = 0;
        for (int i = 0; i < k; i++) {
            double oi = table.getTableRow(i).freq;
            double ei = expectedFreqs[i];
            if(ei < 5d)
                throw new ExpectedValueAssumptionException(ei);
            double diff = oi - ei;
            testStat += diff * diff / ei;
        }

        return testStat <= chi;
    }


    /**  @see #fitsDistribution(FreqDistTable, double[], String, double)  */
    public static boolean isIndependent(FreqDistTable table, double[] expectedFreqs, double alpha){
        return fitsDistribution(table, expectedFreqs, "uniform", alpha);
    }





    //////////////////////////////////////////
    /*  HOMOGENEITY | INDEPENDENCE (2 VAR)  */
    //////////////////////////////////////////


    /**
     * For more information look
     * <a href="https://en.wikipedia.org/wiki/Chi-squared_test#Example_chi-squared_test_for_categorical_data">here</a>.
     * @param table any matrix
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return true if the distribution of the categorical variable is homogeneous for all populations
     */
    public static boolean isHomogeneous(double[][] table, double alpha) {
        int lastRow = table.length + 1;
        int lastCol = table[0].length + 1;

        double[][] wholeTable = new double[lastRow][lastCol];
        // extend it
        for (int i = 0; i < lastRow; i++)
            for (int j = 0; j < lastCol; j++)
                wholeTable[i][j] = (i+1 == lastRow || j+1 == lastCol) ? 0d : table[i][j];

        // fill the table with sum values
        double rightDown = 0;
        for (int row = 0; row < lastRow - 1; row++) {
            double sum = 0;
            for (int i = 0; i < lastCol - 1; i++) {
                sum += table[row][i];
            }
            wholeTable[row][lastCol - 1] = sum;
            rightDown += sum;
        }
        for (int col = 0; col < lastCol - 1; col++) {
            double sum = 0;
            for (int i = 0; i < lastRow - 1; i++) {
                sum += table[i][col];
            }
            wholeTable[lastRow - 1][col] = sum;
        }
        wholeTable[lastRow - 1][lastCol -1] = rightDown;

        // update the table with ratios
        for (int i = 0; i < lastRow - 1; i++) {
            for (int j = 0; j < lastCol - 1; j++) {
                double val = wholeTable[i][lastCol - 1] * wholeTable[lastRow - 1][j] / rightDown;
                wholeTable[i][j] = val;
            }
        }

        // do the test
        double testStat = 0;
        for (int i = 0; i < lastRow - 1; i++) {
            for (int j = 0; j < lastCol - 1; j++) {
                double eij = wholeTable[i][j];
                double oij = table[i][j];
                double diff = eij - oij;
                testStat += diff * diff / eij;
            }
        }

        double v = (lastRow - 2) * (lastCol - 2);
        double chi = ChiSquaredDist.invCumLeftTailed(v, 1d - alpha);
        return testStat <= chi;
    }


    /**  @see #isHomogeneous(double[][], double)  */
    public static boolean isIndependent(double[][] table, double alpha){
        return isHomogeneous(table, alpha);
    }


    ///////////////
    /*  HELPERS  */
    ///////////////

    private static int getParameterNumber(String distType) {
        if(distType == "uniform")
            return 0;
        if(distType == "poisson")
            return 1;
        if(distType == "normal")
            return 2;
        throw new UnsupportedDistributionTypeString();
    }




}
