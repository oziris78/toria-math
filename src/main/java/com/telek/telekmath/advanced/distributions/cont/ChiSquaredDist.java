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

package com.telek.telekmath.advanced.distributions.cont;


import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.utils.TelekMathException.*;
import com.telek.telekmath.utils.TMath;



public class ChiSquaredDist  {


    /* No constructor */
    private ChiSquaredDist(){}


    ///////////////
    /*  METHODS  */
    ///////////////

    public static double expectedValue(double degOfFreedom){
        return degOfFreedom;
    }

    public static double variance(double degOfFreedom){
        return 2d * degOfFreedom;
    }

    public static double getAlpha(double degOfFreedom){
        return degOfFreedom / 2d;
    }

    public static double getDegreeOfFreedom(double alpha){
        return 2d * alpha;
    }


    /**
     * @param v degrees of freedom
     * @param x any value
     * @return probability density function (PDF) result for v and x
     */
    public static double density(double v, double x){
        if(x < 0) return 0;
        double alpha = v / 2d;
        return TMath.pow(x / 2d, alpha - 1d) / 2d * TMath.exp(-x / 2d) / TMath.gamma(alpha);
    }



    /**
     * Returns P(X <= x) <br>
     * @param v degrees of freedom
     * @param x any value
     * @return cumulative density function (CDF) result
     */
    public static double cumulativeProbability(double v, double x){
        if(x <= 0)
            return 0;

        return TMath.regularizedGammaP(v / 2d, x / 2d);
    }



    /**
     * Left tailed inverse cumulative density function (ICDF). <br>
     * For degrees of freedom values higher than 10_000_000 this function might
     * throw an Exception or just take a REALLY long time to finish. <br>
     * Returns the variable "val" for this equation:  p = P(X <= val)
     * @param v degrees of freedom
     * @param p any value in range [0,1]
     * @return inverse cumulative density function (ICDF) result
     */
    public static double invCumLeftTailed(double v, double p) {
        if(!TRange.ZERO_TO_ONE.isInRange(p)) throw new NotInRangeException(TRange.ZERO_TO_ONE, p);
        if(v < 1d) throw new InvalidValueException("degrees of freedom (v)", v);

        double lowerBound = 0;
        double upperBound = Double.POSITIVE_INFINITY;

        if (p == 0.0) return lowerBound;
        if (p == 1.0) return upperBound;

        final double mu = expectedValue(v);
        final double sig = TMath.sqrt(variance(v));

        final boolean chebyshevApplies = !(Double.isInfinite(mu) || Double.isNaN(mu) ||
                Double.isInfinite(sig) || Double.isNaN(sig));

        if (lowerBound == Double.NEGATIVE_INFINITY) {
            if (chebyshevApplies) {
                lowerBound = mu - sig * TMath.sqrt((1d - p) / p);
            }
            else {
                lowerBound = -1d;
                while (cumulativeProbability(v, lowerBound) >= p)
                    lowerBound *= 2d;
            }
        }

        if (upperBound == Double.POSITIVE_INFINITY) {
            if (chebyshevApplies) {
                upperBound = mu + sig * TMath.sqrt(p / (1d - p));
            }
            else {
                upperBound = 1d;
                while (cumulativeProbability(v, upperBound) < p)
                    upperBound *= 2d;
            }
        }


        double initial = lowerBound + 0.5d * (upperBound - lowerBound);

        double yInitial = computeObjectiveValue(v, p, initial);
        if (TMath.abs(yInitial) <= 1E-15) return initial;

        double yMin = computeObjectiveValue(v, p, lowerBound);
        if (TMath.abs(yMin) <= 1E-15) return lowerBound;

        if (yInitial * yMin < 0)
            return brent(v, p, lowerBound, initial, yMin, yInitial);

        double yMax = computeObjectiveValue(v, p, upperBound);
        if (TMath.abs(yMax) <= 1E-15) return upperBound;

        if (yInitial * yMax < 0) return brent(v, p, initial, upperBound, yInitial, yMax);

        throw new ThisLineIsNeverExecutedException();
    }




    /**
     * Right tailed inverse cumulative density function. <br>
     * Returns the variable "val" for this equation:  p = P(X >= val)
     * @param v degrees of freedom
     * @param p any value in range [0,1]
     * @return inverse cumulative density function (ICDF) result
     */
    public static double invCumRightTailed(double v, double p){
        return invCumLeftTailed(v, 1d - p);
    }





    ///////////////
    /*  HELPERS  */
    ///////////////

    private static double computeObjectiveValue(double v, double p, double x) {
        return cumulativeProbability(v, x) - p;
    }

    private static double brent(double v, double prob, double lo, double hi, double fLo, double fHi) {
        double a = lo;
        double fa = fLo;
        double b = hi;
        double fb = fHi;
        double c = a;
        double fc = fa;
        double d = b - a;
        double e = d;

        final double t = 1.0E-9;
        final double eps = 1e-14;

        while (true) {
            if (TMath.abs(fc) < TMath.abs(fb)) {
                a = b;
                b = c;
                c = a;
                fa = fb;
                fb = fc;
                fc = fa;
            }

            final double tol = 2 * eps * TMath.abs(b) + t;
            final double m = 0.5 * (c - b);

            if (TMath.abs(m) <= tol || eqForBrent(fb))
                return b;


            if (TMath.abs(e) < tol || TMath.abs(fa) <= TMath.abs(fb)) {
                d = m;
                e = d;
            }

            else {
                double s = fb / fa;
                double p, q;
                if (a == c) {
                    p = 2 * m * s;
                    q = 1 - s;
                }
                else {
                    q = fa / fc;
                    final double r = fb / fc;
                    p = s * (2 * m * q * (q - r) - (b - a) * (r - 1));
                    q = (q - 1) * (r - 1) * (s - 1);
                }
                if (p > 0) {
                    q = -q;
                }
                else {
                    p = -p;
                }
                s = e;
                e = d;
                if (p >= 1.5 * m * q - TMath.abs(tol * q) || p >= TMath.abs(0.5 * s * q)) {
                    d = m;
                    e = d;
                }
                else {
                    d = p / q;
                }
            }
            a = b;
            fa = fb;

            if (TMath.abs(d) > tol) {
                b += d;
            }
            else if (m > 0) {
                b += tol;
            }
            else {
                b -= tol;
            }
            fb = computeObjectiveValue(v, prob, b);
            if ((fb > 0 && fc > 0) || (fb <= 0 && fc <= 0)) {
                c = a;
                fc = fa;
                d = b - a;
                e = d;
            }

        }
    }

    private static boolean eqForBrent(double x) {
        long xInt = Double.doubleToLongBits(x);
        long yInt = Double.doubleToLongBits(0);

        final long SGN_MASK = 0x8000000000000000L;
        if (xInt < 0) xInt = SGN_MASK - xInt;
        if (yInt < 0) yInt = SGN_MASK - yInt;

        return TMath.abs(xInt - yInt) <= 1 && !Double.isNaN(x);
    }





}
