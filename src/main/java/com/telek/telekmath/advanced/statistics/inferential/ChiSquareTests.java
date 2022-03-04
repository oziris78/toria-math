package com.telek.telekmath.advanced.statistics.inferential;


import com.telek.telekmath.advanced.distributions.cont.ChiSquaredDist;
import com.telek.telekmath.advanced.statistics.freqtable.FreqDistTable;
import com.telek.telekmath.core.matrices.TMatrix;
import com.telek.telekmath.utils.TelekMathException.*;
import com.telek.telekutils.arrayref.oned.*;


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

    /*  No constructor  */
    private ChiSquareTests(){}


    ///////////////
    /*  METHODS  */
    ///////////////



    /**
     * Performs a "goodness of fit" test. Which is the same as "test for independence" with one variable. <br>
     * This is the recommended method for you to use if you want to do this test without creating
     * a frequency distribution table. All other methods that will do the same thing with a different type of
     * array will create a casted double array (they will create a copy) and use this method with that copied array.
     * @param observedFreqs observed frequencies array
     * @param expectedFreqs expected frequencies array, you will need to create this array
     *                      on your own using the distribution that you're going to test
     * @param distType a string specifying the distribution type, must be one of these: "uniform", "normal", "poisson"
     * @param alpha a value in range [0,1] specifying the confidence level,
     *              if you want %99 confidence level then you need to enter 0.01 (%1) as alpha
     * @return true if the frequency distribution fits the distribution specified with the distType parameter
     */
    public static boolean fitsDistribution(ArrayRef observedFreqs, double[] expectedFreqs, String distType, double alpha){
        final int k = observedFreqs.getSize();
        if(expectedFreqs.length != k)
            throw new NotEqualArrayLengthException("expectedFreqs", "observedFreqs");
        final int p = getParameterNumber(distType);
        final double chi = ChiSquaredDist.invCumLeftTailed(k - p - 1d, 1d - alpha);

        // test statistic
        double testStat = 0;
        for (int i = 0; i < k; i++) {
            double oi = observedFreqs.getValue(i);
            double ei = expectedFreqs[i];
            double diff = oi - ei;
            if(ei < 5d)
                throw new ExpectedValueAssumptionException(ei);
            testStat += diff * diff / ei;
        }

        return testStat <= chi;
    }


    ///////////////////   ALIASES   ///////////////////

    /**  @see #fitsDistribution(ArrayRef, double[], String, double)  */
    public static boolean fitsDistribution(double[] observedFreqs, double[] expectedFreqs, String distType, double alpha){
        return fitsDistribution(new DoubleArrRef(observedFreqs), expectedFreqs, distType, alpha);
    }

    /**  @see #fitsDistribution(ArrayRef, double[], String, double)  */
    public static boolean fitsDistribution(float[] observedFreqs, double[] expectedFreqs, String distType, double alpha){
        return fitsDistribution(new FloatArrRef(observedFreqs), expectedFreqs, distType, alpha);
    }

    /**  @see #fitsDistribution(ArrayRef, double[], String, double)  */
    public static boolean fitsDistribution(Number[] observedFreqs, double[] expectedFreqs, String distType, double alpha){
        return fitsDistribution(new NumberArrRef(observedFreqs), expectedFreqs, distType, alpha);
    }

    /**  @see #fitsDistribution(ArrayRef, double[], String, double)  */
    public static boolean fitsDistribution(int[] observedFreqs, double[] expectedFreqs, String distType, double alpha){
        return fitsDistribution(new IntArrRef(observedFreqs), expectedFreqs, distType, alpha);
    }

    /**  @see #fitsDistribution(double[], double[], String, double)  */
    public static boolean isIndependent(double[] observedFreqs, double[] expectedFreqs, double alpha){
        return fitsDistribution(observedFreqs, expectedFreqs, "uniform", alpha);
    }

    /**  @see #fitsDistribution(double[], double[], String, double)  */
    public static boolean isIndependent(float[] observedFreqs, double[] expectedFreqs, double alpha){
        return fitsDistribution(observedFreqs, expectedFreqs, "uniform", alpha);
    }

    /**  @see #fitsDistribution(double[], double[], String, double)  */
    public static boolean isIndependent(Number[] observedFreqs, double[] expectedFreqs, double alpha){
        return fitsDistribution(observedFreqs, expectedFreqs, "uniform", alpha);
    }

    /**  @see #fitsDistribution(double[], double[], String, double)  */
    public static boolean isIndependent(int[] observedFreqs, double[] expectedFreqs, double alpha){
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
    public static boolean isHomogeneous(TMatrix table, double alpha) {
        int lastRow = table.getRowSize() + 1;
        int lastCol = table.getColSize() + 1;
        TMatrix wholeTable = table.copy(lastRow, lastCol);

        // fill the table with sum values
        double rightDown = 0;
        for (int row = 0; row < lastRow - 1; row++) {
            double sum = 0;
            for (int i = 0; i < lastCol - 1; i++) {
                sum += table.getCell(row, i);
            }
            wholeTable.setCell(row, lastCol - 1, sum);
            rightDown += sum;
        }
        for (int col = 0; col < lastCol - 1; col++) {
            double sum = 0;
            for (int i = 0; i < lastRow - 1; i++) {
                sum += table.getCell(i, col);
            }
            wholeTable.setCell(lastRow - 1, col, sum);
        }
        wholeTable.setCell(lastRow - 1, lastCol -1, rightDown);

        // update the table with ratios
        for (int i = 0; i < lastRow - 1; i++) {
            for (int j = 0; j < lastCol - 1; j++) {
                double val = wholeTable.getCell(i, lastCol - 1) * wholeTable.getCell(lastRow - 1, j) / rightDown;
                wholeTable.setCell(i, j, val);
            }
        }

        // do the test
        double testStat = 0;
        for (int i = 0; i < table.getRowSize(); i++) {
            for (int j = 0; j < table.getColSize(); j++) {
                double eij = wholeTable.getCell(i, j);
                double oij = table.getCell(i, j);
                double diff = eij - oij;
                testStat += diff * diff / eij;
            }
        }

        double v = (table.getRowSize() - 1) * (table.getColSize() - 1);
        double chi = ChiSquaredDist.invCumLeftTailed(v, 1d - alpha);
        return testStat <= chi;
    }


    /**  @see #isHomogeneous(TMatrix, double)  */
    public static boolean isIndependent(TMatrix table, double alpha){
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
