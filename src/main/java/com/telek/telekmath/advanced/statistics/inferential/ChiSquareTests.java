package com.telek.telekmath.advanced.statistics.inferential;


import com.telek.telekmath.utils.TelekMathException.*;


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


    // test for independence (two var)
    public static boolean isIndependent(boolean test){
        return isHomogeneous(); // revert to this
    }

    // homogeneity
    public static boolean isHomogeneous() {
        return false;
    }


    ///////////////
    /*  HELPERS  */
    ///////////////




}
