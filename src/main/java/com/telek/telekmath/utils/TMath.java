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
package com.telek.telekmath.utils;


import com.telek.telekmath.core.constants.TMathConstants;
import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.utils.TelekMathException.*;



/**
 * A class that has a lot of utility functions. <br>
 * Few methods in this class were written by <a href="https://github.com/tommyettinger">Tommy Ettinger</a>. <br>
 * Few methods in this class were taken from Apache Commons Math, most of these methods were <b>changed</b> in
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
        if(exponent == 0) return 1;
        if(exponent == 1) return base;
        int result = 1;
        for (int i = 0; i < exponent; i++)
            result *= base;
        return result;
    }



    /**
     * Fast factorial function that only works with integers <br>
     * This method only works for num < 14.
     * @param num any integer
     * @return factorial(num)
     */
    public static int ifactorial(int num){
        return (num != 0) ? num * ifactorial(num-1) : 1;
    }



    /**
     * Uses the {@link #gamma(double)} function to calculate the factorial.
     * @param num any number
     * @return factorial(num)
     */
    public static double factorial(double num){
        return gamma(num + 1);
    }




    public static int permutation(int n, int r){
        if( !(n>=r && r>=0) ) throw new PermutationException(n, r);
        return TMath.ifactorial(n) / TMath.ifactorial(n - r);
    }



    public static int repeatedPermutation(int n, int... rValues){
        for(int r : rValues) {
            if( !(n >= r) ) throw new RepeatedPermutationException(n, rValues);
            if( !(r >= 0) ) throw new RepeatedPermutationException(n, rValues);
        }
        int result = TMath.ifactorial(n);
        for(int r : rValues) result /= TMath.ifactorial(r);
        return result;
    }


    public static int combination(int n, int r){
        if( !(n>=r && r>=0) ) throw new CombinationException(n,r);
        return TMath.ifactorial(n) / (TMath.ifactorial( n - r ) * TMath.ifactorial(r));
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



    public static int abs(final int x) {
        return (x < 0) ? -x : x;
    }

    public static long abs(final long x) {
        return (x < 0L) ? -x : x;
    }

    public static float abs(final float x) {
        return (x < 0.0f) ? -x : (x == 0.0f) ? 0.0f : x; // -0.0 => +0.0
    }

    public static double abs(double x) {
        return (x < 0.0) ? -x : (x == 0.0) ? 0.0 : x; // -0.0 => +0.0
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


    public static double sqrt(double x) {
        return Math.sqrt(x);
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

    private static final int EXP_INT_TABLE_MAX_INDEX = 750;
    private static final double LN_2_A = 0.693147063255310059;
    private static final long HEX_40000000 = 0x40000000L; // 1073741824L
    private static final double LN_2_B = 1.17304635250823482e-7;
    private static final double TWO_POWER_52 = 4503599627370496.0;


    public static double gamma(double x){
        return exp( logGamma(x) );
    }


    public static double beta(double a, double b){
        return exp( logBeta(a,b) );
    }


    public static double regularizedBeta(double x, double funcAlpha, double funcBeta) {

        if (Double.isNaN(x) || Double.isNaN(funcAlpha) || Double.isNaN(funcBeta) || x < 0 || x > 1 || funcAlpha <= 0.0 || funcBeta <= 0.0)
            return Double.NaN;

        if (x > (funcAlpha + 1d) / (funcAlpha + funcBeta + 2d)) {
            return 1d - regularizedBeta(1d - x, funcBeta, funcAlpha);
        }

        // start of evaluate function
        double p0 = 1d, p1 = 1d, q0 = 0d, q1 = 1d;
        double c = p1 / q1;
        int n = 0;
        double relativeError = Double.MAX_VALUE;
        while (n < Integer.MAX_VALUE && relativeError > 10e-15) {
            ++n;

            double b;
            if (n % 2 == 0) {
                double m = n / 2d;
                b = (m * (funcBeta - m) * x) / ((funcAlpha + (2 * m) - 1) * (funcAlpha + (2 * m)));
            }
            else {
                double m = (n - 1.0) / 2.0;
                b = -((funcAlpha + m) * (funcAlpha + funcBeta + m) * x) / ((funcAlpha + (2 * m)) * (funcAlpha + (2 * m) + 1.0));
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

        return TMath.exp((funcAlpha * TMath.log(x)) + (funcBeta * TMath.log(1.0 - x)) - TMath.log(funcAlpha) - TMath.logBeta(funcAlpha, funcBeta)) *
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
            for (int i = ApacheArrays.LANCZOS.length - 1; i > 0; --i) {
                sum += (ApacheArrays.LANCZOS[i] / (x + i));
            }
            sum += ApacheArrays.LANCZOS[0];

            double tmp = x + 4.7421875d + .5;
            return ( (x + .5) * Math.log(tmp) ) - tmp + TMathConstants.HALF_LOG_2_PI + Math.log(sum / x);
        }
    }


    public static double pow(double x, double y) {
        final double lns[] = new double[2];

        if (y == 0.0) {
            return 1.0;
        }

        if (x != x) { // X is NaN
            return x;
        }


        if (x == 0) {
            long bits = Double.doubleToLongBits(x);
            if ((bits & 0x8000000000000000L) != 0) {
                // -zero
                long yi = (long) y;

                if (y < 0 && y == yi && (yi & 1) == 1) {
                    return Double.NEGATIVE_INFINITY;
                }

                if (y > 0 && y == yi && (yi & 1) == 1) {
                    return -0.0;
                }
            }

            if (y < 0) {
                return Double.POSITIVE_INFINITY;
            }
            if (y > 0) {
                return 0.0;
            }

            return Double.NaN;
        }

        if (x == Double.POSITIVE_INFINITY) {
            if (y != y) { // y is NaN
                return y;
            }
            if (y < 0.0) {
                return 0.0;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        }

        if (y == Double.POSITIVE_INFINITY) {
            if (x * x == 1.0) {
                return Double.NaN;
            }

            if (x * x > 1.0) {
                return Double.POSITIVE_INFINITY;
            } else {
                return 0.0;
            }
        }

        if (x == Double.NEGATIVE_INFINITY) {
            if (y != y) { // y is NaN
                return y;
            }

            if (y < 0) {
                long yi = (long) y;
                if (y == yi && (yi & 1) == 1) {
                    return -0.0;
                }

                return 0.0;
            }

            if (y > 0)  {
                long yi = (long) y;
                if (y == yi && (yi & 1) == 1) {
                    return Double.NEGATIVE_INFINITY;
                }

                return Double.POSITIVE_INFINITY;
            }
        }

        if (y == Double.NEGATIVE_INFINITY) {

            if (x * x == 1.0) {
                return Double.NaN;
            }

            if (x * x < 1.0) {
                return Double.POSITIVE_INFINITY;
            } else {
                return 0.0;
            }
        }

        /* Handle special case x<0 */
        if (x < 0) {
            // y is an even integer in this case
            if (y >= TWO_POWER_52 || y <= -TWO_POWER_52) {
                return pow(-x, y);
            }

            if (y == (long) y) {
                // If y is an integer
                return ((long)y & 1) == 0 ? pow(-x, y) : -pow(-x, y);
            } else {
                return Double.NaN;
            }
        }

        /* Split y into ya and yb such that y = ya+yb */
        double ya;
        double yb;
        if (y < 8e298 && y > -8e298) {
            double tmp1 = y * HEX_40000000;
            ya = y + tmp1 - tmp1;
            yb = y - ya;
        } else {
            double tmp1 = y * 9.31322574615478515625E-10;
            double tmp2 = tmp1 * 9.31322574615478515625E-10;
            ya = (tmp1 + tmp2 - tmp1) * HEX_40000000 * HEX_40000000;
            yb = y - ya;
        }

        /* Compute ln(x) */
        final double lores = log(x, lns);
        if (Double.isInfinite(lores)){ // don't allow this to be converted to NaN
            return lores;
        }

        double lna = lns[0];
        double lnb = lns[1];

        /* resplit lns */
        double tmp1 = lna * HEX_40000000;
        double tmp2 = lna + tmp1 - tmp1;
        lnb += lna - tmp2;
        lna = tmp2;

        // y*ln(x) = (aa+ab)
        final double aa = lna * ya;
        final double ab = lna * yb + lnb * ya + lnb * yb;

        lna = aa+ab;
        lnb = -(lna - aa - ab);

        double z = 1.0 / 120.0;
        z = z * lnb + (1.0 / 24.0);
        z = z * lnb + (1.0 / 6.0);
        z = z * lnb + 0.5;
        z = z * lnb + 1.0;
        z = z * lnb;

        final double result = exp(lna, z, null);
        //result = result + result * z;
        return result;
    }


    /**
     * Can be always used instead of Math.log(x)
     * @param x any val
     * @return ln(x)
     */
    public static double log(final double x) {
        return log(x, null);
    }


    public static double exp(double x) {
        return exp(x, 0.0, null);
    }


    public static double regularizedGammaP(double a, double x) {
        return regularizedGammaP(a, x, 10e-15, Integer.MAX_VALUE);
    }


    /* HELPERS FOR APACHE */


    private static double exp(double x, double extra, double[] hiPrec) {
        double intPartA;
        double intPartB;
        int intVal;
        if (x < 0.0) {
            intVal = (int) -x;

            if (intVal > 746) {
                if (hiPrec != null) {
                    hiPrec[0] = 0.0;
                    hiPrec[1] = 0.0;
                }
                return 0.0;
            }

            if (intVal > 709) {
                final double result = exp(x+40.19140625, extra, hiPrec) / 285040095144011776.0;
                if (hiPrec != null) {
                    hiPrec[0] /= 285040095144011776.0;
                    hiPrec[1] /= 285040095144011776.0;
                }
                return result;
            }

            if (intVal == 709) {
                final double result = exp(x+1.494140625, extra, hiPrec) / 4.455505956692756620;
                if (hiPrec != null) {
                    hiPrec[0] /= 4.455505956692756620;
                    hiPrec[1] /= 4.455505956692756620;
                }
                return result;
            }

            intVal++;

            intPartA = ApacheArrays.EXP_INT_TABLE_A[EXP_INT_TABLE_MAX_INDEX-intVal];
            intPartB = ApacheArrays.EXP_INT_TABLE_B[EXP_INT_TABLE_MAX_INDEX-intVal];

            intVal = -intVal;
        } else {
            intVal = (int) x;

            if (intVal > 709) {
                if (hiPrec != null) {
                    hiPrec[0] = Double.POSITIVE_INFINITY;
                    hiPrec[1] = 0.0;
                }
                return Double.POSITIVE_INFINITY;
            }

            intPartA = ApacheArrays.EXP_INT_TABLE_A[EXP_INT_TABLE_MAX_INDEX+intVal];
            intPartB = ApacheArrays.EXP_INT_TABLE_B[EXP_INT_TABLE_MAX_INDEX+intVal];
        }

        final int intFrac = (int) ((x - intVal) * 1024.0);
        final double fracPartA = ApacheArrays.EXP_FRAC_TABLE_A[intFrac];
        final double fracPartB = ApacheArrays.EXP_FRAC_TABLE_B[intFrac];
        final double epsilon = x - (intVal + intFrac / 1024.0);
        double z = 0.04168701738764507;
        z = z * epsilon + 0.1666666505023083;
        z = z * epsilon + 0.5000000000042687;
        z = z * epsilon + 1.0;
        z = z * epsilon + -3.940510424527919E-20;
        double tempA = intPartA * fracPartA;
        double tempB = intPartA * fracPartB + intPartB * fracPartA + intPartB * fracPartB;
        final double tempC = tempB + tempA;
        final double result;
        if (extra != 0.0) {
            result = tempC*extra*z + tempC*extra + tempC*z + tempB + tempA;
        } else {
            result = tempC*z + tempB + tempA;
        }

        if (hiPrec != null) {
            hiPrec[0] = tempA;
            hiPrec[1] = tempC*extra*z + tempC*extra + tempC*z + tempB;
        }

        return result;
    }

    private static double log(final double x, final double[] hiPrec) {
        if (x==0) { // Handle special case of +0/-0
            return Double.NEGATIVE_INFINITY;
        }
        long bits = Double.doubleToLongBits(x);

        if ((bits & 0x8000000000000000L) != 0 || x != x) {
            if (x != 0.0) {
                if (hiPrec != null) {
                    hiPrec[0] = Double.NaN;
                }

                return Double.NaN;
            }
        }

        if (x == Double.POSITIVE_INFINITY) {
            if (hiPrec != null) {
                hiPrec[0] = Double.POSITIVE_INFINITY;
            }

            return Double.POSITIVE_INFINITY;
        }

        int exp = (int)(bits >> 52)-1023;

        if ((bits & 0x7ff0000000000000L) == 0) {
            if (x == 0) {
                if (hiPrec != null) {
                    hiPrec[0] = Double.NEGATIVE_INFINITY;
                }

                return Double.NEGATIVE_INFINITY;
            }

            bits <<= 1;
            while ( (bits & 0x0010000000000000L) == 0) {
                exp--;
                bits <<= 1;
            }
        }


        if (exp == -1 || exp == 0) {
            if (x < 1.01 && x > 0.99 && hiPrec == null) {
                double xa = x - 1.0;
                double xb = xa - x + 1.0;
                double tmp = xa * HEX_40000000;
                double aa = xa + tmp - tmp;
                double ab = xa - aa;
                xa = aa;
                xb = ab;

                double ya = ApacheArrays.LN_QUICK_COEF[ApacheArrays.LN_QUICK_COEF.length-1][0];
                double yb = ApacheArrays.LN_QUICK_COEF[ApacheArrays.LN_QUICK_COEF.length-1][1];

                for (int i = ApacheArrays.LN_QUICK_COEF.length - 2; i >= 0; i--) {
                    aa = ya * xa;
                    ab = ya * xb + yb * xa + yb * xb;
                    tmp = aa * HEX_40000000;
                    ya = aa + tmp - tmp;
                    yb = aa - ya + ab;

                    aa = ya + ApacheArrays.LN_QUICK_COEF[i][0];
                    ab = yb + ApacheArrays.LN_QUICK_COEF[i][1];
                    tmp = aa * HEX_40000000;
                    ya = aa + tmp - tmp;
                    yb = aa - ya + ab;
                }

                aa = ya * xa;
                ab = ya * xb + yb * xa + yb * xb;
                tmp = aa * HEX_40000000;
                ya = aa + tmp - tmp;
                yb = aa - ya + ab;

                return ya + yb;
            }
        }

        double lnm[] = ApacheArrays.LN_MANT[(int)((bits & 0x000ffc0000000000L) >> 42)];

        double epsilon = (bits & 0x3ffffffffffL) / (TWO_POWER_52 + (bits & 0x000ffc0000000000L));

        double lnza = 0.0;
        double lnzb = 0.0;

        if (hiPrec != null) {
            double tmp = epsilon * HEX_40000000;
            double aa = epsilon + tmp - tmp;
            double ab = epsilon - aa;
            double xa = aa;
            double xb = ab;

            double numer = bits & 0x3ffffffffffL;
            double denom = TWO_POWER_52 + (bits & 0x000ffc0000000000L);
            aa = numer - xa*denom - xb * denom;
            xb += aa / denom;

            double ya = ApacheArrays.LN_HI_PREC_COEF[ApacheArrays.LN_HI_PREC_COEF.length-1][0];
            double yb = ApacheArrays.LN_HI_PREC_COEF[ApacheArrays.LN_HI_PREC_COEF.length-1][1];

            for (int i = ApacheArrays.LN_HI_PREC_COEF.length - 2; i >= 0; i--) {
                aa = ya * xa;
                ab = ya * xb + yb * xa + yb * xb;
                tmp = aa * HEX_40000000;
                ya = aa + tmp - tmp;
                yb = aa - ya + ab;

                aa = ya + ApacheArrays.LN_HI_PREC_COEF[i][0];
                ab = yb + ApacheArrays.LN_HI_PREC_COEF[i][1];
                tmp = aa * HEX_40000000;
                ya = aa + tmp - tmp;
                yb = aa - ya + ab;
            }

            aa = ya * xa;
            ab = ya * xb + yb * xa + yb * xb;

            lnza = aa + ab;
            lnzb = -(lnza - aa - ab);
        } else {
            lnza = -0.16624882440418567;
            lnza = lnza * epsilon + 0.19999954120254515;
            lnza = lnza * epsilon + -0.2499999997677497;
            lnza = lnza * epsilon + 0.3333333333332802;
            lnza = lnza * epsilon + -0.5;
            lnza = lnza * epsilon + 1.0;
            lnza = lnza * epsilon;
        }

        double a = LN_2_A*exp;
        double b = 0.0;
        double c = a+lnm[0];
        double d = -(c-a-lnm[0]);
        a = c;
        b = b + d;

        c = a + lnza;
        d = -(c - a - lnza);
        a = c;
        b = b + d;

        c = a + LN_2_B*exp;
        d = -(c - a - LN_2_B*exp);
        a = c;
        b = b + d;

        c = a + lnm[1];
        d = -(c - a - lnm[1]);
        a = c;
        b = b + d;

        c = a + lnzb;
        d = -(c - a - lnzb);
        a = c;
        b = b + d;

        if (hiPrec != null) {
            hiPrec[0] = a;
            hiPrec[1] = b;
        }

        return a + b;
    }

    private static double regularizedGammaP(double a, double x, double epsilon, int maxIterations) {
        double ret;

        if (Double.isNaN(a) || Double.isNaN(x) || (a <= 0.0) || (x < 0.0)) {
            ret = Double.NaN;
        } else if (x == 0.0) {
            ret = 0.0;
        } else if (x >= a + 1) {
            // use regularizedGammaQ because it should converge faster in this
            // case.
            ret = 1.0 - regularizedGammaQ(a, x, epsilon, maxIterations);
        } else {
            // calculate series
            double n = 0.0; // current element index
            double an = 1.0 / a; // n-th element in the series
            double sum = an; // partial sum
            while (Math.abs(an/sum) > epsilon &&
                    n < maxIterations &&
                    sum < Double.POSITIVE_INFINITY) {
                // compute next element in the series
                n = n + 1.0;
                an = an * (x / (a + n));

                // update partial sum
                sum = sum + an;
            }
            if (n >= maxIterations) {
                throw new RuntimeException("MaxCountExceededException from Apache");
            } else if (Double.isInfinite(sum)) {
                ret = 1.0;
            } else {
                ret = exp(-x + (a * log(x)) - logGamma(a)) * sum;
            }
        }

        return ret;
    }

    private static double regularizedGammaQ(double regA, double x, double epsilon, int maxIterations) {
        double ret;

        if (Double.isNaN(regA) || Double.isNaN(x) || (regA <= 0.0) || (x < 0.0)) {
            ret = Double.NaN;
        } else if (x == 0.0) {
            ret = 1.0;
        } else if (x < regA + 1.0) {
            // use regularizedGammaP because it should converge faster in this
            // case.
            ret = 1.0 - regularizedGammaP(regA, x, epsilon, maxIterations);
        } else {
            // create continued fraction

            // start of evaluate function
            {
                double p0 = 1.0;
                double p1 = 1d - regA + x;
                double q0 = 0.0;
                double q1 = 1.0;
                double c = p1 / q1;
                int n = 0;
                double relativeError = Double.MAX_VALUE;
                while (n < maxIterations && relativeError > epsilon) {
                    ++n;
                    double a = ((2.0 * n) + 1.0) - regA + x;
                    double b = n * (regA - n);
                    double p2 = a * p1 + b * p0;
                    double q2 = a * q1 + b * q0;
                    boolean infinite = false;
                    if (Double.isInfinite(p2) || Double.isInfinite(q2)) {
                        double scaleFactor = 1d;
                        double lastScaleFactor = 1d;
                        final int maxPower = 5;
                        final double scale = Math.max(a,b);
                        if (scale <= 0) {  // Can't scale
                            throw new RuntimeException("ConvergenceException from Apache code");
                        }
                        infinite = true;
                        for (int i = 0; i < maxPower; i++) {
                            lastScaleFactor = scaleFactor;
                            scaleFactor *= scale;
                            if (a != 0.0 && a > b) {
                                p2 = p1 / lastScaleFactor + (b / scaleFactor * p0);
                                q2 = q1 / lastScaleFactor + (b / scaleFactor * q0);
                            } else if (b != 0) {
                                p2 = (a / scaleFactor * p1) + p0 / lastScaleFactor;
                                q2 = (a / scaleFactor * q1) + q0 / lastScaleFactor;
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

                    // prepare for next iteration
                    c = p2 / q2;
                    p0 = p1;
                    p1 = p2;
                    q0 = q1;
                    q1 = q2;
                }

                if (n >= Integer.MAX_VALUE) throw new RuntimeException("MaxCountExceededException from Apache code");
                ret = 1.0 / c;
            }

            // end of eval

            ret = exp(-x + (regA * log(x)) - logGamma(regA)) * ret;
        }

        return ret;
    }




}
