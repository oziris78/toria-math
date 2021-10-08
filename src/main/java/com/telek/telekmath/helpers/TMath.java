package com.telek.telekmath.helpers;

import org.apache.commons.math3.special.*;


/**
 * A class that has a lot of utility functions.
 * atn(), atan2(), acos(), asin() methods were written by Tommy Ettinger, https://github.com/tommyettinger
 */
public final class TMath {


    /**
     * Uses Apache Common Math's Gamma.logGamma function to evaluate gamma(x)
     * @param x any double value
     * @return gamma(x)
     */
    public static double gamma(double x){
        return Math.exp( Gamma.logGamma(x) );
    }


    /**
     * Uses Apache Common Math's Beta.logBeta function to evaluate beta(a,b)
     * @param a any double value
     * @param b any double value
     * @return beta(a,b)
     */
    public static double beta(double a, double b){
        return Math.exp( Beta.logBeta(a,b) ); //
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
        if( !(n>=r && r>=0) ) throw new RuntimeException(String.format("Invalid values for permutation for perm(%d,%d)", n, r));
        return TMath.factorial(n) / TMath.factorial( n - r );
    }


    public static int repeatedPermutation(int n, int... rValues){
        for(int r : rValues) {
            if( !(n >= r) ) throw new RuntimeException( getErrorStringForRepeatedPermutation(n, rValues) );
            if( !(r >= 0) ) throw new RuntimeException( getErrorStringForRepeatedPermutation(n, rValues) );
        }
        int result = TMath.factorial(n);
        for(int r : rValues) result /= TMath.factorial(r);
        return result;
    }

    public static int combination(int n, int r){
        if( !(n>=r && r>=0) ) throw new RuntimeException(String.format("Invalid values for combination for comb(%d,%d)", n, r));
        return TMath.factorial(n) / (TMath.factorial( n - r ) * TMath.factorial(r));
    }

    public static int repeatedCombination(int n, int r){
        if( !(n>=r && r>=0) ) throw new RuntimeException(String.format("Invalid values for repeated combination for comb(%d,%d)", n, r));
        return combination(n+r-1, r);
    }


    /**
     * Takes oldX from the range [oldA, oldB] and returns what it would represent if it was in range [newA, newB]
     * @param oldRangeLeft start value of old interval (left value)
     * @param oldRangeRight end value of old interval (right value)
     * @param newRangeLeft start value of new interval (left value)
     * @param newRangeRight end value of new interval (right value)
     * @param oldX any value from the old interval aka [oldRangeLeft, oldRangeRight]
     * @return The new value of oldX in the new interval aka [newRangeLeft, newRangeRight]
     */
    public static double mapRange(double oldRangeLeft, double oldRangeRight, double newRangeLeft, double newRangeRight, double oldX){
        return newRangeLeft + ( (oldX-oldRangeLeft) * (newRangeRight-newRangeLeft) ) / (oldRangeRight - oldRangeLeft);
    }


    /**
     * Takes oldX from [-1,1] and returns what it would be in [0,1]
     * @param oldX any value from [-1,1] interval
     * @return the value of oldX in [0,1] interval
     */
    public static double mapRange(double oldX){
        return mapRange(-1d, 1d, 0d, 1d, oldX);
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



    public static float acos(float a) {
        float a2 = a * a;  // a squared
        float a3 = a * a2; // a cubed
        if (a >= 0f) {
            return (float) Math.sqrt(1f - a) *
                    (1.5707288f - 0.2121144f * a + 0.0742610f * a2 - 0.0187293f * a3);
        }
        else {
            return 3.14159265358979323846f - (float) Math.sqrt(1f + a) *
                    (1.5707288f + 0.2121144f * a + 0.0742610f * a2 + 0.0187293f * a3);
        }
    }


    public static float asin(float a) {
        float a2 = a * a;  // a squared
        float a3 = a * a2; // a cubed
        if (a >= 0f) {
            return 1.5707963267948966f - (float) Math.sqrt(1f - a) *
                    (1.5707288f - 0.2121144f * a + 0.0742610f * a2 - 0.0187293f * a3);
        }
        else {
            return -1.5707963267948966f + (float) Math.sqrt(1f + a) *
                    (1.5707288f + 0.2121144f * a + 0.0742610f * a2 + 0.0187293f * a3);
        }
    }


    public static float atan2(final float y, float x) {
        float n = y / x;
        if(n != n) n = (y == x ? 1f : -1f); // if both y and x are infinite, n would be NaN
        else if(n - n != n - n) x = 0f; // if n is infinite, y is infinitely larger than x.
        if(x > 0)
            return atn(n);
        else if(x < 0) {
            if(y >= 0) return atn(n) + 3.14159265358979323846f;
            else return atn(n) - 3.14159265358979323846f;
        }
        else if(y > 0) return x + 1.5707963267948966f;
        else if(y < 0) return x - 1.5707963267948966f;
        else return x + y; // returns 0 for 0,0 or NaN if either y or x is NaN
    }


    private static float atn(final float i) {
        final float n = Math.abs(i);
        final float c = (n - 1f) / (n + 1f);
        final float c2 = c * c;
        final float c3 = c * c2;
        final float c5 = c3 * c2;
        final float c7 = c5 * c2;
        return Math.copySign(0.7853981633974483f + (0.999215f * c - 0.3211819f * c3 + 0.1462766f * c5 - 0.0389929f * c7), i);
    }



    /*  HELPERS  */

    private static String getErrorStringForRepeatedPermutation(int n, int... rValues){
        StringBuilder sbError = new StringBuilder( String.format("Invalid values for repeated permutation for perm(%d,", n) );
        for(int i = 0; i < rValues.length; i++) {
            if(i+1 == rValues.length) sbError.append(rValues[i] + ")");
            else sbError.append(rValues[i] + ",");
        }
        return sbError.toString();
    }


}
