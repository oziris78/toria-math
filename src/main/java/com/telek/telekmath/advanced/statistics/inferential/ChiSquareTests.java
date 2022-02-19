package com.telek.telekmath.advanced.statistics.inferential;


import com.telek.telekmath.advanced.distributions.cont.ChiSquaredDist;
import com.telek.telekmath.core.matrices.TMatrix;
import com.telek.telekmath.utils.TelekMathException.*;
import com.telek.telekutils.plain.TArrays;


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


    /*
    Destekleyeceğin türleri belirle
    float,double,int,long array'leri (tekli ve çoklu boyutlarda)
    FreqDistTable sınıfı ya da FrequencyClass[]
    TMatrix?

    Assumption'ları her metodun açıklamasına yaz (sınıfın açıklamasına koy oraya yönlendir)
    */

    // "uniform", "poisson", "normal"

    // goodness of fit
    public static boolean fitsDistribution(String distType){
        throw new UnsupportedDistributionTypeString();
    }



    // test for independence (one var)
    public static boolean isIndependent(){
        return false;
    }




    //////////////////////////////////////////
    /*  HOMOGENEITY | INDEPENDENCE (2 VAR)  */
    //////////////////////////////////////////


    /**  @see #isHomogeneous(TMatrix, double)  */
    public static boolean isIndependent(TMatrix table, double alpha){
        return isHomogeneous(table, alpha); // revert to this
    }


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




    ///////////////
    /*  HELPERS  */
    ///////////////




}
