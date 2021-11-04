package com.telek.telekmath;

import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.exceptions.RepeatedPermutationException;
import com.telek.telekmath.exceptions.TelekMathException.*;
import java.util.HashMap;



/**
 * A class that has a lot of utility functions.
 * beta(a,b) and logGamma(x) methods are taken from Apache Commons Math.
 * atn(), atan2(), acos(), asin() methods were written by Tommy Ettinger, https://github.com/tommyettinger
 */
public final class TMath {



    /*  ------------  */
    /*  MATH METHODS  */
    /*  ------------  */


    /**
     * Uses Apache Common Math's Gamma.logGamma function to evaluate gamma(x)
     * @param x any double value
     * @return gamma(x)
     */
    public static double gamma(double x){
        return Math.exp( logGamma(x) );
    }

    /**
     * Uses Apache Common Math's Beta.logBeta function to evaluate beta(a,b)
     * @param a any double value
     * @param b any double value
     * @return beta(a,b)
     */
    public static double beta(double a, double b){
        return Math.exp( logBeta(a,b) ); //
    }




    /**
     * Works in linear time aka O(n)
     * @param n any integer
     * @return true if n is prime, else false
     */
    public static boolean isPrime(int n){
        if(n <= 1) return false;
        for(int i = 2; i < n; i++)
            if(n % i == 0) return false;
        return true;
    }



    /**
     * Fast power function that only works with integers
     * @param base any integer
     * @param exponent any integer
     * @return base ^ exponent
     */
    public static int pow(int base, int exponent){
        return (exponent != 0) ? base * pow(base,exponent-1) : 1;
    }



    /**
     * Fast factorial function that only works with integers
     * @param num any integer
     * @return factorial(num)
     */
    public static int factorial(int num){
        return (num != 0) ? num * factorial(num-1) : 1;
    }


    public static int permutation(int n, int r){
        if( !(n>=r && r>=0) ) throw new PermutationException(n, r);
        return TMath.factorial(n) / TMath.factorial( n - r );
    }


    public static int repeatedPermutation(int n, int... rValues){
        for(int r : rValues) {
            if( !(n >= r) ) throw new RepeatedPermutationException(n, rValues);
            if( !(r >= 0) ) throw new RepeatedPermutationException(n, rValues);
        }
        int result = TMath.factorial(n);
        for(int r : rValues) result /= TMath.factorial(r);
        return result;
    }


    public static int combination(int n, int r){
        if( !(n>=r && r>=0) ) throw new CombinationException(n,r);
        return TMath.factorial(n) / (TMath.factorial( n - r ) * TMath.factorial(r));
    }


    public static int repeatedCombination(int n, int r){
        if( !(n>=r && r>=0) ) throw new RepeatedCombinationException(n,r);
        return combination(n+r-1, r);
    }


    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }



    /**
     * Takes oldValue from the range [oldA, oldB] and returns what it would represent if it was in range [newA, newB]
     * @param oldRangeLeft start value of old interval (left value)
     * @param oldRangeRight end value of old interval (right value)
     * @param newRangeLeft start value of new interval (left value)
     * @param newRangeRight end value of new interval (right value)
     * @param oldValue any value from the old interval aka [oldRangeLeft, oldRangeRight]
     * @return The new value of oldX in the new interval aka [newRangeLeft, newRangeRight]
     */
    public static double mapRange(double oldRangeLeft, double oldRangeRight, double newRangeLeft, double newRangeRight, double oldValue){
        return newRangeLeft + ( (oldValue-oldRangeLeft) * (newRangeRight-newRangeLeft) ) / (oldRangeRight - oldRangeLeft);
    }



    /**  @see #mapRange(double, double, double, double, double)  */
    public static double mapRange(TRange oldRange, TRange newRange, double oldValue){
        return mapRange(oldRange.left, oldRange.right, newRange.left, newRange.right, oldValue);
    }



    /**
     * Takes oldValue from [-1,1] and returns what it would be in [0,1]
     * @param oldValue any value from [-1,1] interval
     * @return the value of oldX in [0,1] interval
     */
    public static double mapRange(double oldValue){
        return mapRange(-1d, 1d, 0d, 1d, oldValue);
    }



    /**
     * This method is A lot faster than using '(int) Math.floor(x)'
     * @param x any double value
     * @return floor(x)
     */
    public static int floor(double x) {
        int xi = (int) x;
        return (x < xi) ? (xi - 1) : xi;
    }




    /*  ---------------  */
    /*  SPECIAL METHODS  */
    /*  ---------------  */



    /**
     * Stringifies the output of {@link com.telek.telekmath.special.DiscreteMath#primeFactorization(long)}
     * @param factors output of DiscreteMath.primeFactorization(long)
     * @return factors in a string form
     */
    public static String stringifyFactors(HashMap<Long,Long> factors){
        StringBuilder sb = new StringBuilder();
        for(Long key : factors.keySet()) sb.append(String.format("%d^%d * ", key, factors.get(key)));
        return sb.substring(0, sb.length()-2);
    }



    /*  -------  */
    /*  HELPERS  */
    /*  -------  */


    // APACHE COMMONS MATH
    private static final double[] LANCZOS = {
            0.99999999999999709182,
            57.156235665862923517,
            -59.597960355475491248,
            14.136097974741747174,
            -0.49191381609762019978,
            .33994649984811888699e-4,
            .46523628927048575665e-4,
            -.98374475304879564677e-4,
            .15808870322491248884e-3,
            -.21026444172410488319e-3,
            .21743961811521264320e-3,
            -.16431810653676389022e-3,
            .84418223983852743293e-4,
            -.26190838401581408670e-4,
            .36899182659531622704e-5,
    };


    // APACHE COMMONS MATH
    private static double logBeta(double a, double b) {
        double ret;

        if (Double.isNaN(a) || Double.isNaN(b) || a <= 0.0 || b <= 0.0) {
            ret = Double.NaN;
        }
        else {
            ret = logGamma(a) + logGamma(b) - logGamma(a + b);
        }

        return ret;
    }


    // APACHE COMMONS MATH
    private static double logGamma(double x) {
        double ret;

        if (Double.isNaN(x) || (x <= 0.0)) {
            ret = Double.NaN;
        } else {
            double g = 607.0 / 128.0;

            double sum = 0.0;
            for (int i = LANCZOS.length - 1; i > 0; --i) {
                sum = sum + (LANCZOS[i] / (x + i));
            }
            sum = sum + LANCZOS[0];

            double tmp = x + g + .5;
            ret = ( (x + .5) * Math.log(tmp) ) - tmp + TMathConstants.HALF_LOG_2_PI + Math.log(sum / x);
        }

        return ret;
    }



}
