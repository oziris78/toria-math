package com.telek.telekmath.advanced.statistics.regression;


import com.telek.telekmath.core.numbers.TRange;
import com.telek.telekmath.core.functions.other.TPolynomial;

import java.util.Objects;


/**
 * A container class with one constructor and many fields for Regression calculations.
 */
public class RegressionResult {

    /** Number of points in this regression analysis */
    public final double n;

    /** True if the line has a positive slope (b1 > 0) */
    public final boolean hasPositiveDirection;

    /** True if there's a correlation between the x and y values */
    public final boolean areTheyCorrelated;

    /** Sum of all X values */
    public final double sumX;

    /** Sum of all Y values */
    public final double sumY;

    /** Sum of all X*Y values */
    public final double sumXY;

    /** Sum of all X values squared (X^2) */
    public final double sumX2;

    /** Sum of all Y values squared (Y^2) */
    public final double sumY2;

    /** Sum of all error values squared */
    public final double sumE2;

    /** The estimator line of exact Y, also written as "y hat" (y^) */
    public final TPolynomial line;

    /** Standard error of the estimator (y hat) */
    public final double S; // S

    /** Variance of the estimator (y hat) */
    public final double S2; // S^2

    /** Sum of Regression Squares */
    public final double SRS;

    /** Sum of Total Squares */
    public final double STS;

    /** Sum of Error Squares */
    public final double SES;

    /** The correlation coefficient */
    public final double r;

    /** The determination coefficient */
    public final double R2;

    /** Standard error of the correlation coefficient (r) */
    public final double SR; // S_r

    /** Confidence inteval of the correlation coefficient (r) */
    public final TRange confIntOfCorrelationCoef;



    public RegressionResult(double n, double sumX, double sumY, double sumX2, double sumY2, double sumXY,
                            TPolynomial line, double sumE2, double S2, double standardErrorOfTheEstimator, double STS,
                            double SRS, double SES, double R2, double r, double SR,
                            boolean hasPositiveDirection, TRange confIntOfCorrelationCoef, boolean areTheyCorrelated)
    {
        this.n = n;
        this.sumX = sumX;
        this.sumY = sumY;
        this.sumX2 = sumX2;
        this.sumY2 = sumY2;
        this.sumXY = sumXY;
        this.line = line;
        this.sumE2 = sumE2;
        this.S2 = S2;
        this.S = standardErrorOfTheEstimator;
        this.STS = STS;
        this.SRS = SRS;
        this.SES = SES;
        this.R2 = R2;
        this.r = r;
        this.SR = SR;
        this.hasPositiveDirection = hasPositiveDirection;
        this.confIntOfCorrelationCoef = confIntOfCorrelationCoef;
        this.areTheyCorrelated = areTheyCorrelated;
    }


    @Override
    public String toString() {
        return "RegressionResult{" +
                "n=" + n +
                ", hasPositiveDirection=" + hasPositiveDirection +
                ", areTheyCorrelated=" + areTheyCorrelated +
                ", sumX=" + sumX +
                ", sumY=" + sumY +
                ", sumXY=" + sumXY +
                ", sumX2=" + sumX2 +
                ", sumY2=" + sumY2 +
                ", sumE2=" + sumE2 +
                ", line=" + line +
                ", S=" + S +
                ", S2=" + S2 +
                ", SRS=" + SRS +
                ", STS=" + STS +
                ", SES=" + SES +
                ", r=" + r +
                ", R2=" + R2 +
                ", SR=" + SR +
                ", confIntOfCorrelationCoef=" + confIntOfCorrelationCoef +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegressionResult that = (RegressionResult) o;
        return Double.compare(that.n, n) == 0 &&
                hasPositiveDirection == that.hasPositiveDirection &&
                areTheyCorrelated == that.areTheyCorrelated &&
                Double.compare(that.sumX, sumX) == 0 &&
                Double.compare(that.sumY, sumY) == 0 &&
                Double.compare(that.sumXY, sumXY) == 0 &&
                Double.compare(that.sumX2, sumX2) == 0 &&
                Double.compare(that.sumY2, sumY2) == 0 &&
                Double.compare(that.sumE2, sumE2) == 0 &&
                Double.compare(that.S, S) == 0 &&
                Double.compare(that.S2, S2) == 0 &&
                Double.compare(that.SRS, SRS) == 0 &&
                Double.compare(that.STS, STS) == 0 &&
                Double.compare(that.SES, SES) == 0 &&
                Double.compare(that.r, r) == 0 &&
                Double.compare(that.R2, R2) == 0 &&
                Double.compare(that.SR, SR) == 0 &&
                Objects.equals(line, that.line) &&
                Objects.equals(confIntOfCorrelationCoef, that.confIntOfCorrelationCoef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, hasPositiveDirection, areTheyCorrelated,
                sumX, sumY, sumXY, sumX2, sumY2, sumE2, line, S, S2,
                SRS, STS, SES, r, R2, SR, confIntOfCorrelationCoef
        );
    }
}
