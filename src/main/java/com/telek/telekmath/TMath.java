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
import com.telek.telekmath.exceptions.*;
import com.telek.telekmath.exceptions.TelekMathException.*;




/**
 * A class that has a lot of utility functions. <br>
 * Few methods in this class were written by <a href="https://github.com/tommyettinger">Tommy Ettinger</a>. <br>
 * Few methods in this class were taken from Apache Commons Math, most of these methods were <b>CHANGED</b> in
 * many ways to fit telek-math. <br>
 * To see every method that wasn't written by <a href="https://github.com/oziris78">me</a>, see the
 * sections below in this class that were seperated by big multiline comments.
 */
public final class TMath {



    //////////////
    /*  FIELDS  */
    //////////////

    // empty for now



    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////


    /* No constructor */
    private TMath(){}


    ////////////////////
    /*  MATH METHODS  */
    ////////////////////



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



    /*  ---------------  */
    /*  SPECIAL METHODS  */
    /*  ---------------  */



    public static boolean areEqual(double d1, double d2){
        boolean b1 = Double.isNaN(d1);
        boolean b2 = Double.isNaN(d2);
        if(b1 || b2) return b1 && b2;

        boolean b3 = Double.isInfinite(d1);
        boolean b4 = Double.isInfinite(d2);
        if(b3 || b4) return b3 && b4;

        return Math.abs(d1 - d2) < TMathConstants.EPSILON;
    }




    /*  -------  */
    /*  HELPERS  */
    /*  -------  */


    // empty for now




    /////////////////////////////////////////
    /////////////////////////////////////////
    /*  METHODS TAKEN FROM TOMMY ETTINGER  */
    /////////////////////////////////////////
    /////////////////////////////////////////



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




    //////////////////////////////////////////////
    //////////////////////////////////////////////
    /*  METHODS TAKEN FROM APACHE COMMONS MATH  */
    //////////////////////////////////////////////
    //////////////////////////////////////////////


    private static final double[] LANCZOS = {
        0.99999999999999709182, 57.156235665862923517, -59.597960355475491248,
        14.136097974741747174, -0.49191381609762019978, .33994649984811888699e-4,
        .46523628927048575665e-4, -.98374475304879564677e-4, .15808870322491248884e-3,
        -.21026444172410488319e-3, .21743961811521264320e-3, -.16431810653676389022e-3,
        .84418223983852743293e-4, -.26190838401581408670e-4, .36899182659531622704e-5,
    };


    public static double gamma(double x){
        return Math.exp( logGamma(x) );
    }


    public static double beta(double a, double b){
        return Math.exp( logBeta(a,b) );
    }


    public static double regularizedBeta(double x, double regA, double regB) {

        if (Double.isNaN(x) || Double.isNaN(regA) || Double.isNaN(regB) || x < 0 || x > 1 || regA <= 0.0 || regB <= 0.0)
            return Double.NaN;

        if (x > (regA + 1d) / (regA + regB + 2d)) {
            return 1d - regularizedBeta(1d - x, regB, regA);
        }

        // start of evaluate function
        double p0 = 1d, p1 = 1d, q0 = 0d, q1 = 1d;
        double c = p1 / q1;
        int n = 0;
        double relativeError = Double.MAX_VALUE;
        while (n < Integer.MAX_VALUE && relativeError > 1E-8) {
            ++n;

            double b;
            if (n % 2 == 0) {
                double m = n / 2d;
                b = (m * (regB - m) * x) / ((regA + (2 * m) - 1) * (regA + (2 * m)));
            }
            else {
                double m = (n - 1.0) / 2.0;
                b = -((regA + m) * (regA + regB + m) * x) / ((regA + (2 * m)) * (regA + (2 * m) + 1.0));
            }

            double p2 = p1 + b * p0;
            double q2 = q1 + b * q0;
            boolean infinite = false;
            if (Double.isInfinite(p2) || Double.isInfinite(q2)) {
                double scaleFactor = 1d;
                double lastScaleFactor;
                final int maxPower = 5;
                final double scale = Math.max(1d, b);
                for (int i = 0; i < maxPower; i++) {
                    lastScaleFactor = scaleFactor;
                    scaleFactor *= scale;
                    if (1d > b) {
                        p2 = p1 / lastScaleFactor + (b / scaleFactor * p0);
                        q2 = q1 / lastScaleFactor + (b / scaleFactor * q0);
                    } else if (b != 0) {
                        p2 = (1d / scaleFactor * p1) + p0 / lastScaleFactor;
                        q2 = (1d / scaleFactor * q1) + q0 / lastScaleFactor;
                    }
                    infinite = Double.isInfinite(p2) || Double.isInfinite(q2);
                    if (!infinite) {
                        break;
                    }
                }
            }

            if (infinite) throw new RuntimeException("ConvergenceException from Apache code");
            double r = p2 / q2;
            if (Double.isNaN(r)) throw new RuntimeException("ConvergenceException from Apache code");
            relativeError = Math.abs(r / c - 1.0);

            c = p2 / q2;
            p0 = p1;
            p1 = p2;
            q0 = q1;
            q1 = q2;
        }

        if (n >= Integer.MAX_VALUE) throw new RuntimeException("MaxCountExceededException from Apache code");
        // end of eval

        return Math.exp((regA * Math.log(x)) + (regB * Math.log(1.0 - x)) - Math.log(regA) - TMath.logBeta(regA, regB)) *
                1d / c;

    }


    public static double logBeta(double a, double b) {
        if (Double.isNaN(a) || Double.isNaN(b) || a <= 0.0 || b <= 0.0)
            return Double.NaN;
        else
            return logGamma(a) + logGamma(b) - logGamma(a + b);
    }


    public static double logGamma(double x) {
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
