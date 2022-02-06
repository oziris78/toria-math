/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.telek.telekmath;

import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.functions.TRange;
import com.telek.telekmath.exceptions.InvalidValueException;
import com.telek.telekmath.exceptions.RepeatedPermutationException;
import com.telek.telekmath.exceptions.TelekMathException.*;




/**
 * A class that has a lot of utility functions.
 * beta(a,b) and logGamma(x) methods are taken from Apache Commons Math.
 * atn(), atan2(), acos(), asin() methods were written by Tommy Ettinger, https://github.com/tommyettinger
 */
public final class TMath {


    //////////////
    /*  FIELDS  */
    //////////////

    /** Defines how many terms from the Taylor expansion will be used to calculate {@link #hypergeometric(double, double, double, double)}.
     * Increase this value to get more accurate results.
     */
    public static int HYPERGEO_FUNC_ITERATIONS = 200;

    /* No constructor */
    private TMath(){}


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
        return Math.exp( logBeta(a,b) );
    }



    /**
     * @param z any value
     * @return erf(z)
     */
    public static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));
        double ans = 1 - t * Math.exp(-z*z - 1.26551223 + t * (1.00002368 + t *
                (0.37409196 + t * (0.09678418 + t * (-0.18628806 + t * (0.27886807 +
                        t * (-1.13520398 + t * (1.48851587 + t * (-0.82215223 + t * (0.17087277))))))))));
        return Math.copySign(ans, z);
    }


    /**
     * This uses an algorithm by Peter John Acklam, as implemented by Sherali Karimov.
     * <a href="https://web.archive.org/web/20150910002142/http://home.online.no/~pjacklam/notes/invnorm/impl/karimov/StatUtil.java">
     *     Original source</a>.
     * <a href="https://web.archive.org/web/20151030215612/http://home.online.no/~pjacklam/notes/invnorm/">
     *     Information on the algorithm</a>.
     * <a href="https://en.wikipedia.org/wiki/Probit_function">Wikipedia's page on the probit function</a> may help, but
     * is more likely to just be confusing.
     * <br>
     *
     * @param d should be between 0 and 1, exclusive, but other values are tolerated
     * @return a normal-distributed double centered on 0.0; all results will be between -38.5 and 38.5, both inclusive
     */
    public static double probit(double d) {
        if (d <= 0 || d >= 1) {
            return Math.copySign(38.5, d - 0.5);
        }
        else if (d < 0.02425) {
            final double q = Math.sqrt(-2.0 * Math.log(d));
            return (((((-7.784894002430293e-03 * q + -3.223964580411365e-01) * q + -2.400758277161838e+00) *
                    q + -2.549732539343734e+00) * q + 4.374664141464968e+00) * q + 2.938163982698783e+00) / (
                    (((7.784695709041462e-03 * q + 3.224671290700398e-01) * q + 2.445134137142996e+00) *
                            q + 3.754408661907416e+00) * q + 1.0);
        }
        else if (0.97575 < d) {
            final double q = Math.sqrt(-2.0 * Math.log(1 - d));
            return -(((((-7.784894002430293e-03 * q + -3.223964580411365e-01) * q + -2.400758277161838e+00) *
                    q + -2.549732539343734e+00) * q + 4.374664141464968e+00) * q + 2.938163982698783e+00) / (
                    (((7.784695709041462e-03 * q + 3.224671290700398e-01) * q + 2.445134137142996e+00) *
                            q + 3.754408661907416e+00) * q + 1.0);
        }
        else {
            final double q = d - 0.5;
            final double r = q * q;
            return (((((-3.969683028665376e+01 * r + 2.209460984245205e+02) * r + -2.759285104469687e+02)
                    * r + 1.383577518672690e+02) * r + -3.066479806614716e+01) * r + 2.506628277459239e+00) * q / (
                    ((((-5.447609879822406e+01 * r + 1.615858368580409e+02) * r + -1.556989798598866e+02)
                            * r + 6.680131188771972e+01) * r + -1.328068155288572e+01) * r + 1.0);
        }
    }



    /**
     * This method has around 1e-8 error. <br>
     * Written by <a href="https://www.github.com/oziris78">Oğuzhan Topaloğlu</a> <br>
     * Look here for more info: <a href="https://en.wikipedia.org/wiki/Hypergeometric_function">
     *     Wikipedia</a> <br>
     * @param a any value
     * @param b any value
     * @param c any positive value
     * @param z any value in range [-1,1]
     * @return <sub>2</sub>F<sub>1</sub>(a,b;c;z)
     */
    public static double hypergeometric(double a, double b, double c, double z){
        if(!(Math.abs(z) < 1) || c <= 0) {
            return Double.NaN;
        }

        double res = 1d;
        double last = a * b * z / c;
        int i = 2;
        res += last;
        while(i < HYPERGEO_FUNC_ITERATIONS){
            last *= (a+i-1) * (b+i-1) * z;
            last /= (c+i-1);
            last /= i; // for factorial
            res += last;
            i++;
        }
        return res;
    }





    /**
     * Works in linear time aka O(n)
     * @param n any long
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
        if(exponent < 0) throw new InvalidValueException("exponent", exponent);
        return (exponent != 0) ? base * pow(base,exponent-1) : 1;
    }



    /**
     * Fast factorial function that only works with integers <br>
     * This method only works for num < 14.
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
    public static double mapRange(double oldRangeLeft, double oldRangeRight,
                                  double newRangeLeft, double newRangeRight, double oldValue){
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



    /**
     * This method was written by Tommy Ettinger.
     * @param a any float
     * @return arcsin(a), it is faster than Math.asin(a) but not as accurate
     */
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


    /**
     * This method was written by Tommy Ettinger.
     * @param a any float
     * @return arccos(a), it is faster than Math.acos(a) but not as accurate
     */
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




    /*  ---------------  */
    /*  SPECIAL METHODS  */
    /*  ---------------  */



    public static boolean areEqual(float f1, float f2){
        return Math.abs(f1 - f2) < TMathConstants.EPSILON;
    }

    public static boolean areEqual(double d1, double d2){
        if(Double.isNaN(d1) && Double.isNaN(d2)) return true;
        if(Double.isNaN(d1) && !Double.isNaN(d2)) return false;
        if(!Double.isNaN(d1) && Double.isNaN(d2)) return false;
        return Math.abs(d1 - d2) < TMathConstants.EPSILON;
    }




    /*  -------  */
    /*  HELPERS  */
    /*  -------  */


    // APACHE COMMONS MATH
    private static final double[] LANCZOS = {
        0.99999999999999709182, 57.156235665862923517, -59.597960355475491248,
        14.136097974741747174, -0.49191381609762019978, .33994649984811888699e-4,
        .46523628927048575665e-4, -.98374475304879564677e-4, .15808870322491248884e-3,
        -.21026444172410488319e-3, .21743961811521264320e-3, -.16431810653676389022e-3,
        .84418223983852743293e-4, -.26190838401581408670e-4, .36899182659531622704e-5,
    };


    // APACHE COMMONS MATH
    private static double logBeta(double a, double b) {
        if (Double.isNaN(a) || Double.isNaN(b) || a <= 0.0 || b <= 0.0)
            return Double.NaN;
        else
            return logGamma(a) + logGamma(b) - logGamma(a + b);
    }


    // APACHE COMMONS MATH
    private static double logGamma(double x) {
        if (Double.isNaN(x) || (x <= 0.0)) {
            return Double.NaN;
        }
        else {
            double sum = 0.0;
            for (int i = LANCZOS.length - 1; i > 0; --i) {
                sum += (LANCZOS[i] / (x + i));
            }
            sum += LANCZOS[0];

            double tmp = x + 4.7421875d + .5;
            return ( (x + .5) * Math.log(tmp) ) - tmp + TMathConstants.HALF_LOG_2_PI + Math.log(sum / x);
        }
    }




}
